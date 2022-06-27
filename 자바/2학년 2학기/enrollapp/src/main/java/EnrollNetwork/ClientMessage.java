package EnrollNetwork;

public class ClientMessage {
    //프로토콜 번호
    public final static int PROTOCOL_LOGIN_RESPONSE = 1000;
    public final static int PROTOCOL_LOGIN_SUC = 3010;
    public final static int PROTOCOL_LOGIN_FAIL = 3020;

    //관리자 프로토콜
    public final static int PROTOCOL_ADMIN_INSERT_PRO_ID_EXIST = 12011;
    public final static int PROTOCOL_ADMIN_INSERT_PRO = 12012;
    public final static int PROTOCOL_ADMIN_INSERT_SUC = 12013;
    public final static int PROTOCOL_ADMIN_INSERT_FAIL = 12014;

    public final static int PROTOCOL_ADMIN_INSERT_STU_ID_EXIST = 12021;
    public final static int PROTOCOL_ADMIN_INSERT_STU = 12022;
    public final static int PROTOCOL_ADMIN_INSERT_STU_SUC = 12023;
    public final static int PROTOCOL_ADMIN_INSERT_STU_FAIL = 12024;

    public final static int PROTOCOL_ADMIN_INSERT_NOT_OPEN_SUB_ID_EXIST = 12031;
    public final static int PROTOCOL_ADMIN_INSERT_NOT_OPEN_SUB = 12032;
    public final static int PROTOCOL_ADMIN_INSERT_NOT_OPEN_SUB_SUC = 12033;
    public final static int PROTOCOL_ADMIN_INSERT_NOT_OPEN_SUB_FAIL = 12034;

    public final static int PROTOCOL_ADMIN_UPDT_NOT_OPEN_SUB = 12041;
    public final static int PROTOCOL_ADMIN_UPDT_NOT_OPEN_SUB_NOT_EXIST = 12042;
    public final static int PROTOCOL_ADMIN_UPDT_NOT_OPEN_SUB_SUC = 12043;
    public final static int PROTOCOL_ADMIN_UPDT_NOT_OPEN_SUB_FAIL = 12044;

    public final static int PROTOCOL_ADMIN_DEL_NOT_OPEN_SUB_SUC = 12051;
    public final static int PROTOCOL_ADMIN_DEL_NOT_OPEN_SUB_FAIL = 12052;

    public final static int PROTOCOL_ADMIN_UPDATE_PLAN_MODIFY_DATE = 12061;
    public final static int PROTOCOL_ADMIN_UPDATE_PLAN_MODIFY_DATE_ID_NOT_EXIST = 12062;
    public final static int PROTOCOL_ADMIN_UPDATE_PLAN_MODIFY_DATE_SUC = 12063;
    public final static int PROTOCOL_ADMIN_UPDATE_PLAN_MODIFY_DATE_FAIL = 12064;

    public final static int PROTOCOL_ADMIN_UPDATE_ENROLL_TERM_SUC = 12071;
    public final static int PROTOCOL_ADMIN_UPDATE_ENROLL_TERM_FAIL = 12072;

    public final static int PROTOCOL_ADMIN_SELECT_PRO_ALL = 12080;

    public final static int PROTOCOL_ADMIN_SELECT_STU_ALL = 12090;

    public final static int PROTOCOL_ADMIN_SELECT_OPEN_SUB_ALL = 12100;

    public final static int PROTOCOL_ADMIN_INSERT_OPEN_SUB = 12111;
    public final static int PROTOCOL_ADMIN_INSERT_OPEN_SUB_ID_NOT_EXIST = 12112;
    public final static int PROTOCOL_ADMIN_INSERT_OPEN_SUB_SUC = 12113;
    public final static int PROTOCOL_ADMIN_INSERT_OPEN_SUB_FAIL = 12114;

    public final static int PROTOCOL_ADMIN_UPDATE_OPEN_SUB = 12121;
    public final static int PROTOCOL_ADMIN_UPDATE_OPEN_SUB_ID_NOT_EXIST = 12122;
    public final static int PROTOCOL_ADMIN_UPDATE_OPEN_SUB_SUC = 12123;
    public final static int PROTOCOL_ADMIN_UPDATE_OPEN_SUB_FAIL = 12124;

    public final static int PROTOCOL_ADMIN_DEL_OPEN_SUB_SUC = 12131;
    public final static int PROTOCOL_ADMIN_DEL_OPEN_SUB_FAIL = 12132;

