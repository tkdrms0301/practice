package Persistence.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    private int userNum;
    private String userID;
    private String userPW;
    private String email;
}
