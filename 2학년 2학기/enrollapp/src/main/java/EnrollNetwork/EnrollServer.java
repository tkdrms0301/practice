package EnrollNetwork;

import EnrollNetwork.Exception.DateException;
import EnrollNetwork.Exception.EnrollException;
import persistence.MyBatisConnectionFactory;
import persistence.dao.*;
import persistence.dto.*;
import service.*;
import java.io.*;
import java.net.*;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.List;

public class EnrollServer implements Runnable {
    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;
    Thread thread = null;

    static int[] userIdList = new int[50]; // 최대 로그인 가능한 인원
    static int userCount = 0;

    AdminDAO adminDAO = null;
    ProfessorDAO professorDAO = null;
    StudentDAO studentDAO = null;
    SubjectNotOpenDAO subjectNotOpenDAO = null;
    SubjectEnrollDAO subjectEnrollDAO = null;
    SubjectOpenDAO subjectOpenDAO = null;

    AdminService adminService = null;
    ProfessorService professorService = null;
    StudentService studentService = null;
    SubjectOpenService subjectOpenService = null;
    SubjectEnrollService subjectEnrollService = null;
    SubjectNotOpenService subjectNotOpenService = null;

    int userId;

    public EnrollServer(Socket socket) {
        this.socket = socket;
        thread = new Thread(this);
        thread.start();
    }

