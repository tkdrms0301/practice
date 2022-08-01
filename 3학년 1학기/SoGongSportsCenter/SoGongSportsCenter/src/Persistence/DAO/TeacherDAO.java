package Persistence.DAO;

import java.sql.*;
import java.util.List;

import Persistence.DTO.TeacherDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TeacherDAO {

    private DataSource ds;
    private static TeacherDAO instance;

    private TeacherDAO(){
        try{
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/OOSE");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static TeacherDAO getInstance(){
        if(instance == null)
            instance = new TeacherDAO();

        return  instance;
    }

    public void Create(TeacherDTO teacherDTO){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO teacher VALUES (?,?,?)";
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, teacherDTO.getTeacherId());
            pstmt.setString(2, teacherDTO.getTeacherName());
            pstmt.setString(3, teacherDTO.getTeacherPassWord());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) conn.close();
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public List<TeacherDTO> selectAll(TeacherDTO TeacherDTO){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            String sql = "SELECT * FROM teahcer";

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();

            TeacherDTO teacherDTO;

            while(rs.next()){
                String TeacherId = rs.getString("TeacherId");
                String TeacherName = rs.getString("TeacherName");
                String TeacherPassWord = rs.getString("TeacherPassWord");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(rs != null) {rs.close();}
                if(pstmt != null) {pstmt.close();}
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return null;
    }
}
