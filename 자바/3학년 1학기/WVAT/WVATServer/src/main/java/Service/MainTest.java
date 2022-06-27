package Service;

import InspectionTool.Modules;
import Persistence.DAO.InspectionRecordsDAO;
import Persistence.DAO.PayLoadDAO;
import Persistence.DAO.ReportDAO;
import Persistence.DTO.InspectionRecordsDTO;
import Persistence.DTO.PayLoadDTO;
import Persistence.DTO.ReportDTO;
import Persistence.MybatisConnectionFactory;
import URLParser.URLList;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.*;

public class MainTest {

    final static String OSCommand = "OSCommand";
    final static String SQLInjection = "SQLInjection";
    final static String CVE = "CVE";
    final static String DirectoryIndexing = "DirectoryIndexing";
    final static String InformationLeakage = "InformationLeakage";
    final static String XSS = "XSS";
    final static String FormatString = "FormatString";
    final static String PathTracking = "PathTracking";
    final static String AdminExpose = "AdminExpose";
    final static String LocationDisclosure = "LocationDisclosure";
    final static String PlainText = "PlainText";
    final static String WebMethod = "WebMethod";

    static boolean OSCommandCheck = false;
    static boolean SQLInjectionCheck = false;
    static boolean CVECheck = false;
    static boolean DirectoryIndexingCheck = false;
    static boolean InformationLeakageCheck = false;
    static boolean XSSCheck = false;
    static boolean FormatStringCheck = false;
    static boolean PathTrackingCheck = false;
    static boolean AdminExposeCheck = false;
    static boolean LocationDisclosureCheck = false;
    static boolean PlainTextCheck = false;
    static boolean WebMethodCheck = false;

