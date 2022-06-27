package persistence.dto;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PerProfessorClassStudentDTO {
    private int id;
    private String name;
    private Date birth;
    private String phoneNumber;
    private String major;
    private int grade;
}
