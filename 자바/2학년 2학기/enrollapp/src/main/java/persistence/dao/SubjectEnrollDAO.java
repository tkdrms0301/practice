package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.*;
import persistence.mapper.SubjectEnrollMapper;
import java.sql.Date;
import java.util.List;

public class SubjectEnrollDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public SubjectEnrollDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // 학년별 수강 신청 기간 설정
    public void updateSubjectEnrollTime(Date enroll_time_start, Date enroll_time_end, int possible_grade){
        SqlSession session = sqlSessionFactory.openSession();
        SubjectEnrollMapper mapper = session.getMapper(SubjectEnrollMapper.class);
        try{
            mapper.updateSubjectEnrollTime(enroll_time_start, enroll_time_end, possible_grade);
            session.commit();
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();
        }
    }

    // 수강 신청시 수강신청 인원 증가
    public void insertSubjectEnrollTakeClassState(int student_user_id, int subject_id, int number_people){
        SqlSession session = sqlSessionFactory.openSession();
        SubjectEnrollMapper mapper = session.getMapper(SubjectEnrollMapper.class);
        try{
            mapper.insertSubjectEnrollTakeClassState(student_user_id, subject_id);
            mapper.updateSubjectNumberPeople(subject_id, number_people);
            session.commit();
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();
        }
    }

    // 학생 전체 정보 조회
    public List<StudentDTO> getStudentAll(){
        SqlSession session = sqlSessionFactory.openSession();
        SubjectEnrollMapper mapper = session.getMapper(SubjectEnrollMapper.class);
        List<StudentDTO> all = null;
        try{
            all = mapper.getStudentAll();
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();

        }
        return all;
    }

    // 개설 교과목 전체 조회
    public List<SubjectOpenDTO> getSubjectOpenAll(){
        SqlSession session = sqlSessionFactory.openSession();
        SubjectEnrollMapper mapper = session.getMapper(SubjectEnrollMapper.class);
        List<SubjectOpenDTO> all = null;
        try{
            all = mapper.getSubjectOpenAll();
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();

        }
        return all;
    }


    // 개설과목으로 전체 정보 조회
    public List<SubjectEnrollDTO> getSubjectEnroll(){
        SqlSession session = sqlSessionFactory.openSession();
        SubjectEnrollMapper mapper = session.getMapper(SubjectEnrollMapper.class);
        List<SubjectEnrollDTO> all = null;
        try{
            all = mapper.getSubjectEnroll();
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();

        }
        return all;
    }

    // 전체 학년 수강 신청 날짜 조회
    public List<SubjectEnrollTimeDTO> getSubjectEnrollTimeAll(){
        SqlSession session = sqlSessionFactory.openSession();
        SubjectEnrollMapper mapper = session.getMapper(SubjectEnrollMapper.class);
        List<SubjectEnrollTimeDTO> all = null;
        try{
            all = mapper.getSubjectEnrollTimeAll();
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();

        }
        return all;
    }

    // 개인 수강신청 시간표
    public List<MySubjectEnrollDTO> getMySubjectEnroll(int student_user_id){
        SqlSession session = sqlSessionFactory.openSession();
        SubjectEnrollMapper mapper = session.getMapper(SubjectEnrollMapper.class);
        List<MySubjectEnrollDTO> all = null;
        try{
            all = mapper.getMySubjectEnroll(student_user_id);
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();

        }
        return all;
    }


    // 수강 취소
    public void deleteSubjectEnroll(int student_user_id, int subject_id, int number_people){
        SqlSession session = sqlSessionFactory.openSession();
        SubjectEnrollMapper mapper = session.getMapper(SubjectEnrollMapper.class);
        List<MySubjectEnrollDTO> all = null;
        try{
            mapper.deleteSubjectEnroll(student_user_id, subject_id);
            mapper.updateSubjectNumberPeople(subject_id , number_people);
            session.commit();
        }catch ( Exception e)
        {
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();

        }
    }
}
