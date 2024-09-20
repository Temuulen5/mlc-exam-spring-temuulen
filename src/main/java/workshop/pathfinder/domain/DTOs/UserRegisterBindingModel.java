package workshop.pathfinder.domain.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;
import workshop.pathfinder.validation.checkUniqueUser.ValidateUserName;
import workshop.pathfinder.validation.passwardMatcher.PasswordMatch;

@Data
@PasswordMatch
@ValidateUserName
public class UserRegisterBindingModel {
    @NotNull
    @Size(min = 3)
    private String userName;
    @Size(min = 5)
    private String fullName;
    @Email
    private String email;
    @Min(0)
    @Max(100)
    private int age;
    @Size(min = 5, max = 20)
    private String password;
    @Size(min = 5, max = 20)
    private String confirmPassword;
}