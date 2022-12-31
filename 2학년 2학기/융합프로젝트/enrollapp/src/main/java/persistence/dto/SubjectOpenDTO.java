package persistence.dto;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SubjectOpenDTO extends SubjectNotOpenDTO{
    private int max_people;
    private int number_people;
    private String subject_plan;
    private Date plan_modify_date;
    private int credit;
    private String day;
    private String time;
    private String classroom;
    private int professor_user_id;

    @Override
    public void printInfo() {
        System.out.println("|" + getSubject_id() + "|" + getSubject_name() + "|" + getPossible_grade() + "|" + getNumber_people() + "/" + getMax_people() + "|"
        + getSubject_plan() + "|" + getCredit() + "|" + getDay() + "|" + getTime() + "|" + getClassroom());
    }
}