    public String changeByteToString(byte[] recvHeader) throws IOException { // read시 바이트배열을 문자열 변환
        int bytesRcvd;
        int totalBytesRcvd = 0;  // 지금까지 받은 바이트 수

        int dataLength = byteArrayToInt(recvHeader);
        if (dataLength == 0) return null;

        byte[] recvData = new byte[dataLength];

        while (totalBytesRcvd < dataLength) {
            if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, dataLength - totalBytesRcvd)) == -1)
                throw new SocketException("Connection close prematurely");
            totalBytesRcvd += bytesRcvd;
        }

        return new String(recvData);
    }

    public int changeByteToInt(byte[] recvHeader) throws IOException { // read시 바이트배열을 정수값 변환
        int bytesRcvd;
        int totalBytesRcvd = 0;  // 지금까지 받은 바이트 수

        int dataLength = byteArrayToInt(recvHeader);
        if (dataLength == 0) return 0;

        byte[] recvData = new byte[dataLength];

        while (totalBytesRcvd < dataLength) {
            if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, 4 - totalBytesRcvd)) == -1)
                throw new SocketException("Connection close prematurely1");
            totalBytesRcvd += bytesRcvd;
        }

        return byteArrayToInt(recvData);
    }

    public int readHeader(byte[] receivePacket){ // 헤더 5자리 정수값 변환
        return (receivePacket[0] * 10000 + receivePacket[1] * 1000 + receivePacket[2] * 10 + receivePacket[3]);
    }

    private static int byteArrayToInt(byte[] data) {
        if (data == null || data.length != 4) return 0x0;
        return (int)((0xff & data[0]) << 24 | (0xff & data[1]) << 16 | (0xff & data[2]) << 8 | (0xff & data[3]) << 0);
    }

    @Override
    public void run() {
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            adminDAO = new AdminDAO();
            professorDAO = new ProfessorDAO();
            studentDAO = new StudentDAO();
            adminService = new AdminService(adminDAO);
            professorService = new ProfessorService(professorDAO);
            studentService = new StudentService(studentDAO);
            Protocol protocol = new Protocol();
            protocol.getLoginPacket(Message.MESSAGE_TYPE_REQUEST, Message.NULL_HEADER);
            dos.write(protocol.getPacket(), 0, protocol.getSize());
            dos.flush();
            byte[] receiveHeader = new byte[4];
            int readHeader = 0;
            int group_id = 0;

            while (true) {
                dis.read(receiveHeader);
                readHeader = readHeader(receiveHeader);
                if(readHeader == ServerMessage.PROTOCOL_LOGIN_REQUEST) {
                    dis.read(receiveHeader);    // id 읽어옴
                    int id = changeByteToInt(receiveHeader);

                    dis.read(receiveHeader);   // pw 읽어옴
                    String pw = changeByteToString(receiveHeader);

                    System.out.println(ClientMessage.LOGIN_ID_REQ + id);
                    System.out.println(ClientMessage.LOGIN_PW_REQ + pw);
                    boolean flag = false;

                    //리스트에서 검색
                    List<AdminDTO> all = adminService.findAll();
                    for (AdminDTO dto : all) {
                        if (dto.getId() == id && dto.getPw().equals(pw)) {
                            group_id = dto.getGroup_id(); // group_id 저장
                            flag = true;
                            userId = dto.getId();
                            break;
                        }
                    }

                    boolean isExist = false;
                    for(int loginId : userIdList) {
                        if (userId == loginId) {
                            isExist = true;
                            break;
                        }
                    }

                    if (flag && !isExist) { // 로그인성공 프로토콜
                        userIdList[userCount++] = userId;
                        System.out.println(ClientMessage.LOGIN_SUC);
                        protocol.getLoginPacket(Message.MESSAGE_TYPE_RESULT, Message.CODE_TYPE_1);
                        protocol.setIntToByte(group_id);
                    } else { // 로그인실패 프로토콜
                        System.out.println(ClientMessage.LOGIN_FAIL);
                        protocol.getLoginPacket(Message.MESSAGE_TYPE_RESULT, Message.CODE_TYPE_2);
                    }
                    dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                    dos.flush();
                    break;
                }
            }
            switch (group_id){
                case Message.LOGIN_TYPE_ADMIN:
                    adminMenu();
                    break;
                case Message.LOGIN_TYPE_PROFESSOR:
                    professorMenu();
                    break;
                case Message.LOGIN_TYPE_STUDENT:
                    studentMenu();
                    break;
            }
            for(int i = 0; i < userCount; i++){
                if(userIdList[i] == userId){
                    userIdList[i] = userIdList[userCount - 1];
                    userIdList[userCount - 1] = 0;
                    userCount--;
                }
            }
        } catch(IOException ioe) {
            System.out.println("Sending eorror : " + ioe.getMessage());
        }
    }
    public void adminMenu() { // 관리자 로그인
        System.out.println(ClientMessage.LOGIN_ADMIN);
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            adminDAO = new AdminDAO();
            professorDAO = new ProfessorDAO();
            studentDAO = new StudentDAO();
            subjectEnrollDAO = new SubjectEnrollDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            subjectNotOpenDAO = new SubjectNotOpenDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            subjectOpenDAO = new SubjectOpenDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            adminService = new AdminService(adminDAO);
            professorService = new ProfessorService(professorDAO);
            studentService = new StudentService(studentDAO);
            subjectEnrollService = new SubjectEnrollService(subjectEnrollDAO);
            subjectNotOpenService = new SubjectNotOpenService(subjectNotOpenDAO);
            subjectOpenService = new SubjectOpenService(subjectOpenDAO, subjectNotOpenDAO);

            Protocol protocol = null;

            byte[] receiveHeader = new byte[4];
            int headerReader = 0;

            int id = 0;
            String subject_name = "";
            int subject_grade = 0;
            int max_people = 0;
            int number_people = 0;
            String subject_plan = "";
            String subject_plan_modify = "";
            int credit = 0;
            String day = "";
            String time = "";
            String classroom = "";
            int professor_id = 0;
            boolean isExist;
            while (true) {
                dis.read(receiveHeader);
                headerReader = readHeader(receiveHeader);
                protocol = new Protocol(Message.LOGIN_TYPE_ADMIN, Message.MESSAGE_TYPE_RESPONSE, (int)receiveHeader[2]);

                switch (headerReader) {
                    case ServerMessage.PROTOCOL_ADMIN_CRT_PRO_REQUEST_ID: // 교수 생성 요청
                        isExist = true;

                        dis.read(receiveHeader);    // id 읽어옴
                        id = changeByteToInt(receiveHeader);
                        List<ProfessorDTO> professorDTOList = professorService.findProfessorAll();  // 이미 있는 id 인지 판단
                        for (ProfessorDTO dto : professorDTOList) {
                            if (dto.getId() == id) {
                                isExist = false;
                                break;
                            }
                        }
                        if(isExist){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_PROFESSOR_ACCOUNT, Message.CODE_TYPE_2);
                        }else{
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_PROFESSOR_ACCOUNT, Message.CODE_TYPE_1);
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_ADMIN_CRT_PRO_REQUEST_INFO:
                        dis.read(receiveHeader);   // name 읽어옴
                        String pw = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);   // birth 읽어옴
                        String birth = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);   // phoneNum 읽어옴
                        String phoneNum = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);   // major 읽어옴
                        String major = changeByteToString(receiveHeader);
                        try{
                            professorService.insertProfessor(id, pw, birth, phoneNum, major);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_PROFESSOR_ACCOUNT, Message.CODE_TYPE_3);
                        }catch (SQLIntegrityConstraintViolationException e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_PROFESSOR_ACCOUNT, Message.CODE_TYPE_4);
                        } finally {
                            id = 0;
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_CRT_STU_REQUEST_ID: // 학생 생성 요청
                        isExist = true;

                        dis.read(receiveHeader);    // id 읽어옴
                        id = changeByteToInt(receiveHeader);

                        List<StudentDTO> studentDTOS = studentService.findStudentAll();  // 이미 있는 id 인지 판단
                        for (StudentDTO dto : studentDTOS) {
                            if (dto.getId() == id) {
                                isExist = false;
                                break;
                            }
                        }
                        if(isExist){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_STUDENT_ACCOUNT, Message.CODE_TYPE_2);
                        }else{
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_STUDENT_ACCOUNT, Message.CODE_TYPE_1);
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_ADMIN_CRT_STU_REQUEST_INFO:
                        dis.read(receiveHeader);   // name 읽어옴
                        pw = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);   // birth 읽어옴
                        birth = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);   // phoneNum 읽어옴
                        phoneNum = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);   // major 읽어옴
                        major = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);    // grade 읽어옴
                        int grade = changeByteToInt(receiveHeader);

                        try{
                            studentService.insertStudent(id, pw, birth, phoneNum, major, grade);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_STUDENT_ACCOUNT, Message.CODE_TYPE_3);
                        }catch (SQLIntegrityConstraintViolationException e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_STUDENT_ACCOUNT, Message.CODE_TYPE_4);
                        } finally {
                            id = 0;
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_CRT_NOT_OPEN_SUBJECT_REQUEST_ID: // 비개설 교과목 생성 요청
                        isExist = true;

                        dis.read(receiveHeader);    // id 읽어옴
                        id = changeByteToInt(receiveHeader);

                        List<SubjectNotOpenDTO> subjectNotOpenDTOS = subjectNotOpenDAO.getSubjectNotOpenAll();  // 이미 있는 id 인지 판단
                        for (SubjectNotOpenDTO dto : subjectNotOpenDTOS) {
                            if (dto.getSubject_id() == id) {
                                isExist = false;
                                break;
                            }
                        }
                        if(isExist){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_SUBJECT, Message.CODE_TYPE_2); // 생성 허가
                        }else{
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_SUBJECT, Message.CODE_TYPE_1); // 존재하는 id
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_ADMIN_CRT_SUBJECT_REQUEST_INFO: // 비개설 교과목 생성
                        dis.read(receiveHeader);   // name 읽어옴
                        subject_name = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);   // grade 읽어옴
                        subject_grade = changeByteToInt(receiveHeader);

                        try{
                            subjectNotOpenService.insertSubjectNotOpen(id, subject_name, subject_grade);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_SUBJECT, Message.CODE_TYPE_3); // 생성 성공
                        }catch (Exception e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_SUBJECT, Message.CODE_TYPE_4); // 생성 실패
                        } finally {
                            id = 0;
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_UPDT_NOT_OPEN_SUBJECT_REQUEST_ID:
                        isExist = false;

                        dis.read(receiveHeader);    // id 읽어옴
                        id = changeByteToInt(receiveHeader);

                        subjectNotOpenDTOS = subjectNotOpenDAO.getSubjectNotOpenAll();  // 있는 id 인지 판단
                        for (SubjectNotOpenDTO dto : subjectNotOpenDTOS) {
                            if (dto.getSubject_id() == id && !dto.isOpen()) {
                                subject_name = dto.getSubject_name();
                                subject_grade = dto.getPossible_grade();
                                isExist = true;
                                break;
                            }
                        }
                        if(isExist){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_SUBJECT, Message.CODE_TYPE_1); // 생성 허가
                        }else{
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_SUBJECT, Message.CODE_TYPE_2); // 존재하는 id
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_ADMIN_UPDT_NOT_OPEN_SUBJECT_REQUEST_NAME: // 비개설 교과목 이름 수정
                        dis.read(receiveHeader);
                        subject_name = changeByteToString(receiveHeader);

                        try{
                            subjectNotOpenService.updateSubjectNotOpen(id,subject_name, subject_grade);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_SUBJECT, Message.CODE_TYPE_3); // 수정 성공
                        }catch (Exception e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_SUBJECT, Message.CODE_TYPE_4); // 수정 실패
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_UPDT_NOT_OPEN_SUBJECT_REQUEST_GRADE: // 학년 수정 요청
                        dis.read(receiveHeader);
                        subject_grade = changeByteToInt(receiveHeader);

                        try{
                            subjectNotOpenService.updateSubjectNotOpen(id,subject_name, subject_grade);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_SUBJECT, Message.CODE_TYPE_3); // 수정 성공
                        }catch (Exception e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_SUBJECT, Message.CODE_TYPE_4); // 수정 실패
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }

                    case ServerMessage.PROTOCOL_ADMIN_DEL_NOT_OPEN_SUBJECT_REQUEST_ID: // 비개설 교과목 삭제 요청
                        isExist = false;

                        dis.read(receiveHeader);    // id 읽어옴
                        id = changeByteToInt(receiveHeader);
                        System.out.println(id + "a");
                        subjectNotOpenDTOS = subjectNotOpenDAO.getSubjectNotOpenAll();  // 있는 id 인지 판단
                        for (SubjectNotOpenDTO dto : subjectNotOpenDTOS) {
                            if (dto.getSubject_id() == id && dto.isOpen() == false) {
                                isExist = true;
                                break;
                            }
                        }
                        if(isExist){
                            subjectNotOpenService.deleteSubjectNotOpen(id);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_DELETE_SUBJECT, Message.CODE_TYPE_1); // 삭제 성공
                        }else{
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_DELETE_SUBJECT, Message.CODE_TYPE_2); // 삭제 실패
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_ADMIN_UPDT_SUBJECT_PLAN_REQUEST_ID: // 강의계획서 입력요청
                        isExist = true;

                        dis.read(receiveHeader);    // id 읽어옴
                        id = changeByteToInt(receiveHeader);

                        List<SubjectOpenDTO> subjectOpenDTOS = subjectOpenDAO.getSubjectOpenAll();  // 이미 있는 id 인지 판단
                        for (SubjectOpenDTO dto : subjectOpenDTOS) {
                            if (dto.getSubject_id() == id) {
                                isExist = false;
                                break;
                            }
                        }
                        if(isExist){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_SET_SUBJECT_PLAN, Message.CODE_TYPE_2);
                        }else{
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_SET_SUBJECT_PLAN, Message.CODE_TYPE_1);
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_ADMIN_UPDT_SUBJECT_PLAN_REQUEST_DATE: // 강의계획서 수정날짜 요청
                        dis.read(receiveHeader);   // 기간 읽어옴
                        String subject_plan_modity = changeByteToString(receiveHeader);

                        try{
                            subjectOpenService.updateSubjectPlanModifySubjectOpen(id, subject_plan_modity);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_SET_SUBJECT_PLAN, Message.CODE_TYPE_3);
                        }catch (Exception e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_SET_SUBJECT_PLAN, Message.CODE_TYPE_4);
                        } finally {
                            id = 0;
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_UPDT_EROLL_DATE_PER_GRADE: // 학년별 수강 가능한 날짜 입력 요청
                        dis.read(receiveHeader);
                        int possible_grade = changeByteToInt(receiveHeader);
                        dis.read(receiveHeader);
                        String startDate = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);
                        String endDate = changeByteToString(receiveHeader);

                        try{
                            subjectEnrollService.updateEnrollTime(possible_grade, startDate, endDate);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_SET_ENROLL_TIME, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_SET_ENROLL_TIME, Message.CODE_TYPE_2);
                        } finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_SELECT_PRO_REQUEST: // 전체 교수 목록 요청
                        professorDTOList = professorService.findProfessorAll();
                        int listSize = professorDTOList.size();
                        protocol.setAdminResponseListPacket(Message.FUNCTION_TYPE_FIND_PROFESSOR, listSize); // 교수 목록 길이 추가
                        SimpleDateFormat trnasFormatter = new SimpleDateFormat("yyyy-MM-dd");
                        for(ProfessorDTO dto : professorDTOList){ // 교수별 정보 길이 및 데이터 추가가
                           protocol.setIntToByte(dto.getId());
                            protocol.setStrToByte(dto.getName());
                            protocol.setStrToByte(trnasFormatter.format(dto.getBirth()));
                            protocol.setStrToByte(dto.getPhoneNumber());
                            protocol.setStrToByte(dto.getMajor());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_ADMIN_SELECT_STU_REQUEST: // 전체 학생 목록 요청
                        studentDTOS = studentService.findStudentAll();
                        listSize = studentDTOS.size();
                        protocol.setAdminResponseListPacket(Message.FUNCTION_TYPE_FIND_STUDENT, listSize); // 학생 목록 길이 추가
                        trnasFormatter = new SimpleDateFormat("yyyy-MM-dd");

                        for(StudentDTO dto : studentDTOS){ // 학생별 정보 길이 및 데이터 추가
                            protocol.setIntToByte(dto.getId());
                            protocol.setStrToByte(dto.getName());
                            protocol.setStrToByte(trnasFormatter.format(dto.getBirth()));
                            protocol.setStrToByte(dto.getPhoneNumber());
                            protocol.setStrToByte(dto.getMajor());
                            protocol.setIntToByte(dto.getGrade());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_ADMIN_SELECT_OPEN_SUBJECT_REQUEST: // 교과목 목록 요청
//                        subjectNotOpenDTOS = subjectNotOpenService.getSubjectNotOpenAll();
//                        listSize = subjectNotOpenDTOS.size();
//
//                        protocol.setAdminResponseListPacket(Message.FUNCTION_TYPE_FIND_OPEN_SUBJECT, listSize);
//                        for(SubjectNotOpenDTO dto : subjectNotOpenDTOS){
//                            protocol.setIntToByte(dto.getSubject_id());
//                            protocol.setStrToByte(dto.getSubject_name());
//                            protocol.setIntToByte(dto.getPossible_grade());
//                            if(dto.isOpen()){
//                                protocol.setIntToByte(1);
//                            }else{
//                                protocol.setIntToByte(0);
//                            }
//                        }
                        subjectOpenDTOS = subjectOpenService.getSubjectOpenAll();
                        listSize = subjectOpenDTOS.size();
                        protocol.setAdminResponseListPacket(Message.FUNCTION_TYPE_FIND_OPEN_SUBJECT, listSize);
                        trnasFormatter = new SimpleDateFormat("yyyy-MM-dd");

                        for(SubjectOpenDTO dto : subjectOpenDTOS){
                            protocol.setIntToByte(dto.getSubject_id());
                            protocol.setStrToByte(dto.getSubject_name());
                            protocol.setIntToByte(dto.getPossible_grade());
                            protocol.setIntToByte(dto.getMax_people());
                            protocol.setIntToByte(dto.getNumber_people());
                            protocol.setStrToByte(trnasFormatter.format(dto.getPlan_modify_date()));
                            protocol.setIntToByte(dto.getCredit());
                            protocol.setStrToByte(dto.getDay());
                            protocol.setStrToByte(dto.getTime());
                            protocol.setStrToByte(dto.getClassroom());
                            protocol.setIntToByte(dto.getProfessor_user_id());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_ADMIN_CRT_OPEN_SUBJECT_REQUEST_ID: // 개설 교과목 생성 요청
                        isExist = false;

                        dis.read(receiveHeader);    // id 읽어옴
                        id = changeByteToInt(receiveHeader);
                        subjectNotOpenDTOS = subjectNotOpenService.getSubjectNotOpenAll();  // 이미 있는 id 인지 판단
                        for (SubjectNotOpenDTO dto : subjectNotOpenDTOS) {
                            if (dto.getSubject_id() == id && !dto.isOpen()) {
                                isExist = true;
                                break;
                            }
                        }
                        if(isExist){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_OPEN_SUBJECT, Message.CODE_TYPE_1);
                        }else{
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_OPEN_SUBJECT, Message.CODE_TYPE_2);
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_ADMIN_CRT_OPEN_SUBJECT_REQUEST_INFO:
                        dis.read(receiveHeader);
                        max_people = changeByteToInt(receiveHeader);
                        subject_plan = ServerMessage.DEFAULT_SUBJECT_PLAN;
                        subject_plan_modity = new ServerMessage().setDefaultDate();
                        dis.read(receiveHeader);
                        credit = changeByteToInt(receiveHeader);
                        dis.read(receiveHeader);
                        day = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);
                        time = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);
                        classroom = changeByteToString(receiveHeader);
                        dis.read(receiveHeader);
                        professor_id = changeByteToInt(receiveHeader);

                        try{
                            subjectOpenService.openSubject(id, max_people, subject_plan, subject_plan_modity, credit, day, time, classroom, professor_id);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_OPEN_SUBJECT, Message.CODE_TYPE_3);
                        }catch (Exception e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_MAKE_OPEN_SUBJECT, Message.CODE_TYPE_4);
                        } finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_UDPT_OPEN_SUBJECT_REQUEST_ID:
                        isExist = false;

                        dis.read(receiveHeader);    // id 읽어옴
                        id = changeByteToInt(receiveHeader);

                        subjectOpenDTOS = subjectOpenService.getSubjectOpenAll();  // 있는 id 인지 판단
                        for (SubjectOpenDTO dto : subjectOpenDTOS) {
                            if (dto.getSubject_id() == id) {
                                subject_name = dto.getSubject_name();
                                subject_grade = dto.getPossible_grade();
                                max_people = dto.getMax_people();
                                credit = dto.getCredit();
                                day = dto.getDay();
                                time = dto.getTime();
                                classroom = dto.getClassroom();
                                isExist = true;
                                break;
                            }
                        }
                        if(isExist){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_1);
                        }else{
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_2);
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_ADMIN_UDPT_OPEN_SUBJECT_REQUEST_MAX_PEOPLE:
                        dis.read(receiveHeader);
                        max_people = changeByteToInt(receiveHeader);
                        try{
                            subjectOpenService.updateSubjectOpen(id, subject_name, max_people, subject_grade, credit, day, time, classroom);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_3);
                        }catch (Exception e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_4);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_UDPT_OPEN_SUBJECT_REQUEST_CREDIT:
                        dis.read(receiveHeader);
                        credit = changeByteToInt(receiveHeader);

                        try{
                            subjectOpenService.updateSubjectOpen(id, subject_name, max_people, subject_grade, credit, day, time, classroom);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_3);
                        }catch (Exception e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_4);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_UDPT_OPEN_SUBJECT_REQUEST_DAY:
                        dis.read(receiveHeader);
                        day = changeByteToString(receiveHeader);

                        try{
                            subjectOpenService.updateSubjectOpen(id, subject_name, max_people, subject_grade, credit, day, time, classroom);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_3);
                        }catch (Exception e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_4);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_UDPT_OPEN_SUBJECT_REQUEST_TIME:
                        dis.read(receiveHeader);
                        time = changeByteToString(receiveHeader);

                        try{
                            subjectOpenService.updateSubjectOpen(id, subject_name, max_people, subject_grade, credit, day, time, classroom);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_3);
                        }catch (Exception e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_4);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_UDPT_OPEN_SUBJECT_REQUEST_CLASSROOM:
                        dis.read(receiveHeader);
                        classroom = changeByteToString(receiveHeader);

                        try{
                            subjectOpenService.updateSubjectOpen(id, subject_name, max_people, subject_grade, credit, day, time, classroom);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_3);
                        }catch (Exception e){
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_4);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_ADMIN_CLOSE_OPEN_SUBJECT_REQUEST:
                        isExist = false;

                        dis.read(receiveHeader);    // id 읽어옴
                        id = changeByteToInt(receiveHeader);
                        System.out.println(id);
                        subjectOpenDTOS = subjectOpenService.getSubjectOpenAll();  // 이미 있는 id 인지 판단
                        for (SubjectOpenDTO dto : subjectOpenDTOS) {
                            if (dto.getSubject_id() == id) {
                                isExist = true;
                                break;
                            }
                        }
                        if(isExist){
                            subjectOpenService.deleteSubjectOpen(id);
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_DELETE_OPEN_SUBJECT, Message.CODE_TYPE_1);
                        }else{
                            protocol.getAdminResponsePacket(Message.FUNCTION_TYPE_DELETE_OPEN_SUBJECT, Message.CODE_TYPE_2);
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                }
            }
        } catch (IOException ioe) {
            System.out.println(ServerMessage.IOEXCEPTION);
        }
    }

    public void professorMenu(){
        System.out.println(ClientMessage.LOGIN_PROFESSOR);
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            adminDAO = new AdminDAO();
            professorDAO = new ProfessorDAO();
            studentDAO = new StudentDAO();
            subjectEnrollDAO = new SubjectEnrollDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            subjectNotOpenDAO = new SubjectNotOpenDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            subjectOpenDAO = new SubjectOpenDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            adminService = new AdminService(adminDAO);
            professorService = new ProfessorService(professorDAO);
            studentService = new StudentService(studentDAO);
            subjectEnrollService = new SubjectEnrollService(subjectEnrollDAO);
            subjectNotOpenService = new SubjectNotOpenService(subjectNotOpenDAO);
            subjectOpenService = new SubjectOpenService(subjectOpenDAO, subjectNotOpenDAO);

            Protocol protocol = new Protocol();

            byte[] receiveHeader = new byte[4];
            int headerReader = 0;
            int subjectId = 0;

            int id;

            String pw;
            String name;
            String birth;
            String phoneNum;
            String major;

            String subjectPlan = "";

            List<ProfessorDTO> all = professorService.findProfessorAll();
            ProfessorDTO professorDTO = null;
            for(ProfessorDTO dto : all){
                if (dto.getId() == userId) {
                    professorDTO = dto;
                    break;
                }
            }

            while(true){
                dis.read(receiveHeader);
                headerReader = readHeader(receiveHeader);

                protocol = new Protocol(Message.LOGIN_TYPE_PROFESSOR, Message.MESSAGE_TYPE_RESPONSE, receiveHeader[2]);
                SimpleDateFormat transFormatter = new SimpleDateFormat("yyyy-MM-dd");
                switch (headerReader) {
                    // 개인정보 수정
                    case ServerMessage.PROTOCOL_PRO_UPDT_INFO_REQUEST_PW: // 비밀번호 수정
                        dis.read(receiveHeader);
                        pw = changeByteToString(receiveHeader);
                        professorDTO.setPw(pw);
                        try{
                            professorService.updateProfessor(professorDTO.getId(), professorDTO.getPw(), professorDTO.getName(), transFormatter.format(professorDTO.getBirth()), professorDTO.getPhoneNumber(), professorDTO.getMajor());
                            protocol.getProfessorResponsePacket(Message.TYPE_CHANGE_PROFESSOR_INFO, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.getProfessorResponsePacket(Message.TYPE_CHANGE_PROFESSOR_INFO, Message.CODE_TYPE_2);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_PRO_UPDT_INFO_REQUEST_NAME: // 이름 수정
                        dis.read(receiveHeader);
                        name = changeByteToString(receiveHeader);
                        professorDTO.setName(name);
                        try{
                            professorService.updateProfessor(professorDTO.getId(), professorDTO.getPw(), professorDTO.getName(), transFormatter.format(professorDTO.getBirth()), professorDTO.getPhoneNumber(), professorDTO.getMajor());
                            protocol.getProfessorResponsePacket(Message.TYPE_CHANGE_PROFESSOR_INFO, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.getProfessorResponsePacket(Message.TYPE_CHANGE_PROFESSOR_INFO, Message.CODE_TYPE_2);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_PRO_UPDT_INFO_REQUEST_BIRTH: // 생년월일 수정
                        dis.read(receiveHeader);
                        birth = changeByteToString(receiveHeader);
                        professorDTO.setBirth(Date.valueOf(birth));
                        try{
                            professorService.updateProfessor(professorDTO.getId(), professorDTO.getPw(), professorDTO.getName(), transFormatter.format(professorDTO.getBirth()), professorDTO.getPhoneNumber(), professorDTO.getMajor());
                            protocol.getProfessorResponsePacket(Message.TYPE_CHANGE_PROFESSOR_INFO, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.getProfessorResponsePacket(Message.TYPE_CHANGE_PROFESSOR_INFO, Message.CODE_TYPE_2);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_PRO_UPDT_INFO_REQUEST_PHONENUMBER: //휴대전화번호 수정
                        dis.read(receiveHeader);
                        phoneNum = changeByteToString(receiveHeader);
                        professorDTO.setPhoneNumber(phoneNum);
                        try{
                            professorService.updateProfessor(professorDTO.getId(), professorDTO.getPw(), professorDTO.getName(), transFormatter.format(professorDTO.getBirth()), professorDTO.getPhoneNumber(), professorDTO.getMajor());
                            protocol.getProfessorResponsePacket(Message.TYPE_CHANGE_PROFESSOR_INFO, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.getProfessorResponsePacket(Message.TYPE_CHANGE_PROFESSOR_INFO, Message.CODE_TYPE_2);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_PRO_UPDT_INFO_REQUEST_MAJOR: // 전공 수정
                        dis.read(receiveHeader);
                        major = changeByteToString(receiveHeader);
                        professorDTO.setMajor(major);
                        try{
                            professorService.updateProfessor(professorDTO.getId(), professorDTO.getPw(), professorDTO.getName(), transFormatter.format(professorDTO.getBirth()), professorDTO.getPhoneNumber(), professorDTO.getMajor());
                            protocol.getProfessorResponsePacket(Message.TYPE_CHANGE_PROFESSOR_INFO, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.getProfessorResponsePacket(Message.TYPE_CHANGE_PROFESSOR_INFO, Message.CODE_TYPE_2);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }
                    // 강의 계획서 입력
                    case ServerMessage.PROTOCOL_PRO_INSERT_SUBJECT_PLAN_REQUEST_ID:
                        dis.read(receiveHeader);
                        subjectId = changeByteToInt(receiveHeader);

                        boolean isExist = false;
                        List<SubjectOpenDTO> subjectOpenDTOList = subjectOpenService.getSubjectOpenAll();  // 이미 있는 id 인지 판단
                        for (SubjectOpenDTO dto : subjectOpenDTOList) {
                            if (dto.getSubject_id() == subjectId) {
                                subjectPlan = dto.getSubject_plan();
                                isExist = true;
                                break;
                            }
                        }
                        if(isExist){
                            protocol.getProfessorResponsePacket(Message.TYPE_INSERT_SUBJECT_PLAN, Message.CODE_TYPE_1);   // 교과목 ID 존재
                        }else{
                            protocol.getProfessorResponsePacket(Message.TYPE_INSERT_SUBJECT_PLAN, Message.CODE_TYPE_2);   // 교과목 ID 존재 X
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_PRO_INSERT_SUBJECT_PLAN_REQUEST_PLAN:
                        dis.read(receiveHeader);
                        if(subjectPlan.equals("(null)")){
                            subjectPlan = changeByteToString(receiveHeader);
                            try {
                                subjectOpenService.updateSubjectPlanSubjectOpen(subjectId, subjectPlan);
                                protocol.getProfessorResponsePacket(Message.TYPE_INSERT_SUBJECT_PLAN, Message.CODE_TYPE_3);   // 강의게획서 입력 성공
                            }catch (DateException e) {
                                protocol.getProfessorResponsePacket(Message.TYPE_INSERT_SUBJECT_PLAN, Message.CODE_TYPE_4);   // 강의게획서 입력 실패
                            }
                        }else{
                            protocol.getProfessorResponsePacket(Message.TYPE_INSERT_SUBJECT_PLAN, Message.CODE_TYPE_4);
                        }
                        subjectId = 0;
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                        // 강의 계획서 수정
                    case ServerMessage.PROTOCOL_PRO_UPDT_SUBJECT_PLAN_REQUEST_ID:
                        dis.read(receiveHeader);
                        subjectId = changeByteToInt(receiveHeader);

                        isExist = false;
                        subjectOpenDTOList = subjectOpenService.getSubjectOpenAll();  // 이미 있는 id 인지 판단
                        for (SubjectOpenDTO dto : subjectOpenDTOList) {
                            if (dto.getSubject_id() == subjectId) {
                                isExist = true;
                                break;
                            }
                        }
                        if(isExist){
                            protocol.getProfessorResponsePacket(Message.CHANGE_SUBJECT_PLAN, Message.CODE_TYPE_1);   // 교과목 ID 존재
                        }else{
                            protocol.getProfessorResponsePacket(Message.CHANGE_SUBJECT_PLAN, Message.CODE_TYPE_2);   // 교과목 ID 존재 X
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_PRO_UPDT_SUBJECT_PLAN_REQUEST_PLAN:
                        dis.read(receiveHeader);
                        subjectPlan = changeByteToString(receiveHeader);
                        try {
                            subjectOpenService.updateSubjectPlanSubjectOpen(subjectId, subjectPlan);
                            protocol.getProfessorResponsePacket(Message.CHANGE_SUBJECT_PLAN, Message.CODE_TYPE_3);   // 강의게획서 입력 성공
                        } catch (Exception e) {
                            protocol.getProfessorResponsePacket(Message.CHANGE_SUBJECT_PLAN, Message.CODE_TYPE_4);   // 강의게획서 입력 실패
                        } finally {
                            subjectId = 0;
                            dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                            dos.flush();
                            break;
                        }
                    // 교과목 목록 조회
                    case ServerMessage.PROTOCOL_PRO_SELECT_SUBJECT_ALL_REQUEST:
                        List<SubjectOpenDTO> subjectOpenDTOS = subjectOpenService.getSubjectOpenProfessorAll(professorDTO.getName());
                        int len = subjectOpenDTOS.size();

                        protocol.setProfessorResponseListPacket(Message.TYPE_FIND_PROFESSOR_SUBJECT, len);

                        for (SubjectOpenDTO dto : subjectOpenDTOS) {
                            protocol.setIntToByte(dto.getSubject_id());
                            protocol.setStrToByte(dto.getSubject_name());
                            protocol.setIntToByte(dto.getPossible_grade());
                            protocol.setIntToByte(dto.getNumber_people());
                            protocol.setIntToByte(dto.getMax_people());
                            protocol.setIntToByte(dto.getCredit());
                            protocol.setStrToByte(dto.getDay());
                            protocol.setStrToByte(dto.getTime());
                            protocol.setStrToByte(dto.getClassroom());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        break;
                    // 교과목 강의계획서 조회
                    case ServerMessage.PROTOCOL_PRO_SELECT_SUBJECT_PLAN_ALL_REQUEST:
                        subjectOpenDTOS = subjectOpenService.getSubjectOpenProfessorAll(professorDTO.getName());
                        len = subjectOpenDTOS.size();

                        protocol.setProfessorResponseListPacket(Message.TYPE_FIND_SUBJECT_PLAN, len);

                        for (SubjectOpenDTO dto : subjectOpenDTOS) {
                            protocol.setStrToByte(dto.getSubject_name());
                            protocol.setStrToByte(dto.getSubject_plan());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        break;
                    // 교과목 수강신청 학생 목록 조회
                    case ServerMessage.PROTOCOL_PRO_SELECT_STUDENT_ALL_PER_SUBJECT_REQUEST:
                        dis.read(receiveHeader);
                        subjectId = changeByteToInt(receiveHeader);

                        List<PerProfessorClassStudentDTO> dtos = subjectOpenService.perProfessorClassStudent(subjectId);
                        len = dtos.size();
                        protocol.setProfessorResponseListPacket(Message.TYPE_SUBJECT_CLASS_STUDENT, len);

                        for (PerProfessorClassStudentDTO dto : dtos) {
                            protocol.setIntToByte(dto.getId());
                            protocol.setStrToByte(dto.getName());
                            protocol.setStrToByte(transFormatter.format(dto.getBirth()));
                            protocol.setStrToByte(dto.getPhoneNumber());
                            protocol.setStrToByte(dto.getMajor());
                            protocol.setIntToByte(dto.getGrade());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        break;
                    // 교과목 시간표 조회
                    case ServerMessage.PROTOCOL_PRO_SELECT_SUBJECT_TIME_TABLE_REQUEST:
                        subjectOpenDTOS = subjectOpenService.getSubjectOpenProfessorAll(professorDTO.getName());
                        len = subjectOpenDTOS.size();

                        protocol.setProfessorResponseListPacket(Message.TYPE_FIND_SUBJECT_TIME, len);

                        for (SubjectOpenDTO dto : subjectOpenDTOS) {
                            protocol.setStrToByte(dto.getSubject_name());
                            protocol.setStrToByte(dto.getDay());
                            protocol.setStrToByte(dto.getTime());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        break;
                }
            }
        }catch (Exception ioe){
            System.out.println(ServerMessage.CLIENT_EXIT + ioe.getMessage());
        }
    }

    public void studentMenu(){
        System.out.println(ClientMessage.LOGIN_STUDENT);
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            adminDAO = new AdminDAO();
            professorDAO = new ProfessorDAO();
            studentDAO = new StudentDAO();
            subjectEnrollDAO = new SubjectEnrollDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            subjectNotOpenDAO = new SubjectNotOpenDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            subjectOpenDAO = new SubjectOpenDAO(MyBatisConnectionFactory.getSqlSessionFactory());
            adminService = new AdminService(adminDAO);
            professorService = new ProfessorService(professorDAO);
            studentService = new StudentService(studentDAO);
            subjectEnrollService = new SubjectEnrollService(subjectEnrollDAO);
            subjectNotOpenService = new SubjectNotOpenService(subjectNotOpenDAO);
            subjectOpenService = new SubjectOpenService(subjectOpenDAO, subjectNotOpenDAO);

            SimpleDateFormat transFormatter = new SimpleDateFormat("yyyy-MM-dd");

            Protocol protocol = new Protocol();

            byte[] receiveHeader = new byte[4];
            int headerReader = 0;
            int subjectId = 0;

            int id;
            int len;
            String pw;
            String name;
            String birth;
            String phoneNum;
            String major;
            int grade;

            boolean isExist;

            List<SubjectOpenDTO> subjectOpenDTOS = null;
            List<MySubjectEnrollDTO> mySubjectEnrollDTOS = null;

            SubjectOpenDTO subjectOpenDTO = null;

            String subjectPlan = "";

            List<StudentDTO> all = studentService.findStudentAll(); // 로그인한 유저 정보 저장
            StudentDTO studentDTO = null;
            for(StudentDTO dto : all){
                if (dto.getId() == userId) {
                    studentDTO = dto;
                    break;
                }
            }

            while(true){
                dis.read(receiveHeader);
                headerReader = readHeader(receiveHeader);

                protocol = new Protocol(Message.LOGIN_TYPE_STUDENT, Message.MESSAGE_TYPE_RESPONSE, receiveHeader[2]);
                switch (headerReader) {
                    // 개인정보 수정
                    case ServerMessage.PROTOCOL_STU_UPDT_INFO_REQUEST_PW: // 비밀번호 수정
                        dis.read(receiveHeader);
                        pw = changeByteToString(receiveHeader);
                        studentDTO.setPw(pw);
                        try{
                            studentService.updateStudent(studentDTO.getId(), studentDTO.getPw(), studentDTO.getName(), transFormatter.format(studentDTO.getBirth()), studentDTO.getPhoneNumber(), studentDTO.getMajor(), studentDTO.getGrade());
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_2);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_STU_UPDT_INFO_REQUEST_NAME: // 이름 수정
                        dis.read(receiveHeader);
                        name = changeByteToString(receiveHeader);
                        studentDTO.setName(name);
                        try{
                            studentService.updateStudent(studentDTO.getId(), studentDTO.getPw(), studentDTO.getName(), transFormatter.format(studentDTO.getBirth()), studentDTO.getPhoneNumber(), studentDTO.getMajor(), studentDTO.getGrade());
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_2);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_STU_UPDT_INFO_REQUEST_BIRTH: // 생년월일 수정
                        dis.read(receiveHeader);
                        birth = changeByteToString(receiveHeader);
                        studentDTO.setBirth(Date.valueOf(birth));
                        try{
                            studentService.updateStudent(studentDTO.getId(), studentDTO.getPw(), studentDTO.getName(), transFormatter.format(studentDTO.getBirth()), studentDTO.getPhoneNumber(), studentDTO.getMajor(), studentDTO.getGrade());
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_2);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_STU_UPDT_INFO_REQUEST_PHONENUMBER: // 휴대전화번호 수정
                        dis.read(receiveHeader);
                        phoneNum = changeByteToString(receiveHeader);
                        studentDTO.setPhoneNumber(phoneNum);
                        try{
                            studentService.updateStudent(studentDTO.getId(), studentDTO.getPw(), studentDTO.getName(), transFormatter.format(studentDTO.getBirth()), studentDTO.getPhoneNumber(), studentDTO.getMajor(), studentDTO.getGrade());
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_2);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_STU_UPDT_INFO_REQUEST_MAJOR: // 전공 수정
                        dis.read(receiveHeader);
                        major = changeByteToString(receiveHeader);
                        studentDTO.setMajor(major);
                        try{
                            studentService.updateStudent(studentDTO.getId(), studentDTO.getPw(), studentDTO.getName(), transFormatter.format(studentDTO.getBirth()), studentDTO.getPhoneNumber(), studentDTO.getMajor(), studentDTO.getGrade());
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_2);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }
                    case ServerMessage.PROTOCOL_STU_UPDT_INFO_REQUEST_GRADE: // 학년 수정
                        dis.read(receiveHeader);
                        grade = changeByteToInt(receiveHeader);
                        studentDTO.setGrade(grade);
                        try{
                            studentService.updateStudent(studentDTO.getId(), studentDTO.getPw(), studentDTO.getName(), transFormatter.format(studentDTO.getBirth()), studentDTO.getPhoneNumber(), studentDTO.getMajor(), studentDTO.getGrade());
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_1);
                        }catch (Exception e){
                            protocol.setStudentResponsePacket(Message.TYPE_CHANGE_STUDENT_INFO, Message.CODE_TYPE_2);
                        }finally {
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }
                        // 수강 신청
                    case ServerMessage.PROTOCOL_STU_ENROLL_SUBJECT_REQUEST:
                        dis.read(receiveHeader);
                        subjectId = changeByteToInt(receiveHeader);
                        try{
                            subjectEnrollService.insertSubjectEnrollTakeClassState(studentDTO.getId(), subjectId);
                            protocol.setStudentResponsePacket(Message.TYPE_ENROLL_SUBJECT, Message.CODE_TYPE_1);   // 교과목 ID 존재
                        }catch (EnrollException e){
                            protocol.setStudentResponsePacket(Message.TYPE_ENROLL_SUBJECT, Message.CODE_TYPE_2);   // 교과목 ID 존재 X
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_STU_CANCEL_ENROLL_SUBJECT_REQUEST:
                        dis.read(receiveHeader);
                        subjectId = changeByteToInt(receiveHeader);

                        isExist = false;
                        subjectOpenDTOS = subjectOpenService.getSubjectOpenAll();  // 이미 있는 id 인지 판단
                        for (SubjectOpenDTO dto : subjectOpenDTOS) {
                            if (dto.getSubject_id() == subjectId) {
                                isExist = true;
                                break;
                            }
                        }
                        if(isExist){
                            subjectEnrollService.deleteSubjectEnroll(studentDTO.getId(), subjectId);
                            protocol.setStudentResponsePacket(Message.TYPE_DROP_ENROLL_SUBJECT, Message.CODE_TYPE_1);   // 교과목 ID 존재
                        }else{
                            protocol.setStudentResponsePacket(Message.TYPE_DROP_ENROLL_SUBJECT, Message.CODE_TYPE_2);   // 교과목 ID 존재 X
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize()); // 프로토콜 전송
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_STU_SELECT_OPEN_SUBJECT_ALL_REQUEST_GRADE:     // 학년이 구분자인 개설 교과목 조회
                        dis.read(receiveHeader);
                        int possible_grade = changeByteToInt(receiveHeader);

                        subjectOpenDTOS = subjectOpenService.getSubjectOpenGradeAll(possible_grade);

                        if (subjectOpenDTOS == null) {
                            protocol.setStudentResponseListPacket(Message.TYPE_FIND_PROFESSOR_SUBJECT, 0);

                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }

                        len = subjectOpenDTOS.size(); // 교과목 튜플 개수 설정

                        protocol.setStudentResponseListPacket(Message.TYPE_FIND_PROFESSOR_SUBJECT, len);

                        for (SubjectOpenDTO dto : subjectOpenDTOS) {
                            protocol.setIntToByte(dto.getSubject_id());
                            protocol.setStrToByte(dto.getSubject_name());
                            protocol.setIntToByte(dto.getPossible_grade());
                            protocol.setIntToByte(dto.getNumber_people());
                            protocol.setIntToByte(dto.getMax_people());
                            protocol.setIntToByte(dto.getCredit());
                            protocol.setStrToByte(dto.getDay());
                            protocol.setStrToByte(dto.getTime());
                            protocol.setStrToByte(dto.getClassroom());
                            protocol.setIntToByte(dto.getProfessor_user_id());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_STU_SELECT_OPEN_SUBJECT_ALL_REQUEST_PRO:     // 교수가 구분자인 개설 교과목 조회
                        dis.read(receiveHeader);
                        String professor_name = changeByteToString(receiveHeader);
                        subjectOpenDTOS = subjectOpenService.getSubjectOpenProfessorAll(professor_name);

                        if (subjectOpenDTOS == null) {
                            protocol.setStudentResponseListPacket(Message.TYPE_FIND_PROFESSOR_SUBJECT, 0);

                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }

                        len = subjectOpenDTOS.size(); // 교과목 튜플 개수 설정
                        protocol.setStudentResponseListPacket(Message.TYPE_FIND_PROFESSOR_SUBJECT, len);

                        for (SubjectOpenDTO dto : subjectOpenDTOS) {
                            protocol.setIntToByte(dto.getSubject_id());
                            protocol.setStrToByte(dto.getSubject_name());
                            protocol.setIntToByte(dto.getPossible_grade());
                            protocol.setIntToByte(dto.getNumber_people());
                            protocol.setIntToByte(dto.getMax_people());
                            protocol.setIntToByte(dto.getCredit());
                            protocol.setStrToByte(dto.getDay());
                            protocol.setStrToByte(dto.getTime());
                            protocol.setStrToByte(dto.getClassroom());
                            protocol.setIntToByte(dto.getProfessor_user_id());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_STU_SELECT_OPEN_SUBJECT_ALL_REQUEST_GRADE_PRO:     // 학년 + 교수가 구분자인 개설 교과목 조회
                        dis.read(receiveHeader);
                        possible_grade = changeByteToInt(receiveHeader);

                        dis.read(receiveHeader);
                        professor_name = changeByteToString(receiveHeader);

                        subjectOpenDTOS = subjectOpenService.getSubjectOpenProfessorAndGradeAll(professor_name, possible_grade);

                        if (subjectOpenDTOS == null) {
                            protocol.setStudentResponseListPacket(Message.TYPE_FIND_PROFESSOR_SUBJECT, 0);

                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                            break;
                        }

                        len = subjectOpenDTOS.size(); // 교과목 튜플 개수 설정

                        protocol.setStudentResponseListPacket(Message.TYPE_FIND_PROFESSOR_SUBJECT, len);

                        for (SubjectOpenDTO dto : subjectOpenDTOS) {
                            protocol.setIntToByte(dto.getSubject_id());
                            protocol.setStrToByte(dto.getSubject_name());
                            protocol.setIntToByte(dto.getPossible_grade());
                            protocol.setIntToByte(dto.getNumber_people());
                            protocol.setIntToByte(dto.getMax_people());
                            protocol.setIntToByte(dto.getCredit());
                            protocol.setStrToByte(dto.getDay());
                            protocol.setStrToByte(dto.getTime());
                            protocol.setStrToByte(dto.getClassroom());
                            protocol.setIntToByte(dto.getProfessor_user_id());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        break;
                    case ServerMessage.PROTOCOL_STU_SELECT_OPEN_SUBJECT_ALL_REQUEST:     // 전체 개설 교과목 조회
                        subjectOpenDTOS = subjectOpenService.getSubjectOpenAll();
                        len = subjectOpenDTOS.size(); // 교과목 튜플 개수 설정

                        protocol.setStudentResponseListPacket(Message.TYPE_FIND_PROFESSOR_SUBJECT, len);

                        for (SubjectOpenDTO dto : subjectOpenDTOS) {
                            protocol.setIntToByte(dto.getSubject_id());
                            protocol.setStrToByte(dto.getSubject_name());
                            protocol.setIntToByte(dto.getPossible_grade());
                            protocol.setIntToByte(dto.getNumber_people());
                            protocol.setIntToByte(dto.getMax_people());
                            protocol.setIntToByte(dto.getCredit());
                            protocol.setStrToByte(dto.getDay());
                            protocol.setStrToByte(dto.getTime());
                            protocol.setStrToByte(dto.getClassroom());
                            protocol.setIntToByte(dto.getProfessor_user_id());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        break;
                    // 선택교과목 강의계획서 조회
                    case ServerMessage.PROTOCOL_STU_SELECT_SUBJECT_PLAN_REQUEST:
                        dis.read(receiveHeader);
                        subjectId = changeByteToInt(receiveHeader);
                        subjectOpenDTOS = subjectOpenService.getSubjectOpenAll();
                        subjectOpenDTO = null;
                        isExist = false;
                        for(SubjectOpenDTO dto : subjectOpenDTOS){
                            if(dto.getSubject_id() == subjectId){
                                subjectOpenDTO = dto;
                                isExist = true;
                            }
                        }
                        if(isExist){
                            protocol.setStudentResponsePacket(Message.TYPE_FIND_SELECT_SUBJECT_PLAN, Message.CODE_TYPE_1);
                            protocol.setStrToByte(subjectOpenDTO.getSubject_name());
                            protocol.setStrToByte(subjectOpenDTO.getSubject_plan());
                        }else{
                            protocol.setStudentResponsePacket(Message.TYPE_FIND_SELECT_SUBJECT_PLAN, Message.CODE_TYPE_2);
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        break;
                    // 본인시간표 조회
                    case ServerMessage.PROTOCOL_STU_SELECT_MY_TIME_TABLE_REQUEST:
                        mySubjectEnrollDTOS = subjectEnrollService.getMySubjectEnroll(studentDTO.getId());
                        len = mySubjectEnrollDTOS.size(); // 교과목 튜플 개수 설정
                        protocol.setStudentResponseListPacket(Message.TYPE_SUBJECT_CLASS_STUDENT, len);
                        for (MySubjectEnrollDTO dto : mySubjectEnrollDTOS) {
                            protocol.setStrToByte(dto.getSubject_name());
                            protocol.setStrToByte(dto.getDay());
                            protocol.setStrToByte(dto.getTime());
                            protocol.setStrToByte(dto.getClassroom());
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        break;
                }
            }
        }catch (IOException ioe){
            System.out.println(ServerMessage.IOEXCEPTION);
        }
        catch (Exception e){
            System.out.println(ServerMessage.EXCEPTION);
        }
    }

    public static void main(String[] args) {
        ServerSocket sSocket = null;
        Socket socket = null;
        try {
            sSocket = new ServerSocket(3000);      // Client와 연결을 담당하는 ServerSocket
            while (true) {
                System.out.println(ServerMessage.CLIENT_WAITING);
                socket = sSocket.accept();                       // 데이터 주고받는 Socket
                System.out.println(ServerMessage.CLIENT_CONNECT);

                new EnrollServer(socket);
            }
        }
        catch (IOException e) {
            System.out.println(ServerMessage.IOEXCEPTION);
        }

        try{
            sSocket.close();
        }
        catch(IOException ioException){
            System.err.println("Unable to close. "+ ServerMessage.IOEXCEPTION);
        }
    }
}