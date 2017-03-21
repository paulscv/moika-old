package io.khasang.moika.entity;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.BoxTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class BoxTypeEntityTest {

    @Autowired
    BoxTypeDao boxTypeDao;


    @Test
    @Transactional
    public void testBoxList() {
        List<BoxType> typeList = null;
        try {
            typeList = boxTypeDao.getAll();
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("List is null", typeList);
        Assert.assertFalse("List is empty", typeList.isEmpty());
        boolean isTypeCode = false;
        for (BoxType item : typeList) {
            if (item.getTypeCode().equalsIgnoreCase("CAR")) {
                isTypeCode = true;
                break;
            }
        }
        Assert.assertTrue("Box types list not contain  \"CAR\"", isTypeCode);
    }


}
