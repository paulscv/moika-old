package io.khasang.moika.validator.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Валидирует, что строка непустая и её длина находится в допустимом диапазоне.
 * Объединяет функциональность аннотаций @NotNull и @Length.
 * Позволяет единообразно уведомлять пользователя как в случае, если он не заполнил поле,
 * так и если заполнил, но неприемлемо по длине.
 *
 * @author Rostislav Dublin
 * @since 21.03.2017
 */
@Constraint(validatedBy = {NotNullLengthValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface NotNullLength {

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    String message() default "{io.khasang.moika.validator.common.NotNullLength.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

