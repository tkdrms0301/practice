package Network;

public class MSG {
    public static final int HEADER = 2;                                 // 헤더 길이
    public static final int FIXED_LEN = 4;                              // 고정 길이
    public static final int UNDEFINED = -1;                             // 프로토콜이 지정되어 있지 않은 경우
    public static final int MAX_LEN = 10000;                             // 프로토콜이 지정되어 있지 않은 경우

    public static final int PT_TYPE_REQUEST = 0x01;                     // 요청
    public static final int PT_TYPE_RESPOND = 0x02;                     // 응답
    public static final int PT_TYPE_SEND = 0x03;                        // 전송


    //요청
    public static final int PT_REQ_SIGN_UP = 0x01;                      // 회원가입 요청
    public static final int PT_REQ_SIGN_IN = 0x02;                      // 로그인 요청
    public static final int PT_REQ_MY_INSPECTION_RECORD = 0x04;         // 내 점검기록 요청
    public static final int PT_REQ_MY_REPORT_DOWNLOAD = 0x05;           // 내 레포트 다운로드 요청
    public static final int PT_REQ_INSPECTION = 0x06;                   // 진단 요청
    public static final int PT_REQ_INSPECTION_PROGRESS = 0x07;          // 진단 진행률 요청
    public static final int PT_REQ_GUIDELINE = 0x08;                    // 가이드라인 요청
    public static final int PT_REQ_REPORT_DOWNLOAD = 0x09;              // 레포트 다운로드 요청
    public static final int PT_REQ_GUIDELINE_DOWNLOAD = 0x10;          // 가이드라인 다운로드 요청

    // 응답
    public static final int PT_RES_SUCESS = 0x01;                       // 응답 성공
    public static final int PT_RES_FAIL = 0x02;                         // 응답 실패
    public static final int PT_RES_MY_REPORT_RECORD_SEND = 0x03;           // 응답 레포트 목록 전송
    public static final int PT_RES_REPORT_SEND = 0x04;                  // 응답 레포트 전송
    public static final int PT_RES_INSPECTION_PROGRESS = 0x05;          // 응답 진단 진행률

    public static final int PT_RES_GUIDELINE_RECORD_SEND = 0x06;        // 응답 가이드라인 목록 전송
    public static final int PT_RES_GUIDELINE_SEND = 0x07;               // 응답 가이드라인 전송

    // 전송
    public static final int PT_SEND_SIGN_UP = 0x01;                     // 회원가입 정보전송
    public static final int PT_SEND_SIGN_IN = 0x02;                     // 로그인 정보전송

    // 요소별 길이
    public static final int ID_LENGTH = 45;                             // ID 길이
    public static final int PW_LENGTH = 45;                             // PW 길이
    public static final int EMAIL_LENGTH = 45;                          // 이메일 길이
    public static final int DOMAIN_LENGTH = 100;                        // 도메인 길이
    public static final int FILE_NAME_LENGTH = 100;                     // 파일 이름 길이
    public static final int VULNERABILITY_TYPE_LENGTH = 50;             // 취약점 항목 이름 길이
    public static final int DATE_LENGTH = 10;                           // 날짜 길이
    public static final int CHECK_LIST_LENGTH = 12;                     // 진단 항목 체크 리스트 길이
    public static final int INSPECTION_METHOD_LENGTGH = 20;             // 진단 방법 길이
    public static final int PROGRESS_LENGTH = 4;                        // 진행률 길이

}

