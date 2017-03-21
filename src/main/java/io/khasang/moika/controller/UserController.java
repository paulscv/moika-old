package io.khasang.moika.controller;

import io.khasang.moika.dao.impl.UserDAOImpl;
import io.khasang.moika.entity.Role;
import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import io.khasang.moika.util.BindingResultToMapParser;
import io.khasang.moika.util.DataAccessUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Контроллер интерфейсов пользователя
 *
 * @author Rostislav Dublin, Kovalev Aleksandr
 * @since 2017-03-01
 */

@Controller
@RequestMapping(path = "/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
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

    //TODO Возможно стоит добавить функционал подтверждения регистрации через email (?!phone?!)
    @PostMapping()
    @ResponseBody
    public Object createUser(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return Collections.singletonMap("errors", BindingResultToMapParser.getMap(result));
        }
        user.setEnabled(true);
        userService.createClientUser(user);
        return Collections.singletonMap("redirect", " ");
    }

    @RequestMapping("/getallusers")
    @ResponseBody
    public List<User> user() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object loginUser(@RequestBody User user) {
        //возвращаем null если пользователь уже залогирован
        if (getCurrentUser() != null) {
            return null;
        }
        try {
            AuthenticationManager authenticationManager = authenticationManagerBuilder.getOrBuild();
            Authentication request = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            return Collections.singletonMap("redirect", " ");
        } catch (AuthenticationException e) {
            return Collections.singletonMap("errorMsg", "Authentication failed: " + e.getMessage());
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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
        if (getCurrentUser() != null) {
            new SecurityContextLogoutHandler().logout(request, response,
                    SecurityContextHolder.getContext().getAuthentication());
        }
        return "redirect: /";
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody Map<String, Object> userForUpdateData) {

        User userForUpdate = userService.findById(id);

        if (userForUpdate == null) {
            return new ResponseEntity("No User found for ID " + id, HttpStatus.NOT_FOUND);
        }

        dataAccessUtil.setNewValuesToBean(userForUpdate, userForUpdateData);

        Set<ConstraintViolation<User>> errors = validator.validate(userForUpdate);
        if (!errors.isEmpty()) {
            return new ResponseEntity(errors, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        userService.updateUser(userForUpdate);

        return new ResponseEntity(userForUpdate, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Object getUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object loginUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        userService.deleteUser(user);
        return user;
    }

    @PostMapping(path = "/eee", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User test123(@RequestBody Object user, BindingResult result) {
        return new User();
    }


    @PostMapping(value = "/validation",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> validation(
            @RequestBody User userToCheck,
            @RequestParam(defaultValue = "") String fieldName,
            BindingResult result) {

        Set<ConstraintViolation<User>> violations = validator.validate(userToCheck);

        Map<String, List<String>> errors = new HashMap<>();
        for (ConstraintViolation cv : violations) {

            String violatedPathName = cv.getPropertyPath().toString();

            if (!fieldName.equals("") && !violatedPathName.equals(fieldName)) {
                continue;
            }

            errors.compute(cv.getPropertyPath().toString(), (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(cv.getMessage());
                return v;
            });
        }

        if (errors.isEmpty()) {
            return Collections.singletonMap("success", true);
        } else {
            if(fieldName.equals("")) {
                return Collections.singletonMap("error", errors);
            }else{
                return Collections.singletonMap("error",
                        errors.get(fieldName).stream().collect(Collectors.joining("; ")));
            }
        }
    }

    //Функции управления ролями
    @RequestMapping(value = "/{id}/role", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object grantRole(@RequestBody Role role, @PathVariable("id") long id) {
        User user = userService.findById(id);
        userService.grantRole(user, role);
        return user;
    }

    @RequestMapping(value = "/{id}/role", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object revokeRole(@RequestBody Role role, @PathVariable("id") long id) {
        User user = userService.findById(id);
        userService.revokeRole(user, role);
        return user;
    }

}
