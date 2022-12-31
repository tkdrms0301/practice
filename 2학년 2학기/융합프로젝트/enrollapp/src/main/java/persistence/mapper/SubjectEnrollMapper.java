package persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import persistence.dto.*;

import java.sql.Date;
import java.util.List;

public interface SubjectEnrollMapper {

    @Update("update subject_enroll_time set enroll_time_start = #{enroll_time_start}, enroll_time_end = #{enroll_time_end} where possible_grade = #{possible_grade};")
    void updateSubjectEnrollTime(@Param("enroll_time_start") Date enroll_time_start, @Param("enroll_time_end") Date enroll_time_end, @Param("possible_grade") int possible_grade);

    @Insert("insert into take_class_state(student_user_id, subject_id) values (#{student_user_id}, #{subject_id});")
    void insertSubjectEnrollTakeClassState(@Param("student_user_id") int student_user_id, @Param("subject_id") int subject_id);

    @Select("select * from user inner join student on user.id = student.user_id")
    @Results(id="studentResultSet", value ={
            @Result(property = "id", column = "id"),
            @Result(property = "pw", column = "pw"),
            @Result(property = "group_id", column = "group_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "birth", column = "birth"),
            @Result(property = "phoneNumber", column = "phoneNumber"),
            @Result(property = "major", column = "major"),
            @Result(property = "grade", column = "grade"),
    })
    List<StudentDTO> getStudentAll();

    String findSubjectOpenAll =  "select * from subject " +
            "INNER JOIN subject_open " +
            "ON subject.subject_id = subject_open.subject_id " +
            "INNER JOIN subject_time " +
            "ON subject.subject_id = subject_time.subjectOpen_subject_id " +
            "INNER JOIN per_professor_class " +
            "ON subject.subject_id = per_professor_class.subject_id;";

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

    String findSubjectOpenAndEnrollTimeAll =
            "select * from subject " +
            "INNER JOIN subject_open ON subject.subject_id = subject_open.subject_id " +
            "INNER JOIN subject_time ON subject.subject_id = subject_time.subjectOpen_subject_id " +
            "INNER JOIN per_professor_class ON subject.subject_id = per_professor_class.subject_id " +
            "INNER JOIN subject_enroll_time ON subject.possible_grade = subject_enroll_time.possible_grade";

    @Select(findSubjectOpenAndEnrollTimeAll)
    @Results(id="subjectEnrollResultSet", value ={
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
            @Result(property = "professor_user_id", column = "professor_user_id"),
            @Result(property = "enroll_time_start", column = "enroll_time_start"),
            @Result(property = "enroll_time_end", column = "enroll_time_end")
    })
    List<SubjectEnrollDTO> getSubjectEnroll();

    String getSubjectEnrollTimeAll = "SELECT * FROM subject_enroll_time;";
    @Select(getSubjectEnrollTimeAll)
    @Results(id="subjectEnrollTimeResultSet", value = {
            @Result(property = "subject_id", column = "subject_id"),
            @Result(property = "enroll_time_start", column = "enroll_time_start"),
            @Result(property = "enroll_time_end", column = "enroll_time_end")
    })
    List<SubjectEnrollTimeDTO> getSubjectEnrollTimeAll();

    String updateSubjectNumberPeople = "update subject_open set number_people = #{number_people} where subject_id = #{subject_id};";
    @Update(updateSubjectNumberPeople)
    void updateSubjectNumberPeople(@Param("subject_id") int subject_id, @Param("number_people") int number_people);

    String getMySubjectEnroll = "select * from subject " +
            "INNER JOIN subject_open ON subject.subject_id = subject_open.subject_id " +
            "INNER JOIN subject_time ON subject.subject_id = subject_time.subjectOpen_subject_id " +
            "INNER JOIN per_professor_class ON subject.subject_id = per_professor_class.subject_id " +
            "INNER JOIN subject_enroll_time ON subject.possible_grade = subject_enroll_time.possible_grade " +
            "INNER JOIN take_class_state ON subject.subject_id = take_class_state.subject_id " +
            "WHERE take_class_state.student_user_id = #{student_user_id}";
    @Select(getMySubjectEnroll)
    @Results(id="MySubjectEnrollResultSet", value ={
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
            @Result(property = "professor_user_id", column = "professor_user_id"),
            @Result(property = "enroll_time_start", column = "enroll_time_start"),
            @Result(property = "enroll_time_end", column = "enroll_time_end"),
            @Result(property = "student_user_id", column = "student_user_id")
    })
    List<MySubjectEnrollDTO> getMySubjectEnroll(@Param("student_user_id") int student_user_id);

    String deleteEnroll = "DELETE from take_class_state where student_user_id = #{student_user_id} and subject_id = #{subject_id}";
    @Delete(deleteEnroll)
    void deleteSubjectEnroll(@Param("student_user_id") int student_user_id, @Param("subject_id") int subject_id);
}
