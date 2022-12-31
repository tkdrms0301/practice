package persistence.dto;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class MySubjectEnrollDTO {
    private int subject_id;
    private String subject_name;
    private boolean open;
    private int possible_grade;
    private int max_people;
    private int number_people;
    private String subject_plan;
    private Date plan_modify_date;
    private int credit;
    private String day;
    private String time;
    private String classroom;
    private int professor_user_id;
    private Date enroll_time_start;
    private Date enroll_time_end;
    private int student_user_id;
}
