package EnrollNetwork;

public class Message {
    public static int FIXED_LEN = 4;                       // 키보드로부터 입력받은 데이터 길이를 저장할 변수 크기
    public static int INTEGER_LEN = 4;                     // int형을 바이트 배열로 변환한 크기
    public static int BOOL_LEN = 1;                        // boolean형의 크기
    public static int NULL_HEADER = 0;                     // 헤더의 요소 중 값이 NULL인 경우

    public static final int TYPE_UNDEFINED = -1;           // 프로토콜이 지정되어 있지 않은 경우

    public static final int LOGIN_TYPE_LOGIN = 0;          // 로그인
    public static final int LOGIN_TYPE_ADMIN = 1;          // 관리자
    public static final int LOGIN_TYPE_PROFESSOR = 2;      // 교수
    public static final int LOGIN_TYPE_STUDENT = 3;        // 학생

    public static final int MESSAGE_TYPE_REQUEST = 1;      // 요청
    public static final int MESSAGE_TYPE_RESPONSE = 2;     // 응답
    public static final int MESSAGE_TYPE_RESULT = 3;       // 결과

    public static final int LEN_PROTOCOL_TYPE = 4;                  // 프로토콜 타입 길이

    // User 데이터 길이
    public static final int LEN_ID = 4;                             // ID 길이(int)
    public static final int LEN_PW = 20;                            // PW 길이(String)
    public static final int LEN_GROUP_ID = 4;                       // Group id 길이(int)
    public static final int LEN_NAME = 20;                          // 이름 길이(String)
    public static final int LEN_BIRTH_DAY = 10;                     // 생년월일 길이(Date)
    public static final int LEN_PHONE_NUM = 20;                     // 휴대폰 번호 길이(String)

    public static final int LEN_USER_ALL_DATA = 74;                 // User에서 ID를 뺀 나머지 길이의 총 합

    // Professor 데이터 길이
    public static final int LEN_MAJOR = 30;                         // 전공 길이(String)

    // Student 데이터 길이
    public static final int LEN_GRADE = 4;                          // 학년 길이(int)

    // Subject 데이터 길이
    public static final int LEN_SUBJECT_ID = 4;                     // 과목코드 길이(int)
    public static final int LEN_SUBJECT_NAME = 20;                   // 과목 이름 길이(String)
    public static final int LEN_SUBJECT_IS_OPEN = 1;                // 오픈 여부 확인 길이(boolean)
    public static final int LEN_SUBJECT_GRADE = 4;                  // 이수 대상 학년 길이(int)
    public static final int LEN_SUBJECT_ALL_DATA = 17;              // Subject의 모든 데이터 길이의 총 합

    // 개설 교과목 데이터 길이
    public static final int LEN_MAX_PEOPLE = 4;                             // 최대 수강 인원 길이(int)
    public static final int LEN_NUMBER_PEOPLE = 4;                          // 현재 수강 인원 길이(int)
    public static final int LEN_SUBJECT_PLAN = 100;                         // 강의계획서 길이(String)
    public static final int LEN_SET_DAY = 10;                               // 강의 계획서 입력 기간 설정 길이(Date)
    public static final int LEN_SUBJECT_CREDIT = 4;                         // 학점 길이(int)
    public static final int LEN_SUBJECT_DAY = 10;                           // 강의 날짜 길이(String)
    public static final int LEN_SUBJECT_TIME = 10;                          // 강의 시간 길이(String)
    public static final int LEN_SUBJECT_CLASSROOM = 10;                     // 강의실 길이(String)
    public static final int LEN_SUBJECT_PROFESSOR_ID = 4;                   // 담당 교수 id 길이(int)
    public static final int LEN_SUBJECT_OPEN_ALL_DATA = 156;                // Subject_open에서 subject_id를 뺀 나머지 길이의 총 합


    // Admin
    public static final int FUNCTION_TYPE_MAKE_PROFESSOR_ACCOUNT = 1;        // 교수 계정 생성
    public static final int FUNCTION_TYPE_MAKE_STUDENT_ACCOUNT = 2;          // 학생 계정 생성
    public static final int FUNCTION_TYPE_MAKE_SUBJECT = 3;                  // 비개설 교과목 생성
    public static final int FUNCTION_TYPE_UPDATE_SUBJECT = 4;                // 비개설 교과목 수정
    public static final int FUNCTION_TYPE_DELETE_SUBJECT = 5;                // 비개설 교과목 삭제
    public static final int FUNCTION_TYPE_SET_SUBJECT_PLAN = 6;              // 강의 계획서 입력 기간 설정
    public static final int FUNCTION_TYPE_SET_ENROLL_TIME = 7;               // 학년별 수강 신청 기간 설정
    public static final int FUNCTION_TYPE_FIND_PROFESSOR = 8;                // 교수 정보 조회
    public static final int FUNCTION_TYPE_FIND_STUDENT = 9;                  // 학생 정보 조회
    public static final int FUNCTION_TYPE_FIND_OPEN_SUBJECT = 10;            // 개설 교과목 정보 조회
    public static final int FUNCTION_TYPE_MAKE_OPEN_SUBJECT = 11;            // 개설 교과목 정보 생성
    public static final int FUNCTION_TYPE_UPDATE_OPEN_SUBJECT = 12;            // 개설 교과목 정보 수정
    public static final int FUNCTION_TYPE_DELETE_OPEN_SUBJECT = 13;            // 개설 교과목 폐강

    // Professor
    public static final int TYPE_CHANGE_PROFESSOR_INFO = 1;         // 개인정보 및 비밀번호 수정
    public static final int TYPE_INSERT_SUBJECT_PLAN = 2;           // 강의 계획서 입력
    public static final int CHANGE_SUBJECT_PLAN = 3;                // 강의 계획서 수정
    public static final int TYPE_FIND_PROFESSOR_SUBJECT = 4;        // 교과목 목록 조회
    public static final int TYPE_FIND_SUBJECT_PLAN = 5;             // 교과목 강의 계획서 조회
    public static final int TYPE_SUBJECT_CLASS_STUDENT = 6;         // 교과목 수강 신청 학생 목록 조회
    public static final int TYPE_FIND_SUBJECT_TIME = 7;             // 교과목 시간표 조회

    // Student
    public static final int TYPE_CHANGE_STUDENT_INFO = 1;           // 개인정보 및 비밀번호 수정
    public static final int TYPE_ENROLL_SUBJECT = 2;               // 수강 신청
    public static final int TYPE_DROP_ENROLL_SUBJECT = 3;                // 수강 취소
    public static final int TYPE_FIND_SUBJECTALL = 4;               // 개설 교과목 목록(전학년) 조회
    public static final int TYPE_FIND_SELECT_SUBJECT_PLAN = 5;      // 선택 교과목 강의 계획서 조회
    public static final int TYPE_FIND_MY_SCHEDULE = 6;              // 본인 시간표 조회

    public static final int CODE_TYPE_1 = 1;
    public static final int CODE_TYPE_2 = 2;
    public static final int CODE_TYPE_3 = 3;
    public static final int CODE_TYPE_4 = 4;
    public static final int CODE_TYPE_5 = 5;
    public static final int CODE_TYPE_6 = 6;
}