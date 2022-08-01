package Persistence.DAO;

import Persistence.DTO.LockerDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LockerDAO {
    private static LockerDAO instance;
    private static DataSource ds;

    private LockerDAO() {
    }

    public static LockerDAO getInstance() {
        if (instance == null) {
            try {
                Context context = new InitialContext();
                ds = (DataSource) context.lookup("java:comp/env/jdbc/OOSE");
                return instance = new LockerDAO();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public List<LockerDTO> readAll() {
        List<LockerDTO> lockerDTOList = new ArrayList<>();
        String sql = "SELECT * FROM locker";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                LockerDTO lockerDTO = new LockerDTO();
                int lockerNumber = rs.getInt("locker_number");
                int lockerLocation = rs.getInt("locker_location");
                int amount = rs.getInt("amount");
                int userId = rs.getInt("user_id");

                lockerDTO.setLockerNumber(lockerNumber);
                lockerDTO.setLockerLocation(lockerLocation);
                lockerDTO.setAmount(amount);
                lockerDTO.setUserId(userId);
                lockerDTOList.add(lockerDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return lockerDTOList;
    }

    public void createLocker(LockerDTO lockerDTO) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "Insert Into locker (locker_number, locker_location, amount) Values(?,?,?)";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, lockerDTO.getLockerNumber());
            pstmt.setInt(2, lockerDTO.getLockerLocation());
            pstmt.setInt(3, lockerDTO.getAmount());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void updateLocker(LockerDTO lockerDTO) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "UPDATE locker SET locker_location=?, amount=?, user_id=? WHERE locker_number=?";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, lockerDTO.getLockerLocation());
            pstmt.setInt(2, lockerDTO.getAmount());
            pstmt.setInt(3, lockerDTO.getUserId());
            pstmt.setInt(4, lockerDTO.getLockerNumber());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteLocker(int lockerNumber) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "DELETE FROM locker WHERE locker_number = ?";

        try {

            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, lockerNumber);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void assignLocker(int lockerNumber, int userId) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "UPDATE locker SET user_id = ? WHERE locker_number = ?";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, userId);
            pstmt.setInt(2, lockerNumber);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
