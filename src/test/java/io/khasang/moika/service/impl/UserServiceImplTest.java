package io.khasang.moika.service.impl;

import io.khasang.moika.config_for_test_purposes.TestAppConfig;
import io.khasang.moika.entity.User;
import io.khasang.moika.service.UserService;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.FlushModeType;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
@EnableTransactionManagement
@Transactional
public class UserServiceImplTest {

    static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    UserService userService;

    @Autowired
    Validator validator;

    @Autowired
    SessionFactory sessionFactory;

    @Ignore
    @Test
    public void performUserCRUD() throws Exception {
        String login = "admin";
        String phone = "9272170718";
        String email = "abcd@mail.ru";

        User user;

        //Delete if exists
        user = userService.findByLogin(login);
        if (user != null) {
            userService.deleteUser(user);
            LOGGER.debug("Existed User deleted");
        }


        //Create
        user = new User();
        user.setLogin(login);
        user.setFirstName("Петруха");
        user.setLastName("Кулебякин");
        user.setEmail(email);
        user.setPassword("admin");
        user.setPhone(phone);
        userService.createUser(user);
        LOGGER.debug("New User created");

        long id = user.getId();
        Assert.assertTrue(id > 0);

        //Read by login
        user = null;
        user = userService.findByLogin(login);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(user.getId(), id);
        LOGGER.debug("User found by id");

        //Update
        String middleName = "Контрабасович";
        user.setMiddleName(middleName);
        userService.updateUser(user);
        LOGGER.debug("User updated by new middleName");

        //Read by email
        user = null;
        user = userService.findByEmail(email);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(user.getId(), id);

        Assert.assertEquals(user.getMiddleName(), middleName);
        LOGGER.debug("MiddleName checked after update");

//        //Delete
//        userService.deleteUser(user);
//        LOGGER.debug("User deleted");

//        //Read by id
//        user = null;
//        user = userService.findById(id);
//        Assert.assertNull(user);
//        LOGGER.debug("User absent");
    }

    @Test
    public void userEmailUniqueValidationTest() {
        //Check for user with given Email and create if not exists
        String email = "EmailUniqueValidationTest@mail.ru";
        /*
        System.out.println(sessionFactory.getCurrentSession().getFlushMode());

        User firstUser = userService.findByEmail(email);
        if (firstUser == null) {
            firstUser = new User();
            firstUser.setLogin("nextUser");
            firstUser.setFirstName("Петруха");
            firstUser.setLastName("Кулебякин");
            firstUser.setEmail(email);
            firstUser.setPassword("admin");
            firstUser.setPhone("1234567890");
            userService.createUser(firstUser);
            LOGGER.debug("Absent First User created");
        } else {
            LOGGER.debug("User exists");
        }

        //Validate user
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(firstUser);
        assertEquals(0, constraintViolations.size());
        //assertEquals("may not be null", constraintViolations.iterator().next().getMessage());
*/
        //Create
        User nextUser = new User();
        nextUser.setLogin("nextUser");
        nextUser.setFirstName("Петруха");
        nextUser.setLastName("Кулебякин");
        nextUser.setEmail(email);
        nextUser.setPassword("admin");
        nextUser.setPhone("0987654321");

        FlushModeType initialFlushModeType = sessionFactory.getCurrentSession().getFlushMode();
        System.out.println("Stated FlushModeType before a entity save  operation: "+ initialFlushModeType);
        userService.createUser(nextUser);
        LOGGER.debug("Next User created");
        System.out.println(sessionFactory.getCurrentSession().getFlushMode());

        //constraintViolations = validator.validate(nextUser);
        //assertTrue(constraintViolations.size() > 0);
        //assertEquals("may not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void userLoginValidationTest() {
        User nextUser = new User();
        nextUser.setLogin("");
        Set<ConstraintViolation<User>> violations = validator.validateProperty(nextUser, "login");

        nextUser.setLogin(null);
        violations = validator.validateProperty(nextUser, "login");

        LOGGER.debug("Next User created");
        System.out.println(sessionFactory.getCurrentSession().getFlushMode());
    }


/*
    @Test
    public void deleteUser() throws Exception {
        User user =
    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void isLoginUsed() throws Exception {

    }

    @Test
    public void isEmailFree() throws Exception {

    }

    @Test
    public void getEncodedPassword() throws Exception {

    }
*/

}