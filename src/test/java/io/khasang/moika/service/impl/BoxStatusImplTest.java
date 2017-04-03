package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.service.BoxStatusDataAccessService;
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
public class BoxStatusImplTest {

    @Autowired
    BoxStatusDataAccessService boxStatusService;


    @Test
    @Transactional
    public void testGetBoxeStatusList(){
        List<BoxStatus> boxStatusList = null;
        try {
            boxStatusList = boxStatusService.getAllStatuses();
        } catch (MoikaDaoException e) {
            Assert.fail( e.getMessage());
        }
        Assert.assertNotNull("Box status list is null",boxStatusList);
        Assert.assertFalse("Box status list is empty", boxStatusList.isEmpty());
        boolean isCode = false;
        for (BoxStatus item : boxStatusList) {
            if (item.getStatusCode().equals("WORKING")) {
                isCode = true;
            }
        }
        Assert.assertTrue("Box status list not contain status code WORKING",isCode);
    }


    @Test
    @Transactional
    public void testGetBoxStatusByCode(){
        BoxStatus boxStatus = null;
        try {
            boxStatus = (BoxStatus)boxStatusService.getStatusByCode("WORKING");
        } catch (MoikaDaoException e) {
            Assert.fail( e.getMessage());
        }
        Assert.assertNotNull("Box status  is null",boxStatus);
        Assert.assertTrue("Box status not code ON",boxStatus.getStatusCode().equals("WORKING"));
    }
}
