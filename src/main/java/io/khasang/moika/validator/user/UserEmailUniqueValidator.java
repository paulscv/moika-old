package io.khasang.moika.validator.user;

import io.khasang.moika.entity.User;
import io.khasang.moika.entity.User_;
import io.khasang.moika.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валидатор уникальности Email данного пользователя.
 * @author Rostislav Dublin
 * @since 2017-03-15
 */
public class UserEmailUniqueValidator implements ConstraintValidator<UserEmailUnique, User> {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserService userService;

    public UserEmailUniqueValidator() {
        System.out.println("UserEmailUniqueValidator!!!");
    }

    @Override
    public void initialize(UserEmailUnique constraintAnnotation) {
    }

    /**
     * Проверка отсутствия ДРУГОГО пользователя с идентичным адресом.
     * Заметьте, что он выполняется в отдельной транзакции, которую сам же и начинает.
     * Заметьте, что ему разрешено "грязное чтение". Здесь это уместно, так как запрос делается ради проверки того,
     * не занят ли уже данный адрес электронной почты, и пусть он захватит даже те транзакции, которые ещё не завершены.
     *
     * @param user              проверяемый пользователь
     * @param constraintContext контекст ограничения
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
    public boolean isValid(User user, ConstraintValidatorContext constraintContext) {
        if (user == null || StringUtils.isEmpty(user.getEmail())) {
            return true;
        }

        boolean isValid = !userService.isEmailUsed(user.getEmail(), user);

        if (!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext
                    .buildConstraintViolationWithTemplate(UserEmailUnique.MESSAGE)
                    .addPropertyNode(User_.email.getName()).addConstraintViolation();
        }
        return isValid;
    }
}
