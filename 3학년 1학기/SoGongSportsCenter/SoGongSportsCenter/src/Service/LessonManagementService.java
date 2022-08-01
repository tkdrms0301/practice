package Service;

import Persistence.DAO.LessonDAO;
import Persistence.DAO.SaleDAO;
import Persistence.DTO.Lesson;
import Persistence.DTO.SaleDTO;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public class LessonManagementService {
    private SaleDAO saleDAO;

    private LessonDAO lessonDAO;

    private static final String centerName = "소공체육센터";

    public LessonManagementService(SaleDAO saleDAO,LessonDAO lessonDAO)
    {
        this.saleDAO = saleDAO;
        this.lessonDAO = lessonDAO;
    }

    public boolean createLesson(HashMap<String,Object> map)
    {
        boolean result = lessonDAO.createLesson(map);
        return result;
    }

    public boolean lectureRegistration(int userId,int lessonId)
    {
        boolean result = lessonDAO.lectureRegistration(userId,lessonId);
        Lesson lesson = lessonDAO.selectLessonWithId(lessonId);

        Date date = new Date(System.currentTimeMillis());

        SaleDTO sale = new SaleDTO(centerName,lesson.getLessonName(),date,lesson.getPrice());
        saleDAO.InsertSale(sale);
        return result;
    }

    public List<SaleDTO> findSalesByPeriod(Date start, Date end) {
        List<SaleDTO> saleDTOList = saleDAO.selectSalesByPeriod(start, end);
        return saleDTOList;
    }

    public List<SaleDTO> findSalesByLesson(String lessonName) {
        List<SaleDTO> saleDTOList = saleDAO.selectSalesByLesson(lessonName);
        return saleDTOList;
    }
}
