package Persistence.DAO;

import Persistence.DTO.AttachedFileDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttachedFileDAO {

    private DataSource ds;
    private static AttachedFileDAO instance;

    public AttachedFileDAO(){
        try{
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/OOSE");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static AttachedFileDAO getInstance(){
        if(instance == null){
            instance = new AttachedFileDAO();
        }

        return instance;
    }

    public boolean createAttachedFile(AttachedFileDTO attachedFile, int announcementId){
        Connection conn = null;
        PreparedStatement pstmt = null;

        String SQL = "INSERT INTO attachedFile " +
                "(Announcement_ID," +
                "AttachedFile)" +
                "VALUES (?, ?);";

        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, announcementId);
            pstmt.setBlob(2, attachedFile.getAttachedFile());

            int result = pstmt.executeUpdate();

            if(result != 1){
                System.out.println("생성에 실패하였습니다.");
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(conn != null) conn.close();
                if(pstmt != null) {pstmt.close();}
            }catch (SQLException e){
                e.printStackTrace();
            }

        }

        return true;
    }

    public List<AttachedFileDTO> readAttachedFile(int announcementId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<AttachedFileDTO> attachedFileDTOS = new ArrayList<>();

        String SQL = "SELECT * FROM attachedfile WHERE Announcement_ID=?";

        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, announcementId);
            rs = pstmt.executeQuery();

            while(rs.next()){
                int attachedFileId = rs.getInt("AttachedFile_ID");
                int announcementIdResult = rs.getInt("Announcement_ID");
                Blob attachedFile = rs.getBlob("AttachedFile");

                if(attachedFile != null){

                    attachedFileDTOS.add(new AttachedFileDTO(attachedFileId, announcementIdResult, attachedFile));

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(conn != null) conn.close();
                if(pstmt != null) {pstmt.close();}
                if(rs != null) {rs.close();}
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return attachedFileDTOS;
    }

//    public void deleteAttachedFile(int announcementId){
//
//    }
}
