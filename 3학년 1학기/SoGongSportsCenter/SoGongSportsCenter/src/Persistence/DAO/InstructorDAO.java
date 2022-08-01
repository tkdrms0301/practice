package Persistence.DAO;

import Persistence.DTO.InstructorDTO;
import Persistence.DTO.UserDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO extends UserDAO {
    private DataSource ds;
    private static InstructorDAO instance;

    public InstructorDAO(){
        try{
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/OOSE");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static InstructorDAO getInstance(){

        if(instance == null){
            instance = new InstructorDAO();
        }

        return instance;
    }

    @Override
    public List<UserDTO> selectUser() {
        List<UserDTO> instructorDTOS = new ArrayList<>();
        String sql = "SELECT * FROM USER where userType = 'instructor'";
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
                instructorDTOS.add(userDTO);
            }
        }catch (SQLException e) {
            System.out.printf("SELECT INSTRUCTOR ERROR");
        }finally {
            try{
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(stmt != null && !stmt.isClosed()){
                    stmt.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                System.out.println("SQL INSTRUCTOR CLOSE ERROR");
            }
        }
        return instructorDTOS;
    }

    public List<InstructorDTO> selectInstructor() {
        List<InstructorDTO> instructorDTOS = new ArrayList<>();
        String sql = "SELECT user.userId, userPassword, userName, userType, instructor_id FROM USER JOIN INSTRUCTOR ON user.userId = instructor.userId where userType = 'instructor'";
        Connection conn = null;
        Statement stmt= null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                InstructorDTO instructorDTO = new InstructorDTO();
                int userId = rs.getInt("userId");
                String userPassword = rs.getString("userPassword");
                String userName = rs.getString("userName");
                String userType = rs.getString("userType");
                int instructorId = rs.getInt("instructor_id");

                instructorDTO.setUserId(userId);
                instructorDTO.setUserPassword(userPassword);
                instructorDTO.setUserName(userName);
                instructorDTO.setUserType(userType);
                instructorDTO.setInstructorId(instructorId);
                instructorDTOS.add(instructorDTO);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(stmt != null && !stmt.isClosed()){
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
        return instructorDTOS;
    }

    public InstructorDTO selectInstructorById(int instructorId) {
        List<InstructorDTO> instructorDTOS = new ArrayList<>();

        String sql = "SELECT user.userId, userPassword, userName, userType, instructor_id FROM USER JOIN INSTRUCTOR ON user.userId = instructor.userId where instructor_id = ? ";
        Connection conn = null;
        ResultSet rs = null;
        InstructorDTO instructorDTO = new InstructorDTO();

        try {
            conn = ds.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,instructorId);
            rs = psmt.executeQuery();

            rs.next();
            int userId = rs.getInt("userId");
            String userPassword = rs.getString("userPassword");
            String userName = rs.getString("userName");
            String userType = rs.getString("userType");

            instructorDTO.setUserId(userId);
            instructorDTO.setUserPassword(userPassword);
            instructorDTO.setUserName(userName);
            instructorDTO.setUserType(userType);
            instructorDTO.setInstructorId(instructorId);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null){
                    conn.close();
                }
            }
            catch(SQLException e){
                System.out.println("SQL ADMIN CLOSE ERROR");
            }
        }

        return instructorDTO;
    }


    public void createInstructor(int userId, int instructorId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "Insert Into Instructor (userId) Values(?)";
        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(pstmt != null && !pstmt.isClosed()){
                    pstmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            }
            catch(SQLException e){
                System.out.println("SQL INSTRUCTOR CLOSE ERROR");
            }
        }
    }

    public void updateInstructorId(int userId, int instructorId){
        // 필요하면 만들예정
    }
}