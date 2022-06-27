package EnrollNetwork;

public class Protocol {
    private byte[] packet;
    private int loginType;
    private int messageType;
    private int functionType;
    private int codeType;
    private int size = 0;

    public Protocol() {
        loginType = Message.NULL_HEADER;
        messageType = Message.NULL_HEADER;;
        functionType = Message.NULL_HEADER;;
        codeType = Message.NULL_HEADER;;
    }

    public Protocol(int loginType, int messageType, int functionType) {
        this.loginType = loginType;
        this.messageType = messageType;
        this.functionType = functionType;
    }

    public byte[] getPacket() {
        return packet;
    }

    public int getSize() {
        return size;
    }

    // Login 관련 패킷 사이즈 설정
    public byte[] getLoginPacket(int messageType, int functionType) {
        switch (messageType) {
            case Message.MESSAGE_TYPE_REQUEST:
                packet = new byte[Message.LEN_PROTOCOL_TYPE];
                break;
            case Message.MESSAGE_TYPE_RESULT:
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_GROUP_ID];
                break;
            case Message.MESSAGE_TYPE_RESPONSE:
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN * 2 + Message.LEN_ID + Message.LEN_PW]; // int형은 4 바이트로 변환 + 4 가르키는 FIXED_LEN
                break;
        }
        size = 0;
        packet[size++] = (byte) loginType;
        packet[size++] = (byte) messageType;
        packet[size++] = (byte) functionType;
        packet[size++] = (byte) codeType;
        return packet;
    }

    public void setRequestId(int functionType) {
        packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_ID];      // int형은 4바이트로 변환 + 이것을 가르키는 FIXED_LEN
        packet[size++] = (byte) loginType;
        packet[size++] = (byte) messageType;
        packet[size++] = (byte) functionType;

        switch (functionType) {
            case Message.FUNCTION_TYPE_DELETE_SUBJECT:
            case Message.FUNCTION_TYPE_FIND_PROFESSOR:
            case Message.FUNCTION_TYPE_FIND_STUDENT:
            case Message.FUNCTION_TYPE_FIND_OPEN_SUBJECT:
            case Message.FUNCTION_TYPE_DELETE_OPEN_SUBJECT:
                packet[size++] = (byte) Message.NULL_HEADER;
                break;
            default:
                packet[size++] = (byte) Message.MESSAGE_TYPE_REQUEST;
        }
    }


    // Admin 관련 요청 패킷 사이즈 설정
    public byte[] getAdminRequestPacket(int functionType, int codeType) {      // Admin 요청 패킷 사이즈 설정
        switch (functionType) {
            case Message.FUNCTION_TYPE_MAKE_PROFESSOR_ACCOUNT:  // 교수 계정 생성
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN * 6 + Message.LEN_USER_ALL_DATA + Message.LEN_MAJOR];   // user_id는 빠진 상태임
                break;
            case Message.FUNCTION_TYPE_MAKE_STUDENT_ACCOUNT:    // 학생 계정 생성
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN * 7 + Message.LEN_USER_ALL_DATA+ Message.LEN_MAJOR + Message.LEN_GRADE];   // user_id는 빠진 상태임
                break;
            case Message.FUNCTION_TYPE_MAKE_SUBJECT:            // 비개설 교과목 생성
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN * 3 + Message.LEN_SUBJECT_NAME + Message.LEN_SUBJECT_IS_OPEN + Message.LEN_SUBJECT_GRADE];
                break;
            case Message.FUNCTION_TYPE_UPDATE_SUBJECT:          // 비개설 교과목 수정
                if (codeType == 2) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_SUBJECT_NAME];    // 비개설 교과목 이름 수정
                } else if (codeType == 3) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_SUBJECT_GRADE];   // 비개설 교과목 수강 학년 수정
                }
                break;
            case Message.FUNCTION_TYPE_SET_SUBJECT_PLAN:        // 강의 계획서 입력 기간 설정:
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN * 2 +  Message.LEN_SET_DAY * 2];
                break;
            case Message.FUNCTION_TYPE_SET_ENROLL_TIME:         // 학년별 수강 신청 기간 설정
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN * 3 + Message.LEN_SUBJECT_GRADE + Message.LEN_SET_DAY * 2];
                break;
            case Message.FUNCTION_TYPE_FIND_PROFESSOR:          // 교수 정보 조회
            case Message.FUNCTION_TYPE_FIND_STUDENT:            // 학생 정보 조회
            case Message.FUNCTION_TYPE_FIND_OPEN_SUBJECT:       // 개설 교과목 정보 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE];
                break;
            case Message.FUNCTION_TYPE_MAKE_OPEN_SUBJECT:       // 개설 교과목 정보 생성
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN * 9 + Message.LEN_SUBJECT_OPEN_ALL_DATA];   // 이거 함수 하나에서 처리하는 것으로 가정해 subject_id길이 포함시키지 않았음
                break;
            case Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT:     // 개설 교과목 정보 수정
                if (codeType == 2) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.INTEGER_LEN];     // 최대 수강 인원 수정
                } else if (codeType == 3) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_SUBJECT_CREDIT];         // 학점 수정
                } else if (codeType == 4) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_SUBJECT_DAY];         // 강의 날짜 수정
                } else if (codeType == 5) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_SUBJECT_TIME];         // 강의 시간 수정
                } else if (codeType == 6) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_SUBJECT_CLASSROOM];         // 강의실 수정
                }
                break;
        }
        size = 0;
        packet[size++] = (byte) Message.LOGIN_TYPE_ADMIN;
        packet[size++] = (byte) Message.MESSAGE_TYPE_REQUEST;
        packet[size++] = (byte) functionType;
        packet[size++] = (byte) codeType;
        return packet;
    }



    public byte[] getAdminResponsePacket(int functionType, int codeType) {      // Admin 응답 패킷 사이즈 설정
        switch (codeType) {
            case Message.FUNCTION_TYPE_MAKE_PROFESSOR_ACCOUNT:  // 교수 계정 생성
            case Message.FUNCTION_TYPE_MAKE_STUDENT_ACCOUNT:    // 학생 계정 생성
            case Message.FUNCTION_TYPE_MAKE_SUBJECT:            // 비개설 교과목 생성
            case Message.FUNCTION_TYPE_UPDATE_SUBJECT:          // 비개설 교과목 수정
            case Message.FUNCTION_TYPE_DELETE_SUBJECT:          // 비개설 교과목 삭제
            case Message.FUNCTION_TYPE_SET_SUBJECT_PLAN:        // 강의 계획서 입력 기간 설정
            case Message.FUNCTION_TYPE_SET_ENROLL_TIME:         // 학년별 수강 신청 기간 설정
            case Message.FUNCTION_TYPE_MAKE_OPEN_SUBJECT:       // 개설 교과목 정보 생성
            case Message.FUNCTION_TYPE_UPDATE_OPEN_SUBJECT:     // 개설 교과목 정보 수정
            case Message.FUNCTION_TYPE_DELETE_OPEN_SUBJECT:     // 개설 교과목 폐강
                packet = new byte[Message.LEN_PROTOCOL_TYPE];
                break;
            case Message.FUNCTION_TYPE_FIND_PROFESSOR:          // 교수 정보 조회
                // 수정해야 함
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN * 7 + Message.LEN_ID + Message.LEN_USER_ALL_DATA + Message.LEN_MAJOR];   // user_id는 빠진 상태임
                break;
            case Message.FUNCTION_TYPE_FIND_STUDENT:            // 학생 정보 조회
                // 수정해야 함
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN * 7 + Message.LEN_USER_ALL_DATA+ Message.LEN_MAJOR + Message.LEN_GRADE];   // user_id는 빠진 상태임
            case Message.FUNCTION_TYPE_FIND_OPEN_SUBJECT:       // 개설 교과목 정보 조회
                // 수정해야 함
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN * 10 + Message.LEN_SUBJECT_ID + Message.LEN_SUBJECT_OPEN_ALL_DATA];   // 이거 함수 하나에서 처리하는 것으로 가정해 subject_id길이 포함시키지 않았음
                break;
        }
        size = 0;
        packet[size++] = (byte) Message.LOGIN_TYPE_ADMIN;
        packet[size++] = (byte) Message.MESSAGE_TYPE_RESPONSE;
        packet[size++] = (byte) functionType;
        packet[size++] = (byte) codeType;
        return packet;
    }

    public byte[] getProfessorRequestPacket(int functionType, int codeType) {
        switch (functionType) {
            case Message.TYPE_CHANGE_PROFESSOR_INFO:         // 개인정보 및 비밀번호 수정
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_MAJOR];  // 모든 개인정보 변경 가능
                break;
            case Message.TYPE_INSERT_SUBJECT_PLAN:           // 강의 계획서 입력
            case Message.CHANGE_SUBJECT_PLAN:                // 강의 계획서 수정
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_SUBJECT_PLAN];
                break;
            case Message.TYPE_FIND_PROFESSOR_SUBJECT:                 // 교과목 목록 조회
            case Message.TYPE_FIND_SUBJECT_PLAN:             // 교과목 강의 계획서 조회
            case Message.TYPE_FIND_SUBJECT_TIME:             // 교과목 시간표 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE];
            case Message.TYPE_SUBJECT_CLASS_STUDENT:         // 교과목 수강 신청 학생 목록 조회
                setRequestId(functionType);
                break;
        }
        size = 0;
        packet[size++] = (byte) loginType;
        packet[size++] = (byte) messageType;
        packet[size++] = (byte) functionType;
        packet[size++] = (byte) codeType;
        return packet;
    }

    public byte[] getProfessorResponsePacket(int functionType, int codeType) {
        switch (functionType) {
            case Message.TYPE_CHANGE_PROFESSOR_INFO:         // 개인정보 및 비밀번호 수정
            case Message.TYPE_INSERT_SUBJECT_PLAN:           // 강의 계획서 입력
            case Message.CHANGE_SUBJECT_PLAN:                // 강의 계획서 수정
            case Message.TYPE_SUBJECT_CLASS_STUDENT:         // 교과목 수강 신청 학생 목록 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE];  // 모든 개인정보 변경 가능
                break;
            case Message.TYPE_FIND_PROFESSOR_SUBJECT:        // 교과목 목록 조회
            case Message.TYPE_FIND_SUBJECT_PLAN:             // 교과목 강의 계획서 조회
            case Message.TYPE_FIND_SUBJECT_TIME:             // 교과목 시간표 조회
                // 수정해야 함
                break;
        }
        size = 0;
        packet[size++] = (byte) loginType;
        packet[size++] = (byte) messageType;
        packet[size++] = (byte) functionType;
        packet[size++] = (byte) codeType;
        return packet;
    }

    public byte[] setAdminResponseListPacket(int functionType, int len) {
        switch (functionType) {
            case Message.FUNCTION_TYPE_FIND_PROFESSOR:          // 교수 정보 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + (Message.FIXED_LEN * 7 + Message.LEN_ID + Message.LEN_USER_ALL_DATA + Message.LEN_MAJOR) * len];
                break;
            case Message.FUNCTION_TYPE_FIND_STUDENT:            // 학생 정보 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + (Message.FIXED_LEN * 8 + Message.LEN_ID + Message.LEN_USER_ALL_DATA + Message.LEN_MAJOR + Message.LEN_GRADE) * len];
                break;
            case Message.FUNCTION_TYPE_FIND_OPEN_SUBJECT:       // 개설 교과목 정보 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + (Message.FIXED_LEN * 10 + Message.LEN_SUBJECT_ID + Message.LEN_SUBJECT_OPEN_ALL_DATA) * len];   // 이거 함수 하나에서 처리하는 것으로 가정해 subject_id길이 포함시키지 않았음
                break;
        }
        packet[size++] = (byte) loginType;
        packet[size++] = (byte) messageType;
        packet[size++] = (byte) functionType;
        packet[size++] = (byte) codeType;

        byte[] payloadSize = intToBytes(len);

        for (int i = 0; i < payloadSize.length; i++) {
            packet[size++] = payloadSize[i];
        }

        return packet;
    }

    public byte[] setProfessorResponseListPacket(int functionType, int len) {
        switch (functionType) {
            case Message.TYPE_FIND_PROFESSOR_SUBJECT:         // 교과목 목록 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + (Message.FIXED_LEN * 13 + Message.LEN_SUBJECT_ALL_DATA + Message.LEN_SUBJECT_OPEN_ALL_DATA) * len];   // 이거 함수 하나에서 처리하는 것으로 가정해 subject_id길이 포함시키지 않았음
                break;
            case Message.TYPE_FIND_SUBJECT_PLAN:             // 교과목 강의 계획서 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + (Message.FIXED_LEN * 2 + Message.LEN_SUBJECT_NAME + Message.LEN_SUBJECT_PLAN) * len];
                break;
            case Message.TYPE_SUBJECT_CLASS_STUDENT:        // 교과목 수강 신청한 학생 목록 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + (Message.FIXED_LEN * 8 + Message.LEN_ID + Message.LEN_USER_ALL_DATA + Message.LEN_MAJOR + Message.LEN_GRADE) * len];
                break;
            case Message.TYPE_FIND_SUBJECT_TIME:             // 교과목 시간표 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + (Message.FIXED_LEN * 3 + Message.LEN_SUBJECT_NAME + Message.LEN_SUBJECT_DAY + Message.LEN_SUBJECT_TIME) * len];
                break;
        }
        packet[size++] = (byte) loginType;
        packet[size++] = (byte) messageType;
        packet[size++] = (byte) functionType;
        packet[size++] = (byte) codeType;

        byte[] payloadSize = intToBytes(len);

        for (int i = 0; i < payloadSize.length; i++) {
            packet[size++] = payloadSize[i];
        }

        return packet;
    }

    public byte[] setStudentResponseListPacket(int functionType, int len) {
        switch (functionType) {
            case Message.TYPE_FIND_SUBJECTALL:               // 개설 교과목 목록(전학년) 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + (Message.FIXED_LEN * 10 + Message.LEN_SUBJECT_ALL_DATA + Message.LEN_SUBJECT_OPEN_ALL_DATA) * len];
            case Message.TYPE_FIND_MY_SCHEDULE:              // 본인 시간표 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + (Message.FIXED_LEN * 16 + Message.LEN_SUBJECT_ALL_DATA + Message.LEN_SUBJECT_OPEN_ALL_DATA + Message.LEN_SET_DAY * 3) * len];
        }
        packet[size++] = (byte) loginType;
        packet[size++] = (byte) messageType;
        packet[size++] = (byte) functionType;
        packet[size++] = (byte) codeType;

        byte[] payloadSize = intToBytes(len);

        for (int i = 0; i < payloadSize.length; i++) {
            packet[size++] = payloadSize[i];
        }
        return packet;
    }

    public byte[] setStudentRequestPacket(int functionType, int codeType) {
        switch (functionType) {
            case Message.TYPE_CHANGE_STUDENT_INFO:           // 개인정보 및 비밀번호 수정
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_MAJOR];  // 모든 개인정보 변경 가능
                break;
            case Message.TYPE_ENROLL_SUBJECT:                // 수강 신청
            case Message.TYPE_DROP_ENROLL_SUBJECT:           // 수강 취소
            case Message.TYPE_FIND_SUBJECTALL:               // 개설 교과목 목록(전학년) 조회
                if (codeType == 1) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_GRADE];
                } else if (codeType == 2) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_NAME];
                } else if (codeType == 3) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN * 2 + Message.LEN_GRADE + Message.LEN_NAME];
                } else if (codeType == 4) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE];
                }
                break;
            case Message.TYPE_FIND_SELECT_SUBJECT_PLAN:      // 선택 교과목 강의 계획서 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_SUBJECT_ID];
                break;
            case Message.TYPE_FIND_MY_SCHEDULE:              // 본인 시간표 조회
                packet = new byte[Message.LEN_PROTOCOL_TYPE];
                break;
        }
        size = 0;
        packet[size++] = (byte) loginType;
        packet[size++] = (byte) messageType;
        packet[size++] = (byte) functionType;
        packet[size++] = (byte) codeType;
        return packet;
    }

    public byte[] setStudentResponsePacket(int functionType, int codeType) {
        switch (functionType) {
            case Message.TYPE_CHANGE_STUDENT_INFO:           // 개인정보 및 비밀번호 수정
            case Message.TYPE_ENROLL_SUBJECT:                // 수강 신청
            case Message.TYPE_DROP_ENROLL_SUBJECT:           // 수강 취소
                packet = new byte[Message.LEN_PROTOCOL_TYPE];
                break;
            case Message.TYPE_FIND_SELECT_SUBJECT_PLAN:      // 선택 교과목 강의 계획서 조회
                if (codeType == 1) {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE + Message.FIXED_LEN + Message.LEN_SUBJECT_PLAN];
                } else {
                    packet = new byte[Message.LEN_PROTOCOL_TYPE];
                }
                break;
        }

        size = 0;
        packet[size++] = (byte) loginType;
        packet[size++] = (byte) messageType;
        packet[size++] = (byte) functionType;
        packet[size++] = (byte) codeType;

        return packet;
    }

    // 문자열을 byte배열로 바꿔 패킷에 저장하는 메소드
    public void setStrToByte(String data) {
        byte[] payload = data.getBytes();

        byte[] payloadSize = intToBytes(payload.length);

        // 입력받은 데이터의 길이를 저장
        for (int i = 0; i < payloadSize.length; i++) {
            packet[size++] = payloadSize[i];
        }

        // 입력받은 데이터의 내용을 삽입
        for (int i = 0; i < payload.length; i++) {
            packet[size++] = payload[i];
        }
    }

    // 정수를 byte배열로 바꿔 패킷에 저장하는 메소드
    public void setIntToByte(int data) {
        byte[] payload = intToBytes(data);

        byte[] payloadSize = intToBytes(payload.length);

        for (int i = 0; i < payloadSize.length; i++) {
            packet[size++] = payloadSize[i];
        }

        for (int i = 0; i < payloadSize.length; i++) {
            packet[size++] = payload[i];
        }
    }



    private static byte[] intToBytes(final int data) {
        return new byte[] {
                (byte)((data >> 24) & 0xff),    // 24~31비트의 자릿수를 byte로 변환
                (byte)((data >> 16) & 0xff),    // 09~23비트의 자릿수를 byte로 변환
                (byte)((data >> 8) & 0xff),     // 08~15비트의 자릿수를 byte로 변환
                (byte)((data >> 0) & 0xff)     // 00~07비트의 자릿수를 byte로 변환
        };
    }
}