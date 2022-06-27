package persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import persistence.dto.PerProfessorClassDTO;
import persistence.dto.PerProfessorClassStudentDTO;
import persistence.dto.SubjectOpenDTO;

import java.sql.Date;
import java.util.List;

public interface SubjectOpenMapper {
    String findSubjectOpenAll =
            "select * from subject INNER JOIN subject_open ON subject.subject_id = subject_open.subject_id INNER JOIN subject_time ON subject.subject_id = subject_time.subjectOpen_subject_id INNER JOIN per_professor_class ON subject.subject_id = per_professor_class.subject_id;";

    @Select(findSubjectOpenAll)
    @Results(id="subjectOpenAllResultSet", value ={
            @Result(property = "subject_id", column = "subject_id"),
            @Result(property = "subject_name", column = "subject_name"),
            @Result(property = "open", column = "open"),
            @Result(property = "max_people", column = "max_people"),
            @Result(property = "number_people", column = "number_people"),
            @Result(property = "possible_grade", column = "possible_grade"),
            @Result(property = "subject_plan", column = "subject_plan"),
            @Result(property = "plan_modify_date", column = "plan_modify_date"),
            @Result(property = "credit", column = "credit"),
            @Result(property = "day", column = "day"),
            @Result(property = "time", column = "time"),
            @Result(property = "classroom", column = "classroom"),
            @Result(property = "professor_user_id", column = "professor_user_id")
    })
    List<SubjectOpenDTO> getSubjectOpenAll();

    String findSubjectOpenGrade =  "select * from subject " +
            "INNER JOIN subject_open " +
            "ON subject.subject_id = subject_open.subject_id " +
            "INNER JOIN subject_time " +
            "ON subject.subject_id = subject_time.subjectOpen_subject_id " +
            "INNER JOIN per_professor_class " +
            "ON subject.subject_id = per_professor_class.subject_id " +
            "where possible_grade = #{possible_grade};";
    @Select(findSubjectOpenGrade)
    @ResultMap("subjectOpenAllResultSet")
    List<SubjectOpenDTO> getSubjectOpenGradeAll(@Param("possible_grade") int possible_grade);

    String findSubjectOpenProfessor =  "select * from subject " +
            "INNER JOIN subject_open " +
            "ON subject.subject_id = subject_open.subject_id " +
            "INNER JOIN subject_time " +
            "ON subject.subject_id = subject_time.subjectOpen_subject_id " +
            "INNER JOIN per_professor_class " +
            "ON subject.subject_id = per_professor_class.subject_id " +
            "where professor_user_id = #{professor_user_id};";
    @Select(findSubjectOpenProfessor)
    @ResultMap("subjectOpenAllResultSet")
    List<SubjectOpenDTO> getSubjectOpenProfessorAll(@Param("professor_user_id") int professor_id);

    String findSubjectOpenProfessorAndGrade =  "select * from subject " +
            "INNER JOIN subject_open " +
            "ON subject.subject_id = subject_open.subject_id " +
            "INNER JOIN subject_time " +
            "ON subject.subject_id = subject_time.subjectOpen_subject_id " +
            "INNER JOIN per_professor_class " +
            "ON subject.subject_id = per_professor_class.subject_id " +
            "where professor_user_id = #{professor_user_id} " +
            "and possible_grade = #{possible_grade};";
    @Select(findSubjectOpenProfessorAndGrade)
    @ResultMap("subjectOpenAllResultSet")
    List<SubjectOpenDTO> getSubjectOpenProfessorAndGradeAll(@Param("professor_user_id") int professor_user_id, @Param("possible_grade") int possible_grade);

    @Insert("{CALL openSubject(#{subject_id}, #{max_people}, #{subject_plan}," +
            " #{plan_modify_date}, #{credit}, #{day}, #{time}, #{classroom}, #{professor_user_id})}")
    @Options(statementType = StatementType.CALLABLE)
    void openSubject(@Param("subject_id") int subject_id,
               @Param("max_people") int max_people,
               @Param("subject_plan") String subject_plan,
               @Param("plan_modify_date") Date plan_modify_date,
               @Param("credit") int credit,
               @Param("day") String day,
               @Param("time") String time,
               @Param("classroom") String classroom,
               @Param("professor_user_id") int professor_user_id);

    @Update("{ CALL updateSubjectOpen( #{subject_id}, #{subject_name}, #{max_people}, #{possible_grade}, #{credit}, #{day}, #{time}, #{classroom})}")
    @Options(statementType = StatementType.CALLABLE)
    void updateSubjectOpen(@Param("subject_id") int subject_id,@Param("subject_name") String subject_name, @Param("max_people") int max_people, @Param("possible_grade") int possible_grade, @Param("credit") int credit, @Param("day") String day, @Param("time") String time, @Param("classroom") String classroom);

    @Delete("{ CALL closeSubject(#{close_subject_id})}")
    @Options(statementType = StatementType.CALLABLE)
    void deleteSubjectOpen(@Param("close_subject_id") int subject_id);

    String getPerProfessorClass = "select * from per_professor_class where subject_id = #{subject_id};";
    @Select(getPerProfessorClass)
    @Results(id="perProfessorClass", value = {
            @Result(property = "professor_user_id", column = "professor_user_id"),
            @Result(property = "subject_id", column = "subject_id")
    })
    List<PerProfessorClassDTO> getPerProfessorClass(@Param("subject_id") int subject_id);

    String getPerProfessorClassStudent =
            "select * from take_class_state " +
                    "INNER JOIN user ON user.id = take_class_state.student_user_id " +
                    "INNER JOIN student ON student.user_id = user.id " +
                    "where take_class_state.subject_id = #{subject_id} ";
    @Select(getPerProfessorClassStudent)
    @Results(id="perProfessorClassStudent", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "birth", column = "birth"),
            @Result(property = "phoneNumber", column = "phoneNumber"),
            @Result(property = "major", column = "major"),
            @Result(property = "grade", column = "grade")
    })
    List<PerProfessorClassStudentDTO> getPerProfessorClassStudent(@Param("subject_id") int subject_id);

    String getPerProfessorClassStudentCount =
            "select count(*) from take_class_state " +
                    "INNER JOIN user ON user.id = take_class_state.student_user_id " +
                    "INNER JOIN student ON student.user_id = user.id " +
                    "where take_class_state.subject_id = #{subject_id} ";
    @Select(getPerProfessorClassStudentCount)
    int getPerProfessorClassStudentCount(@Param("subject_id") int subject_id);

    String updateSubjectPlanModifySubjectOpen =  "update subject_open set plan_modify_date = #{plan_modify_date} where subject_id = #{subject_id}";
    @Update(updateSubjectPlanModifySubjectOpen)
    void updateSubjectPlanModifySubjectOpen(@Param("subject_id") int subject_id, @Param("plan_modify_date") Date plan_modify_date);

    String updateSubjectPlanSubjectOpen =  "update subject_open set subject_plan = #{subject_plan} where subject_id = #{subject_id}";
    @Update(updateSubjectPlanSubjectOpen)
    void updateSubjectPlanSubjectOpen(@Param("subject_id") int subject_id, @Param("subject_plan") String subject_plan);
}