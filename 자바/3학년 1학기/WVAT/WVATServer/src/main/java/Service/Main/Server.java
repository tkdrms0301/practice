package Service.Main;

import Network.MSG;
import Network.Protocol;
import Persistence.DAO.UserDAO;
import Persistence.DTO.UserDTO;
import Persistence.MybatisConnectionFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server implements Runnable {
    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;
    Protocol protocol = null;
    InspectionService inspectionService;
    GuideLineService guideLineService;
    ReportsService reportsService;

    byte receiveHeader[];

    public Server(Socket socket) {
        this.socket = socket;
        run();
    }

    @Override
    public void run() {
        try {
            System.out.println("실행됨");
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            byte[] receiveHeader = new byte[MSG.HEADER];

            while (true) {
                dis.read(receiveHeader);
                System.out.println("패킷 읽어들임");
                System.out.println(receiveHeader[0]);
                System.out.println(receiveHeader[1]);

                switch (receiveHeader[0]) {
                    case MSG.PT_TYPE_REQUEST:       // 요청 타입
                        receiveRequest(receiveHeader[1]);
                        break;

                    case MSG.PT_TYPE_SEND:              // 정보 전송 타입
                        receiveSend(receiveHeader[1]);
                        break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveRequest(int header) {
        switch (header) {
            case MSG.PT_REQ_SIGN_UP:                      // 회원가입 요청
                SignService signService = new SignService(dis, dos, socket, receiveHeader);
                signService.signResponse();
                break;
            case MSG.PT_REQ_SIGN_IN:                      // 로그인 요청
                LoginService loginService = new LoginService(dis, dos, socket, receiveHeader);
                loginService.loginResponse();
                break;
            case MSG.PT_REQ_MY_INSPECTION_RECORD:         // 내 점검기록 요청
                reportsService = new ReportsService(dis, dos, socket, receiveHeader);
                reportsService.myReportListResponse();
                break;
            case MSG.PT_REQ_MY_REPORT_DOWNLOAD:              // 내 레포트 다운로드 요청
                reportsService.myReportDownloadResponse();
                break;
            case MSG.PT_REQ_INSPECTION:                   // 진단 요청
                inspectionService = new InspectionService(dis, dos, socket, receiveHeader);
                inspectionService.inspectionResponse();
                break;
            case MSG.PT_REQ_INSPECTION_PROGRESS:          // 진단 진행률 요청
                inspectionService.inspectionProgressResponse();
                break;
            case  MSG.PT_REQ_REPORT_DOWNLOAD:          //방금 검사한 레포트 요청
                reportsService = new ReportsService(dis, dos, socket, receiveHeader);
                reportsService.reportDownloadResonse();
                break;
            case MSG.PT_REQ_GUIDELINE:                    // 가이드라인 목록 요청
                guideLineService = new GuideLineService(dis, dos, socket, receiveHeader);
                guideLineService.listResponse();
                break;
            case MSG.PT_REQ_GUIDELINE_DOWNLOAD:          // 가이드라인 다운로드 요청
                guideLineService.downloadResponse();
                break;
        }
    }
    
    private void receiveSend(int header) {
            switch (header) {
                case MSG.PT_SEND_SIGN_UP:                     // 회원가입 정보전송:
                    SignService signService = new SignService(dis, dos, socket, receiveHeader);
                    signService.signUp();
                    break;
                case MSG.PT_SEND_SIGN_IN:                     // 로그인 정보전송:
                    LoginService loginService = new LoginService(dis, dos, socket, receiveHeader);
                    loginService.login();
                    break;
            }
    }
    
    public static void main(String[] args) {
        ServerSocket sSocket = null;
        Socket socket = null;
        try {
            sSocket = new ServerSocket( 3000);      // Client와 연결을 담당하는 ServerSocket
            while (true) {
                System.out.println("접속 대기중");
                socket = sSocket.accept();                       // 데이터 주고받는 Socket
                System.out.println("접속 성공");

                new Server(socket);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try{
            sSocket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
