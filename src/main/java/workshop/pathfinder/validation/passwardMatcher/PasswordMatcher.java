package workshop.pathfinder.validation.passwardMatcher;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import workshop.pathfinder.domain.DTOs.UserRegisterBindingModel;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterBindingModel> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterBindingModel userRegisterBindingModel, ConstraintValidatorContext context) {

        if (userRegisterBindingModel.getPassword() != null &&
            userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode(userRegisterBindingModel.getConfirmPassword())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }

}