    public final static int PROTOCOL_PROFESSOR_UPDATE_INFO_SUC = 22011;
    public final static int PROTOCOL_PROFESSOR_UPDATE_INFO_FAIL = 22012;

    //교수 프로토콜
    public final static int PROTOCOL_PROFESSOR_INSERT_ID_EXIST = 22021;
    public final static int PROTOCOL_PROFESSOR_INSERT = 22022;
    public final static int PROTOCOL_PROFESSOR_INSERT_SUC = 22023;
    public final static int PROTOCOL_PROFESSOR_INSERT_FAIL = 22024;

    public final static int PROTOCOL_PROFESSOR_SUBJECT_PLAN_UPDATE_ID_EXIST = 22031;
    public final static int PROTOCOL_PROFESSOR_SUBJECT_PLAN_UPDATE = 22032;
    public final static int PROTOCOL_PROFESSOR_SUBJECT_PLAN_UPDATE_SUC = 22033;
    public final static int PROTOCOL_PROFESSOR_SUBJECT_PLAN_UPDATE_FAIL = 22034;

    public final static int PROTOCOL_PROFESSOR_SUBJECT_LIST = 22040;

    public final static int PROTOCOL_PROFESSOR_SUBJECT_PLAN_LIST = 22050;

    public final static int PROTOCOL_PROFESSOR_ENROLL_STUDENT = 22060;

    public final static int PROTOCOL_PROFESSOR_SUBJECT_ID_NOT_EXIST = 22062;

    public final static int PROTOCOL_PROFESSOR_SUBJECT_TIMETABLE = 22070;

    //학생 프로토콜
    public final static int PROTOCOL_STUDENT_UPDATE_INFO_SUC = 32011;
    public final static int PROTOCOL_STUDENT_UPDATE_INFO_FAIL = 32012;

    public final static int PROTOCOL_STUDENT_ENROLL_SUC = 32021;
    public final static int PROTOCOL_STUDENT_ENROLL_FAIL = 32022;

    public final static int PROTOCOL_STUDENT_ENROLL_CANCEL_SUC = 32031;
    public final static int PROTOCOL_STUDENT_ENROLL_CANCEL_FAIL = 32032;

    public final static int PROTOCOL_STUDENT_OPEN_SUBJECT_LIST = 32040;

    public final static int PROTOCOL_STUDENT_SELECT_SUBJECT_PLAN_LIST = 32051;
    public final static int PROTOCOL_STUDENT_SELECT_SUBJECT_PLAN_FAIL = 32052;

    public final static int PROTOCOL_STUDENT_MY_TIMETABLE = 32060;

    //메뉴
    final static String MENU_INPUT = "번호 : ";
    public final static int INPUT_COMMAND_1 = 1;
    public final static int INPUT_COMMAND_2 = 2;
    public final static int INPUT_COMMAND_3 = 3;
    public final static int INPUT_COMMAND_4 = 4;
    public final static int INPUT_COMMAND_5 = 5;
    public final static int INPUT_COMMAND_6 = 6;
    public final static int INPUT_COMMAND_7 = 7;
    public final static int INPUT_COMMAND_8 = 8;
    public final static int INPUT_COMMAND_9 = 9;
    public final static int INPUT_COMMAND_10 = 10;
    public final static int INPUT_COMMAND_11 = 11;
    public final static int INPUT_COMMAND_12 = 12;
    public final static int INPUT_COMMAND_13 = 13;
    public final static int INPUT_COMMAND_14 = 14;

    //main
    final static String MAIN_USAGE_MESSAGE = "Usage: host_ip port_number";
    final static String INPUT_ERROR_NUMBER = "잘못된 번호 입력";
    final static String SOCKET_EXCEPTION_ERROR = "Connection close prematurely";
    final static String CONNECTED = "Connected";
    final static String UNKNOWN_HOST_EXCEPTION = "UnknownHostException";
    final static String IO_EXCEPTION = "IOException";
    final static String PROGRAM_EXIT = "-----프로그램 종료-----";


    //0x00 로그인
    final static String LOGIN_REQ = "*---로그인---*";
    final static String LOGIN_ID_REQ = "ID : ";
    final static String LOGIN_PW_REQ = "PW : ";
    final static String LOGIN_SUC = "로그인 성공";
    final static String LOGIN_FAIL = "로그인 실패";

