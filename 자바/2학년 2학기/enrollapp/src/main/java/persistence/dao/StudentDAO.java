package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.StudentDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentDAO {
    protected final DataSource ds = PooledDataSource.getDataSource();

    public List<StudentDTO> findStudentAll(){ // 학생 조회
        Connection conn = null;
        String sql = "select * from user natural join student where user.id = student.user_id;";
        Statement stmt= null;
        ResultSet rs = null;

        List<StudentDTO> studentDTOS = new ArrayList<>();
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                StudentDTO studentDTO = new StudentDTO();
                int id = rs.getInt("id");
                String pw = rs.getString("pw");
                int group_id = rs.getInt("group_id");
                String name = rs.getString("name");
                Date birth = rs.getDate("birth");
                String phoneNumber = rs.getString("phoneNumber");
                String major = rs.getString("major");
                int grade = rs.getInt("grade");

                studentDTO.setId(id);
                studentDTO.setPw(pw);
                studentDTO.setGroup_id(group_id);
                studentDTO.setName(name);
                studentDTO.setBirth(birth);
                studentDTO.setPhoneNumber(phoneNumber);
                studentDTO.setMajor(major);
                studentDTO.setGrade(grade);
                studentDTOS.add(studentDTO);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            try{
                if(conn != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    rs.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return studentDTOS;
    }

    //학생 생성
    public void insertStudent(int id, String name, String strBirth, String phoneNumber, String major, int grade) throws SQLIntegrityConstraintViolationException{
        String pw;
        Date birth;

        Connection conn = null;

        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cstmt = null;
        String sql = "select * from user where group_id = 3 and id = " + id;

        try{
            conn = ds.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            birth = Date.valueOf(strBirth);
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            pw = sdf.format(birth);

            cstmt = conn.prepareCall("{call insertStudent(?,?,?,?,?,?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, pw);
            cstmt.setString(3, name);
            cstmt.setDate(4, birth);
            cstmt.setString(5, phoneNumber);
            cstmt.setString(6,major);
            cstmt.setInt(7,grade);

            cstmt.execute();

            conn.commit();
        } catch(SQLIntegrityConstraintViolationException e){
            System.out.println("중복된 id 존재");
        } catch (SQLException e) {
            System.out.println("error : " + e);
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally{
            try{
                if(conn != null){
                    rs.close();
                }
                if(conn != null){
                    rs.close();
                }
                if(conn != null){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    // 학생 수정
    public void updateStudent(int id, String pw, String name, String strBirth, String phoneNumber, String major, int grade){
        Connection conn = null;
        String sql = "SELECT * FROM USER NATURAL JOIN STUDENT WHERE group_id = 3 and user.id = " + id;
        Statement stmt= null;
        ResultSet rs = null;
        CallableStatement cstmt = null;
        List<StudentDTO> studentDTOS = new ArrayList<>();
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.isBeforeFirst()) {
                rs.next();
                Date birth = Date.valueOf(strBirth);

                cstmt = conn.prepareCall("{call updateStudent(?, ?, ?, ?, ?, ?, ?)}");
                cstmt.setInt(1, id);
                cstmt.setString(2, pw);
                cstmt.setString(3, name);
                cstmt.setDate(4, birth);
                cstmt.setString(5, phoneNumber);
                cstmt.setString(6, major);
                cstmt.setInt(7, grade);

                cstmt.executeUpdate();
            } else {
                System.out.println("no");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            try{
                if(conn != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    rs.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
