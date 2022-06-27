package service;

import persistence.dao.AdminDAO;
import persistence.dao.StudentDAO;
import persistence.dto.AdminDTO;
import persistence.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public List<StudentDTO> findStudentAll(){
        List<StudentDTO> all = studentDAO.findStudentAll();
        return all;
    }

    public void insertStudent(int id, String name, String strBirth, String phoneNumber, String major, int grade) throws SQLIntegrityConstraintViolationException{
        try {
            studentDAO.insertStudent(id, name, strBirth, phoneNumber, major, grade);
        }catch (SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
        }catch (InputMismatchException e){
            e.printStackTrace();
        }

    }

    public void updateStudent(int id, String pw, String name, String strBirth, String phoneNumber, String major, int grade){
        try{
            studentDAO.updateStudent(id, pw, name, strBirth, phoneNumber, major, grade);
        }catch (InputMismatchException e){
            e.printStackTrace();
        }
    }
}
