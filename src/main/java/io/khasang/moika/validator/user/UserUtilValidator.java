package io.khasang.moika.validator.user;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Map;

/***
 * Вылидатор для проверки данных с фронтэнда
 *
 * @since 10.03.2017
 * @author Kovalev Aleksandr
 */
@Component
public class UserUtilValidator implements Validator{
    private final static String EMAIL_PATTERN = ".+@.+\\..+";
    private final static String LOGIN_PATTERN = "^\\w+$";

    @Override
    public boolean supports(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }


    @Override
    @SuppressWarnings("unchecked")
    public void validate(Object target, Errors errors) {
        Map<String,Object> map = (Map<String, Object>) target;
        if(map.containsKey("email")){
            if(!StringUtils.isEmpty(map.get("email"))&&!map.get("email").toString().matches(EMAIL_PATTERN)){
                errors.reject("error","Неверный формат email адреса");
            }
        }else if(map.containsKey("login")){
            if(!StringUtils.isEmpty(map.get("login"))&&!map.get("login").toString().matches(LOGIN_PATTERN)){
                errors.reject("error","Неверный формат логина");
            }
        }
    }
}
