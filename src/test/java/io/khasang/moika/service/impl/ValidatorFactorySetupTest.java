package io.khasang.moika.service.impl;

import io.khasang.moika.config_for_test_purposes.TestAppConfig;
import io.khasang.moika.dao.UserDAO;
import io.khasang.moika.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestAppConfig.class})
public class ValidatorFactorySetupTest {

    @Autowired
    UserDAO userDAO;

    @Test
    public void test(){
        User u = new User();
        u.setEmail("abcd@mail.ru");
        userDAO.create(u);
    }
}
