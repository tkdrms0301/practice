package EnrollNetwork;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EnrollClient {
    private Socket socket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private Scanner sc;
    private int command;

    public EnrollClient(String serverName, int serverPort) { // client 생성자 -> Socket, DataInputStream, DataOutputStream
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println(ClientMessage.CONNECTED + socket);
            sc = new Scanner(System.in);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException uhe) {
            System.out.println(ClientMessage.UNKNOWN_HOST_EXCEPTION);
        } catch (IOException ioe) {
            System.out.println(ClientMessage.IO_EXCEPTION);
        }
    }

    private static int byteArrayToInt(byte[] data) { // 바이트배열 read시 정수값으로 변환
        if (data == null || data.length != 4) return 0x0;
        return (int)( // NOTE: type cast not necessary for int
                (0xff & data[0]) << 24  |
                        (0xff & data[1]) << 16  |
                        (0xff & data[2]) << 8   |
                        (0xff & data[3]) << 0
        );
    }

    public String changeByteToString(byte[] recvHeader) throws IOException { // 바이트배열 read시 문자열으로 변환
        int bytesRcvd;
        int totalBytesRcvd = 0;  // 지금까지 받은 바이트 수

        int dataLength = byteArrayToInt(recvHeader);
        if (dataLength == 0) return null;

        byte[] recvData = new byte[dataLength];

        while (totalBytesRcvd < dataLength) {
            if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, dataLength - totalBytesRcvd)) == -1)
                throw new SocketException(ClientMessage.SOCKET_EXCEPTION_ERROR);
            totalBytesRcvd += bytesRcvd;
        }

        return new String(recvData);
    }

    public int changeByteToInt(byte[] recvHeader) throws IOException { // write시 정수값을 바이트배열로 변환
        int bytesRcvd;
        int totalBytesRcvd = 0;  // 지금까지 받은 바이트 수

        int dataLength = byteArrayToInt(recvHeader);
        if (dataLength == 0) return 0;

        byte[] recvData = new byte[dataLength];

        while (totalBytesRcvd < dataLength) {
            if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, 4 - totalBytesRcvd)) == -1)
                throw new SocketException(ClientMessage.SOCKET_EXCEPTION_ERROR);
            totalBytesRcvd += bytesRcvd;
        }

        return byteArrayToInt(recvData);
    }

    public int readHeader(byte[] receivePacket){ // 프로토콜 헤더 정수값으로 변환
        return (receivePacket[0] * 10000 + receivePacket[1] * 1000 + receivePacket[2] * 10 + receivePacket[3]);
    }

    public void clientMenu(){
        try {
            byte[] receivePacket = new byte[4];
            int headerReader = 0;
            Protocol protocol = null;
            int group_id = 0;
            while (true){
                dis.read(receivePacket);
                headerReader = readHeader(receivePacket);
                protocol = new Protocol();
                System.out.println(ClientMessage.LOGIN_REQ);
                if(headerReader == ClientMessage.PROTOCOL_LOGIN_RESPONSE){ // 로그인 id, pw 전송
                    System.out.print(ClientMessage.LOGIN_ID_REQ);
                    int id = sc.nextInt();
                    System.out.print(ClientMessage.LOGIN_PW_REQ);
                    String pw = sc.next();
                    protocol.getLoginPacket(Message.MESSAGE_TYPE_RESPONSE, Message.NULL_HEADER);
                    protocol.setIntToByte(id);
                    protocol.setStrToByte(pw);
                    dos.write(protocol.getPacket(), 0, protocol.getSize());
                    dos.flush();
                }else if(headerReader == ClientMessage.PROTOCOL_LOGIN_SUC){ // 로그인 성공
                    System.out.println(ClientMessage.LOGIN_SUC);
                    dis.read(receivePacket);
                    group_id = changeByteToInt(receivePacket); // group_id에 따라 개인 메뉴로 이동
                    break;
                }else if(headerReader == ClientMessage.PROTOCOL_LOGIN_FAIL){ // 로그인 실패 종료
                    System.out.println(ClientMessage.LOGIN_FAIL);
                    return;
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
        }catch (IOException ioe){
            System.out.println(ClientMessage.IO_EXCEPTION);
        }
    }

    public void adminMenu(){ //관리자 메뉴
        System.out.println(ClientMessage.LOGIN_ADMIN);

        try{
            byte[] receivePacket = new byte[4];
            int headerReader = 0;
            Protocol protocol = null;
            int id;
            String name;
            String birth;
            String phoneNumber;
            String major;
            int grade;

            int subject_id;
            String subject_name;
            int possible_grade;

            String plan_modify_date;
            String start_date;
            String end_date;

            int max_people;
            String subject_plan;
            int credit;
            String day;
            String time;
            String classroom;
            int professor_id;
            int line;
            while(true){
                System.out.print(ClientMessage.ADMIN_MENU);
                command = sc.nextInt();
                protocol = new Protocol(Message.LOGIN_TYPE_ADMIN, Message.MESSAGE_TYPE_REQUEST, command);
                switch (command){
                    case ClientMessage.INPUT_COMMAND_1:
                        System.out.println(ClientMessage.CRT_PRO_ACC); // 교수 계정 생성

                        System.out.print(ClientMessage.CRT_PRO_ACC_ID_REQ); // id
                        id = sc.nextInt();

                        // 프로토콜 데이터 넣기
                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_MAKE_PROFESSOR_ACCOUNT, Message.CODE_TYPE_1);
                        protocol.setIntToByte(id);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        // 프로토콜 읽기
                        while (true){
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_PRO_ID_EXIST) { // 교수 id 존재
                                System.out.println(ClientMessage.CRT_PRO_ACC_ID_ERROR);
                                break;
                            }else if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_PRO){ // 교수 id 존재 하지 않음 -> 생성진행
                                System.out.print(ClientMessage.CRT_PRO_ACC_NAME_REQ);
                                name = sc.next();
                                System.out.print(ClientMessage.CRT_PRO_ACC_BIRTH_REQ);
                                birth = sc.next();
                                System.out.print(ClientMessage.CRT_PRO_ACC_PHONE_REQ);
                                phoneNumber = sc.next();
                                System.out.print(ClientMessage.CRT_PRO_ACC_MAJOR_REQ);
                                major = sc.next();
                                protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_MAKE_PROFESSOR_ACCOUNT, Message.CODE_TYPE_2);
                                protocol.setStrToByte(name);
                                protocol.setStrToByte(birth);
                                protocol.setStrToByte(phoneNumber);
                                protocol.setStrToByte(major);
                                dos.write(protocol.getPacket(), 0, protocol.getSize());
                                dos.flush();
                            }else if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_SUC){ // 교수 생성 성공
                                System.out.println(ClientMessage.CRT_PRO_ACC_SUC);
                                break;
                            }else if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_FAIL){ // 교수 생성 실패
                                System.out.println(ClientMessage.CRT_PRO_ACC_FAIL);
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_2:
                        System.out.println(ClientMessage.CRT_STU_ACC); // 학생 계정 생성

                        System.out.print(ClientMessage.CRT_STU_ACC_ID_REQ); // id
                        id = sc.nextInt();

                        // 프로토콜 데이터 넣기
                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_MAKE_STUDENT_ACCOUNT, Message.CODE_TYPE_1);
                        protocol.setIntToByte(id);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        // 프로토콜 읽기
                        while (true){
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_STU_ID_EXIST) { // 학생 id 존재
                                System.out.println(ClientMessage.CRT_NOT_OPEN_SUB_ID_ERROR);
                                break;
                            }else if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_STU){ // 학생 id 존재 하지 않음 -> 생성진행
                                System.out.print(ClientMessage.CRT_STU_ACC_NAME_REQ);
                                name = sc.next();
                                System.out.print(ClientMessage.CRT_STU_ACC_BIRTH_REQ);
                                birth = sc.next();
                                System.out.print(ClientMessage.CRT_STU_ACC_PHONE_REQ);
                                phoneNumber = sc.next();
                                System.out.print(ClientMessage.CRT_STU_ACC_MAJOR_REQ);
                                major = sc.next();
                                System.out.print(ClientMessage.CRT_STU_ACC_GRADE_REQ);
                                grade = sc.nextInt();
                                protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_MAKE_STUDENT_ACCOUNT, Message.CODE_TYPE_2);
                                protocol.setStrToByte(name);
                                protocol.setStrToByte(birth);
                                protocol.setStrToByte(phoneNumber);
                                protocol.setStrToByte(major);
                                protocol.setIntToByte(grade);
                                dos.write(protocol.getPacket(), 0, protocol.getSize());
                                dos.flush();
                            }else if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_STU_SUC){ // 학생 생성 성공
                                System.out.println(ClientMessage.CRT_STU_ACC_SUC);
                                break;
                            }else if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_STU_FAIL){ // 학생 생성 실패
                                System.out.println(ClientMessage.CRT_STU_ACC_FAIL);
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_3:
                        System.out.println(ClientMessage.CRT_NOT_OPEN_SUB); // 비개설 교과목 생성
                        System.out.print(ClientMessage.CRT_NOT_OPEN_SUB_ID_REQ); // 비개설 교과목 id 입력
                        subject_id = sc.nextInt();

                        // 프로토콜 데이터 넣기
                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_MAKE_SUBJECT, Message.CODE_TYPE_1);
                        protocol.setIntToByte(subject_id);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        // 프로토콜 읽기
                        while (true){
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_NOT_OPEN_SUB_ID_EXIST) { // 비개설 교과목 id 존재
                                System.out.println(ClientMessage.CRT_NOT_OPEN_SUB_ID_ERROR);
                                break;
                            }else if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_NOT_OPEN_SUB){ // 비개설 교과목 id 존재 하지 않음 -> 생성진행
                                System.out.print(ClientMessage.CRT_NOT_OPEN_SUB_NAME_REQ);
                                name = sc.next();
                                System.out.print(ClientMessage.CRT_NOT_OPEN_SUB_GRADE_REQ);
                                possible_grade = sc.nextInt();
                                protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_MAKE_SUBJECT, Message.CODE_TYPE_2);
                                protocol.setStrToByte(name);
                                protocol.setIntToByte(possible_grade);
                                dos.write(protocol.getPacket(), 0, protocol.getSize());
                                dos.flush();
                            }else if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_NOT_OPEN_SUB_SUC){ // 비개설 교과목 생성 성공
                                System.out.println(ClientMessage.CRT_NOT_OPEN_SUB_SUC);
                                break;
                            }else if(headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_NOT_OPEN_SUB_FAIL){ // 비개설 교과목 생성 실패
                                System.out.println(ClientMessage.CRT_NOT_OPEN_SUB_FAIL);
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_4:
                        System.out.println(ClientMessage.UPDT_NOT_OPEN_SUB); // 비개설 교과목 수정
                        System.out.print(ClientMessage.UPDT_NOT_OPEN_SUB_ID_REQ); // 비개셜 교과목 id
                        subject_id = sc.nextInt();

                        // 프로토콜 데이터 넣기
                        protocol.setRequestId(Message.FUNCTION_TYPE_UPDATE_SUBJECT);
                        protocol.setIntToByte(subject_id);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);
                        if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDT_NOT_OPEN_SUB) { // 비개설교과목 id 존재 -> 수정진행
                            while (true) {
                                System.out.println(ClientMessage.UPDT_NOT_OPEN_SUB_ITEM);
                                System.out.print(ClientMessage.MENU_INPUT);
                                command = sc.nextInt();
                                switch (command) {
                                    case 1:
                                        System.out.print(ClientMessage.UPDT_NOT_OPEN_SUB_NAME_REQ); //비개설 교과목 이름 수정
                                        name = sc.next();
                                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_UPDATE_SUBJECT, Message.CODE_TYPE_2);
                                        protocol.setStrToByte(name);
                                        break;
                                    case 2:
                                        System.out.print(ClientMessage.UPDT_NOT_OPEN_SUB_GRADE_REQ); // 비개설 교과목 수강가능한 학년 수정
                                        possible_grade = sc.nextInt();
                                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_UPDATE_SUBJECT, Message.CODE_TYPE_3);
                                        protocol.setIntToByte(possible_grade);
                                        break;
                                    default:
                                        System.out.println(ClientMessage.UPDT_NOT_OPEN_SUB_INPUT_FAIL);
                                        continue;
                                }
                                dos.write(protocol.getPacket(), 0, protocol.getSize());
                                dos.flush();

                                break;
                            }

                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDT_NOT_OPEN_SUB_SUC) { // 비개설 교과목 수정 성공
                                System.out.println(ClientMessage.UPDT_NOT_OPEN_SUB_SUC);
                            } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDT_NOT_OPEN_SUB_FAIL) { // 비개설 교과목 수정 실패
                                System.out.println(ClientMessage.UPDT_NOT_OPEN_SUB_FAIL);
                            }

                        } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDT_NOT_OPEN_SUB_NOT_EXIST) { // 비개설교과목 id 존재하지 않음
                            System.out.println(ClientMessage.UPDT_NOT_OPEN_SUB_ID_ERROR);
                            break;
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_5:
                        System.out.println(ClientMessage.DEL_NOT_OPEN_SUB); // 비개설 교과목 삭제
                        System.out.print(ClientMessage.DEL_NOT_OPEN_SUB_ID_REQ); // subject_id 입력
                        subject_id = sc.nextInt();

                        // 프로토콜 데이터 넣기
                        protocol.setRequestId(Message.FUNCTION_TYPE_DELETE_SUBJECT);
                        protocol.setIntToByte(subject_id);
                        System.out.println(readHeader(protocol.getPacket()));
                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        // 프로토콜 읽기
                        while (true) {
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if (headerReader == ClientMessage.PROTOCOL_ADMIN_DEL_NOT_OPEN_SUB_SUC) { // 비개설 교과목 삭제 성공
                                System.out.println(ClientMessage.DEL_NOT_OPEN_SUB_SUC);
                                break;
                            } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_DEL_NOT_OPEN_SUB_FAIL) { // 비개설 교과목 삭제 실패
                                System.out.println(ClientMessage.DEL_NOT_OPEN_SUB_FAIL);
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_6:
                        System.out.println(ClientMessage.INSERT_PLAN_MODIFY); // 강의계획서 입력기간 설정
                        System.out.print(ClientMessage.INSERT_PLAN_MODIFY_ID_REQ); // subject_id 입력
                        subject_id = sc.nextInt();

                        // 프로토콜 데이터 넣기
                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_SET_SUBJECT_PLAN, Message.CODE_TYPE_1);
                        protocol.setIntToByte(subject_id);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        // 프로토콜 읽기
                        while (true) {
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDATE_PLAN_MODIFY_DATE) { // 개설 교과목 id 존재 -> 입력기간 설정
                                System.out.print(ClientMessage.INSERT_PLAN_MODIFY_DATE_REQ);
                                plan_modify_date = sc.next();
                                protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_SET_SUBJECT_PLAN, Message.CODE_TYPE_2);
                                protocol.setStrToByte(plan_modify_date);

                                dos.write(protocol.getPacket(), 0, protocol.getSize());
                                dos.flush();
                            } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDATE_PLAN_MODIFY_DATE_ID_NOT_EXIST) { // 개설 교과목 id 존재하지 않음
                                System.out.println(ClientMessage.INSERT_PLAN_MODIFY_ID_ERROR);
                                break;
                            } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDATE_PLAN_MODIFY_DATE_SUC) { // 입력 성공
                                System.out.println(ClientMessage.INSERT_PLAN_MODIFY_SUC);
                                break;
                            } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDATE_PLAN_MODIFY_DATE_FAIL) { // 입력 실패
                                System.out.println(ClientMessage.INSERT_PLAN_MODIFY_FAIL);
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_7:
                        System.out.println(ClientMessage.INSERT_GRADE_SUBJECT_DATE); // 학년별 수강신청 기간 설정
                        System.out.print(ClientMessage.INSERT_GRADE_SUBJECT_DATE_GRADE_REQ); // grade 입력
                        grade = sc.nextInt();
                        System.out.print(ClientMessage.INSERT_GRADE_SUBJECT_DATE_START_REQ); // 시작 날짜 입력
                        start_date = sc.next();
                        System.out.print(ClientMessage.INSERT_GRADE_SUBJECT_DATE_ENR_REQ); // 끝 날짜 입력
                        end_date = sc.next();
                        // 프로토콜 데이터 넣기
                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_SET_ENROLL_TIME, Message.CODE_TYPE_1);
                        protocol.setIntToByte(grade);
                        protocol.setStrToByte(start_date);
                        protocol.setStrToByte(end_date);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        // 프로토콜 읽기
                        while (true) {
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDATE_ENROLL_TERM_SUC) { // 학년별 수강신청 기간 설정 성공
                                System.out.println(ClientMessage.INSERT_GRADE_SUBJECT_DATE_SUC);
                                break;
                            } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDATE_ENROLL_TERM_FAIL) { // 학년별 수강신청 기간 설정 실패
                                System.out.println(ClientMessage.INSERT_GRADE_SUBJECT_DATE_FAIL);
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_8:
                        System.out.println(ClientMessage.READ_PROFESSOR); // 교수 정보 조회 (전체조회)
                        // 프로토콜 데이터 넣기
                        protocol.setRequestId(Message.FUNCTION_TYPE_FIND_PROFESSOR);
                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        // 프로토콜 읽기
                        while (true) {
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if (headerReader == ClientMessage.PROTOCOL_ADMIN_SELECT_PRO_ALL) { // 교수 정보 조회
                                System.out.printf("|%-10s|%-20s|%-10s|%-20s|%-20s\n","id","name","birth","phoneNumber","major");
                                dis.read(receivePacket);
                                line = byteArrayToInt(receivePacket); // 교수 리스트 튜플 개수 읽기
                                for(int i = 0; i < line; i++){ // 출력
                                    System.out.print("|");
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-20s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-20s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.println(changeByteToString(receivePacket));
                                }
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_9:
                        System.out.println(ClientMessage.READ_STUDENT); // 학생 정보 조회 (전체조회)
                        // 프로토콜 데이터 넣기
                        protocol.setRequestId(Message.FUNCTION_TYPE_FIND_STUDENT);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        // 프로토콜 읽기
                        while (true) {
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if (headerReader == ClientMessage.PROTOCOL_ADMIN_SELECT_STU_ALL) { // 학생 정보 조회
                                System.out.printf("|%-10s|%-20s|%-10s|%-20s|%-20s|%-10s","id","name","birth","phoneNumber","major","grade");

                                dis.read(receivePacket); // 학생 튜플 개수 읽기
                                line = byteArrayToInt(receivePacket);
                                System.out.println(line);
                                for(int i = 0; i < line; i++){ // 출력
                                    System.out.print("|");
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-20s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-20s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-20s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.println(changeByteToInt(receivePacket));
                                }
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_10:
                        System.out.println(ClientMessage.READ_ALL_SUBJECT); // 교과목 정보 조회
                        // 프로토콜 데이터 넣기
                        protocol.setRequestId(Message.FUNCTION_TYPE_FIND_OPEN_SUBJECT);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();
                        // 프로토콜 읽기
                        while (true) {
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);

                            if (headerReader == ClientMessage.PROTOCOL_ADMIN_SELECT_OPEN_SUB_ALL) { // 개설 교과목 정보 조회
                                System.out.printf("|%-10s|%-20s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s",
                                        "subject_id","subject_name","possible_grade","max_people", "number_people", "plan_modify_date", "credit", "day", "time", "classroom", "professor_id");
                                System.out.println();
                                dis.read(receivePacket); // 개설 교과목 튜플 개수 읽기
                                line = byteArrayToInt(receivePacket);
                                for(int i = 0; i < line; i++){ // 출력
                                    System.out.print("|");
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-20s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.printf("%-10s|", changeByteToString(receivePacket));
                                    dis.read(receivePacket);
                                    System.out.println(changeByteToInt(receivePacket));
                                }

                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_11:
                        System.out.println(ClientMessage.CRT_OPEN_SUB); // 개설 교과목 생성
                        System.out.print(ClientMessage.CRT_OPEN_SUB_ID_REQ); // id
                        subject_id = sc.nextInt();

                        // 프로토콜 데이터 넣기
                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_MAKE_OPEN_SUBJECT, Message.CODE_TYPE_1);
                        protocol.setIntToByte(subject_id);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        // 프로토콜 읽기
                        while (true) {
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if (headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_OPEN_SUB) { // 비개설 교과목 id 존재 -> 생성진행
                                System.out.print(ClientMessage.CRT_OPEN_SUB_MAX_NUMBER_REQ);
                                max_people = sc.nextInt();
                                System.out.print(ClientMessage.CRT_OPEN_SUB_MAJOR_REQ);
                                credit = sc.nextInt();
                                System.out.print(ClientMessage.CRT_OPEN_SUB_DAY_REQ);
                                day = sc.next();
                                System.out.print(ClientMessage.CRT_OPEN_TIME_REQ);
                                time = sc.next();
                                System.out.print(ClientMessage.CRT_OPEN_SUB_CLASSROOM_REQ);
                                classroom = sc.next();
                                System.out.print(ClientMessage.CRT_OPEN_SUB_PRO_ID);
                                professor_id = sc.nextInt();
                                protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_MAKE_OPEN_SUBJECT, Message.CODE_TYPE_2);
                                protocol.setIntToByte(max_people);
                                protocol.setIntToByte(credit);
                                protocol.setStrToByte(day);
                                protocol.setStrToByte(time);
                                protocol.setStrToByte(classroom);
                                protocol.setIntToByte(professor_id);

                                dos.write(protocol.getPacket(), 0, protocol.getSize());
                                dos.flush();
                            } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_OPEN_SUB_ID_NOT_EXIST) { // 비개설 교과목 id 존재 하지 않음
                                System.out.println(ClientMessage.CRT_OPEN_SUB_ID_ERROR);
                                break;
                            } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_OPEN_SUB_SUC) { // 개설 교과목 생성 성공
                                System.out.println(ClientMessage.CRT_PRO_ACC_SUC);
                                break;
                            } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_INSERT_OPEN_SUB_FAIL) { // 개설 교과목 생성 실패
                                System.out.println(ClientMessage.CRT_PRO_ACC_FAIL);
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_12:
                        System.out.println(ClientMessage.UPDT_OPEN_SUB); // 개설 교과목 수정
                        System.out.print(ClientMessage.UPDT_OPEN_SUB_ID_REQ); // id
                        subject_id = sc.nextInt();

                        // 프로토콜 데이터 넣기
                        protocol.setRequestId(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT);
                        protocol.setIntToByte(subject_id);
                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);
                        if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDATE_OPEN_SUB) { // 비개설 교과목 id 존재
                            protocol = new Protocol();
                            while (true) {
                                System.out.print(ClientMessage.UPDT_OPEN_SUB_ITEM);
                                command = sc.nextInt();
                                switch (command){
                                    case ClientMessage.INPUT_COMMAND_1: // 최대 수강인원 수정
                                        System.out.print(ClientMessage.UPDT_OPEN_SUB_MAXPEOPLE_REQ);
                                        max_people = sc.nextInt();
                                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_2);
                                        protocol.setIntToByte(max_people);
                                        System.out.println(readHeader(protocol.getPacket()));;
                                        break;
                                    case ClientMessage.INPUT_COMMAND_2: // 학점 수정
                                        System.out.print(ClientMessage.UPDT_OPEN_SUB_CREDIT);
                                        credit = sc.nextInt();
                                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_3);
                                        protocol.setIntToByte(credit);
                                        break;
                                    case ClientMessage.INPUT_COMMAND_3: // 요일 수정
                                        System.out.print(ClientMessage.UPDT_OPEN_SUB_DAY_REQ);
                                        day = sc.next();
                                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_4);
                                        protocol.setStrToByte(day);
                                        break;
                                    case ClientMessage.INPUT_COMMAND_4: // 시간 수정
                                        System.out.print(ClientMessage.UPDT_OPEN_SUB_TIME_REQ);
                                        time = sc.next();
                                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_5);
                                        protocol.setStrToByte(time);
                                        break;
                                    case ClientMessage.INPUT_COMMAND_5: // 강의실 수정
                                        System.out.print(ClientMessage.UPDT_OPEN_SUB_CLASSROOM_REQ);
                                        classroom = sc.next();
                                        protocol.getAdminRequestPacket(Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT, Message.CODE_TYPE_6);
                                        protocol.setStrToByte(classroom);
                                        break;
                                    default:
                                        System.out.println(ClientMessage.UPDT_OPEN_SUB_INPUT_FAIL); // 잘못된 커맨드 입력
                                        continue;
                                }
                                dos.write(protocol.getPacket(), 0, protocol.getSize());
                                dos.flush();
                                break;
                            }

                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);

                            if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDATE_OPEN_SUB_SUC) { // 개설 교과목 수정 성공
                                System.out.println(ClientMessage.UPDT_OPEN_SUB_SUC);
                            } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDATE_OPEN_SUB_FAIL) { // 개설 교과목 수정 실패
                                System.out.println(ClientMessage.UPDT_OPEN_SUB_FAIL);
                            }
                        } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_UPDATE_OPEN_SUB_ID_NOT_EXIST) { // 비개설 교과목 id 존재 하지 않음 -> 생성진행
                            System.out.println(ClientMessage.UPDT_OPEN_SUB_ID_ERROR);
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_13:
                        System.out.println(ClientMessage.DEL_OPEN_SUB); // 개설 교과목 폐강
                        System.out.print(ClientMessage.DEL_OPEN_SUB_ID_REQ); // id
                        subject_id = sc.nextInt();

                        // 프로토콜 데이터 넣기
                        protocol.setRequestId(Message.FUNCTION_TYPE_DELETE_OPEN_SUBJECT);
                        protocol.setIntToByte(subject_id);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        //프로토콜 읽기
                        while (true) {
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if (headerReader == ClientMessage.PROTOCOL_ADMIN_DEL_OPEN_SUB_SUC) { // 폐강 성공
                                System.out.println(ClientMessage.DEL_OPEN_SUB_SUC);
                                break;
                            } else if (headerReader == ClientMessage.PROTOCOL_ADMIN_DEL_OPEN_SUB_FAIL) { // 폐강 실패
                                System.out.println(ClientMessage.DEL_OPEN_SUB_FAIL);
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_14: // 프로그램 정료
                        System.out.println(ClientMessage.PROGRAM_EXIT);
                        return;
                    default:
                        System.out.println(ClientMessage.INPUT_ERROR_NUMBER); // 잘못된 커맨드 입력
                        break;
                }
            }
        }catch (IOException ioe){
            System.out.println("error : " + ioe.getMessage());
        }
    }

    public void professorMenu() { // 교수 메뉴
        Protocol protocol = new Protocol();

        System.out.println(ClientMessage.LOGIN_PROFESSOR);
        byte[] receivePacket = new byte[4];
        int headerReader = 0;

        try {
            while(true) {
                System.out.print(ClientMessage.PRO_MENU);
                command = sc.nextInt();
                protocol = new Protocol(Message.LOGIN_TYPE_PROFESSOR, Message.MESSAGE_TYPE_REQUEST, command);

                switch (command){
                    case ClientMessage.INPUT_COMMAND_1:     // 교수 개인정보 수정   | 전공 변경이 없어서 CodeType에 5번 추가함
                        System.out.println(ClientMessage.UPDT_PRO_INFO); // 교수 개인정보 수정
                        System.out.println(ClientMessage.UPDT_PRO_INFO_ITEM);
                        int codeType = sc.nextInt();

                        switch (codeType) {
                            case ClientMessage.INPUT_COMMAND_1: // 비밀번호 수정
                                protocol.getProfessorRequestPacket(command, codeType);

                                System.out.print(ClientMessage.UPDT_PRO_INFO_NEW_PW_REQ);
                                protocol.setStrToByte(sc.next());
                                System.out.println(readHeader(protocol.getPacket()));
                                break;
                            case ClientMessage.INPUT_COMMAND_2: // 이름 수정
                                protocol.getProfessorRequestPacket(command, codeType);

                                System.out.print(ClientMessage.UPDT_PRO_INFO_NEW_NAME_REQ);
                                protocol.setStrToByte(sc.next());;
                                break;
                            case ClientMessage.INPUT_COMMAND_3: // 생년월일 수정
                                protocol.getProfessorRequestPacket(command, codeType);

                                System.out.print(ClientMessage.UPDT_PRO_INFO_NEW_BIRTH_REQ);
                                protocol.setStrToByte(sc.next());;
                                break;
                            case ClientMessage.INPUT_COMMAND_4: // 휴대전화번호 수정
                                protocol.getProfessorRequestPacket(command, codeType);

                                System.out.print(ClientMessage.UPDT_PRO_INFO_NEW_PHONE_REQ);
                                protocol.setStrToByte(sc.next());;
                                break;
                            case ClientMessage.INPUT_COMMAND_5: // 전공 수정
                                protocol.getProfessorRequestPacket(command, codeType);

                                System.out.print(ClientMessage.UPDT_PRO_INFO_NEW_MAJOR_REQ);
                                protocol.setStrToByte(sc.next());
                                break;
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);

                        if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_UPDATE_INFO_SUC) {    // 교수 개인정보 수정 성공
                            System.out.println(ClientMessage.UPDT_PRO_INFO_SUC);
                        } else if(headerReader == ClientMessage.PROTOCOL_PROFESSOR_UPDATE_INFO_FAIL){  // 교수 개인정보 수정 실패
                            System.out.println(ClientMessage.UPDT_PRO_INFO_FAIL);
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_2:     // 강의계획서 입력
                        System.out.println(ClientMessage.INSERT_SUBJECT_PLAN); // 강의계획서 입력
                        protocol.getProfessorRequestPacket(command, Message.CODE_TYPE_1);     // 교과목 id 입력

                        System.out.print(ClientMessage.INSERT_SUBJECT_PLAN_SUBJECT_ID_REQ);
                        protocol.setIntToByte(sc.nextInt());
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);

                        if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_INSERT_ID_EXIST) {    // 교과목 id 존재
                            protocol.getProfessorRequestPacket(command, Message.CODE_TYPE_2);     // 강의계획서 입력
                            System.out.print(ClientMessage.INSERT_SUBJECT_PLAN_NEW_PLAN_REQ);
                            sc.nextLine();
                            protocol.setStrToByte(sc.nextLine());       // 강의계획서 입력
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                        } else if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_INSERT) {     // 교과목 id 존재 X
                            System.out.println(ClientMessage.INSERT_SUBJECT_PLAN_SUBJECT_ID_ERROR);
                            break;
                        }

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);

                        if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_INSERT_SUC) {     // 강의계획서 입력 성공
                            System.out.println(ClientMessage.INSERT_SUBJECT_PLAN_SUC);
                        } else if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_INSERT_FAIL) {     // 강의계획서 입력 실패
                            System.out.println(ClientMessage.INSERT_SUBJECT_PLAN_FAIL);
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_3:     // 강의계획서 수정
                        System.out.println(ClientMessage.UPDT_SUBJECT_PLAN); // 강의계획서 입력
                        protocol.getProfessorRequestPacket(command, Message.CODE_TYPE_1);     // 교과목 id 입력

                        System.out.print(ClientMessage.UPDT_SUBJECT_PLAN_SUBJECT_ID_REQ);
                        protocol.setIntToByte(sc.nextInt());
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);

                        if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_SUBJECT_PLAN_UPDATE_ID_EXIST) {    // 교과목 id 존재
                            protocol.getProfessorRequestPacket(command, Message.CODE_TYPE_2);     // 강의계획서 수정
                            System.out.print(ClientMessage.UPDT_SUBJECT_SUBJECT_PLAN);
                            sc.nextLine();
                            protocol.setStrToByte(sc.nextLine());       // 강의계획서 입력
                            dos.write(protocol.getPacket(), 0, protocol.getSize());
                            dos.flush();
                        } else if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_SUBJECT_PLAN_UPDATE) {     // 교과목 id 존재 X
                            System.out.println(ClientMessage.UPDT_SUBJECT_PLAN_SUBJECT_ID_ERROR);
                            break;
                        }

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);
                        if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_SUBJECT_PLAN_UPDATE_SUC) {     // 강의계획서 수정 성공
                            System.out.println(ClientMessage.UPDT_SUBJECT_PLAN_SUC);
                        } else if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_SUBJECT_PLAN_UPDATE_FAIL) {     // 강의계획서 수정 실패
                            System.out.println(ClientMessage.UPDT_SUBJECT_PLAN_FAIL);
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_4:     // 교과목 목록 조회
                        System.out.println(ClientMessage.READ_SUBJECT);     // 교과목 목록 조회
                        protocol.getProfessorRequestPacket(command, Message.NULL_HEADER);     // 교과목 id 입력

                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);
                        if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_SUBJECT_LIST) { // 담당교과목 목록 조회
                            dis.read(receivePacket); // 교과목 튜플 개수 읽기
                            int len = byteArrayToInt(receivePacket);

                            System.out.printf("|%-10s|%-20s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s","subject_id","subject_name","possible_grade","number_people", "max_people", "credit", "day", "time", "classroom");
                            System.out.println();
                            for (int i = 0; i < len; i++) {
                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToInt(receivePacket)); // subject_id

                                dis.read(receivePacket);
                                System.out.printf("%-20s|", changeByteToString(receivePacket)); // subject_name

                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToInt(receivePacket)); // possible_grade

                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToInt(receivePacket)); // number_people

                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToInt(receivePacket)); // max_people

                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToInt(receivePacket)); // credit

                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToString(receivePacket)); // day

                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToString(receivePacket)); // time

                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToString(receivePacket)); // classroom
                                System.out.println();
                            }
                        }
                        System.out.println(ClientMessage.READ_SUBJECT_END);
                        break;
                    case ClientMessage.INPUT_COMMAND_5:     // 교과목 강의계획서 목록 조회
                        System.out.println(ClientMessage.READ_SUBJECT_PLAN); // 강의계획서 목록조회
                        protocol.getProfessorRequestPacket(command, Message.NULL_HEADER);
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);
                        if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_SUBJECT_PLAN_LIST) {
                            dis.read(receivePacket); // 교과목 튜플 개수 읽기
                            int len = byteArrayToInt(receivePacket);

                            System.out.printf("|%-20s|%-100s \n", "subject_name", "subject_plan");

                            for (int i = 0; i < len; i++) {
                                dis.read(receivePacket);
                                System.out.printf("%-20s|", changeByteToString(receivePacket)); // subject_name

                                dis.read(receivePacket);
                                System.out.printf("%-100s|", changeByteToString(receivePacket)); // subject_plan

                                System.out.println();
                            }
                            System.out.println(ClientMessage.READ_SUBJECT_PLAN_END);
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_6:     // 교과목 수강신청 학생 목록 조회
                        System.out.println(ClientMessage.READ_STUDENT_ON_SUBJECT); // 교과목 수강신청 학생 목록 조회
                        protocol.getProfessorRequestPacket(Message.TYPE_SUBJECT_CLASS_STUDENT, Message.NULL_HEADER);

                        System.out.print(ClientMessage.READ_STUDENT_ON_SUBJECT_ID_REQ);
                        int subjectId = sc.nextInt(); // 교과목 id 입력
                        protocol.setIntToByte(subjectId);

                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);

                        if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_ENROLL_STUDENT) {
                            dis.read(receivePacket); // 입력한 교과목의 수강신청학생 튜플 개수 읽기
                            int len = byteArrayToInt(receivePacket);
                            System.out.printf("%-10s|%-20s|%-10s|%-20s|%-20s|%-10s \n","id","name","birth","phoneNumber","major","grade");
                            for(int i = 0; i < len; i++){
                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-20s|", changeByteToString(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToString(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-20s|", changeByteToString(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-20s|", changeByteToString(receivePacket));
                                dis.read(receivePacket);
                                System.out.println(changeByteToInt(receivePacket));
                            }
                            System.out.println(ClientMessage.READ_STUDENT_ON_SUBJECT_END);
                        } else if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_SUBJECT_ID_NOT_EXIST) {
                            System.out.println(ClientMessage.READ_STUDENT_ON_SUBJECT_ID_ERROR);
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_7:     // 교과목 시간표 조회
                        System.out.println(ClientMessage.READ_TIME_TABLE); // 교과목 시간표 조회
                        protocol.getProfessorRequestPacket(Message.TYPE_FIND_SUBJECT_TIME, Message.NULL_HEADER);

                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);

                        if (headerReader == ClientMessage.PROTOCOL_PROFESSOR_SUBJECT_TIMETABLE) {
                            dis.read(receivePacket);
                            int len = byteArrayToInt(receivePacket); // 담당교과목 튜플 개수 읽기

                            System.out.printf("|%-20s|%-10s|%-10s", "subject_name", "day", "time"); // classromm 어디?
                            System.out.println();

                            for (int i = 0; i < len; i++) {
                                dis.read(receivePacket);
                                System.out.printf("%-20s|", changeByteToString(receivePacket)); // subject_name

                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToString(receivePacket)); // day

                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToString(receivePacket)); // time
                                System.out.println();
                            }
                            System.out.println(ClientMessage.READ_TIME_TABLE_END);
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_8:
                        System.out.println(ClientMessage.PROGRAM_EXIT);
                        return;
                    default:
                        System.out.println(ClientMessage.INPUT_ERROR_NUMBER);
                        break;
                }
            }
        }catch (IOException ioe){
            System.out.println("error : " + ioe.getMessage());
        }
    }

    public void studentMenu(){ // 학생 메뉴
        System.out.println(ClientMessage.LOGIN_STUDENT);
        Protocol protocol = null;
        byte[] receivePacket = new byte[4];
        int headerReader = 0;
        int codeType = 0;
        int subjectId = 0;
        int len = 0;
        try {
            while(true){
                System.out.print(ClientMessage.STU_MENU);
                command = sc.nextInt();
                protocol = new Protocol(Message.LOGIN_TYPE_STUDENT, Message.MESSAGE_TYPE_REQUEST, command);

                switch (command){
                    case ClientMessage.INPUT_COMMAND_1:
                        System.out.println(ClientMessage.UPDT_STU_INFO); // 개인정보 수정
                        System.out.print(ClientMessage.UPDT_STU_INFO_ITEM);
                        codeType = sc.nextInt();

                        switch (codeType) {
                            case ClientMessage.INPUT_COMMAND_1: // 비밀번호 수정
                                protocol.setStudentRequestPacket(command, codeType);

                                System.out.print(ClientMessage.UPDT_STU_INFO_NEW_PW_REQ);
                                protocol.setStrToByte(sc.next());
                                break;
                            case ClientMessage.INPUT_COMMAND_2: // 이름 수정
                                protocol.setStudentRequestPacket(command, codeType);

                                System.out.print(ClientMessage.UPDT_STU_INFO_NEW_NAME_REQ);
                                protocol.setStrToByte(sc.next());
                                break;
                            case ClientMessage.INPUT_COMMAND_3: //생년월일 수정
                                protocol.setStudentRequestPacket(command, codeType);

                                System.out.print(ClientMessage.UPDT_STU_INFO_NEW_BIRTH_REQ);
                                protocol.setStrToByte(sc.next());
                                break;
                            case ClientMessage.INPUT_COMMAND_4: // 휴대전화번호 수정
                                protocol.setStudentRequestPacket(command, codeType);

                                System.out.print(ClientMessage.UPDT_STU_INFO_NEW_PHONE_REQ);
                                protocol.setStrToByte(sc.next());
                                break;
                            case ClientMessage.INPUT_COMMAND_5: // 전공 수정
                                protocol.setStudentRequestPacket(command, codeType);

                                System.out.print(ClientMessage.UPDT_STU_INFO_NEW_MAJOR_REQ);
                                protocol.setStrToByte(sc.next());
                                break;
                            case ClientMessage.INPUT_COMMAND_6: // 학년 수정
                                protocol.setStudentRequestPacket(command, codeType);

                                System.out.print(ClientMessage.UPDT_STU_INFO_NEW_GRADE_REQ);
                                protocol.setIntToByte(sc.nextInt());
                                break;
                            default:
                                System.out.println(ClientMessage.INPUT_ERROR_NUMBER);
                                break;
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);

                        if (headerReader == ClientMessage.PROTOCOL_STUDENT_UPDATE_INFO_SUC) {    // 개인정보 수정 성공
                            System.out.println(ClientMessage.UPDT_STU_INFO_SUC);
                        } else if(headerReader == ClientMessage.PROTOCOL_STUDENT_UPDATE_INFO_FAIL) {                        // 개인정보 수정 실패
                            System.out.println(ClientMessage.UPDT_STU_INFO_FAIL);
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_2:
                        System.out.println(ClientMessage.ENROLL_SUBJECT); // 수강 신청
                        System.out.print(ClientMessage.ENROLL_SUBJECT_ID_REQ); // id
                        int subject_id = sc.nextInt();

                        // 프로토콜 데이터 넣기
                        protocol.setRequestId(Message.TYPE_ENROLL_SUBJECT);
                        protocol.setIntToByte(subject_id);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        //프로토콜 읽기
                        while (true) {
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if (headerReader == ClientMessage.PROTOCOL_STUDENT_ENROLL_SUC) { // 수강 신청 성공
                                System.out.println(ClientMessage.ENROLL_SUBJECT_SUC);
                                break;
                            } else if (headerReader == ClientMessage.PROTOCOL_STUDENT_ENROLL_FAIL) { // 수강 신청 실패
                                System.out.println(ClientMessage.ENROLL_SUBJECT_FAIL);
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_3:
                        System.out.println(ClientMessage.CANCEL_SUBJECT); // 수강 취소
                        System.out.print(ClientMessage.CANCEL_SUBJECT_ID_REQ); // id
                        subject_id = sc.nextInt();

                        // 프로토콜 데이터 넣기
                        protocol.setRequestId(Message.TYPE_DROP_ENROLL_SUBJECT);
                        protocol.setIntToByte(subject_id);

                        // 전송
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        //프로토콜 읽기
                        while (true) {
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if (headerReader == ClientMessage.PROTOCOL_STUDENT_ENROLL_CANCEL_SUC) { // 수강 취소 성공
                                System.out.println(ClientMessage.CANCEL_SUBJECT_SUC);
                                break;
                            } else if (headerReader == ClientMessage.PROTOCOL_STUDENT_ENROLL_CANCEL_FAIL) { // 수강 취소 실패
                                System.out.println(ClientMessage.CANCEL_SUBJECT_FAIL);
                                break;
                            }
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_4:
                        System.out.println(ClientMessage.READ_STU_OPEN_SUBJECT); // 개설 교과목 조회
                        System.out.print(ClientMessage.READ_STU_OPEN_SUBJECT_MENU);
                        codeType = sc.nextInt();
                        switch (codeType) {
                            case ClientMessage.INPUT_COMMAND_1:     // 학년 입력
                                protocol.setStudentRequestPacket(Message.TYPE_FIND_SUBJECTALL, codeType);

                                System.out.print(ClientMessage.READ_STU_OPEN_SUBJECT_GRADE_ERQ);
                                protocol.setIntToByte(sc.nextInt());
                                break;
                            case ClientMessage.INPUT_COMMAND_2:     // 교수 입력
                                protocol.setStudentRequestPacket(Message.TYPE_FIND_SUBJECTALL, codeType);

                                System.out.print(ClientMessage.READ_STU_OPEN_SUBJECT_PROFESSOR_ERQ);
                                protocol.setStrToByte(sc.next());
                                break;
                            case ClientMessage.INPUT_COMMAND_3:     // 학년, 교수 입력
                                protocol.setStudentRequestPacket(Message.TYPE_FIND_SUBJECTALL, codeType);

                                System.out.print(ClientMessage.READ_STU_OPEN_SUBJECT_GRADE_ERQ);
                                protocol.setIntToByte(sc.nextInt());

                                System.out.print(ClientMessage.READ_STU_OPEN_SUBJECT_PROFESSOR_ERQ);
                                protocol.setStrToByte(sc.next());
                                break;
                            case ClientMessage.INPUT_COMMAND_4:     // 전체 목록 출력
                                protocol.setStudentRequestPacket(Message.TYPE_FIND_SUBJECTALL, codeType);
                                break;
                        }
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);
                        if (headerReader == ClientMessage.PROTOCOL_STUDENT_OPEN_SUBJECT_LIST) {    // 1, 2, 3, 4번 전부 동일함
                            dis.read(receivePacket);
                            len = byteArrayToInt(receivePacket); // 교과목 튜플 개수 읽기

                            System.out.printf("%-10s|%-20s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s",
                                    "subject_id","subject_name","possible_grade","number_people", "max_people", "credit", "day", "time", "classroom", "professor_id");
                            System.out.println();
                            for(int i = 0; i < len; i++) { // 출력
                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-20s|", changeByteToString(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToInt(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToString(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToString(receivePacket));
                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToString(receivePacket));
                                dis.read(receivePacket);
                                System.out.println(changeByteToInt(receivePacket));
                            }

                        }
                        System.out.println(ClientMessage.READ_STU_OPEN_SUBJECT_END); // 개설 교과목 조회 끝
                        break;
                    case ClientMessage.INPUT_COMMAND_5:
                        System.out.println(ClientMessage.STU_READ_SUBJECT_PLAN); // 선택 강의계획서 조회
                        protocol.setStudentRequestPacket(Message.TYPE_FIND_SELECT_SUBJECT_PLAN, Message.NULL_HEADER);

                        System.out.print(ClientMessage.STU_READ_SUBJECT_PLAN_ID_REQ);
                        subjectId = sc.nextInt(); // 교과목 id 입력
                        protocol.setIntToByte(subjectId);
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        while (true){
                            dis.read(receivePacket);
                            headerReader = readHeader(receivePacket);
                            if (headerReader == ClientMessage.PROTOCOL_STUDENT_SELECT_SUBJECT_PLAN_LIST) { // 선택 강의계획서 출력
                                dis.read(receivePacket);
                                System.out.printf("%-20s : %-20s", "subject_name" ,changeByteToString(receivePacket)); // subject_name
                                System.out.println();
                                dis.read(receivePacket);
                                System.out.printf("%-20s : %-100s", "subject_plan" ,changeByteToString(receivePacket)); // subject_plan
                                System.out.println();
                                break;
                            }else if(headerReader == ClientMessage.PROTOCOL_STUDENT_SELECT_SUBJECT_PLAN_FAIL){
                                System.out.println(ClientMessage.STU_READ_SUBJECT_PLAN_ID_ERROR);
                                break;
                            }
                        }

                        break;
                    case ClientMessage.INPUT_COMMAND_6:
                        System.out.println(ClientMessage.READ_STU_TIME_TABLE); // 본인 시간표 조회
                        protocol.setStudentRequestPacket(Message.TYPE_SUBJECT_CLASS_STUDENT, Message.NULL_HEADER);
                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                        dos.flush();

                        dis.read(receivePacket);
                        headerReader = readHeader(receivePacket);

                        if (headerReader == ClientMessage.PROTOCOL_STUDENT_MY_TIMETABLE) {
                            dis.read(receivePacket); // 교과목 튜플 개수 읽기
                            len = byteArrayToInt(receivePacket);

                            System.out.printf("%-20s|%-10s|%-10s|%-10s", "subject_name", "day", "time", "classroom");
                            System.out.println();

                            for (int i = 0; i < len; i++) { // 출력
                                dis.read(receivePacket);
                                System.out.printf("%-20s|", changeByteToString(receivePacket)); // subject_name

                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToString(receivePacket)); // day

                                dis.read(receivePacket);
                                System.out.printf("%-10s|", changeByteToString(receivePacket)); // time

                                dis.read(receivePacket);
                                System.out.printf("%-10s", changeByteToString(receivePacket)); // classroom
                                System.out.println();
                            }
                            System.out.println(ClientMessage.READ_STU_TIME_TABLE_END);
                        }
                        break;
                    case ClientMessage.INPUT_COMMAND_7:
                        System.out.println(ClientMessage.PROGRAM_EXIT);
                        return;
                    default:
                        System.out.println(ClientMessage.INPUT_ERROR_NUMBER);
                        break;
                }
            }
        }catch (Exception ioe){
            System.out.println("error : " + ioe.getMessage());
        }
    }

    public static void main(String[] args) {
        EnrollClient enrollClient = null;
        if (args.length != 2)
            System.out.println(ClientMessage.MAIN_USAGE_MESSAGE);
        else{
            enrollClient = new EnrollClient(args[0], Integer.parseInt(args[1]));
            enrollClient.clientMenu();
        }
    }
}