package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.ProfessorDTO;
import persistence.dto.StudentDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorDAO {
    protected final DataSource ds = PooledDataSource.getDataSource();

    public List<ProfessorDTO> findProfessorAll() { // 교수 조회
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String selectQuery = "select * from user natural join professor where user.id = professor.user_id;";
        List<ProfessorDTO> professorDTOS = new ArrayList<>();

        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(selectQuery);
            while (rs.next()) {
                ProfessorDTO professorDTO = new ProfessorDTO();
                int id = rs.getInt("id");
                String pw = rs.getString("pw");
                int group_id = rs.getInt("group_id");
                String name = rs.getString("name");
                Date birth = rs.getDate("birth");
                String phoneNumber = rs.getString("phoneNumber");
                String major = rs.getString("major");
                professorDTO.setId(id);
                professorDTO.setPw(pw);
                professorDTO.setGroup_id(group_id);
                professorDTO.setName(name);
                professorDTO.setBirth(birth);
                professorDTO.setPhoneNumber(phoneNumber);
                professorDTO.setMajor(major);
                professorDTOS.add(professorDTO);
            }
        } catch (SQLException e) {
            System.out.println("error : " + e);
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (conn != null && !rs.isClosed()) {
                    rs.close();
                }
                if (conn != null && !stmt.isClosed()) {
                    rs.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return professorDTOS;
    }

    //교수 생성
    public void insertProfessor(int id, String name, String strBirth, String phoneNumber, String major) throws SQLIntegrityConstraintViolationException {
        String pw;
        Date birth;

        Connection conn = null;

        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cstmt = null;
        String sql = "select * from user where group_id = 2 and id = " + id;

        try {
            conn = ds.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);


            birth = Date.valueOf(strBirth);

            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            pw = sdf.format(birth);

            cstmt = conn.prepareCall("{call insertProfessor(?,?,?,?,?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, pw);
            cstmt.setString(3, name);
            cstmt.setDate(4, birth);
            cstmt.setString(5, phoneNumber);
            cstmt.setString(6, major);

            cstmt.execute();

            conn.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("중복된 id 존재");
        } catch (SQLException e) {
            System.out.println("error : " + e);
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    rs.close();
                }
                if (conn != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //교수 수정
    public void updateProfessor(int professor_id, String pw, String name, String strBirth, String phoneNumber, String major) {
        Connection conn = null;
        String sql = "SELECT * FROM USER NATURAL JOIN STUDENT WHERE group_id = 2 and user.id = " + professor_id;
        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cstmt = null;
        List<ProfessorDTO> studentDTOS = new ArrayList<>();
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.isBeforeFirst()) {
                rs.next();
                ProfessorDTO professorDTO = new ProfessorDTO();
                int id = rs.getInt("id");

                Date birth = Date.valueOf(strBirth);

                cstmt = conn.prepareCall("{call updateProfessor(?, ?, ?, ?, ?, ?)}");
                cstmt.setInt(1, id);
                cstmt.setString(2, pw);
                cstmt.setString(3, name);
                cstmt.setDate(4, birth);
                cstmt.setString(5, phoneNumber);
                cstmt.setString(6, major);

                cstmt.executeUpdate();
            } else {
                System.out.println("no");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (conn != null && !rs.isClosed()) {
                    rs.close();
                }
                if (conn != null && !stmt.isClosed()) {
                    rs.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}