package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.UpersonDao;
import io.khasang.moika.entity.Uperson;
import io.khasang.moika.entity.Uphone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class UpersonImplTest {


    @Autowired
    UpersonDao personDao;

/*
    @Test
    @Transactional
    public void testUperoneList() {
        final String testString = "Мойка на Мойке";
        List<WashFacility> fcltList = null;
        try {
            fcltList = fcltService.getWashFacilitiesOnNet(1);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Facility  list is null", fcltList);
        Assert.assertFalse("Facility  list is empty", fcltList.isEmpty());
        boolean isWashFacility = false;
        boolean isBox = false;
        int dur = 0;
        for (WashFacility item : fcltList) {
            if (item.getName().equalsIgnoreCase("Мойка на Мойке")) {
                isWashFacility = true;
                List<WashBox> boxList = item.getWashBoxes();
                for (WashBox box : boxList) {
                    if (box.getBoxName().equalsIgnoreCase("Бокс 1")) {
                        isBox = true;
                        break;
                    }
                }
            }
        }
        Assert.assertTrue("Facility  list not contain "+testString, isWashFacility);
        Assert.assertTrue("Facility  list not contain box", isBox);
    }
*/
    @Test
    @Transactional
    public void testAddUperson() {
        final String personName = "Сидоров Иван Петрович";
        Uperson person = new Uperson(); // подготовили класс для тестирования

        person.setName(personName);
        person.setBirthDate(Date.valueOf("1998-05-15"));

        List<Uphone> phoneList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Uphone phone = new Uphone();
            phone.setNumber("962-555-55-55"+i);
            phoneList.add(phone);
        }

        person.setPhones(phoneList);

        Uperson resPerson = new Uperson();
        try {
            resPerson = personDao.create(person);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Facility  list is null", resPerson);
        boolean isTel = false;
            if (resPerson.getName().equalsIgnoreCase("Сидоров Иван Петрович")) {
                Assert.assertEquals("tel list not ", 4, resPerson.getPhones().size());
                List<Uphone>  resPhoneList = resPerson.getPhones();
                for (Uphone phone : resPhoneList) {
                    if (phone.getNumber().equalsIgnoreCase("962-555-55-551")) {
                        isTel= true;
                        break;
                    }
                }
        }
        Assert.assertTrue("Person does not exist"+personName, resPerson.getName().equalsIgnoreCase(personName));
        Assert.assertTrue("Phone  list not contain number", isTel);
    }
}
