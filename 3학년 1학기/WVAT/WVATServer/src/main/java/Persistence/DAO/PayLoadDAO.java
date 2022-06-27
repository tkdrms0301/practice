package Persistence.DAO;

import Persistence.DTO.PayLoadDTO;
import Persistence.DTO.UserDTO;
import Persistence.PooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PayLoadDAO {
    private final DataSource ds = PooledDataSource.getDataSource();
    private SqlSessionFactory sqlSessionFactory = null;

    public PayLoadDAO(SqlSessionFactory sqlSessionFactory){this.sqlSessionFactory = sqlSessionFactory;}

    public List<PayLoadDTO> payLoadSelectPrint(String vulnerabilityType) {
        List<PayLoadDTO> payLoadDTOS = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            payLoadDTOS = session.selectList("mapper.PayLoad.payLoadSelectPrint", vulnerabilityType);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return payLoadDTOS;
    }

    public List<PayLoadDTO> randomPayLoadSelectPrint(String vulnerabilityType) {
        Random random = new Random(); //랜덤 객체 생성
        random.setSeed(System.currentTimeMillis()); // 현재 시간으로 시드값 설정
        int randomValue = 0;

        List<PayLoadDTO> payLoadDTOS = null;
        List<PayLoadDTO> randomPayLoadDTOS = new ArrayList<>();

        SqlSession session = sqlSessionFactory.openSession();
        try {
            payLoadDTOS = session.selectList("mapper.PayLoad.payLoadSelectPrint", vulnerabilityType);

            for (int i = 0; i < 10; i++){
                randomValue = random.nextInt(payLoadDTOS.size());
                randomPayLoadDTOS.add(payLoadDTOS.get(randomValue));
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return randomPayLoadDTOS;
    }

    public List<PayLoadDTO> orderPayLoadSelectPrint(String vulnerabilityType) {
        List<PayLoadDTO> payLoadDTOS = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            payLoadDTOS = session.selectList("mapper.PayLoad.orderPayLoadSelectPrint", vulnerabilityType);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return payLoadDTOS;
    }

    public List<PayLoadDTO> randomAndOrderPayLoadSelectPrint(String vulnerabilityType) {
        List<PayLoadDTO> randomPayload = randomPayLoadSelectPrint(vulnerabilityType);
        List<PayLoadDTO> orderPayload = orderPayLoadSelectPrint(vulnerabilityType);
        List<PayLoadDTO> randomAndOrder = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            randomAndOrder.add(orderPayload.get(i));
        }

        for (int i = 0; i < 10; i++){
            randomAndOrder.add(randomPayload.get(i));
        }

        return randomAndOrder;
    }

    public void updatePayLoadCount(String payload){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            session.update("mapper.PayLoad.updatePayLoadCount", payload);
        } catch (Exception e){
            session.rollback();
            System.out.println("error");
        } finally {
            session.commit();
            session.close();
        }
    }

    public List<PayLoadDTO> selectOsCommandInjection(String payloadKeyword){
        List<PayLoadDTO> osCommandPayload = new ArrayList<>();
        SqlSession session = sqlSessionFactory.openSession();
        try{
            List<PayLoadDTO> randomPayload = randomPayLoadSelectPrint("osCommand");
            List<PayLoadDTO> orderPayload =  session.selectList("mapper.PayLoad.orderOsCommandInjectionSelectPrint", payloadKeyword);

            int end = 10;
            if(orderPayload.size() < 10){
                for(int i = 0; i <orderPayload.size(); i++){
                    osCommandPayload.add(orderPayload.get(i));
                }
            }else{
                for(int i =0; i < 10; i++){
                    osCommandPayload.add(orderPayload.get(i));
                }
            }

            if(randomPayload.size() < 10){
                for(int i = 0; i <orderPayload.size(); i++){
                    osCommandPayload.add(randomPayload.get(i));
                }
            }else{
                for(int i =0; i < 10; i++){
                    osCommandPayload.add(randomPayload.get(i));
                }
            }

        }catch (Exception e){
            session.rollback();
            System.out.println("error");
        }finally {
            session.commit();
            session.close();
        }
        return osCommandPayload;
    }
}