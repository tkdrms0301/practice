package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.SubjectNotOpenDTO;

import java.util.List;

public class SubjectNotOpenDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public SubjectNotOpenDAO(SqlSessionFactory sqlSessionFActory) {
        this.sqlSessionFactory = sqlSessionFActory;
    }

    // 비개설 교과목 전체 조회
    public List<SubjectNotOpenDTO> getSubjectNotOpenAll() {
        List<SubjectNotOpenDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("mapper.SubjectNotOpen.selectSubjectAll");
        } finally {
            session.close();
        }
        return list;
    }

    //비개설 교과목 학년 조회
    public List<SubjectNotOpenDTO> getSubjectNotOpenGrade(int grade){
        List<SubjectNotOpenDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("mapper.SubjectNotOpen.selectSubjectGrade", grade);
        } finally {
            session.close();
        }
        return list;
    }

    //비개설 교과목 생성
    public void insertSubjectNotOpen(int subject_id, String subject_name, int possible_grade) throws Exception{
        SqlSession session = sqlSessionFactory.openSession();
        try{
            SubjectNotOpenDTO subjectNotOpenDTO = new SubjectNotOpenDTO();
            subjectNotOpenDTO.setSubject_id(subject_id);
            subjectNotOpenDTO.setSubject_name(subject_name);
            subjectNotOpenDTO.setOpen(false);
            subjectNotOpenDTO.setPossible_grade(possible_grade);
            session.insert("mapper.SubjectNotOpen.insertSubjectNotOpen", subjectNotOpenDTO);
            session.commit();
        } catch (Exception e){
            session.rollback();
            System.out.println("error");
        } finally {
            session.close();
        }
    }

    // 비개설 교과목 수정
    public void updateSubjectNotOpen(int subject_id, String subject_name, int possible_grade) {
        SqlSession session = sqlSessionFactory.openSession();
        try{
            SubjectNotOpenDTO subjectNotOpenDTO = new SubjectNotOpenDTO();
            subjectNotOpenDTO.setSubject_id(subject_id);
            subjectNotOpenDTO.setSubject_name(subject_name);
            subjectNotOpenDTO.setPossible_grade(possible_grade);
            session.update("mapper.SubjectNotOpen.updateSubjectNotOpen", subjectNotOpenDTO);
        } catch (Exception e){
            session.rollback();
            System.out.println("error");
        } finally {
            session.commit();
            session.close();
        }
    }

    // 비개설 교과목 삭제
    public void deleteSubjectNotOpen(int subject_id){
        SqlSession session = sqlSessionFactory.openSession();
        try{
            session.update("mapper.SubjectNotOpen.deleteSubjectNotOpen", subject_id);
        } catch (Exception e){
            session.rollback();
            System.out.println("error");
        } finally {
            session.commit();
            session.close();
        }
    }
}