package workshop.pathfinder.domain.DTOs;

import lombok.Data;
import workshop.pathfinder.validation.checkUserLogin.ValidateLoginForm;

@Data
@ValidateLoginForm
public class UserLoginForm {
    private String userName;
    private String password;
}
