package service;

import persistence.dao.ProfessorDAO;
import persistence.dto.ProfessorDTO;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.List;

public class ProfessorService {
    private final ProfessorDAO professorDAO;

    public ProfessorService(ProfessorDAO professorDAO) {
        this.professorDAO = professorDAO;
    }
    public List<ProfessorDTO> findProfessorAll() {
        List<ProfessorDTO> all = professorDAO.findProfessorAll();
        return all;
    }

    public void insertProfessor(int id, String name, String birth, String phoneNumber, String major) throws SQLIntegrityConstraintViolationException{
        try {
            professorDAO.insertProfessor(id, name, birth, phoneNumber, major);
        }catch (SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
        }catch (InputMismatchException e){
            e.printStackTrace();
        }
    }

    public void updateProfessor(int professor_id, String pw, String name, String birth, String phoneNumber, String major){
        try{
            professorDAO.updateProfessor(professor_id, pw, name, birth, phoneNumber, major);
        }catch (InputMismatchException e){
            e.printStackTrace();
        }
    }
}
