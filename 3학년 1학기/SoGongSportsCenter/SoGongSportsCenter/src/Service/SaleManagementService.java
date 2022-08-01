package Service;

import Persistence.DAO.SaleDAO;
import Persistence.DTO.SaleDTO;

import java.sql.Date;
import java.util.List;

public class SaleManagementService {
    private SaleDAO saleDAO;

    public SaleManagementService() { saleDAO = SaleDAO.getInstance(); }

    public List<SaleDTO> findSalesByPeriod(Date start, Date end) {
        List<SaleDTO> saleDTOList = saleDAO.selectSalesByPeriod(start, end);
        return saleDTOList;
    }

    public List<SaleDTO> findSalesByLesson(String lessonName) {
        List<SaleDTO> saleDTOList = saleDAO.selectSalesByLesson(lessonName);
        return saleDTOList;
    }
}
