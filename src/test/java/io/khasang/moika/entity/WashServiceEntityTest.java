package io.khasang.moika.entity;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashServiceDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class WashServiceEntityTest {

    @Autowired
    WashServiceDao serviceDao;


    @Test
    @Transactional
    public void testWashServiceList() {
        List<WashService> serviceList = null;
        try {
            serviceList = serviceDao.getAll();
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isWashCode = false;
        BigDecimal cost = null;
        int dur = 0;
        for (WashService item : serviceList) {
            if (item.getCarTypeEntity().getTypeCode().equalsIgnoreCase("CAR")) {
                isWashCode = true;
                cost = item.getServiceCost();
                dur = item.getServiceDuration();
                break;
            }
        }
        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", isWashCode);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("350.00").setScale(2), cost);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 300, dur);
    }
}
