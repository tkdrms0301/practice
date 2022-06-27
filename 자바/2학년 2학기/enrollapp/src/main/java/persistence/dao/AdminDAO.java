package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.AdminDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;


public class AdminDAO {
    protected final DataSource ds = PooledDataSource.getDataSource();

    public List<AdminDTO> findAll(){ // 전체 유저 조회 (학생 학년, 전공 제외), (교수 전공 제외)
        Connection conn = null;
        String sql = "SELECT * FROM USER";
        Statement stmt= null;
        ResultSet rs = null;

        List<AdminDTO> adminDTOS = new ArrayList<>();
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                AdminDTO adminDTO = new AdminDTO();
                int id = rs.getInt("id");
                String pw = rs.getString("pw");
                int group_id = rs.getInt("group_id");
                String name = rs.getString("name");
                Date birth = rs.getDate("birth");
                String phoneNumber = rs.getString("phoneNumber");

                adminDTO.setId(id);
                adminDTO.setPw(pw);
                adminDTO.setGroup_id(group_id);
                adminDTO.setName(name);
                adminDTO.setBirth(birth);
                adminDTO.setPhoneNumber(phoneNumber);
                adminDTOS.add(adminDTO);
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
        return adminDTOS;
    }
    public List<AdminDTO> findAdminAll(){ // 관리자 조회
        Connection conn = null;
        String sql = "SELECT * FROM USER WHERE group_id = 1";
        Statement stmt= null;
        ResultSet rs = null;

        List<AdminDTO> adminDTOS = new ArrayList<>();
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                AdminDTO adminDTO = new AdminDTO();
                int id = rs.getInt("id");
                String pw = rs.getString("pw");
                int group_id = rs.getInt("group_id");
                String name = rs.getString("name");
                Date birth = rs.getDate("birth");
                String phoneNumber = rs.getString("phoneNumber");

                adminDTO.setId(id);
                adminDTO.setPw(pw);
                adminDTO.setGroup_id(group_id);
                adminDTO.setName(name);
                adminDTO.setBirth(birth);
                adminDTO.setPhoneNumber(phoneNumber);
                adminDTOS.add(adminDTO);
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
        return adminDTOS;
    }

    public void insertAdmin(int id) { // 관리자 생성
        Scanner s = new Scanner(System.in);

        String pw;
        String name;
        Date birth;
        String phoneNumber;

        Connection conn = null;

        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cstmt = null;
        String sql = "select * from user where group_id = 1 and id = " + id;

        try{
            conn = ds.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            System.out.print("input name : ");
            name = s.next();
            System.out.print("input birth : ");
            birth = Date.valueOf(s.next());
            System.out.print("input phoneNumber : ");
            phoneNumber = s.next();

            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            pw = sdf.format(birth);

            cstmt = conn.prepareCall("{call insertAdmin(?,?,?,?,?)}");
            cstmt.setInt(1, id);
            cstmt.setString(2, pw);
            cstmt.setString(3, name);
            cstmt.setDate(4, birth);
            cstmt.setString(5, phoneNumber);

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
}
