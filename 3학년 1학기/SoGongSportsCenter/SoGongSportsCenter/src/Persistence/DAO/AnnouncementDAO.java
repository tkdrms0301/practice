package Persistence.DAO;

import Persistence.DTO.AnnouncementDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class AnnouncementDAO {

    private DataSource ds;
    private static AnnouncementDAO instance;

    public AnnouncementDAO(){
        try{
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/OOSE");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static AnnouncementDAO getInstance(){

        if(instance == null){
            instance = new AnnouncementDAO();
        }

        return instance;
    }

    public int createAnnouncement(AnnouncementDTO announcement) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        int result = 0;

        String SQL = "INSERT INTO announcement " +
                "(Announcement_Title," +
                "Announcement_Content," +
                "Announcement_Writer_Name," +
                "Write_Date," +
                "IsAttachedFile," +
                "Hits)" +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(SQL);

            pstmt.setString(1, announcement.getAnnouncementTitle());
            pstmt.setString(2, announcement.getAnnouncementContent());
            pstmt.setString(3, announcement.getAnnouncementWriterName());
            pstmt.setDate(4, (Date) announcement.getWriteDate());
            pstmt.setInt(5, announcement.getIsAttachedFile());
            pstmt.setInt(6, 0);

            result = pstmt.executeUpdate();

            if (result != 1) {
                System.out.println("생성에 실패하였습니다.");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    public int getId(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int id = 0;

        String SQL = "SELECT Announcement_ID FROM announcement ORDER BY Announcement_ID desc;";

        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            if(rs.next()){
                id = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(rs != null) {rs.close();}
                if(pstmt != null) {pstmt.close();}
                if(conn != null) conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return id;
    }

    public AnnouncementDTO readAnnouncement(int announcementId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        AnnouncementDTO announcement = null;

        String SQL = "SELECT * FROM announcement WHERE Announcement_ID=?;";

        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, announcementId);
            rs = pstmt.executeQuery();

            while(rs.next()){
                int announcementIdResult = rs.getInt("Announcement_ID");
                String announcementTitle = rs.getString("Announcement_Title");
                String announcementContent = rs.getString("Announcement_Content");
                String announcementWriterName = rs.getString("Announcement_Writer_Name");
                int hits = rs.getInt("Hits");
                int isAttachedFile = rs.getInt("IsAttachedFile");
                Date writeDate = rs.getDate("Write_Date");

                return new AnnouncementDTO( announcementTitle, announcementContent, announcementWriterName, writeDate, isAttachedFile, hits);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(rs != null) {rs.close();}
                if(pstmt != null) {pstmt.close();}
                if(conn != null) conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return announcement;
    }

    public ArrayList<AnnouncementDTO> getList(int pageNum){
        ArrayList<AnnouncementDTO> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String SQL = "SELECT * FROM announcement WHERE ?<Announcement_ID AND Announcement_ID<? ORDER BY Announcement_ID LIMIT 10;";

        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, (pageNum-1) * 10);
            pstmt.setInt(2, pageNum * 10 + 1);
            rs = pstmt.executeQuery();

            while(rs.next()){
               AnnouncementDTO dto = new AnnouncementDTO();
               dto.setAnnouncementId(rs.getInt(1));
               dto.setAnnouncementTitle(rs.getString(2));
               dto.setAnnouncementContent(rs.getString(3));
               dto.setAnnouncementWriterName(rs.getString(4));
               dto.setWriteDate(rs.getDate(5));
               dto.setIsAttachedFile(rs.getInt(6));
               dto.setHits(rs.getInt(7));

               list.add(dto);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(rs != null) {rs.close();}
                if(pstmt != null) {pstmt.close();}
                if(conn != null) conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return list;
    }

    public boolean nextPage(int pageNum){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String SQL = "SELECT * FROM announcement WHERE Announcement_ID<? ORDER BY Announcement_ID LIMIT 10;";

        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, (getId() + 1) - (pageNum - 1) * 10);
            rs = pstmt.executeQuery();

            if(rs.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(rs != null) {rs.close();}
                if(pstmt != null) {pstmt.close();}
                if(conn != null) conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }

    public int updateHits(int announcementId, int hits){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String SQL = "UPDATE announcement SET Hits=? WHERE Announcement_ID=?;";

        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, hits);
            pstmt.setInt(2, announcementId);

            return pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(rs != null) {rs.close();}
                if(pstmt != null) {pstmt.close();}
                if(conn != null) conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return 0;
    }
}
