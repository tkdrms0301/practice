package Persistence.DAO;

import Persistence.DTO.SaleDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    private DataSource ds;
    private static SaleDAO instance;


    public SaleDAO() {
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/OOSE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SaleDAO getInstance(){
        if(instance == null){
            instance = new SaleDAO();
        }

        return instance;
    }


    public List<SaleDTO> selectSalesByPeriod(Date start, Date end) {
        String SQL = "Select * from sales where time >= ?  AND time <= ?";

        List<SaleDTO> saleDTOList = new ArrayList<>();


        try (Connection conn = ds.getConnection();
             PreparedStatement psmt = conn.prepareStatement(SQL)) {
            psmt.setDate(1, start);
            psmt.setDate(2, end);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    SaleDTO saleDTO = new SaleDTO();
                    saleDTO.setCenterName(rs.getString("centerName"));
                    saleDTO.setLessonName(rs.getString("lessonName"));
                    saleDTO.setTime(rs.getDate("time"));
                    saleDTO.setSales(rs.getInt("sales"));
                    saleDTOList.add(saleDTO);
                    System.out.println("!23431243214");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saleDTOList;
    }

    public List<SaleDTO> selectSalesByLesson(String lesson) {
        String SQL = "Select * from sales where lessonName = ?";

        List<SaleDTO> saleDTOList = new ArrayList<>();


        try (Connection conn = ds.getConnection();
             PreparedStatement psmt = conn.prepareStatement(SQL)) {
            psmt.setString(1, lesson);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    SaleDTO saleDTO = new SaleDTO();
                    saleDTO.setCenterName(rs.getString("centerName"));
                    saleDTO.setLessonName(rs.getString("lessonName"));
                    saleDTO.setTime(rs.getDate("time"));
                    saleDTO.setSales(rs.getInt("sales"));
                    saleDTOList.add(saleDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saleDTOList;
    }

    public int InsertSale(SaleDTO sales) {
        String SQL = "INSERT INTO sales " + "(centerName, " + "lessonName, " + "time, " + "sales)" + "VALUES (?, ?, ?, ?);";
        int result = 0;

        try (Connection conn = ds.getConnection();
             PreparedStatement psmt = conn.prepareStatement(SQL)) {
            psmt.setString(1, sales.getCenterName());
            psmt.setString(2, sales.getLessonName());
            psmt.setDate(3, (Date) sales.getTime());
            psmt.setInt(4, sales.getSales());

            psmt.executeUpdate();

            result = psmt.executeUpdate();

            if (result != 1) {
                System.out.println("생성에 실패하였습니다.");
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}