package io.khasang.moika.validator.common;

import io.khasang.moika.entity.User;
import io.khasang.moika.entity.User_;
import io.khasang.moika.service.UserService;
import io.khasang.moika.validator.user.UserEmailUnique;
import org.hibernate.SessionFactory;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;

/**
 * Валидирует, что строка непустая и её длина находится в допустимом диапазоне.
 * @author Rostislav Dublin
 * @since 2017-03-15
 */
public class NotNullLengthValidator implements ConstraintValidator<NotNullLength, CharSequence> {

    Length parameters;
    LengthValidator validator;

    @Override
    public void initialize(NotNullLength constraintAnnotation) {
        parameters = new Length(){

            @Override
            public Class<? extends Annotation> annotationType() {
                return constraintAnnotation.annotationType();
            }

            @Override
            public int min() {
                return constraintAnnotation.min();
            }

            @Override
            public int max() {
                return constraintAnnotation.max();
            }

            @Override
            public String message() {
                return constraintAnnotation.message();
            }

            @Override
            public Class<?>[] groups() {
                return constraintAnnotation.groups();
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return constraintAnnotation.payload();
            }
        } ;
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return false;
        }

        validator = new LengthValidator();
        validator.initialize(parameters);

        return validator.isValid(value, constraintValidatorContext);
    }
}
