package Service.Main;

import Network.MSG;
import Network.Protocol;
import Persistence.DAO.UserDAO;
import Persistence.MybatisConnectionFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;


public class SignService extends MainService{

    public SignService(DataInputStream dis, DataOutputStream dos, Socket socket, byte[] receiveHeader) {
        super(dis, dos, socket, receiveHeader);
    }

    public void signResponse(){
        try {
            protocol = new Protocol(MSG.PT_TYPE_RESPOND);
            protocol.setResponse(MSG.PT_RES_SUCESS);

            dos.write(protocol.getPacket(), 0, protocol.getSize());     //응답 전송
            dos.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void signUp(){
        try{
            UserDAO userDAO = new UserDAO(MybatisConnectionFactory.getSqlSessionFactory());
            protocol = new Protocol(MSG.PT_TYPE_RESPOND);

            if(receiveHeader[1] == MSG.PT_SEND_SIGN_UP){ // 회원가입 정보전송:
                receiveHeader = new byte[MSG.FIXED_LEN];

                dis.read(receiveHeader);
                String id = changeByteToString(receiveHeader);

                dis.read(receiveHeader);
                String pw = changeByteToString(receiveHeader);

                dis.read(receiveHeader);
                String email = changeByteToString(receiveHeader);

                System.out.println("id : " + id + ", pw : " + pw + " email : " + email);

                if(userDAO.userSelectPrint(id) == null){
                    userDAO.userCreate(id, pw, email);
                    System.out.println("성공");
                    protocol.setResponse(MSG.PT_RES_SUCESS);
                }else {
                    System.out.println("실패");
                    protocol.setResponse(MSG.PT_RES_FAIL);
                }
            }
            dos.write(protocol.getPacket(), 0, protocol.getSize());
            dos.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
