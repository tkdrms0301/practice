package persistence.dto;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class SubjectEnrollTimeDTO {
    private int possible_grade;
    private Date enroll_time_start;
    private Date enroll_time_end;
}
