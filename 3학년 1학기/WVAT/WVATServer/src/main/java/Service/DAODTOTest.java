package Service;

import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.net.MalformedURLException;

public class DAODTOTest {
    public static void main(String[] args) throws JRException, IOException, MalformedURLException {




/*        // User 생성 및 User 선택 출력
        UserDAO UserDAO = new UserDAO(MybatisConnectionFactory.getSqlSessionFactory());
        UserDTO userDTO = UserDAO.userSelectPrint("SSK");
        System.out.println("User Num : " + userDTO.getUserNum() + "\nUser ID : " +  userDTO.getUserID());
        System.out.println();

        String createUserID = "GMJ";
        String createUserEmail = "rlahWL@naver.com";
        if(UserDAO.userSelectPrint(createUserID) == null){
            userDTO.setUserID(createUserID);
            userDTO.setEmail(createUserEmail);
            UserDAO.userCreate(userDTO);
        }

        userDTO = UserDAO.userSelectPrint("GMJ");
        System.out.println("User Num : " + userDTO.getUserNum() + "\nUser ID : " +  userDTO.getUserID());
        System.out.println();*/

        // Inspection Records 생성 및 domain 선택 출력
/*        InspectionRecordsDAO inspectionRecordsDAO = new InspectionRecordsDAO(MybatisConnectionFactory.getSqlSessionFactory());
        InspectionRecordsDTO inspectionRecordsDTO;
        String domain;*/

        //naver domain 조회
/*     domain = "naver.com";
        inspectionRecordsDTO = inspectionRecordsDAO.domainSelectPrint(domain);
        System.out.println("Domain Num : " + inspectionRecordsDTO.getDomainNum() + "\nDomain : " + inspectionRecordsDTO.getDomain()+ "\nDate : " + inspectionRecordsDTO.getDate());
        System.out.println();

        //google domain 생성
        domain = "www.google.com";
         inspectionRecordsDTO = inspectionRecordsDAO.domainSelectPrint(domain);
        if(inspectionRecordsDAO.domainSelectPrint(domain) == null){
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setDate(java.sql.Date.valueOf(LocalDate.now()));
            inspectionRecordsDAO.inspectionRecordCreate(inspectionRecordsDTO);
        }*/


  /*      //도메인에 대한 결과 수정
        inspectionRecordsDTO = inspectionRecordsDAO.domainSelectPrint("www.google.com");
        if(inspectionRecordsDTO.getDomain() != null){
            inspectionRecordsDTO.setOsCommand("결과 잼잼");
            inspectionRecordsDAO.osCommandSet(inspectionRecordsDTO);
        }

        //google 도메인 조회
        inspectionRecordsDTO = inspectionRecordsDAO.domainSelectPrint("www.google.com");
        System.out.println("Domain Num : " + inspectionRecordsDTO.getDomainNum() + "\nDomain : " + inspectionRecordsDTO.getDomain()+ "\nDate : " + inspectionRecordsDTO.getDate()+ "\nresult : " + inspectionRecordsDTO.getOsCommand());
        System.out.println();

        //도메인 결과 초기화
       if(inspectionRecordsDAO.domainSelectPrint("www.google.com") != null){
           inspectionRecordsDTO.setDomain("www.google.com");
           inspectionRecordsDTO.setDate(java.sql.Date.valueOf(LocalDate.now()));
           inspectionRecordsDAO.inspectionRecordInit(inspectionRecordsDTO);
       }

        //초기화 된 도메인 조회
        inspectionRecordsDTO = inspectionRecordsDAO.domainSelectPrint("www.google.com");
        System.out.println("Domain Num : " + inspectionRecordsDTO.getDomainNum() + "\nDomain : " + inspectionRecordsDTO.getDomain()+ "\nDate : " + inspectionRecordsDTO.getDate()+ "\nresult : " + inspectionRecordsDTO.getOsCommand());
        System.out.println();*/



    }
}
