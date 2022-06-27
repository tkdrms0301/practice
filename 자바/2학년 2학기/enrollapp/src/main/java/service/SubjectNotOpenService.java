package service;

import persistence.dao.SubjectNotOpenDAO;
import persistence.dto.SubjectNotOpenDTO;

import java.util.InputMismatchException;
import java.util.List;

public class SubjectNotOpenService {
    SubjectNotOpenDAO subjectNotOpenDAO;

    public SubjectNotOpenService(SubjectNotOpenDAO subjectNotOpenDAO){
        this.subjectNotOpenDAO = subjectNotOpenDAO;
    }

    public List<SubjectNotOpenDTO> getSubjectNotOpenAll(){
        List<SubjectNotOpenDTO> all = subjectNotOpenDAO.getSubjectNotOpenAll();
        return all;
    }

    public List<SubjectNotOpenDTO> getSubjectNotOpenGrade(int possible_grade){
        List<SubjectNotOpenDTO> all = subjectNotOpenDAO.getSubjectNotOpenGrade(possible_grade);
        return all;
    }

    public void insertSubjectNotOpen(int subject_id, String subject_name, int possible_grade) throws Exception{
        try{
            subjectNotOpenDAO.insertSubjectNotOpen(subject_id, subject_name, possible_grade);
        }catch (InputMismatchException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateSubjectNotOpen(int subject_id, String subject_name, int possible_grade){
        List<SubjectNotOpenDTO> all = subjectNotOpenDAO.getSubjectNotOpenAll();
        boolean subject_id_flag = false;
        try{
            for(SubjectNotOpenDTO subjectNotOpenDTO : all){
                if(subjectNotOpenDTO.getSubject_id() == subject_id){
                    subject_id_flag = true;
                    break;
                }
            }
            if(subject_id_flag) {
                subjectNotOpenDAO.updateSubjectNotOpen(subject_id, subject_name, possible_grade);
            }else {
                System.out.println("no");
            }
        } catch (InputMismatchException e){
            e.printStackTrace();
        }
    }

    public void deleteSubjectNotOpen(int subject_id){
        List<SubjectNotOpenDTO> all = subjectNotOpenDAO.getSubjectNotOpenAll();
        boolean subject_id_flag = false;
        try{
            for(SubjectNotOpenDTO subjectNotOpenDTO : all){
                if(subjectNotOpenDTO.getSubject_id() == subject_id){
                    subject_id_flag = true;
                    break;
                }
            }
            if(subject_id_flag) {
                subjectNotOpenDAO.deleteSubjectNotOpen(subject_id);
            }else {
                System.out.println("no");
            }
        } catch (InputMismatchException e){
            e.printStackTrace();
        }
    }
}
