package Service;

import Persistence.DAO  .GuideLineDAO;
import Persistence.DTO.GuideLineDTO;
import Persistence.MybatisConnectionFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) throws Exception{

        //파일 경로 가져오기
        GuideLineDAO guideLineDAO = new GuideLineDAO(MybatisConnectionFactory.getSqlSessionFactory());
        GuideLineDTO guideLineDTO = guideLineDAO.guideLineSelectPrint("xss");
        String path = guideLineDTO.getPath();

        ServerSocket server = new ServerSocket(8888);

        System.out.println("ready........");

        Socket clientSocket = server.accept();

        System.out.println(clientSocket);

        OutputStream out = clientSocket.getOutputStream();


        InputStream fin = new FileInputStream(path); // 파일 경로

        while(true){

            int data = fin.read();

            out.write(data);

            if(data == -1){
                break;
            }

        }


        fin.close();

        //flush
        out.flush();

        //close
        out.close();
        clientSocket.close();
        server.close();

    }
}