package io.khasang.moika.integration;


import io.khasang.moika.entity.User;
import io.khasang.moika.util.DataAccessUtil;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserIntegrationTest {

    static final Logger LOGGER = LoggerFactory.getLogger(UserIntegrationTest.class);

//    @Autowired
//    UserService userService;

//    @Ignore
    @Test
    public void createUser() {

        String login = "assistant";
        String phone = "9272170718";
        String email = "abcd@mail.ru";

        User user;

        //Delete if exists
//        user = userService.findByLogin(login);
//        if (user != null) {
//            userService.deleteUser(user);
//            LOGGER.debug("Existed User deleted");
//        }


        //Create
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        user = new User();
        user.setLogin(login);
        user.setFirstName("Петруха");
        user.setLastName("Кулебякин");
        user.setMiddleName("qwe");
        user.setEmail(email);
        user.setPassword("123456Qw");
        user.setPhone(phone);
        Calendar calendar = Calendar.getInstance();
        user.setBirthday(calendar.getTime());
        user.setEnabled(true);

//        Set<Role> roles = new HashSet<>();
//        Role role = new Role();
//        role.setId(12);
//        role.setName("Hacker");
//        role.setDescruiption("01011101");
//        roles.add(role);
//        user.setRoles(roles);


        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        RestTemplate restTemplate = new RestTemplate();
        User result = restTemplate
                .exchange("http://localhost:8080/user/reg", HttpMethod.POST, httpEntity, User.class)
                .getBody();

        Assert.assertNotNull(result);
        Assert.assertEquals(login, result.getLogin());
        Assert.assertNotNull(result.getId());

        ResponseEntity<User> responseEntity = restTemplate.exchange(
                "http://localhost:8080/user/{id}", HttpMethod.GET, null, User.class,
                result.getId()
        );
        User resultUser = responseEntity.getBody();
        Assert.assertNotNull(resultUser);
        Assert.assertEquals(resultUser.getPhone(), result.getPhone());

        LOGGER.debug("New User created");

    }


    @Test
    public void getUser() {

        User resultUser = new RestTemplate().getForObject("http://localhost:8080/users/{id}", User.class, 1L);

        Assert.assertNotNull(resultUser);
        Assert.assertEquals("Дублин", resultUser.getLastName());
    }

    @Test
    public void testUser123() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity httpEntity = new HttpEntity<>(Collections.singletonMap("email", "aaaa"), headers);

        Object result = new RestTemplate().postForObject("http://localhost:8080/users/eee", httpEntity, List.class);
        System.out.println(result.getClass().getSimpleName());
        System.out.println(result.toString());
    }

    @Test
    public void userJSR303ValidationTest() {

        User testUser = new User();
        testUser.setId(0);
        testUser.setLastName("Дублин");
        testUser.setLogin("rostislav");
        testUser.setEmail("crm_guru@mail.ru");
        testUser.setPhone("123111");

        ResponseEntity resultUserEntity = new RestTemplate().postForEntity(
                "http://localhost:8080/users/validation",
                dataAccessUtil.getHttpEntityForJSON(testUser),
                Map.class);

        System.out.println(resultUserEntity.getBody().toString());
    }


    @Test
    public void updateUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                "http://localhost:8080/user/{id}",
                HttpMethod.GET,
                null,
                User.class,
                1L
        );
        User resultUser = responseEntity.getBody();
        Assert.assertNotNull(resultUser);

        HttpEntity<User> httpEntity = new HttpEntity<>(resultUser, headers);
        resultUser.setLastName("Дублин");
        resultUser.setLogin(null);
        User resultUpdUser = null;
        try {
            resultUpdUser = restTemplate.exchange
                    ("http://localhost:8080/user/update/{id}",
                            HttpMethod.PUT,
                            httpEntity,
                            User.class,
                            resultUser.getId())
                    .getBody();
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
        Assert.assertNotNull(resultUpdUser);
        Assert.assertEquals("Дублин", resultUpdUser.getLastName());
        Assert.assertNotNull(resultUpdUser.getId());
    }
    
    
}
