package persistence.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentDTO extends UserDTO{
    private String major;
    private int grade;

    @Override
    public void printInfo() {
        System.out.println("|" + getId() + "|" + getName() + "|" + getBirth() + "|" + getPhoneNumber() + "|" + getMajor() + "|" + getGrade() + "학년|" );
    }
}
