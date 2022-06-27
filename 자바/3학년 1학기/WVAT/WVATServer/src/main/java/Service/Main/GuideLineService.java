package Service.Main;

import Network.MSG;
import Network.Protocol;
import Persistence.DAO.GuideLineDAO;
import Persistence.DAO.ReportDAO;
import Persistence.DAO.UserDAO;
import Persistence.DTO.GuideLineDTO;
import Persistence.DTO.ReportDTO;
import Persistence.MybatisConnectionFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GuideLineService extends MainService{
    public GuideLineService(DataInputStream dis, DataOutputStream dos, Socket socket, byte[] receiveHeader) {
        super(dis, dos, socket, receiveHeader);
    }

    public void listResponse(){
        try {

            protocol = new Protocol(MSG.PT_TYPE_RESPOND);
            protocol.setResponse(MSG.PT_RES_GUIDELINE_RECORD_SEND);

            List<GuideLineDTO> guideLineDTOS = new ArrayList<>();
            GuideLineDAO guideLineDAO = new GuideLineDAO(MybatisConnectionFactory.getSqlSessionFactory());

            guideLineDTOS = guideLineDAO.guideLineAllPrint();

            protocol.setStrToByte(String.valueOf(guideLineDTOS.size()));
            for(int i = 0; i < guideLineDTOS.size(); i++){
                protocol.setStrToByte(guideLineDTOS.get(i).getDate().toString());
            }

            dos.write(protocol.getPacket(), 0, protocol.getSize());     //응답 전송
            dos.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void downloadResponse(){
        try {
            protocol = new Protocol(MSG.PT_TYPE_RESPOND);
            protocol.setResponse(MSG.PT_RES_GUIDELINE_SEND);
            receiveHeader = new byte[MSG.FIXED_LEN];

            dis.read(receiveHeader);
            String vulnerabilityType = changeByteToString(receiveHeader);

            GuideLineDTO guideLineDTO = new GuideLineDTO();
            GuideLineDAO guideLineDAO = new GuideLineDAO(MybatisConnectionFactory.getSqlSessionFactory());

            guideLineDTO = guideLineDAO.guideLineSelectPrint(vulnerabilityType);
            File file = new File(guideLineDTO.getPath()); // 파일 경로
            protocol.setFiletoByte(file);

            dos.write(protocol.getPacket(), 0, protocol.getSize());     //응답 전송
            dos.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
