package workshop.pathfinder.validation.checkUniqueUser;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import workshop.pathfinder.domain.DTOs.UserRegisterForm;
import workshop.pathfinder.repository.UserRepository;

public class UserExistenceValidator implements ConstraintValidator<ValidateUserName, UserRegisterForm> {

    private final UserRepository userRepository;

    public UserExistenceValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void initialize(ValidateUserName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterForm userRegisterForm, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByUsername(userRegisterForm.getUserName());
    }
}
