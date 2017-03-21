package io.khasang.moika.validator.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация валидатора уникальности Email данного пользователя.
 * @author Rostislav Dublin
 * @since 2017-03-15
 */

@Target({TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UserEmailUniqueValidator.class)
@Documented
public @interface UserEmailUnique {

    String MESSAGE = "{user.email.not_unique.message}";

    String message() default MESSAGE;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
