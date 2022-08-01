package Service;

import Persistence.DAO.LockerDAO;
import Persistence.DTO.LockerDTO;

import java.util.List;

public class LockerService {
    LockerDAO lockerDAO;

    public LockerService(){
        lockerDAO = LockerDAO.getInstance();
    }

    public List<LockerDTO> readAll() {
        return lockerDAO.readAll();
    }

    public void createLocker(LockerDTO lockerDTO) {
        lockerDAO.createLocker(lockerDTO);
    }

    public void updateLocker(LockerDTO lockerDTO) {
        lockerDAO.updateLocker(lockerDTO);
    }

    public void deleteLocker(int lockerNumber) {
        lockerDAO.deleteLocker(lockerNumber);
    }

    public void assignLocker(int lockerNumber, int userId) {
        lockerDAO.assignLocker(lockerNumber, userId);
    }
}
