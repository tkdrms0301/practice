package Persistence.DAO;

import Persistence.PooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import javax.sql.DataSource;

import Persistence.DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final DataSource ds = PooledDataSource.getDataSource();
    private SqlSessionFactory sqlSessionFactory = null;

    public UserDAO(SqlSessionFactory sqlSessionFactory){this.sqlSessionFactory = sqlSessionFactory;}

    public UserDTO userSelectPrint(String userID) {
        UserDTO userDTO = new UserDTO();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            userDTO = session.selectOne("mapper.User.userSelectPrint", userID);
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return userDTO;
    }

    public boolean userCreate(String userId, String userPw, String email){
        SqlSession session = sqlSessionFactory.openSession();
        List<UserDTO> userDTOS = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        boolean flag = false;

        userDTOS = session.selectList("mapper.User.userAllPrint");

        for (UserDTO dto : userDTOS) {
            if (dto.getUserID().equals(userId)) return flag;
        }

        try{
            userDTO.setUserID(userId);
            userDTO.setUserPW(userPw);
            userDTO.setEmail(email);
            session.insert("mapper.User.userCreate", userDTO);
            session.commit();
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
        return flag;
    }
}
