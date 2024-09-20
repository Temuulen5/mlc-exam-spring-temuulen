package workshop.pathfinder.validation.checkUniqueUser;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UserExistenceValidator.class)
public @interface ValidateUserName {
    String message() default "Username is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
