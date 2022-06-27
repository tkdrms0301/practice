package persistence.dto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter

public class UserDTO {
    private int id;
    private String pw;
    private int group_id;
    private String name;
    private Date birth;
    private String phoneNumber;

    public void printInfo() {
        System.out.println("|" + getId() + "|" + getName() + "|" + getBirth() + "|" + getPhoneNumber() + "|");
    }
}
