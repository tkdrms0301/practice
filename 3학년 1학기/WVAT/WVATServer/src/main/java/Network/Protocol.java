package Network;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Protocol {
    private byte[] packet;
    private int type;                       // 요청, 응답, 전송
    private int function;                   // 기능
    private int size = 0;


    public Protocol() {
        this.type = MSG.UNDEFINED;
        this.function = MSG.UNDEFINED;
        packet = new byte[2];
    }

    public Protocol(int type) {
        this.type = type;
        this.function = MSG.UNDEFINED;
        packet = new byte[2];
    }

    public byte[] getPacket() {
        return packet;
    }

    public int getSize() {
        return size;
    }

    public byte[] setPacket(int type, int function) {
        switch (type) {
            case MSG.PT_TYPE_REQUEST:
                setRequest(function);
                break;
            case MSG.PT_TYPE_RESPOND:
                setResponse(type);
                break;
            case MSG.PT_TYPE_SEND:
                setSend(function);
                break;
        }
        size = 0;
        packet[size++] =(byte) type;
        packet[size++] =(byte) function;

        return packet;
    }

    // Request 타입 관련 패킷 사이즈 설정
    public void setRequest(int function) {
        switch (function) {
            case MSG.PT_REQ_SIGN_UP:                // 회원가입 요청
                packet = new byte[MSG.HEADER + MSG.FIXED_LEN * 2 + MSG.ID_LENGTH + MSG.PW_LENGTH + MSG.EMAIL_LENGTH];
                break;
            case MSG.PT_REQ_SIGN_IN:                // 로그인 요청
                packet = new byte[MSG.HEADER + MSG.FIXED_LEN * 2 + MSG.ID_LENGTH + MSG.PW_LENGTH];
                break;
            case MSG.PT_REQ_INSPECTION:             // 진단 요청
                packet = new byte[MSG.HEADER + MSG.FIXED_LEN * 3 + MSG.DOMAIN_LENGTH + MSG.CHECK_LIST_LENGTH + MSG.INSPECTION_METHOD_LENGTGH];
                break;
            case MSG.PT_REQ_MY_REPORT_DOWNLOAD:        // 내 레포트 다운로드 요청
            case MSG.PT_REQ_GUIDELINE_DOWNLOAD:     // 가이드라인 다운로드 요청
                packet = new byte[MSG.HEADER + MSG.FIXED_LEN + MSG.FILE_NAME_LENGTH];
                break;
        }
        size = 0;

        this.type = MSG.PT_TYPE_REQUEST;
        this.function = function;

        packet[size++] =(byte) this.type;
        packet[size++] =(byte) this.function;
    }

    // Response 타입 관련 패킷 사이즈 설정
    public void setResponse(int function) {
        switch (function) {
            case MSG.PT_RES_MY_REPORT_RECORD_SEND:     // 응답 레포트 목록 전송
                packet = new byte[MSG.MAX_LEN];
                break;
            case MSG.PT_RES_INSPECTION_PROGRESS:     // 응답 진단 진행률
                packet = new byte[MSG.HEADER + MSG.FIXED_LEN * 2 + MSG.PROGRESS_LENGTH  + MSG.DOMAIN_LENGTH];
                break;
            case MSG.PT_RES_GUIDELINE_RECORD_SEND:  // 응답 가이드라인 전송
                packet = new byte[MSG.MAX_LEN];
                break;
        }
        size = 0;
        this.type = MSG.PT_TYPE_RESPOND;
        this.function = function;

        packet[size++] =(byte) this.type;
        packet[size++] =(byte) this.function;
    }

    // Send 타입 관련 패킷 사이즈 설정
    public void setSend(int function) {
        switch (function) {
            case MSG.PT_SEND_SIGN_UP:                // 회원가입 정보전송
                packet = new byte[MSG.HEADER + MSG.FIXED_LEN * 2 + MSG.ID_LENGTH + MSG.PW_LENGTH + MSG.EMAIL_LENGTH];
                break;
            case MSG.PT_SEND_SIGN_IN:               // 로그인 정보전송
                packet = new byte[MSG.HEADER + MSG.FIXED_LEN * 2 + MSG.ID_LENGTH + MSG.PW_LENGTH];
                break;
        }
        size = 0;

        this.type = MSG.PT_TYPE_SEND;
        this.function = function;

        packet[size++] =(byte) this.type;
        packet[size++] =(byte) this.function;
    }

    public void setStrToByte(String str) {
        byte[] data = str.getBytes();

        byte[] dataSize = intToBytes(data.length);

        // 입력받은 데이터의 길이를 저장
        for (int i = 0; i < dataSize.length; i++) {
            packet[size++] = dataSize[i];
        }

        // 입력받은 데이터의 내용을 삽입
        for (int i = 0; i < data.length; i++) {
            packet[size++] = data[i];
        }
    }

    public void setFiletoByte(File file) throws IOException {
        byte[] data = Files.readAllBytes(file.toPath());
        byte[] dataSize = intToBytes(data.length);

        String name = file.getName();

        packet = new byte[MSG.HEADER + MSG.FIXED_LEN * 2 + MSG.FILE_NAME_LENGTH +  data.length];
        size = 0;
        packet[size++] = (byte) this.type;
        packet[size++] = (byte) this.function;

        setStrToByte(name);

        // 입력받은 데이터의 길이를 저장
        for (int i = 0; i < dataSize.length; i++) {
            packet[size++] = dataSize[i];
        }

        // 입력받은 데이터의 내용을 삽입
        for (int i = 0; i < data.length; i++) {
            packet[size++] = data[i];
        }
    }

    public void setBooltoByte(boolean bol[]) {
        byte[] dataSize = intToBytes(bol.length);

        for (int i = 0; i < dataSize.length; i++) {
            packet[size++] = dataSize[i];
        }

        // 입력받은 데이터의 내용을 삽입
        for (int i = 0; i < bol.length; i++) {
            packet[size++] = (byte) (bol[i] ? 0x01 : 0x00);
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