    final static String LOGIN_ADMIN = "관리자";
    final static String LOGIN_PROFESSOR = "교수";
    final static String LOGIN_STUDENT = "학생";

    //0x01 관리자
    //기본 메뉴
    final static String ADMIN_MENU = "*---관리자 메뉴---*\n" +
            "[1]교수계정 생성\t[2]학생계정 생성\t[3]비개설교과목 생성\n" +
            "[4]비개설교과목 정보수정\t[5]비개설교과목 삭제\t[6]강의계획서 입력기간 설정\n" +
            "[7]학년별 수강신청 기간 설정\t[8]교수 정보 조회\t[9]학생 정보 조회\n" +
            "[10]교과목 정보 조회\t[11]개설 교과목 생성\t[12]개설 교과목 수정\n" +
            "[13]개설 교과목 폐강\t[14]프로그램 종료\ncommand : ";

    //교수 계정 생성
    final static String CRT_PRO_ACC = "*---교수 계정 생성---*";
    final static String CRT_PRO_ACC_ID_REQ = "교수 아이디 : ";
    final static String CRT_PRO_ACC_ID_ERROR = "이미 존재하는 ID입니다";

    final static String CRT_PRO_ACC_NAME_REQ = "교수 이름 : ";
    final static String CRT_PRO_ACC_MAJOR_REQ = "교수 전공 : ";
    final static String CRT_PRO_ACC_BIRTH_REQ = "생년월일(yyyy-mm-dd) : ";
    final static String CRT_PRO_ACC_PHONE_REQ = "휴대전화 번호 : ";
    final static String CRT_PRO_ACC_SUC = "교수 계정 생성완료";
    final static String CRT_PRO_ACC_FAIL = "교수 계정 생성 실패";

    //학생 계정 생성
    final static String CRT_STU_ACC = "*---학생 계정 생성---*";
    final static String CRT_STU_ACC_ID_REQ = "학생 아이디 : ";
    final static String CRT_STU_ACC_ID_ERROR = "이미 존재하는 ID입니다";
    final static String CRT_STU_ACC_NAME_REQ = "학생 이름 : ";
    final static String CRT_STU_ACC_MAJOR_REQ = "학생 전공 : ";
    final static String CRT_STU_ACC_BIRTH_REQ = "생년월일(yyyy-mm-dd) : ";
    final static String CRT_STU_ACC_PHONE_REQ = "휴대전화 번호 : ";
    final static String CRT_STU_ACC_GRADE_REQ = "학년 : ";
    final static String CRT_STU_ACC_SUC = "학생 계정 생성완료";
    final static String CRT_STU_ACC_FAIL = "학생 계정 생성 실패";

    //비개설 교과목 생성
    final static String CRT_NOT_OPEN_SUB = "*---비개설 교과목 생성---*";
    final static String CRT_NOT_OPEN_SUB_ID_REQ = "교과목 ID 입력 : ";
    final static String CRT_NOT_OPEN_SUB_ID_ERROR = "존재하는 ID입니다.";
    final static String CRT_NOT_OPEN_SUB_NAME_REQ = "교과목 이름 : ";
    final static String CRT_NOT_OPEN_SUB_GRADE_REQ = "수강가능 학년 : ";
    final static String CRT_NOT_OPEN_SUB_SUC = "교과목 생성 성공";
    final static String CRT_NOT_OPEN_SUB_FAIL = "교과목 생성 실패";

    //비개설 교과목 정보 수정
    final static String UPDT_NOT_OPEN_SUB = "*---비개설 교과목 수정---*";
    final static String UPDT_NOT_OPEN_SUB_ID_REQ = "교과목 ID : ";
    final static String UPDT_NOT_OPEN_SUB_ID_ERROR = "존재하지 않는 ID입니다";
    final static String UPDT_NOT_OPEN_SUB_ITEM = "수정할 항목 선택 \n이름 : 1\n수강신청 학년 : 2";
    final static String UPDT_NOT_OPEN_SUB_NAME_REQ = "수정할 이름 입력 : ";
    final static String UPDT_NOT_OPEN_SUB_GRADE_REQ = "수정할 학년 입력 : ";
    final static String UPDT_NOT_OPEN_SUB_INPUT_FAIL = "잘못된 번호 입력";
    final static String UPDT_NOT_OPEN_SUB_SUC = "수정 성공";
    final static String UPDT_NOT_OPEN_SUB_FAIL = "수정 실패";

