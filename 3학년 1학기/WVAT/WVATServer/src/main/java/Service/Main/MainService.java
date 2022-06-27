package Service.Main;

import Network.Protocol;
import lombok.Getter;
import lombok.Setter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class MainService {
    @Getter
    @Setter
    static private String userID;
    @Getter
    @Setter
    static private int userNum;

    DataInputStream dis = null;
    DataOutputStream dos = null;
    Socket socket = null;
    byte[] receiveHeader = null;
    Protocol protocol = null;

    public MainService(DataInputStream dis, DataOutputStream dos, Socket socket, byte[] receiveHeader){
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
        this.receiveHeader = receiveHeader;
    }

    public boolean[] changeByteToBoolean(byte[] recvHeader) throws IOException {
        int dataLength = byteArrayToInt(recvHeader);
        boolean data[] = new boolean[dataLength];

        byte[] recvData = new byte[dataLength];

        dis.read(recvData);

        for (int i = 0; i < dataLength; i++)
            data[i] = (recvData[i] == 1);


        return data;
    }

    public String changeByteToString(byte[] recvHeader) throws IOException {
        int bytesRcvd;
        int totalBytesRcvd = 0;  // 지금까지 받은 바이트 수

        int dataLength = byteArrayToInt(recvHeader);
        if (dataLength == 0) return null;

        byte[] recvData = new byte[dataLength];

        while (totalBytesRcvd < dataLength) {
            if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, dataLength - totalBytesRcvd)) == -1)
                throw new SocketException("Connection close prematurely");
            totalBytesRcvd += bytesRcvd;
        }

        return new String(recvData);
    }

    private static int byteArrayToInt(byte[] data) {
        if (data == null || data.length != 4) return 0x0;
        return (int)((0xff & data[0]) << 24 | (0xff & data[1]) << 16 | (0xff & data[2]) << 8 | (0xff & data[3]) << 0);
    }
}
