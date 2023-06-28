package online.shop.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginForm {
    @NotNull
    private String loginId;
    @NotEmpty
    private String password;
}
