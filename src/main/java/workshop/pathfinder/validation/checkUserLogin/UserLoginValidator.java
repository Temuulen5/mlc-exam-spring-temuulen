package workshop.pathfinder.validation.checkUserLogin;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import workshop.pathfinder.domain.DTOs.UserLoginForm;
import workshop.pathfinder.domain.entities.User;
import workshop.pathfinder.repository.UserRepository;

public class UserLoginValidator implements ConstraintValidator<ValidateLoginForm, UserLoginForm> {

    private final UserRepository userRepository;

    public UserLoginValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(ValidateLoginForm constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginForm userLoginModel, ConstraintValidatorContext constraintValidatorContext) {
        User user = this.userRepository.findByUsername(userLoginModel.getUserName());

        return user.getId() != null
               && user.getPassword()
                       .equals(userLoginModel.getPassword());
    }
}
