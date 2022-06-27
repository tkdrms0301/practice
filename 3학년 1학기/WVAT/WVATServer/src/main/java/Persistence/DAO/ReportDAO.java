package Persistence.DAO;

import Persistence.DTO.PayLoadDTO;
import Persistence.DTO.ReportDTO;
import Persistence.DTO.UserDTO;
import Persistence.PooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ReportDAO {
    private final DataSource ds = PooledDataSource.getDataSource();
    private SqlSessionFactory sqlSessionFactory = null;

    public ReportDAO(SqlSessionFactory sqlSessionFactory){this.sqlSessionFactory = sqlSessionFactory;}

    public void reportCreate(int userNum, int domainNum, LocalDate date, String reportPath){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setUserNum(userNum);
            reportDTO.setDomainNum(domainNum);
            reportDTO.setDate(date);
            reportDTO.setReportPath(reportPath);
            session.insert("mapper.Report.reportCreate", reportDTO);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }

    public ReportDTO recentReportByUserNumPrint(int userNum){
        ReportDTO reportDTO = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            reportDTO = session.selectOne("mapper.Report.recentReportByUserNumPrint", userNum);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return reportDTO;
    }

    public List<ReportDTO> reportByUserPrint(int userNum) {
        List<ReportDTO> reportDTOS = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            reportDTOS = session.selectList("mapper.Report.reportByUserPrint", userNum);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return reportDTOS;
    }

    public ReportDTO reportByReportNum(int reportNum) {
        ReportDTO reportDTO = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            reportDTO = session.selectOne("mapper.Report.reportByReportNum", reportNum);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return reportDTO;
    }


}
