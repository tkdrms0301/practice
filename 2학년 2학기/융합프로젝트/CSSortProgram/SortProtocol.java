public class SortProtocol
{
    // �������� Ÿ��
    public static final int PACKET_TYPE_UNDEFINED = -1;	         // ���������� �����Ǿ� ���� ���� ���
	public static final int PACKET_TYPE_EXIT = 0;                // ���α׷� ����
    public static final int PACKET_TYPE_INPUT_WORD = 1;          // �Է� �ܾ�
    public static final int PACKET_TYPE_INPUT_NUMBER = 2;        // �Է� ����
    public static final int PACKET_TYPE_OUTPUT_WORD = 3;         // ��� �ܾ�
    public static final int PACKET_TYPE_OUTPUT_NUMBER = 4;       // ��� ����
    public static final int PACKET_TYPE_INPUT_REQUEST = 5;       // �Է� ��û
    public static final int PACKET_TYPE_INPUT_FINISH = 6;        // �Է� �Ϸ�
    // 5(S->C) -> 1* or 2*, 6, 7(C->S) -> 3*, 4*, 0(S->C ���� ����), (C Ŭ���̾�Ʈ ����) 

    // �������� ����
    public static final int LENGTH_PROTOCOL_TYPE = 1;   // �������� Ÿ�� ����
    public static final int LENGTH_MAX = 1000;		    // �ִ� ������ ����
    public static final int LENGTH_WORD = 16;		    // �ܾ� ����
    public static final int LENGTH_LENGTH_OF_WORD = 1;  // �ܾ� ��Ŷ ����
    public static final int LENGTH_NUMBER = 4;          // ���� ���� (�ʱ� �迭����(4) = ��ȣ��Ʈ(1) + �ڸ���(3))

    protected int protocolType;
	private byte[] packet;

    public SortProtocol() // �⺻ ������
    {					
		this.protocolType = PACKET_TYPE_UNDEFINED;
        getPacket(PACKET_TYPE_UNDEFINED);
	}

	public SortProtocol(int protocolType) // getPacket(int protocolType) ������
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

    // �������� Ÿ�Կ� ���� ����Ʈ �迭 packet�� length�� �ٸ�
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
	    packet[0] = (byte)protocolType;	// packet ����Ʈ �迭�� ù ��° ����Ʈ�� �������� Ÿ�� ����
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
        packet[0] = (byte)protocolType;	// packet ����Ʈ �迭�� ù ��° ����Ʈ�� �������� Ÿ�� ����
        packet[1] = (byte)length;
	    return packet;
    }

    public void setPacket(int packetType, byte[] buffer){ // �Է¹޴� ����Ʈ �迭�� ���� ����
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

	public int getNumber() // LENGTH_NUMBER�� �����Ͽ� �� ū �� �Է� ����
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
        // packet[2] ���� �ڸ���
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
