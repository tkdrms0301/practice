package Persistence.DAO;

import Persistence.DTO.InspectionRecordsDTO;
import Persistence.PooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sql.DataSource;
import java.time.LocalDate;

public class InspectionRecordsDAO {
    private final DataSource ds = PooledDataSource.getDataSource();
    private SqlSessionFactory sqlSessionFactory = null;

    public InspectionRecordsDAO(SqlSessionFactory sqlSessionFactory){this.sqlSessionFactory = sqlSessionFactory;}


    public void inspectionRecordCreate(String domain, LocalDate date){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try{
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setDate(date);
            session.insert("mapper.InspectionRecords.inspectionRecordCreate", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public InspectionRecordsDTO domainSelectPrint(String domain) {
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO = session.selectOne("mapper.InspectionRecords.domainSelectPrint", domain);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return inspectionRecordsDTO;
    }

    public InspectionRecordsDTO domainNumSelectPrint(int domainNum) {
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO = session.selectOne("mapper.InspectionRecords.domainNumSelectPrint", domainNum);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return inspectionRecordsDTO;
    }

    public void inspectionRecordInit(String domain, LocalDate date){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setDate(date);
            session.update("mapper.InspectionRecords.inspectionRecordsInit", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public void osCommandSet(String domain, String osCommandInput, String osCommandOutput){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setOsCommandInput(osCommandInput);
            inspectionRecordsDTO.setOsCommandOutput(osCommandOutput);
            session.update("mapper.InspectionRecords.osCommandSet", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public void sqlInjectionSet(String domain, String sqlinjectionInput, String sqlinjectionOutput){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setSqlInjectionInput(sqlinjectionInput);
            inspectionRecordsDTO.setSqlInjectionOutput(sqlinjectionOutput);

            session.update("mapper.InspectionRecords.sqlInjectionSet", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public void cveSet(String domain, String cveInput, String cveOutput){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setCveInput(cveInput);
            inspectionRecordsDTO.setCveOutput(cveOutput);

            session.update("mapper.InspectionRecords.cveSet", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }


    public void directoryIndexingSet(String domain, String directoryIndexingInput, String directoryIndexingOutput){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setDirectoryIndexingInput(directoryIndexingInput);
            inspectionRecordsDTO.setDirectoryIndexingOutput(directoryIndexingOutput);

            session.update("mapper.InspectionRecords.directoryIndexingSet", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public void informationLeakageSet(String domain, String informationLeakageInput, String informationLeakageOutput){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setInformationLeakageInput(informationLeakageInput);
            inspectionRecordsDTO.setInformationLeakageOutput(informationLeakageOutput);

            session.update("mapper.InspectionRecords.informationLeakageSet", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public void xssSet(String domain, String xssInput, String xssOutput){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setXssInput(xssInput);
            inspectionRecordsDTO.setXssOutput(xssOutput);

            session.update("mapper.InspectionRecords.xssSet", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public void pathTrackingSet(String domain, String pathTrackingInput, String pathTrackingOutput){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setPathTrackingInput(pathTrackingInput);
            inspectionRecordsDTO.setPathTrackingOutput(pathTrackingOutput);

            session.update("mapper.InspectionRecords.pathTrackingSet", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public void adminExposeSet(String domain, String adminExpooseInput, String adminExpooseOutput){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setAdminExposeInput(adminExpooseInput);
            inspectionRecordsDTO.setAdminExposeOutput(adminExpooseOutput);

            session.update("mapper.InspectionRecords.adminExposeSet", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public void locationDisclosureSet(String domain, String locationDisclosureInput, String locationDisclosureOutput){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setLocationDisclosureInput(locationDisclosureInput);
            inspectionRecordsDTO.setLocationDisclosureOutput(locationDisclosureOutput);

            session.update("mapper.InspectionRecords.locationDisclosureSet", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public void plainTextSet(String domain, String plainTextInput, String plainTextOutput){
        InspectionRecordsDTO inspectionRecordsDTO = new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setPlainTextInput(plainTextInput);
            inspectionRecordsDTO.setPlainTextOutput(plainTextOutput);

            session.update("mapper.InspectionRecords.plainTextSet", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public void webMethodSet(String domain, String webMethodInput, String webMethodOutput){
        InspectionRecordsDTO inspectionRecordsDTO =  new InspectionRecordsDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            inspectionRecordsDTO.setDomain(domain);
            inspectionRecordsDTO.setWebMethodInput(webMethodInput);
            inspectionRecordsDTO.setWebMethodOutput(webMethodOutput);

            session.update("mapper.InspectionRecords.webMethodSet", inspectionRecordsDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

}