package persistence.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AdminDTO extends UserDTO{
    @Override
    public void printInfo() {
        System.out.println("|" + getId() + "|" + getName() + "|" + getBirth() + "|" + getPhoneNumber() + "|");
    }
}
