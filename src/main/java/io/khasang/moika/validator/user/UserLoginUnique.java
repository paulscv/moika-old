package io.khasang.moika.validator.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация валидатора уникальности Login данного пользователя.
 * @author Rostislav Dublin
 * @since 2017-03-18
 */

@Target({TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UserLoginUniqueValidator.class)
@Documented
public @interface UserLoginUnique {

    static String MESSAGE = "{user.login.not_unique.message}";

    String message() default MESSAGE;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