    public static void main(String[] args) throws JRException, IOException, MalformedURLException {

        String domain = "http://se.kumoh.ac.kr/";
        /*String domain = "https://www.hsd.co.kr";*//*
        String domain = "http://testphp.vulnweb.com";*/


/*        //단일 페이지
        Modules modules = new Modules();
        modules.setDomain(domain);*//*
       selectNum(modules);*//*
         modules.run();*/



        // 3개 페이지 실험
/*        String[] url = {"http://testphp.vulnweb.com", "http://testphp.vulnweb.com/login.php", "http://testphp.vulnweb.com/AJAX/index.php"};
        //String[] url = {"http://se.kumoh.ac.kr", "http://se.kumoh.ac.kr/board_AWPX69   ", "http://se.kumoh.ac.kr/freeboard/83432"};
        Modules modules = new Modules();
        for(String eurl : url){
            modules.setDomain(eurl);
            modules.run();
        }*/

        boolean checkInspection[] = {true, true,true,true,true,true,true,true,true,true,true};
        //재귀 URL
        URLList urlList = new URLList(domain);
        urlList.run();
        List<String> arr = urlList.toUrlList();

        Modules modules = new Modules();
        for (int i = 0; i < arr.size(); i++) {
            modules.setDomain(arr.get(i));
            modules.run(checkInspection);
            // int progress = 0;
            // setProgress((i + 1) / arr.size() * 100);
        }

        InspectionRecordsDAO inspectionRecordsDAO = new InspectionRecordsDAO(MybatisConnectionFactory.getSqlSessionFactory());
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();

        //해당 도메인이 있는지 확인
        inspectionRecordsDTO = inspectionRecordsDAO.domainSelectPrint(domain);

        if (inspectionRecordsDTO == null) { // 도메인이 존재하지 않는다면
            inspectionRecordsDAO.inspectionRecordCreate(domain, LocalDate.now());
            reRecords(domain, modules);
        } else if (!(inspectionRecordsDTO.getDate().isEqual(LocalDate.now()))) {    //해당 도메인의 검사 결과가 하루가 지났다면
            inspectionRecordsDAO.inspectionRecordInit(domain, LocalDate.now());
            reRecords(domain, modules);
        } else {   //같은 날 검사했다면
            if (inspectionRecordsDTO.getXssOutput() == null) { // 해당 기능 검사결과가 존재한다면 삽입 금지
                inspectionRecordsDAO.xssSet(domain, modules.getXssResult().getInputValue(), modules.getXssResult().getResult());
            }
            if (inspectionRecordsDTO.getSqlInjectionOutput() == null) {
                inspectionRecordsDAO.sqlInjectionSet(domain, modules.getSqlInjectionResult().getInputValue(), modules.getSqlInjectionResult().getResult());
            }
            if (inspectionRecordsDTO.getOsCommandOutput() == null) {
                inspectionRecordsDAO.osCommandSet(domain, modules.getOsCommandResult().getInputValue(), modules.getOsCommandResult().getResult());
            }
            if (inspectionRecordsDTO.getAdminExposeOutput() == null) {
                inspectionRecordsDAO.adminExposeSet(domain, modules.getAdminExposeResult().getInputValue(), modules.getAdminExposeResult().getResult());
            }
            if (inspectionRecordsDTO.getLocationDisclosureOutput() == null) {
                inspectionRecordsDAO.locationDisclosureSet(domain, modules.getLocationDisclosureResult().getInputValue(), modules.getLocationDisclosureResult().getResult());
            }
            if (inspectionRecordsDTO.getPathTrackingOutput() == null) {
                inspectionRecordsDAO.pathTrackingSet(domain, modules.getPathTrackingResult().getInputValue(), modules.getPathTrackingResult().getResult());
            }
            if (inspectionRecordsDTO.getDirectoryIndexingOutput() == null) {
                inspectionRecordsDAO.directoryIndexingSet(domain, modules.getDirectoryIndexingResult().getInputValue(), modules.getDirectoryIndexingResult().getResult());
            }
            if (inspectionRecordsDTO.getInformationLeakageOutput() == null) {
                inspectionRecordsDAO.informationLeakageSet(domain, modules.getInformationLeakageResult().getInputValue(), modules.getInformationLeakageResult().getResult());
            }
            if (inspectionRecordsDTO.getPlainTextOutput() == null) {
                inspectionRecordsDAO.plainTextSet(domain, modules.getPlainTextResult().getInputValue(), modules.getPlainTextResult().getResult());
            }
            if (inspectionRecordsDTO.getWebMethodOutput() == null) {
                inspectionRecordsDAO.webMethodSet(domain, modules.getWebMethodResult().getInputValue(), modules.getWebMethodResult().getResult());
            }
            if (inspectionRecordsDTO.getCveOutput() == null) {
                inspectionRecordsDAO.cveSet(domain, modules.getCveResult().getInputValue(), modules.getCveResult().getResult());
            }
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

        //실제로는 클라이언트가 보낸 check 결과에 따라 값 변경
        XSSCheck = true; SQLInjectionCheck = true; OSCommandCheck = true; AdminExposeCheck = true;
        LocationDisclosureCheck = true; PathTrackingCheck = true; DirectoryIndexingCheck =true;
        InformationLeakageCheck = true; PlainTextCheck = true; WebMethodCheck = true; CVECheck =true;

        boolean[] check = {XSSCheck, SQLInjectionCheck, OSCommandCheck, AdminExposeCheck, LocationDisclosureCheck, PathTrackingCheck
                ,DirectoryIndexingCheck, InformationLeakageCheck, PlainTextCheck, WebMethodCheck, CVECheck};

        reportOut(domain, input, output, check); //리포트 도메인 주소, 입력값, 결과값, 원하는 검사항목
    }

    public static void reRecords(String domain, Modules modules) {
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

    public static void selectNum(Modules modules) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. " + SQLInjection);
        System.out.println("2. " + OSCommand);
        System.out.println("3. " + CVE);
        System.out.println("4. " + DirectoryIndexing);
        System.out.println("5. " + InformationLeakage);
        System.out.println("6. " + XSS);
        System.out.println("7. " + PathTracking);
        System.out.println("8. " + AdminExpose);
        System.out.println("9. " + LocationDisclosure);
        System.out.println("10. " + PlainText);
        System.out.println("11. " + WebMethod);
        System.out.print("검사하고 싶은 취약점 선택 : ");
        String str = sc.nextLine();
        String selectNum[] = str.split(", ");

        modules.select(selectNum);
    }

    //리포트 예와 함수
    public static String[] resultException(String[] array, boolean[] check) {
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
    public static void reportOut(String domain, String[] input, String[] output, boolean[] check) throws JRException {
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
        String fileName = domainSplit[1] + "_" + LocalDate.now().toString() + ".docx";

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
    }

}
