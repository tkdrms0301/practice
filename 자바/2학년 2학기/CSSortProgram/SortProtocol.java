public class SortProtocol
{
    // 프로토콜 타입
    public static final int PACKET_TYPE_UNDEFINED = -1;	         // 프로토콜이 지정되어 있지 않은 경우
	public static final int PACKET_TYPE_EXIT = 0;                // 프로그램 종료
    public static final int PACKET_TYPE_INPUT_WORD = 1;          // 입력 단어
    public static final int PACKET_TYPE_INPUT_NUMBER = 2;        // 입력 숫자
    public static final int PACKET_TYPE_OUTPUT_WORD = 3;         // 출력 단어
    public static final int PACKET_TYPE_OUTPUT_NUMBER = 4;       // 출력 숫자
    public static final int PACKET_TYPE_INPUT_REQUEST = 5;       // 입력 요청
    public static final int PACKET_TYPE_INPUT_FINISH = 6;        // 입력 완료
    // 5(S->C) -> 1* or 2*, 6, 7(C->S) -> 3*, 4*, 0(S->C 서버 종료), (C 클라이언트 종료) 

    // 프로토콜 길이
    public static final int LENGTH_PROTOCOL_TYPE = 1;   // 프로토콜 타입 길이
    public static final int LENGTH_MAX = 1000;		    // 최대 데이터 길이
    public static final int LENGTH_WORD = 16;		    // 단어 길이
    public static final int LENGTH_LENGTH_OF_WORD = 1;  // 단어 패킷 길이
    public static final int LENGTH_NUMBER = 4;          // 숫자 길이 (초기 배열길이(4) = 부호비트(1) + 자리수(3))

    protected int protocolType;
	private byte[] packet;

    public SortProtocol() // 기본 생성자
    {					
		this.protocolType = PACKET_TYPE_UNDEFINED;
        getPacket(PACKET_TYPE_UNDEFINED);
	}

	public SortProtocol(int protocolType) // getPacket(int protocolType) 생성자
    {
		this.protocolType = protocolType;
		getPacket(protocolType);
	}

    public SortProtocol(int protocolType, int length)
    {
        this.protocolType = protocolType;
        getPacket(protocolType, length);
    }

    private void resize(int length)
    {
        byte[] tmp = new byte[length];
        for(int i = 0; i < packet.length; i++)
            tmp[i] = packet[i];
        packet = tmp;
    }

    public byte[] getPacket()
    {
		return packet;
	}

    // 프로토콜 타입에 따라 바이트 배열 packet의 length가 다름
	public byte[] getPacket(int protocolType)
    {
	    if(packet==null)
        {
			switch(protocolType)
            {
                case PACKET_TYPE_UNDEFINED:
                    packet = new byte[LENGTH_MAX];
                    break;
				case PACKET_TYPE_EXIT :
                    packet = new byte[LENGTH_PROTOCOL_TYPE];
                    break;
                case PACKET_TYPE_INPUT_REQUEST:
                    packet = new byte[LENGTH_PROTOCOL_TYPE];
                    break;
                case PACKET_TYPE_INPUT_NUMBER:
                    packet = new byte[LENGTH_PROTOCOL_TYPE + LENGTH_NUMBER];
                    break;
                case PACKET_TYPE_OUTPUT_NUMBER:
                    packet = new byte[LENGTH_PROTOCOL_TYPE + LENGTH_NUMBER];
                    break;
                case PACKET_TYPE_INPUT_FINISH:
                    packet = new byte[LENGTH_PROTOCOL_TYPE];
                    break;
			} // end switch
	    } // end if
	    packet[0] = (byte)protocolType;	// packet 바이트 배열의 첫 번째 바이트에 프로토콜 타입 설정
	    return packet;
	}

    public byte[] getPacket(int protocolType, int length)
    {
        if(packet==null)
        {
			switch(protocolType)
            {
                case PACKET_TYPE_INPUT_WORD:
                    packet = new byte[LENGTH_PROTOCOL_TYPE + LENGTH_LENGTH_OF_WORD + LENGTH_WORD];
                    break;
                case PACKET_TYPE_OUTPUT_WORD:
                    packet = new byte[LENGTH_PROTOCOL_TYPE + LENGTH_LENGTH_OF_WORD + LENGTH_WORD];
                    break;
            }
        }
        packet[0] = (byte)protocolType;	// packet 바이트 배열의 첫 번째 바이트에 프로토콜 타입 설정
        packet[1] = (byte)length;
	    return packet;
    }

    public void setPacket(int packetType, byte[] buffer){ // 입력받는 바이트 배열의 깊은 복사
        byte[] tmp = new byte[buffer.length];
        for(int i = 0; i < buffer.length; i++)
            tmp[i] = buffer[i];
		packet = getPacket(packetType);
        packet = tmp;
		protocolType = packetType;
	}

	public int getProtocolType()
    {
		return protocolType;
	}

    public void setProtocolType(int protocolType)
    {
		this.protocolType = protocolType;
	}

    public String getWord()
    {
        return new String(getPacket(), LENGTH_PROTOCOL_TYPE + LENGTH_LENGTH_OF_WORD, LENGTH_WORD).trim();
	}

	public void setWord(String word)
    {
        byte[] tmp = word.getBytes();
        for(int i = 0; i < word.length(); i++){
            packet[i + LENGTH_LENGTH_OF_WORD + LENGTH_PROTOCOL_TYPE] = tmp[i];
        }
    }

	public int getNumber() // LENGTH_NUMBER를 변경하여 더 큰 수 입력 가능
    {
        int resultInt = 0;
        int count = 0;
        for(int i = 0; packet[i] != 0; i++)
            count++;
        for(int i = count; i >= LENGTH_PROTOCOL_TYPE + 1; i--)
        {
            resultInt += (int)packet[i];
            if(i == LENGTH_PROTOCOL_TYPE + 1)
                break;
            resultInt *= 128;
        }
        if(packet[1] == (byte)0)
            resultInt = -resultInt;
        return resultInt;
	}

	public void setNumber(int number)
    {
        if(number < 0)
        {
            packet[1] = (byte)(0);
            number = -number;
        }
        else
            packet[1] = (byte)(1);
        // packet[2] 부터 자리수
        int i = LENGTH_PROTOCOL_TYPE + 1;
        for(; number != 0; i++)
        {
            if(packet.length == i)
                resize(packet.length * 2);
            packet[i] = (byte)(number % 128);
            number /= 128;
        }
        if(packet.length == i)
            resize(packet.length * 2);
        packet[i] = 0;
	}
}
