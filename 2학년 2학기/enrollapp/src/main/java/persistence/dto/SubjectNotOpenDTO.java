package persistence.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SubjectNotOpenDTO {
    private int subject_id;
    private String subject_name;
    private boolean open;
    private int possible_grade;

    public void printInfo(){
        if(open)
            System.out.println("|" + getSubject_id() + "|" + getSubject_name() + "|개설됨|" + getPossible_grade());
        else
            System.out.println("|" + getSubject_id() + "|" + getSubject_name() + "|개설안됨|" + getPossible_grade());
    }
}