    //비개설 교과목 삭제
    final static String DEL_NOT_OPEN_SUB = "*---비개설 교과목 삭제---*";
    final static String DEL_NOT_OPEN_SUB_ID_REQ = "교과목 ID : ";
    final static String DEL_NOT_OPEN_SUB_SUC = "삭제 성공";
    final static String DEL_NOT_OPEN_SUB_FAIL = "삭제 실패";

    //강의 계획서 입력기간 설정
    final static String INSERT_PLAN_MODIFY = "*---강의 계획서 입력 기간 설정---*";
    final static String INSERT_PLAN_MODIFY_ID_REQ = "교과목 ID입력 : ";
    final static String INSERT_PLAN_MODIFY_ID_ERROR = "존재하지 않는 ID입니다";
    final static String INSERT_PLAN_MODIFY_DATE_REQ = "입력기간 입력 : ";
    final static String INSERT_PLAN_MODIFY_SUC = "입력 성공";
    final static String INSERT_PLAN_MODIFY_FAIL = "입력 실패";

    //학년별 수강신청 기간 설정
    final static String INSERT_GRADE_SUBJECT_DATE = "*---학년별 수강신청 기간 설정---*";
    final static String INSERT_GRADE_SUBJECT_DATE_GRADE_REQ = "학년 입력 : ";
    final static String INSERT_GRADE_SUBJECT_DATE_START_REQ = "시작 날짜 입력 : ";
    final static String INSERT_GRADE_SUBJECT_DATE_ENR_REQ = "끝 날짜 입력 : ";
    final static String INSERT_GRADE_SUBJECT_DATE_SUC = "입력 성공";
    final static String INSERT_GRADE_SUBJECT_DATE_FAIL = "입력 실패";

    //교수 정보 조회
    final static String READ_PROFESSOR = "*---교수 정보 조회---*";
    final static String READ_PROFESSOR_END = "----여기까지 입니다----";

    //학생 정보 조회
    final static String READ_STUDENT = "*---학생 정보 조회---*";
    final static String READ_STUDENT_END = "----여기까지 입니다----";

    //교과목 정보 조회
    final static String READ_ALL_SUBJECT = "*---교과목 정보 조회---*";
    final static String READ_ALL_SUBJECT_END = "----여기까지 입니다----";

    //개설 교과목 개설
    final static String CRT_OPEN_SUB = "*---개설 교과목 생성---*";
    final static String CRT_OPEN_SUB_ID_REQ = "교과목 ID 입력 : ";
    final static String CRT_OPEN_SUB_ID_ERROR = "존재하지않는 ID입니다.";

    final static String CRT_OPEN_SUB_MAJOR_REQ = "교과목 학점 : ";
    final static String CRT_OPEN_SUB_DAY_REQ = "교과목 요일 : ";
    final static String CRT_OPEN_SUB_CLASSROOM_REQ = "교과목 강의실 : ";
    final static String CRT_OPEN_SUB_MAX_NUMBER_REQ = "교과목 수강가능최대인원 : ";
    final static String CRT_OPEN_TIME_REQ = "교과목 시간 : ";
    final static String CRT_OPEN_SUB_PRO_ID = "교과목 담당교수 id : ";
    final static String CRT_OPEN_SUB_SUC = "교과목 개설 성공";
    final static String CRT_OPEN_SUB_FAIL = "교과목 개성 실패";

    //개설 교과목 정보 수정
    final static String UPDT_OPEN_SUB = "*---개설 교과목 수정---*";
    final static String UPDT_OPEN_SUB_ID_REQ = "교과목 ID : ";
    final static String UPDT_OPEN_SUB_ID_ERROR = "존재하지 않는 ID입니다";
    final static String UPDT_OPEN_SUB_ITEM = "수정할 항목 선택 \n최대수강인원: 1\n학점 : 2\n요일 : 3\n시간 : 4\n교실 : 5\n번호 입력 : ";
    final static String UPDT_OPEN_SUB_DAY_REQ = "수정할 요일 입력 : ";
    final static String UPDT_OPEN_SUB_TIME_REQ = "수정할 시간 입력 : ";
    final static String UPDT_OPEN_SUB_CLASSROOM_REQ = "수정할 강의실 입력 : ";
    final static String UPDT_OPEN_SUB_MAXPEOPLE_REQ = "수정할 수강가능 최대인원 입력 : ";
    final static String UPDT_OPEN_SUB_CREDIT = "수정할 학점 입력 : ";
    final static String UPDT_OPEN_SUB_INPUT_FAIL = "잘못된 번호 입력";
    final static String UPDT_OPEN_SUB_SUC = "수정 성공";
    final static String UPDT_OPEN_SUB_FAIL = "수정 실패";

