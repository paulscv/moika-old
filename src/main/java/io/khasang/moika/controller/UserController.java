package io.khasang.moika.controller;

import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import io.khasang.moika.util.BindingResultParser;
import io.khasang.moika.util.ConstraintViolationsParser;
import io.khasang.moika.util.DataAccessUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Контроллер интерфейсов пользователя
 *
 * @author Rostislav Dublin, Kovalev Aleksandr
 * @since 2017-03-01
 */

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private UserService userService;

    @Autowired
    private DataAccessUtil dataAccessUtil;

    @Autowired
    private Validator validator;

    private User getCurrentUser() {
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByLogin(currentLogin);

    }

    /**
     * Создание пользователя на основании JSON-строки с его данными (поле-значение), переданной в теле POST-запроса.
     * Отстроенный объект пользователя сразу валидируется и сохраняется только при отсутствии ошибок.
     *
     * @param user       объект нового пользователя, отстроенный по полученной JSON-строке.
     * @param userResult объект с результатами биндинга переданных данных на объект и его валидации
     * @return карта с одним узлом, именованным в зависимости от результатов операции:
     * "errors" - в случае провала (содержит список ошибок в формате поле->текст ошибки);
     * "success" - ID созданного пользователя
     */
    @PostMapping(produces = "application/json; charset=UTF-8")
    public Map createUser(@RequestBody @Valid User user, BindingResult userResult) {
        if (userResult.hasErrors()) {
            return Collections.singletonMap("errors", BindingResultParser.getFieldErrorFlatMap(userResult));
        }
        user.setEnabled(true);
        userService.createClientUser(user);
        //TODO Возможно стоит добавить функционал подтверждения регистрации через email (?!phone?!)
        return Collections.singletonMap("success", user.getId());
    }

    /**
     * Валидация объекта пользователя на основании JSON-строки с его данными (поле-значение), переданной в теле POST-запроса.
     * Отстроенный объект пользователя сразу валидируется и результаты валидации возвращаются.
     *
     * @param user       объект пользователя, отстроенный по полученной JSON-строке.
     * @param userResult объект с результатами валидации объекта пользователя
     * @param fieldName  фильтр результатов валидации по имени поля. Если задан, то операция считается успешной
     *                   даже при наличии ошибок валидации объекта при условии, что они не относятся к этому полю.
     * @return объект с одним узлом, именованным в зависимости от результатов операции:
     * "errors" - в случае провала (содержит список в формате поле->текст ошибки);
     * "error" - в случае провала в режиме проверки одного поля (содержит единственную ошибку);
     * "success" - в случае успеха (содержит значение true)
     */
    @PostMapping(value = "/validation", produces = "application/json;charset=UTF-8")
    public Map validation(
            @RequestParam(defaultValue = "") String fieldName,
            @RequestBody @Valid User user,
            BindingResult userResult) {

        if (!userResult.hasErrors()) {
            return Collections.singletonMap("success", true);
        }

        Map<String, String> errors = BindingResultParser.getFieldErrorFlatMap(userResult);

        if (fieldName.equals("")) {
            return Collections.singletonMap("errors", errors);
        } else if (errors.containsKey(fieldName)) {
            return Collections.singletonMap("error", errors.get(fieldName));
        }
        return Collections.singletonMap("success", true);
    }

    /**
     * Выдача полного списка пользователей.
     *
     * @return
     */
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Получение данных пользователя по его Id.
     *
     * @param id Id пользователя
     * @return объект пользователя
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        return user;
    }

    /**
     * Обновление пользователя переданными данными. Данные принимаются в виде карты "поле->новое значение",
     * что позволяет передавать сведения о только действительно изменяемых полях. Программа отыскивает пользователя по ID,
     * выбирает его в объект и накладывает на него обновлённые данные. Затем производится валидация.
     * При обнаружении ошибок операция отклоняется а при их отсутствии - производится.
     *
     * @param id        Id пользователя
     * @param updateMap карта устанавливаемых значений
     * @return карта с одним узлом, именованным в зависимости от результатов операции:
     * "errors" - в случае провала (содержит список ошибок в формате поле->текст ошибки);
     * "success" - true
     */
    @PutMapping("/{id}")
    public Map updateUser(@PathVariable Long id, @RequestBody Map<String, Object> updateMap) {

        User userForUpdate = userService.findById(id);

        if (userForUpdate == null) {
            return Collections.singletonMap("errors", "No User found for ID " + id);
        }

        dataAccessUtil.setNewValuesToBean(userForUpdate, updateMap);

        try {
            userService.updateUser(userForUpdate);
        } catch (ConstraintViolationException e) {
            Map<String, String> errors = ConstraintViolationsParser.getFieldErrorFlatMap(e.getConstraintViolations());
            return Collections.singletonMap("errors", errors);
        }
        return Collections.singletonMap("success", true);
    }

    /**
     * Удаление пользователя.
     *
     * @param id Id пользователя
     * @return карта с одним узлом, именованным в зависимости от результатов операции:
     * "error" - в случае провала (содержит текст ошибки);
     * "success" - true
     */
    @DeleteMapping(value = "/{id}")
    public Map deleteUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        userService.deleteUser(user);
        return Collections.singletonMap("success", true);
    }


    /**
     * Аутентификация пользователя на основании JSON-строки с его данными (поле-значение), переданной в теле POST-запроса.
     * Строка должна включать Логин и Пароль, и эта пара должна быть действительной.
     *
     * @param user объект пользователя для аутентификации.
     * @return объект с одним узлом, именованным в зависимости от результатов операции:
     * "errors" -> описание ошибки аутентификации;
     * "success" - true
     */
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public Map loginUser(@RequestBody User user) {
        try {
            AuthenticationManager authenticationManager = authenticationManagerBuilder.getOrBuild();
            Authentication request = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            return Collections.singletonMap("success", true);
        } catch (AuthenticationException e) {
            return Collections.singletonMap("error", e.getMessage());
        }
        /*
        The solution is the following:
        Check user's password
        Authorize user:

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

        Autowire rememberMeService and call:
        rememberMeServices.onLoginSuccess(request, response, authenticatedUser);
    */
    }

    /**
     * Выход аутентифицированного пользователя из системы.
     *
     * @return объект с одним узлом, именованным в зависимости от результатов операции:
     * "success" - true
     */
    @GetMapping("/logout")
    public Object logoutUser(HttpServletRequest request, HttpServletResponse response) {
        if (getCurrentUser() != null) {
            new SecurityContextLogoutHandler().logout(request, response,
                    SecurityContextHolder.getContext().getAuthentication());
        }
        return Collections.singletonMap("success", true);
    }

    //Функции управления ролями
    @PutMapping(value = "/{id}/role", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object grantRole(@RequestBody Role role, @PathVariable("id") long id) {
        User user = userService.findById(id);
        userService.grantRole(user, role);
        return user;
    }

    @DeleteMapping(value = "/{id}/role", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object revokeRole(@RequestBody Role role, @PathVariable("id") long id) {
        User user = userService.findById(id);
        userService.revokeRole(user, role);
        return user;
    }

}
