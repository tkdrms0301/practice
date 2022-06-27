package service;

import EnrollNetwork.Exception.EnrollException;
import persistence.dao.SubjectEnrollDAO;
import persistence.dto.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class SubjectEnrollService {
    SubjectEnrollDAO subjectEnrollDAO;

    public SubjectEnrollService(SubjectEnrollDAO subjectEnrollDAO){
        this.subjectEnrollDAO = subjectEnrollDAO;
    }

    // 수강 신청 가능 날짜 수정
    public void updateEnrollTime(int possible_grade, String enroll_time_start, String enroll_time_end){
        if(enroll_time_end.compareTo(enroll_time_start) < 0){
            System.out.println("잘못된 날짜설정입니다");
            return;
        }
        subjectEnrollDAO.updateSubjectEnrollTime(Date.valueOf(enroll_time_start), Date.valueOf(enroll_time_end), possible_grade);
    }

    // 수강 신청
    public void insertSubjectEnrollTakeClassState(int student_user_id, int subject_id) throws EnrollException{
        List<StudentDTO> studentAll = subjectEnrollDAO.getStudentAll();
        boolean user_flag = false;
        for(StudentDTO dto : studentAll){
            if(student_user_id == dto.getId()){ // user id 조회
                user_flag = true;
                break;
            }
        }
        if(!user_flag)
        {

            try {
                System.out.println("존재하지 않는 ID입니다.");
                throw new EnrollException();
            } catch (EnrollException e) {
                e.printStackTrace();
                throw e;
            }
        }
        boolean subject_flag = false;
        boolean max_people_flag = false;
        int number_people = 0;
        String day = "";
        String time = "";
        List<SubjectEnrollDTO> subjectEnrollDTOS = subjectEnrollDAO.getSubjectEnroll();
        for(SubjectEnrollDTO subjectEnrollDTO : subjectEnrollDTOS){
            if(subject_id == subjectEnrollDTO.getSubject_id()){
                subject_flag = true;
                day = subjectEnrollDTO.getDay();
                time = subjectEnrollDTO.getTime();
                java.util.Date now = new java.util.Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // 수강 가능한 날짜인지 확인
                now = java.sql.Date.valueOf(format.format(now));
                if(subjectEnrollDTO.getEnroll_time_start() == null || subjectEnrollDTO.getEnroll_time_end() == null) {
                    System.out.println("수강신청 날짜 없음");
                    try {
                        throw new EnrollException();
                    } catch (EnrollException e) {
                        throw e;
                    }
                }
                if(subjectEnrollDTO.getEnroll_time_start().compareTo(now) <= 0 && subjectEnrollDTO.getEnroll_time_end().compareTo(now) >= 0) {
                    number_people = subjectEnrollDTO.getNumber_people();
                    if(number_people < subjectEnrollDTO.getMax_people()){ // 최대 수강 인원 초과 확인
                        max_people_flag = true;
                        break;
                    }
                    else{
                        System.out.println("최대 수강 인원을 초과하였습니다");
                        try {
                            throw new EnrollException();
                        } catch (EnrollException e) {
                            throw e;
                        }
                    }
                }else if(!max_people_flag){
                    System.out.println("신청 불가능");
                    try {
                        throw new EnrollException();
                    } catch (EnrollException e) {
                        throw e;
                    }
                }
            }
        }
        if (!subject_flag){
            System.out.println("신청 불가능");
            try {
                throw new EnrollException();
            } catch (EnrollException e) {
                e.printStackTrace();
                throw e;
            }
        }
        List<MySubjectEnrollDTO> mySubjectEnrollDTO = subjectEnrollDAO.getMySubjectEnroll(student_user_id);
        subjectEnrollDTOS = subjectEnrollDAO.getSubjectEnroll();
        for(MySubjectEnrollDTO dto : mySubjectEnrollDTO) {
            if(day.equals(dto.getDay())) {
                for (int i = 0; i < time.length(); i++) {
                    for (int j = 0; j < dto.getTime().length(); j++) {
                        if (time.charAt(i) == dto.getTime().charAt(j)) {
                            System.out.println("동일한 시간 수강신청입니다"); // 개인 시간표 조회 후 중복되는 시간 있는지 확인
                            try {
                                throw new EnrollException();
                            } catch (EnrollException e) {
                                throw e;
                            }
                        }
                    }
                }
            }

        }
        subjectEnrollDAO.insertSubjectEnrollTakeClassState(student_user_id, subject_id, ++number_people);
    }

    // 개인 시간표 조회
    public List<MySubjectEnrollDTO> getMySubjectEnroll(int student_id){
        List<MySubjectEnrollDTO> all = subjectEnrollDAO.getMySubjectEnroll(student_id);
        return all;
    }

    // 수강 신청 취소
    public void deleteSubjectEnroll(int student_user_id, int subject_id){
        List<StudentDTO> studentAll = subjectEnrollDAO.getStudentAll();
        boolean user_flag = false;
        for(StudentDTO dto : studentAll){
            if(student_user_id == dto.getId()){ // user id 조회
                user_flag = true;
                break;
            }
        }
        boolean subject_flag = false;
        int number_people = 0;
        List<SubjectEnrollDTO> subjectEnrollDTOS = subjectEnrollDAO.getSubjectEnroll();
        for(SubjectEnrollDTO subjectEnrollDTO : subjectEnrollDTOS){
            if(subject_id == subjectEnrollDTO.getSubject_id()){ // 교과목 id 검색
                number_people = subjectEnrollDTO.getNumber_people(); // 수강 중인 인원 감소
                subject_flag = true;
                break;
            }
        }
        if(user_flag && subject_flag)
            subjectEnrollDAO.deleteSubjectEnroll(student_user_id, subject_id, --number_people);
    }
}