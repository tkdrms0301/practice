package Persistence.DAO;

import Persistence.DTO.GuideLineDTO;
import Persistence.DTO.PayLoadDTO;
import Persistence.PooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class GuideLineDAO {
    private final DataSource ds = PooledDataSource.getDataSource();
    private SqlSessionFactory sqlSessionFactory = null;

    public GuideLineDAO(SqlSessionFactory sqlSessionFactory){this.sqlSessionFactory = sqlSessionFactory;}

    public GuideLineDTO guideLineSelectPrint(String vulnerabilityType) {
        GuideLineDTO guideLineDTO = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            guideLineDTO = session.selectOne("mapper.GuideLine.guideLineSelectPrint", vulnerabilityType);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return guideLineDTO;
    }

    public List<GuideLineDTO> guideLineAllPrint() {
        List<GuideLineDTO> guideLineDTO = new ArrayList<>();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            guideLineDTO = session.selectList("mapper.GuideLine.guideLineAllPrint");
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return guideLineDTO;
    }

}
