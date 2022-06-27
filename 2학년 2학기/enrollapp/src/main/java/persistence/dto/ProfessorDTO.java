package persistence.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProfessorDTO extends UserDTO{
    private String major;

    @Override
    public void printInfo() {
        System.out.println("|" + getId() + "|" + getName() + "|" + getBirth() + "|" + getPhoneNumber() + "|" + getMajor() + "|");
    }
}
