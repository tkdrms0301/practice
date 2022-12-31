import java.net.*;
import java.io.*;

public class SortServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException 
    {
        final int BUFFER_LENGTH = 10;
        ServerSocket serverSocket = new ServerSocket(3000);
   	    System.out.println("client wait...");
	    Socket socket = serverSocket.accept();
	    System.out.println("client in");

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        BSInsertSortInt bsInsertSortInt;
        BSInsertSortString bsInsertSortString;

        SortProtocol sortProtocol = new SortProtocol(SortProtocol.PACKET_TYPE_INPUT_REQUEST); // 5
	    os.write(sortProtocol.getPacket());

        String stringBuffer[] = new String[BUFFER_LENGTH];
        int stringBufferCount = 0;
        int intBuffer[] = new int[BUFFER_LENGTH];
        int intBufferCount = 0;
        byte[] buf;

        boolean program_stop = false;

        while(true)
        {
            sortProtocol = new SortProtocol();			// �� Protocol ��ü ���� (�⺻ ������)
			buf = sortProtocol.getPacket();	    // �⺻ �����ڷ� ������ ������ ����Ʈ �迭�� ���̰� 1000����Ʈ�� ������
			is.read(buf);						        // Ŭ���̾�Ʈ�κ��� ��Ŷ����
			int packetType = buf[0];			        // ���� �����Ϳ��� ��Ŷ Ÿ�� ����
			sortProtocol.setPacket(packetType,buf);     // �������ݿ� ������ ��������

            switch(packetType){
                case SortProtocol.PACKET_TYPE_EXIT:
					program_stop = true;
					System.out.println("server exit");
                    break;
                
                case SortProtocol.PACKET_TYPE_INPUT_WORD:
                    if(stringBufferCount == stringBuffer.length) // resize
                    {
                        String temp[] = new String[stringBuffer.length * 2];
                        for(int i = 0; i < stringBuffer.length; i++)
                        {
                            temp[i] = stringBuffer[i];
                        }
                        stringBuffer = temp;
                    }
                    stringBuffer[stringBufferCount] = sortProtocol.getWord();
                    System.out.println(stringBuffer[stringBufferCount] + " save word");
                    stringBufferCount++;
                    break;

                case SortProtocol.PACKET_TYPE_INPUT_NUMBER:
                    if(intBufferCount == intBuffer.length) // resize
                    {
                        int temp[] = new int[intBuffer.length * 2];
                        for(int i = 0; i < intBuffer.length; i++)
                        {
                            temp[i] = intBuffer[i];
                        }
                        intBuffer = temp;
                    }
                    intBuffer[intBufferCount] = sortProtocol.getNumber();
                    System.out.println(intBuffer[intBufferCount] + " save number");
                    intBufferCount++;
                    break;

                case SortProtocol.PACKET_TYPE_INPUT_FINISH:
                    // int �迭, string �迭 ����
                    bsInsertSortInt = new BSInsertSortInt();
                    int[] intTmp = new int[intBufferCount];
                    for(int i = 0; i < intBufferCount; i++)
                        intTmp[i] = intBuffer[i];
                    intBuffer = intTmp;
                    intBuffer = bsInsertSortInt.sort(intBuffer);

                    bsInsertSortString = new BSInsertSortString();
                    String[] strTmp = new String[stringBufferCount];
                    for(int i = 0; i < stringBufferCount; i++)
                        strTmp[i] = stringBuffer[i];
                    stringBuffer = strTmp;
                    stringBuffer = bsInsertSortString.sort(stringBuffer);

                    for(int i = 0; i < stringBufferCount; i++)
                    {
                        sortProtocol = new SortProtocol(SortProtocol.PACKET_TYPE_OUTPUT_WORD, stringBuffer[i].length());
                        sortProtocol.setWord(stringBuffer[i]);
                        os.write(sortProtocol.getPacket());
                        System.out.println(stringBuffer[i] + " transmit");
                        Thread.sleep(1000);
                    }

                    for(int i = 0; i < intBufferCount; i++)
                    {
                        sortProtocol = new SortProtocol(SortProtocol.PACKET_TYPE_OUTPUT_NUMBER);
                        sortProtocol.setNumber(intBuffer[i]);
                        os.write(sortProtocol.getPacket());
                        System.out.println(intBuffer[i] + " transmit");
                        Thread.sleep(1000);
                    }
                    sortProtocol = new SortProtocol(SortProtocol.PACKET_TYPE_EXIT);
                    os.write(sortProtocol.getPacket());
                    break;
            }
            if(program_stop) 
                break;
        }
        is.close();
	    os.close();
	    socket.close();
    }
}
