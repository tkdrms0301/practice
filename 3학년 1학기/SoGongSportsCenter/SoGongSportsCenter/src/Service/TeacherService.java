package Service;

import Persistence.DAO.TeacherDAO;
import Persistence.DAO.*;
import Persistence.DTO.*;
import java.util.List;
import java.util.Scanner;

public class TeacherService {

    private final TeacherDAO teacherDAO;
    Scanner sc;

    public TeacherService(TeacherDAO teacherDAO){
        this.teacherDAO = teacherDAO;
        sc = new Scanner(System.in);
    }

    public void create() {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDAO.Create(teacherDTO);
    }

    public List<TeacherDTO> read(){
        TeacherDTO teacherDTO = new TeacherDTO();
        return teacherDAO.selectAll(teacherDTO);
    }
}

