package Service.Main;

import Network.MSG;
import Network.Protocol;
import Persistence.DAO.UserDAO;
import Persistence.DTO.UserDTO;
import Persistence.MybatisConnectionFactory;
import org.w3c.dom.events.EventException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class LoginService extends MainService{
    public LoginService(DataInputStream dis, DataOutputStream dos, Socket socket, byte[] receiveHeader) {
       super(dis, dos, socket, receiveHeader);
    }

    public void loginResponse(){
        try {
            protocol = new Protocol(MSG.PT_TYPE_RESPOND);
            protocol.setResponse(MSG.PT_RES_SUCESS);

            dos.write(protocol.getPacket(), 0, protocol.getSize());     //응답 전송
            dos.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void login(){
        try {
            UserDAO userDAO = new UserDAO(MybatisConnectionFactory.getSqlSessionFactory());
            protocol = new Protocol(MSG.PT_TYPE_RESPOND);
            receiveHeader = new byte[MSG.FIXED_LEN];

            dis.read(receiveHeader);
            String id = changeByteToString(receiveHeader);

            dis.read(receiveHeader);
            String pw = changeByteToString(receiveHeader);

            UserDTO userDTO = userDAO.userSelectPrint(id);
            System.out.println("id : " + userDTO.getUserID() + ", pw : " + userDTO.getUserPW());
            System.out.println("user id : " + id + ", pw : " + pw);

            if (userDTO.getUserID().equals(id) && userDTO.getUserPW().equals(pw)) {
                protocol.setResponse(MSG.PT_RES_SUCESS);
                setUserNum(userDTO.getUserNum());;
                setUserID(userDTO.getUserID());
            } else {
                protocol.setResponse(MSG.PT_RES_FAIL);
            }
            dos.write(protocol.getPacket(), 0, protocol.getSize());
            dos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
