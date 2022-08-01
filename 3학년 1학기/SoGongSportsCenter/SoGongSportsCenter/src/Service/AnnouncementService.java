package Service;

import Persistence.DAO.AnnouncementDAO;
import Persistence.DAO.AttachedFileDAO;
import Persistence.DTO.AnnouncementDTO;
import Persistence.DTO.AttachedFileDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnnouncementService {
    private AnnouncementDAO announcementDAO;
    private AttachedFileDAO attachedFileDAO;

    public AnnouncementService(){
        announcementDAO = AnnouncementDAO.getInstance();
        attachedFileDAO = AttachedFileDAO.getInstance();
    }

    public HashMap<String, Object> createAnnouncement(AnnouncementDTO announcementDTO, AttachedFileDTO attachedFileDTO){
        announcementDAO.createAnnouncement(announcementDTO);
        int insertId = announcementDAO.getId();

        boolean result = true;

        if(attachedFileDTO.getAttachedFile() != null){
            result = attachedFileDAO.createAttachedFile(attachedFileDTO, insertId);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("insertID", insertId);
        map.put("result", result);

        return map;
    }

    public HashMap<String, Object> readAnnouncement(int announcementId){
        AnnouncementDTO announcementDTO = announcementDAO.readAnnouncement(announcementId);
        List<AttachedFileDTO> fileDTOList = attachedFileDAO.readAttachedFile(announcementId);

        announcementDAO.updateHits(announcementId, announcementDTO.getHits());

        HashMap<String, Object> map = new HashMap<>();

        map.put("AnnouncementDTO", announcementDTO);
        map.put("AttachedFileDTO", fileDTOList);

        return map;
    }

    public ArrayList<AnnouncementDTO> getList(int pageNum){

        if(!announcementDAO.nextPage(pageNum)){
            return null;
        }

        ArrayList<AnnouncementDTO> list = announcementDAO.getList(pageNum);

        return list;
    }

    public boolean isNextPage(int pageNum){
        return announcementDAO.nextPage(pageNum);
    }
}
