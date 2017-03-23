package io.khasang.moika.integration;


import io.khasang.moika.entity.User;
import io.khasang.moika.util.DataAccessUtil;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserIntegrationTest extends Assert {

    static final Logger LOGGER = LoggerFactory.getLogger(UserIntegrationTest.class);
    DataAccessUtil dataAccessUtil = new DataAccessUtil();

    @Test
    public void userCRUDTest() {
        String login = "t" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        RestTemplate t = new RestTemplate();

        User user = new User();
        HttpEntity<User> e = new HttpEntity<>(user);
        Map<String, Object> resultMap;

        LOGGER.debug("1. Создание пользователя");
        LOGGER.debug("1.1. Предварительная валидация  - ПРОВАЛ (не задан телефон)");
        user.setLogin(login);
        user.setFirstName("Тест");
        user.setEmail(login + "@mail.ru");
        user.setPassword("123456Qw");

        resultMap = t.postForObject("http://localhost:8080/users", e, Map.class);
        assertTrue(resultMap.containsKey("errors"));
        assertTrue(resultMap.get("errors").toString().contains("phone"));


        LOGGER.debug("1.2. Предварительная валидация  - УСПЕХ");
        user.setPhone("1234567890");
        resultMap = t.postForObject("http://localhost:8080/users/validation", e, Map.class);
        assertTrue(resultMap.containsKey("success"));

        LOGGER.debug("1.3. Собственно создание");
        resultMap = t.postForObject("http://localhost:8080/users", e, Map.class);
        assertTrue(resultMap.containsKey("success"));

        LOGGER.debug("1.4. Проверка наличия присвоенного ID");
        Object idInfo = resultMap.get("success");
        assertTrue(idInfo != null);
        assertTrue(idInfo instanceof Number);
        Long createdUserId = ((Number) idInfo).longValue();

        LOGGER.debug("2. Чтение пользователей");
        LOGGER.debug("2.1 Проверка наличия созданного юзера с присвоенным ID в БД");
        User createdUser = t.getForObject("http://localhost:8080/users/{id}", User.class, createdUserId);
        assertTrue(createdUser != null);
        assertEquals(createdUser.getLogin(), login);

        LOGGER.debug("2.2. Получение списка пользователей и проверка, что вновь созданный в нём есть");
        User[] users = t.getForObject("http://localhost:8080/users", User[].class);
        assertTrue(Arrays.asList(users).stream().anyMatch(u -> u.getId() == createdUserId && u.getLogin().equals(login)));

        LOGGER.debug("3. Обновление пользователя");
        LOGGER.debug("3.1. Попытка выставления некорректного Email - ПРОВАЛ");
        String incorrectEmail = "incorrectEmailAddress";
        Map<String, Object> values = new HashMap<>();
        values.put("email", incorrectEmail);

        HttpEntity<Map<String, Object>> ee = new HttpEntity<>(values);

        resultMap = t.exchange("http://localhost:8080/users/{id}", HttpMethod.PUT, ee, Map.class, createdUserId).getBody();
        assertTrue(resultMap.containsKey("errors"));
        assertTrue(resultMap.get("errors").toString().contains("email"));

        LOGGER.debug("3.2. Выставление Отчетства и Фамилии - УСПЕХ");
        String newMiddleName = "Тестович";
        String newLastName = "Тестов";

        values.clear();
        values.put("middleName", newMiddleName);
        values.put("lastName", newLastName);

        resultMap = t.exchange("http://localhost:8080/users/{id}", HttpMethod.PUT, ee, Map.class, createdUserId).getBody();
        assertTrue(resultMap.containsKey("success"));

        LOGGER.debug("3.3. Получаем изменённого пользователя из БД и проверяем Отчетство и Фамилию");
        createdUser = t.getForObject("http://localhost:8080/users/{id}", User.class, createdUserId);
        assertTrue(createdUser != null);
        assertEquals(createdUser.getMiddleName(), newMiddleName);
        assertEquals(createdUser.getLastName(), newLastName);

        LOGGER.debug("4. Удаление пользователя");
        LOGGER.debug("4.1. Производим удаление");
        HttpEntity eee = HttpEntity.EMPTY;
        resultMap = t.exchange("http://localhost:8080/users/{id}", HttpMethod.DELETE, eee, Map.class, createdUserId).getBody();
        assertTrue(resultMap.containsKey("success"));

        LOGGER.debug("4.2. Проверяем отсутствие юзера в БД");
        createdUser = t.getForObject("http://localhost:8080/users/{id}", User.class, createdUserId);
        assertTrue(createdUser == null);
    }


   // @Autowired
   // UserService userService;
    @Autowired
    DataAccessUtil dataAccessUtil;


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
    public void loginTest() {

        String login = "rostislav";
        String password = "123";

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        //Create
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        RestTemplate restTemplate = new RestTemplate();

        Map result;
        result = restTemplate.postForObject("http://localhost:8080/users/login", httpEntity, Map.class);

        System.out.println(result.toString());

        user.setLogin("www");
        user.setPassword("123");
        result = restTemplate.postForObject("http://localhost:8080/users/login", httpEntity, Map.class);

        System.out.println(result.toString());
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
