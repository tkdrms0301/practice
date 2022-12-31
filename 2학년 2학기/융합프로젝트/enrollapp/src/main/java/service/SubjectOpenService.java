package service;

import EnrollNetwork.Exception.DateException;
import persistence.dao.ProfessorDAO;
import persistence.dao.SubjectNotOpenDAO;
import persistence.dao.SubjectOpenDAO;
import persistence.dto.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class SubjectOpenService {
    SubjectOpenDAO subjectOpenDAO;
    SubjectNotOpenDAO subjectNotOpenDAO;

    public SubjectOpenService(SubjectOpenDAO subjectOpenDAO, SubjectNotOpenDAO subjectNotOpenDAO){
        this.subjectOpenDAO = subjectOpenDAO;
        this.subjectNotOpenDAO = subjectNotOpenDAO;
    }

    // 전체 개설 교과목 조회
    public List<SubjectOpenDTO> getSubjectOpenAll(){
        List<SubjectOpenDTO> all = subjectOpenDAO.getSubjectOpenAll();
        return all;
    }

    // 교수 별 개설 교과목 조회
    public List<SubjectOpenDTO> getSubjectOpenProfessorAll(String professor_name){
        ProfessorDAO professorDAO = new ProfessorDAO();
        List<ProfessorDTO> allProfessor = professorDAO.findProfessorAll();
        for(ProfessorDTO dtos : allProfessor){
            if(professor_name.equals(dtos.getName())){
                List<SubjectOpenDTO> all = subjectOpenDAO.getSubjectOpenProfessorAll(dtos.getId());
                return all;
            }
        }
        return null;
    }

    // 학년 별 개설 교과목 조회
    public List<SubjectOpenDTO> getSubjectOpenGradeAll(int grade){
        List<SubjectOpenDTO> all = subjectOpenDAO.getSubjectGradeOpenAll(grade);
        return all;
    }

    // 학년, 교수이름 별 개설 교과목 조회
    public List<SubjectOpenDTO> getSubjectOpenProfessorAndGradeAll(String professor_name, int possible_grade){
        ProfessorDAO professorDAO = new ProfessorDAO();
        List<ProfessorDTO> allProfessor = professorDAO.findProfessorAll();
        for(ProfessorDTO dtos : allProfessor){
            if(professor_name.equals(dtos.getName())){
                List<SubjectOpenDTO> all = subjectOpenDAO.getSubjectOpenProfessorAndGradeAll(dtos.getId(), possible_grade);
                return all;
            }
        }
        return null;
    }

    // 비개설 교과목 -> 개설 교과목 생성
    public void openSubject(int subject_id, int max_people, String subject_plan, String str_plan_modify_date, int credit, String day, String time, String classroom, int professor_user_id){
        SubjectOpenDTO subjectOpenDTO = new SubjectOpenDTO();
        List<SubjectNotOpenDTO> all = subjectNotOpenDAO.getSubjectNotOpenAll();
        boolean open_flag = false;
        for(SubjectNotOpenDTO dtos : all){
            if(subject_id == dtos.getSubject_id()){
                if(dtos.isOpen()){
                    System.out.println("이미 열린 과목입니다");
                    return;
                }
                open_flag = true;
                break;
            }
        }
        if(!open_flag){
            System.out.println("존재하지 않는 과목입니다");
            return;
        }
        Date plan_modify_date = Date.valueOf(str_plan_modify_date);
        ProfessorDAO professorDAO = new ProfessorDAO();
        List<ProfessorDTO> allProfessor = professorDAO.findProfessorAll();
        boolean flag = false;
        for(ProfessorDTO dtos : allProfessor){
            if(professor_user_id == dtos.getId()){
                flag = true;
            }
        }
        if(!flag) {
            System.out.println("존재하지 않는 id");
            return;
        }
        subjectOpenDTO.setSubject_id(subject_id);
        subjectOpenDTO.setMax_people(max_people);
        subjectOpenDTO.setSubject_plan(subject_plan);
        subjectOpenDTO.setPlan_modify_date(plan_modify_date);
        subjectOpenDTO.setCredit(credit);
        subjectOpenDTO.setDay(day);
        subjectOpenDTO.setTime(time);
        subjectOpenDTO.setClassroom(classroom);
        subjectOpenDTO.setProfessor_user_id(professor_user_id);
        subjectOpenDAO.openSubject(subjectOpenDTO);
    }

    // 개설 교과목 수정
    public void updateSubjectOpen(int subject_id, String subject_name, int max_people, int possible_grade, int credit, String day, String time, String classroom){
        List<SubjectOpenDTO> all = subjectOpenDAO.getSubjectOpenAll();
        boolean flag = false;
        for(SubjectOpenDTO dtos : all){
            if(subject_id == dtos.getSubject_id()){
                flag = true;
            }
        }
        if(!flag) {
            System.out.println("존재하지 않는 id");
            return;
        }
        subjectOpenDAO.updateSubjectOpen(subject_id, subject_name, max_people, possible_grade, credit, day, time, classroom);
    }

    // 개설 교과목 강의계획서 입력
    public void updateSubjectPlanModifySubjectOpen(int subject_id, String subject_plan_modify){
        List<SubjectOpenDTO> all = subjectOpenDAO.getSubjectOpenAll();
        boolean flag = false;
        for(SubjectOpenDTO dtos : all){
            if(subject_id == dtos.getSubject_id()){
                flag = true;
            }
        }
        if(!flag){
            System.out.println("존재하지 않는 id");
            return;
        }
        subjectOpenDAO.updateSubjectPlanModifySubjectOpen(subject_id, Date.valueOf(subject_plan_modify));
    }

    public void updateSubjectPlanSubjectOpen(int subject_id, String subject_plan) throws DateException{
        List<SubjectOpenDTO> all = subjectOpenDAO.getSubjectOpenAll();
        boolean flag = false;
        java.util.Date now = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        now = Date.valueOf(format.format(now));

        for(SubjectOpenDTO dtos : all){
            if(subject_id == dtos.getSubject_id()){
                if (dtos.getPlan_modify_date().compareTo(now) < 0) {
                    try {
                        throw new DateException();
                    } catch (DateException e) {
                        throw e;
                    }
                }
                flag = true;
            }
        }

        if(!flag){
            return;
        }
        System.out.println(subject_plan);
        subjectOpenDAO.updateSubjectPlanSubjectOpen(subject_id, subject_plan);
    }

    // 개설 교과목 -> 비개설 교과목 (폐강)
    public void deleteSubjectOpen(int subject_id){
        List<SubjectOpenDTO> all = subjectOpenDAO.getSubjectOpenAll();
        boolean flag = false;
        for(SubjectOpenDTO dtos : all){
            if(subject_id == dtos.getSubject_id()){
                flag = true;
            }
        }
        if(!flag) {
            return;
        }
        subjectOpenDAO.deleteSubjectOpen(subject_id);
    }

    // 교과목 담당 교수 별 학생 정보
    public List<PerProfessorClassStudentDTO> perProfessorClassStudent(int subject_id){
        List<PerProfessorClassDTO> perProfessorClassDTOS = subjectOpenDAO.getPerProfessorClass(subject_id);
        List<PerProfessorClassStudentDTO> perProfessorClassStudentDTOS = null;
        for(PerProfessorClassDTO dtos : perProfessorClassDTOS){
            if(dtos.getSubject_id() == subject_id){
                perProfessorClassStudentDTOS = subjectOpenDAO.getPerProfessorClassStudent(subject_id);
                break;
            }
        }
        return perProfessorClassStudentDTOS;
    }
}