    //개설 교과목 폐강
    final static String DEL_OPEN_SUB = "*---개설 교과목 폐강---*";
    final static String DEL_OPEN_SUB_ID_REQ = "교과목 ID : ";
    final static String DEL_OPEN_SUB_SUC = "폐강 성공";
    final static String DEL_OPEN_SUB_FAIL = "폐강 실패";


    //0x02 교수
    //기본 메뉴
    final static String PRO_MENU = "*---교수 메뉴---*\n[1]개인정보 및 비밀번호 수정\t[2]강의계획서 입력\t[3]강의계획서 수정\n" +
            "[4]교과목 목록 조회 \t[5]교과목 강의계획서 조회\t[6]교과목 수강신청 학생 목록 조회\n" +
            "[7]교과목 시간표 조회\t[8]프로그램 종료\ncommand : ";
    //0x01 개인정보 및 비밀번호 수정
    final static String UPDT_PRO_INFO = "*---개인정보 및 비밀번호 수정---*";
    final static String UPDT_PRO_INFO_ITEM = "수정할 항목 선택 \n비밀번호: 1\n이름 : 2\n생년월일 : 3\n휴대전화번호 : 4\n전공 : 5";
    final static String UPDT_PRO_INFO_NEW_NAME_REQ = "수정할 이름 입력 : ";
    final static String UPDT_PRO_INFO_NEW_PW_REQ = "수정할 비밀번호 입력 : ";
    final static String UPDT_PRO_INFO_NEW_BIRTH_REQ = "수정할 생년월일 입력(yyyy-mm-dd) : ";
    final static String UPDT_PRO_INFO_NEW_PHONE_REQ = "수정할 휴대전화 번호 입력 : ";
    final static String UPDT_PRO_INFO_NEW_MAJOR_REQ = "수정할 전공 입력 : ";
    final static String UPDT_PRO_INFO_SUC = "수정되었습니다";
    final static String UPDT_PRO_INFO_FAIL = "수정실패하였습니다";

    //0x02 강의계획서 입력
    final static String INSERT_SUBJECT_PLAN = "*---강의계획서 입력---*";
    final static String INSERT_SUBJECT_PLAN_SUBJECT_ID_REQ = "교과목 ID : ";
    final static String INSERT_SUBJECT_PLAN_SUBJECT_ID_ERROR = "잘못된 교과목 ID입니다";
    final static String INSERT_SUBJECT_PLAN_NEW_PLAN_REQ = "강의계획서 입력 : ";
    final static String INSERT_SUBJECT_PLAN_SUC = "강의계획서 수정에 성공했습니다.";
    final static String INSERT_SUBJECT_PLAN_FAIL = "강의계힉서 수정에 실패했습니다";

    //0x03 강의계획서 수정
    final static String UPDT_SUBJECT_PLAN = "*---강의계획서 수정---*";
    final static String UPDT_SUBJECT_PLAN_SUBJECT_ID_REQ = "교과목 ID : ";
    final static String UPDT_SUBJECT_SUBJECT_PLAN = "강의계획서 입력 : ";
    final static String UPDT_SUBJECT_PLAN_SUBJECT_ID_ERROR = "잘못된 교과목 ID입니다";
    final static String UPDT_SUBJECT_PLAN_SUC = "강의계획서 수정에 성공했습니다.";
    final static String UPDT_SUBJECT_PLAN_FAIL = "강의계힉서 수정에 실패했습니다";

    //0x04 교과목 목록 조회
    final static String READ_SUBJECT = "*---교과목 목록 조회---*";
    final static String READ_SUBJECT_END = "----여기까지 입니다----";

    //0x05 교과목 강의계획서 조회
    final static String READ_SUBJECT_PLAN = "*---교과목 강의계획서 조회---*";
    final static String READ_SUBJECT_PLAN_END = "----여기까지 입니다----";


