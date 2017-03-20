package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ServiceStatus;
import io.khasang.moika.service.MoikaServiceStatusService;
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
public class ServiceStatusImplTest {

    @Autowired
    MoikaServiceStatusService serviceStatusService;


    @Test
    @Transactional
    public void testGetServiceStatusList(){
        List<ServiceStatus> serviceStatusList = null;
        try {
            serviceStatusList = serviceStatusService.getAllServiceStatuses();
        } catch (MoikaDaoException e) {
            Assert.fail( e.getMessage());
        }
        Assert.assertNotNull("Service status list is null",serviceStatusList);
        Assert.assertFalse("Service status list is empty", serviceStatusList.isEmpty());
        boolean isCode = false;
        for (ServiceStatus item : serviceStatusList) {
            if (item.getStatusCode().equals("ON")) {
                isCode = true;
            }
        }
        Assert.assertTrue("Service status list not contain status code WORKING",isCode);
    }


    @Test
    @Transactional
    public void testGetServiceStatusByCode(){
        ServiceStatus serviceStatus = null;
        try {
            serviceStatus = serviceStatusService.getServiceStatusByCode("ON");
        } catch (MoikaDaoException e) {
            Assert.fail( e.getMessage());
        }
        Assert.assertNotNull("Service status  is null",serviceStatus);
        Assert.assertTrue("Service status not code ON",serviceStatus.getStatusCode().equals("ON"));
    }
}
