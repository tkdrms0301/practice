package Persistence.DAO;

import Persistence.DTO.UserDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO extends UserDAO {

    private DataSource ds;
    private static AdminDAO instance;

    public AdminDAO(){
        try{
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/OOSE");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static AdminDAO getInstance(){

        if(instance == null){
            instance = new AdminDAO();
        }

        return instance;
    }

    @Override
    public List<UserDTO> selectUser() {
        List<UserDTO> adminDTOS = new ArrayList<>();
        String sql = "SELECT * FROM USER where userType = 'admin'";
        Connection conn = null;
        Statement stmt= null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UserDTO userDTO = new UserDTO();
                int userId = rs.getInt("userId");
                String userPassword = rs.getString("userPassword");
                String userName = rs.getString("userName");
                String userType = rs.getString("userType");

                userDTO.setUserId(userId);
                userDTO.setUserPassword(userPassword);
                userDTO.setUserName(userName);
                userDTO.setUserType(userType);
                adminDTOS.add(userDTO);
            }
        }catch (SQLException e) {
            System.out.printf("SELECT ADMIN ERROR");
        }finally {
            try{
                if(conn != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    stmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            }
            catch(SQLException e){
                System.out.println("SQL ADMIN CLOSE ERROR");
            }
        }
        return adminDTOS;
    }

    public void createAdmin(int userId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "Insert Into Admin (userId) Values(?)";
        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("CREATE ADMIN ERROR");
        }finally {
            try{
                if(conn != null && !pstmt.isClosed()){
                    pstmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            }
            catch(SQLException e){
                System.out.println("SQL ADMIN CLOSE ERROR");
            }
        }
    }
}