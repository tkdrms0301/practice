package Persistence.DAO;

import Persistence.DTO.UserDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private DataSource ds;
    private static UserDAO instance;

    public UserDAO(){
        try{
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/OOSE");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static UserDAO getInstance(){

        if(instance == null){
            instance = new UserDAO();
        }

        return instance;
    }

    public List<UserDTO> selectUser(){
        List<UserDTO> userDTOS = new ArrayList<>();
        String sql = "SELECT * FROM USER";
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
                userDTOS.add(userDTO);
            }
        }catch (SQLException e) {
            System.out.printf("SELECT USER ERROR");
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
                System.out.println("SQL USER CLOSE ERROR");
            }
        }
        return userDTOS;
    }

    public List<UserDTO> selectUserByName(String name){
        List<UserDTO> userDTOS = new ArrayList<>();
        String sql = "SELECT * FROM USER WHERE userName = '" + name + "'";
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
                userDTOS.add(userDTO);
            }
        }catch (SQLException e) {
            System.out.printf("SELECT USER BY NAME ERROR");
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
        return userDTOS;
    }

    public void createUser(int userId, String userPassword, String userName, String userType){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "Insert Into User (userId, userPassword, userName, userType) Values(?,?,?,?)";
        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setString(2, userPassword);
            pstmt.setString(3, userName);
            pstmt.setString(4, userType);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("CREATE USER ERROR");
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
                System.out.println("SQL USER CLOSE ERROR");
            }
        }
    }
}