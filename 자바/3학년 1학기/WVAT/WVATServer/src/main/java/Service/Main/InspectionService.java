package Service.Main;

import InspectionTool.Modules;
import Network.MSG;
import Network.Protocol;
import Persistence.DAO.InspectionRecordsDAO;
import Persistence.DAO.UserDAO;
import Persistence.DTO.InspectionRecordsDTO;
import Persistence.MybatisConnectionFactory;
import URLParser.URLList;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

public class InspectionService extends MainService{
    public InspectionService(DataInputStream dis, DataOutputStream dos, Socket socket, byte[] receiveHeader) {
        super(dis, dos, socket, receiveHeader);
    }

    String domain;
    boolean[] originalCheck;
    boolean[] check;
    String  inspectionType;
    boolean isAllFalse = false;

    public void inspectionResponse(){
        try {
            protocol = new Protocol(MSG.PT_TYPE_RESPOND);
            receiveHeader = new byte[MSG.FIXED_LEN];

            dis.read(receiveHeader);
            domain = changeByteToString(receiveHeader);
            dis.read(receiveHeader);
            originalCheck =  changeByteToBoolean(receiveHeader);
            dis.read(receiveHeader);
            inspectionType = changeByteToString(receiveHeader);

            protocol.setResponse(MSG.PT_RES_SUCESS);
            dos.write(protocol.getPacket(), 0, protocol.getSize());     //응답 전송
            dos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void inspectionProgressResponse(){
        check = new boolean[originalCheck.length];
        for(int i = 0; i < originalCheck.length; i++){
            check[i] = originalCheck[i];
        }
        //Inspection 데이터베이스에서 도메인 조회 후, null인 검사 결과만 진행
        InspectionRecordsDAO inspectionRecordsDAO = new InspectionRecordsDAO(MybatisConnectionFactory.getSqlSessionFactory());
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();

        //해당 도메인이 있는지 확인
        inspectionRecordsDTO = inspectionRecordsDAO.domainSelectPrint(domain);
        Modules modules = new Modules();

        //도메인이 존재하지 않거나 하루가 지났다면
        if(inspectionRecordsDTO == null){
            run(modules); // 클라이언트가 체크한 부분 전체 실행
            inspectionRecordsDAO.inspectionRecordCreate(domain, LocalDate.now());
            reRecords(domain, modules);
        }else if(!(inspectionRecordsDTO.getDate().isEqual(LocalDate.now()))){
            run(modules); // 클라이언트가 체크한 부분 전체 실행
            inspectionRecordsDAO.inspectionRecordInit(domain, LocalDate.now());
            reRecords(domain, modules);
        }else {// 해당 기능 검사결과가 존재한다면 검사 안하게 무조건 false
            if (inspectionRecordsDTO.getXssOutput() != null)                              check[0] = false;
            if (inspectionRecordsDTO.getSqlInjectionOutput() != null)                    check[1] = false;
            if (inspectionRecordsDTO.getOsCommandOutput() != null)                  check[2] = false;
            if (inspectionRecordsDTO.getAdminExposeOutput() != null)                 check[3] = false;
            if (inspectionRecordsDTO.getLocationDisclosureOutput() != null)         check[4] = false;
            if (inspectionRecordsDTO.getPathTrackingOutput() != null)                  check[5] = false;
            if (inspectionRecordsDTO.getDirectoryIndexingOutput() != null)            check[6] = false;
            if (inspectionRecordsDTO.getInformationLeakageOutput() != null)         check[7] = false;
            if (inspectionRecordsDTO.getPlainTextOutput() != null)                       check[8] = false;
            if (inspectionRecordsDTO.getWebMethodOutput() != null)                   check[9] = false;
            if (inspectionRecordsDTO.getCveOutput() != null)                              check[10] = false;

            if(check[0] == false && check[1] == false && check[2] == false && check[3] == false && check[4] == false && check[5] == false && check[6] == false && check[7] == false && check[8] == false && check[9] == false && check[10] == false)
                inspectionType = "single";

            run(modules);  //실행

            //inspection 기록
            if(check[0]) inspectionRecordsDAO.xssSet(domain, modules.getXssResult().getInputValue(), modules.getXssResult().getResult());
            if(check[1]) inspectionRecordsDAO.sqlInjectionSet(domain, modules.getSqlInjectionResult().getInputValue(), modules.getSqlInjectionResult().getResult());
            if(check[2]) inspectionRecordsDAO.osCommandSet(domain, modules.getOsCommandResult().getInputValue(), modules.getOsCommandResult().getResult());
            if(check[3]) inspectionRecordsDAO.adminExposeSet(domain, modules.getAdminExposeResult().getInputValue(), modules.getAdminExposeResult().getResult());
            if(check[4]) inspectionRecordsDAO.locationDisclosureSet(domain, modules.getLocationDisclosureResult().getInputValue(), modules.getLocationDisclosureResult().getResult());
            if(check[5]) inspectionRecordsDAO.pathTrackingSet(domain, modules.getPathTrackingResult().getInputValue(), modules.getPathTrackingResult().getResult());
            if(check[6]) inspectionRecordsDAO.directoryIndexingSet(domain, modules.getDirectoryIndexingResult().getInputValue(), modules.getDirectoryIndexingResult().getResult());
            if(check[7]) inspectionRecordsDAO.informationLeakageSet(domain, modules.getInformationLeakageResult().getInputValue(), modules.getInformationLeakageResult().getResult());
            if(check[8]) inspectionRecordsDAO.plainTextSet(domain, modules.getPlainTextResult().getInputValue(), modules.getPlainTextResult().getResult());
            if(check[9]) inspectionRecordsDAO.webMethodSet(domain, modules.getWebMethodResult().getInputValue(), modules.getWebMethodResult().getResult());
            if(check[10]) inspectionRecordsDAO.cveSet(domain, modules.getCveResult().getInputValue(), modules.getCveResult().getResult());
        }
        inspectionRecordsDTO = inspectionRecordsDAO.domainSelectPrint(domain);

        //취약점 결과(입력값, 출력값)
        String input[] = {inspectionRecordsDTO.getXssInput(), inspectionRecordsDTO.getSqlInjectionInput(), inspectionRecordsDTO.getOsCommandInput(), inspectionRecordsDTO.getAdminExposeInput()
                , inspectionRecordsDTO.getLocationDisclosureInput(), inspectionRecordsDTO.getPathTrackingInput(), inspectionRecordsDTO.getDirectoryIndexingInput()
                , inspectionRecordsDTO.getInformationLeakageInput(), inspectionRecordsDTO.getPlainTextInput(), inspectionRecordsDTO.getWebMethodInput()
                , inspectionRecordsDTO.getCveInput()};

        String output[] = {inspectionRecordsDTO.getXssOutput(), inspectionRecordsDTO.getSqlInjectionOutput(), inspectionRecordsDTO.getOsCommandOutput(), inspectionRecordsDTO.getAdminExposeOutput()
                , inspectionRecordsDTO.getLocationDisclosureOutput(), inspectionRecordsDTO.getPathTrackingOutput(), inspectionRecordsDTO.getDirectoryIndexingOutput(),
                inspectionRecordsDTO.getInformationLeakageOutput(), inspectionRecordsDTO.getPlainTextOutput(), inspectionRecordsDTO.getWebMethodOutput()
                , inspectionRecordsDTO.getCveOutput()};

        ReportsService reportsService = new ReportsService(dis, dos, socket, receiveHeader);
        reportsService.reportOut(domain, input, output, originalCheck); //리포트 도메인 주소, 입력값, 결과값, 원하는 검사항목
    }

    public void reRecords(String domain, Modules modules) {
        InspectionRecordsDAO inspectionRecordsDAO = new InspectionRecordsDAO(MybatisConnectionFactory.getSqlSessionFactory());
        //DB에 기록
        inspectionRecordsDAO.xssSet(domain, modules.getXssResult().getInputValue(), modules.getXssResult().getResult());
        inspectionRecordsDAO.sqlInjectionSet(domain, modules.getSqlInjectionResult().getInputValue(), modules.getSqlInjectionResult().getResult());
        inspectionRecordsDAO.osCommandSet(domain, modules.getOsCommandResult().getInputValue(), modules.getOsCommandResult().getResult());
        inspectionRecordsDAO.adminExposeSet(domain, modules.getAdminExposeResult().getInputValue(), modules.getAdminExposeResult().getResult());
        inspectionRecordsDAO.locationDisclosureSet(domain, modules.getLocationDisclosureResult().getInputValue(), modules.getLocationDisclosureResult().getResult());
        inspectionRecordsDAO.pathTrackingSet(domain, modules.getPathTrackingResult().getInputValue(), modules.getPathTrackingResult().getResult());
        inspectionRecordsDAO.directoryIndexingSet(domain, modules.getDirectoryIndexingResult().getInputValue(), modules.getDirectoryIndexingResult().getResult());
        inspectionRecordsDAO.informationLeakageSet(domain, modules.getInformationLeakageResult().getInputValue(), modules.getInformationLeakageResult().getResult());
        inspectionRecordsDAO.plainTextSet(domain, modules.getPlainTextResult().getInputValue(), modules.getPlainTextResult().getResult());
        inspectionRecordsDAO.webMethodSet(domain, modules.getWebMethodResult().getInputValue(), modules.getWebMethodResult().getResult());
        inspectionRecordsDAO.cveSet(domain, modules.getCveResult().getInputValue(), modules.getCveResult().getResult());
    }

    public void run(Modules modules){
        try{
            if(inspectionType.equals("multi")){
                URLList urlList = new URLList(domain);
                urlList.run();
                List<String> domainList = urlList.toUrlList();

                float progress = 0;

                for(int i = 0; i < domainList.size(); i++){
                    modules.setDomain(domainList.get(i));
                    modules.run(check);

                    protocol = new Protocol(MSG.PT_TYPE_RESPOND);
                    protocol.setResponse(MSG.PT_RES_INSPECTION_PROGRESS);
                    progress = (float) (i + 1) / domainList.size();
                    protocol.setStrToByte(String.valueOf(progress));
                    protocol.setStrToByte(domainList.get(i));
                    dos.write(protocol.getPacket(), 0, protocol.getSize());     //응답 전송
                    dos.flush();
                }

            }else if(inspectionType.equals("single")){
                modules.setDomain(domain);
                modules.run(check);

                protocol = new Protocol(MSG.PT_TYPE_RESPOND);
                protocol.setResponse(MSG.PT_RES_INSPECTION_PROGRESS);

                protocol.setStrToByte("1");
                protocol.setStrToByte(domain);
                dos.write(protocol.getPacket(), 0, protocol.getSize());     //응답 전송
                dos.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
