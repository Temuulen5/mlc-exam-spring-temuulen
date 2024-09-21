package workshop.pathfinder.validation.passwardMatcher;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import workshop.pathfinder.domain.DTOs.UserRegisterForm;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterForm> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterForm userRegisterForm, ConstraintValidatorContext context) {

        if (userRegisterForm.getPassword() != null &&
            userRegisterForm.getPassword().equals(userRegisterForm.getConfirmPassword())) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode(userRegisterForm.getConfirmPassword())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }

}
