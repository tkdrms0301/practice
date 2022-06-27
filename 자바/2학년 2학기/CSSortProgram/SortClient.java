import java.net.*;
import java.io.*;
public class SortClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
        Socket socket = new Socket("127.0.0.1",3000);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        SortProtocol sortProtocol;
        byte[] buf;

        final int RESULT_ARRAY_LENGTH = 10;
        int countWord = 0;
        int countNum = 0;
        String[] arrWordAndNum;
        String[] arrWord;
        String[] arrNumOfString;
        int[] arrNum;

        int[] resultNumber = new int[RESULT_ARRAY_LENGTH];
        String[] resultWord = new String[RESULT_ARRAY_LENGTH];
        while(true)
        {
            sortProtocol = new SortProtocol();
            buf = sortProtocol.getPacket();
            is.read(buf);
            int packetType = buf[0];
            sortProtocol.setPacket(packetType,buf);
            if(packetType == SortProtocol.PACKET_TYPE_EXIT)
            {
                System.out.print("Sorted Number : ");
                for(int i = 0; i < countNum; i++)
                {
                    System.out.print(resultNumber[i] + " ");
                }
                System.out.println();
                System.out.print("Sorted Word : ");
                for(int i = 0; i < countWord; i++)
                {
                    System.out.print(resultWord[i] + " ");
                }
                System.out.println();
                sortProtocol = new SortProtocol(SortProtocol.PACKET_TYPE_EXIT);
                os.write(sortProtocol.getPacket());
                Thread.sleep(1000);
                System.out.println("Exit");
                break;
            }
            switch(packetType)
            {
                case SortProtocol.PACKET_TYPE_INPUT_REQUEST:
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Sorting Words and Nums : ");
                    String wordAndNum = in.readLine();
                    arrWordAndNum = wordAndNum.split(" ");
                    arrWord = new String[arrWordAndNum.length];
                    arrNumOfString = new String[arrWordAndNum.length];

                    for(int i = 0; i < arrWordAndNum.length; i ++)
                    {
                        boolean isNum = true;
                        for(int j = 0; j < arrWordAndNum[i].length(); j++)
                        {
                            if(!('0' <= arrWordAndNum[i].charAt(j) && arrWordAndNum[i].charAt(j) <= '9'))
                            {
                                isNum = false;
                                break;
                            }
                        }
                        if(isNum)
                        {
                            arrNumOfString[countNum++] = arrWordAndNum[i];
                        }
                        else
                        {
                            arrWord[countWord++] = arrWordAndNum[i];
                        }
                    }
                    arrNum = new int[countNum];
                    for(int i = 0; i < countNum; i++)
                    {
                        arrNum[i] = Integer.parseInt(arrNumOfString[i]);
                    }
                    for(int i = 0; i < countWord; i++) {
                        sortProtocol = new SortProtocol(SortProtocol.PACKET_TYPE_INPUT_WORD, arrWord[i].length());
                        sortProtocol.setWord(arrWord[i]);
                        os.write(sortProtocol.getPacket());
                        Thread.sleep(1000);
                    }
                    for(int i = 0; i < countNum; i++) {
                        sortProtocol = new SortProtocol(SortProtocol.PACKET_TYPE_INPUT_NUMBER);
                        sortProtocol.setNumber(arrNum[i]);
                        os.write(sortProtocol.getPacket());
                        Thread.sleep(1000);
                    }
                    sortProtocol = new SortProtocol(SortProtocol.PACKET_TYPE_INPUT_FINISH);
                    os.write(sortProtocol.getPacket());
                    countWord = 0;
                    countNum = 0;
                    break;

                case SortProtocol.PACKET_TYPE_OUTPUT_WORD:
                    resultWord[countWord] = sortProtocol.getWord();
                    System.out.println(resultWord[countWord] + " Sorted Words");
                    countWord++;
                    if(countWord == resultWord.length)
                    {
                        String temp[] = new String[resultWord.length * 2];
                        for(int i = 0; i < resultWord.length; i++)
                        {
                            temp[i] = resultWord[i];
                        }
                        resultWord = temp;
                    }
                    break;

                case SortProtocol.PACKET_TYPE_OUTPUT_NUMBER:
                    resultNumber[countNum] = sortProtocol.getNumber();
                    System.out.println(resultNumber[countNum] + " Sorted Numbers");
                    countNum++;
                    if(countNum == resultNumber.length)
                    {
                        int temp[] = new int[resultNumber.length * 2];
                        for(int i = 0; i < resultNumber.length; i++)
                        {
                            temp[i] = resultNumber[i];
                        }
                        resultNumber = temp;
                    }
                    break;
            }
        }
        os.close();
        is.close();
        socket.close();
    }
}