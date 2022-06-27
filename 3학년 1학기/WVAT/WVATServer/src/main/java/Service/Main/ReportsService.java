package Service.Main;

import Network.MSG;
import Network.Protocol;
import Persistence.DAO.GuideLineDAO;
import Persistence.DAO.InspectionRecordsDAO;
import Persistence.DAO.ReportDAO;
import Persistence.DAO.UserDAO;
import Persistence.DTO.GuideLineDTO;
import Persistence.DTO.InspectionRecordsDTO;
import Persistence.DTO.ReportDTO;
import Persistence.DTO.UserDTO;
import Persistence.MybatisConnectionFactory;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportsService extends MainService{

    public ReportsService(DataInputStream dis, DataOutputStream dos, Socket socket, byte[] receiveHeader) {
        super(dis, dos, socket, receiveHeader);
    }

    @SneakyThrows
    public void myReportListResponse(){
        protocol = new Protocol(MSG.PT_TYPE_RESPOND);
        protocol.setResponse(MSG.PT_RES_MY_REPORT_RECORD_SEND);

        InspectionRecordsDAO inspectionRecordsDAO = new InspectionRecordsDAO(MybatisConnectionFactory.getSqlSessionFactory());
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();

        List<ReportDTO> reportDTOS = new ArrayList<>();
        ReportDAO reportDAO = new ReportDAO(MybatisConnectionFactory.getSqlSessionFactory());

        reportDTOS = reportDAO.reportByUserPrint(getUserNum());

        protocol.setStrToByte(String.valueOf(reportDTOS.size()));
        for(int i = 0; i < reportDTOS.size(); i++){
            inspectionRecordsDTO = inspectionRecordsDAO.domainNumSelectPrint(reportDTOS.get(i).getDomainNum());
            protocol.setStrToByte(reportDTOS.get(i).getReportNum() + "");
            protocol.setStrToByte(inspectionRecordsDTO.getDomain());
            protocol.setStrToByte(reportDTOS.get(i).getDate().toString());
        }

        dos.write(protocol.getPacket(), 0, protocol.getSize());     //응답 전송
        dos.flush();
    }

    public void myReportDownloadResponse(){
        try {
            protocol = new Protocol(MSG.PT_TYPE_RESPOND);
            protocol.setResponse(MSG.PT_RES_REPORT_SEND);
            receiveHeader = new byte[MSG.FIXED_LEN];

            dis.read(receiveHeader);
            String reportNum = changeByteToString(receiveHeader);

            ReportDTO reportDTO = new ReportDTO();
            ReportDAO reportDAO = new ReportDAO(MybatisConnectionFactory.getSqlSessionFactory());

            reportDTO = reportDAO.reportByReportNum(Integer.parseInt(reportNum));
            File file = new File(reportDTO.getReportPath()); // 파일 경로
            protocol.setFiletoByte(file);

            dos.write(protocol.getPacket(), 0, protocol.getSize());     //응답 전송
            dos.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void reportDownloadResonse(){
        try {
            protocol = new Protocol(MSG.PT_TYPE_RESPOND);
            protocol.setResponse(MSG.PT_RES_REPORT_SEND);

            ReportDTO reportDTO = new ReportDTO();
            ReportDAO reportDAO = new ReportDAO(MybatisConnectionFactory.getSqlSessionFactory());

            reportDTO = reportDAO.recentReportByUserNumPrint(getUserNum());
            File file = new File(reportDTO.getReportPath()); // 파일 경로
            protocol.setFiletoByte(file);

            dos.write(protocol.getPacket(), 0, protocol.getSize());     //응답 전송
            dos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //리포트 예와 함수
    public String[] resultException(String[] array, boolean[] check) {
        for (int i = 0; i < array.length; i++) {
            if (!check[i] || array[i] == null) {
                array[i] = "검사 안함";
            }
            if (array[i].equals(" ")) {
                array[i] = "취약점 미 발견";
            }
        }
        return array;
    }

    //리포트 생성 함수
    public void reportOut(String domain, String[] input, String[] output, boolean[] check) throws JRException {
        List jasperPrintList = new ArrayList();
        JasperReport jasperReport = null;

        jasperReport = JasperCompileManager.compileReport("C:\\Users\\tprud\\IdeaProjects\\WVAT\\src\\main\\java\\Persistence\\Reports\\sample1.jrxml");
        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("domain", domain);
        parameters.put("date", LocalDate.now().toString());

        input = resultException(input, check);
        output = resultException(output, check);

        parameters.put("xssInput", input[0]);
        parameters.put("xssOutput", output[0]);
        parameters.put("sqlInjectionInput", input[1]);
        parameters.put("sqlInjectionOutput", output[1]);
        parameters.put("osCommandInput", input[2]);
        parameters.put("osCommandOutput", output[2]);
        parameters.put("adminExposeInput", input[3]);
        parameters.put("adminExposeOutput", output[3]);
        parameters.put("locationDisclosureInput", input[4]);
        parameters.put("locationDisclosureOutput", output[4]);

        JasperPrint jasperPrint = null;
        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
        jasperPrintList.add(jasperPrint);

        jasperReport = JasperCompileManager.compileReport("C:\\Users\\tprud\\IdeaProjects\\WVAT\\src\\main\\java\\Persistence\\Reports\\sample2.jrxml");

        parameters.put("pathTrackingInput", input[5]);
        parameters.put("pathTrackingOutput", output[5]);
        parameters.put("directoryIndexingInput", input[6]);
        parameters.put("directoryIndexingOutput", output[6]);
        parameters.put("informationLeakageInput", input[7]);
        parameters.put("informationLeakageOutput", output[7]);
        parameters.put("planTextInput", input[8]);
        parameters.put("planTextOutput", output[8]);

        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
        jasperPrintList.add(jasperPrint);

        // 저장할 파일명 데이터 다듬기
        String[] domainSplit = domain.split("//|/");
        String dateFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
        String fileName =  domainSplit[1] + "_" +dateFormat + ".docx";

        jasperReport = JasperCompileManager.compileReport("C:\\Users\\tprud\\IdeaProjects\\WVAT\\src\\main\\java\\Persistence\\Reports\\sample3.jrxml");

        parameters.put("webMethodInput", input[9]);
        parameters.put("webMethodOutput", output[9]);
        parameters.put("cveInput", input[10]);
        parameters.put("cveOutput", output[10]);

        jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
        jasperPrintList.add(jasperPrint);

        JRDocxExporter export = new JRDocxExporter();
        export.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\Users\\tprud\\IdeaProjects\\WVAT\\src\\main\\java\\Persistence\\Reports\\" + fileName);
        export.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
/*     export.setExporterInput(new SimpleExporterInput(jasperPrint));
        export.setExporterOutput(new SimpleOutputStreamExporterOutput("C:\\Users\\tprud\\IdeaProjects\\WVAT\\src\\main\\java\\Persistence\\Reports\\sample1.docx"));*/

        export.exportReport();

        ReportDAO reportDAO = new ReportDAO(MybatisConnectionFactory.getSqlSessionFactory());

        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        InspectionRecordsDAO inspectionRecordsDAO = new InspectionRecordsDAO(MybatisConnectionFactory.getSqlSessionFactory());
        inspectionRecordsDTO = inspectionRecordsDAO.domainSelectPrint(domain);
        String path = "C:\\Users\\tprud\\IdeaProjects\\WVAT\\src\\main\\java\\Persistence\\Reports\\"+ fileName;
        reportDAO.reportCreate(getUserNum(), inspectionRecordsDTO.getDomainNum(), LocalDate.now(), path);
    }
}
