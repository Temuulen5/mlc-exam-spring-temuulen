package workshop.pathfinder.domain.DTOs;

import jakarta.validation.constraints.Size;
import lombok.Data;
import workshop.pathfinder.validation.checkUserLogin.ValidateLoginForm;

@Data
@ValidateLoginForm
public class UserLoginForm {
    @Size(min = 3)
    private String userName;
    @Size(min = 5, max = 20)
    private String password;
}