    //0x06 교과목 수강신청 학생 목록 조회
    final static String READ_STUDENT_ON_SUBJECT = "*---교과목 수강신청 학생 목록 조회---*";
    final static String READ_STUDENT_ON_SUBJECT_ID_REQ = "교과목 ID : ";
    final static String READ_STUDENT_ON_SUBJECT_ID_ERROR = "잘못된 교과목 ID입니다";
    final static String READ_STUDENT_ON_SUBJECT_END = "----여기까지 입니다----";

    //0x07 교과목 시간표 조회
    final static String READ_TIME_TABLE = "*---교과목 시간표 조회---*";
    final static String READ_TIME_TABLE_END = "----여기까지 입니다----";

    //0x03 학생
    //기본 메뉴
    final static String STU_MENU = "*---학생 메뉴---*\n[1]개인정보 및 비밀번호 수정\t[2]수강신청\t[3]수강신청 취소\n" +
            "[4]개설교과목 조회\t[5]선택교과목 강의계획서 조회\t[6]본인 시간표 조회\t[7]프로그램 종료\ncommand : ";

    //0x01 개인정보 및 비밀번호 수정
    final static String UPDT_STU_INFO = "*---개인정보 및 비밀번호 수정---*";
    final static String UPDT_STU_INFO_ITEM = "수정할 항목 선택 \n비밀번호: 1\n이름 : 2\n생년월일 : 3\n휴대전화번호 : 4\n전공 : 5\n학년 : 6\ncommand : ";
    final static String UPDT_STU_INFO_NEW_NAME_REQ = "수정할 이름 입력 : ";
    final static String UPDT_STU_INFO_NEW_PW_REQ = "수정할 비밀번호 입력 : ";
    final static String UPDT_STU_INFO_NEW_BIRTH_REQ = "수정할 생년월일 입력(yyyy-mm-dd) : ";
    final static String UPDT_STU_INFO_NEW_PHONE_REQ = "수정할 휴대전화 번호 입력 : ";
    final static String UPDT_STU_INFO_NEW_MAJOR_REQ = "수정할 전공 입력 : ";
    final static String UPDT_STU_INFO_NEW_GRADE_REQ = "수정할 학년 입력 : ";
    final static String UPDT_STU_INFO_SUC = "수정되었습니다";
    final static String UPDT_STU_INFO_FAIL = "수정실패하였습니다";
    //0x02 수강 신청
    final static String ENROLL_SUBJECT = "*---수강신청---*";
    final static String ENROLL_SUBJECT_ID_REQ = "교과목 ID : ";
    final static String ENROLL_SUBJECT_SUC = "수강신청에 성공하였습니다";
    final static String ENROLL_SUBJECT_FAIL = "수강신청에 실패하였습니다";

    //0x03 수강 취소
    final static String CANCEL_SUBJECT = "*---수강신청 취소---*";
    final static String CANCEL_SUBJECT_ID_REQ = "교과목 ID : ";
    final static String CANCEL_SUBJECT_SUC = "수강취소에 성공하였습니다";
    final static String CANCEL_SUBJECT_FAIL = "수강취소에 실패하였습니다";

    //0x04 개설교과목 조회
    final static String READ_STU_OPEN_SUBJECT = "*---개설교과목 조회---*";
    final static String READ_STU_OPEN_SUBJECT_MENU = "학년 입력 : 1\n교수 이름 입력 : 2\n학년, 교수 이름 입력 : 3\n전체 : 4\ncommand : ";
    final static String READ_STU_OPEN_SUBJECT_GRADE_ERQ = "학년 입력 :" ;
    final static String READ_STU_OPEN_SUBJECT_PROFESSOR_ERQ = "교수 입력 :" ;
    final static String READ_STU_OPEN_SUBJECT_END = "----여기까지 입니다----";

    //0x05 선택교과목 강의계획서 조회
    final static String STU_READ_SUBJECT_PLAN = "*---선택교과목 강의계획서 조회---*";
    final static String STU_READ_SUBJECT_PLAN_ID_REQ = "교과목 ID : ";
    final static String STU_READ_SUBJECT_PLAN_ID_ERROR = "잘못된 교과목 ID입니다";

    //0x06 본인 시간표 조회
    final static String READ_STU_TIME_TABLE = "*---본인 시간표 조회---*";
    final static String READ_STU_TIME_TABLE_END = "----여기까지 입니다----";
}