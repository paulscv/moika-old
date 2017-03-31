package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ServiceType;
import io.khasang.moika.service.MoikaServiceTypesService;

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
public class ServiceTypesImplTest {

    @Autowired
    MoikaServiceTypesService serviceTypesService;


    @Test
    @Transactional
    public void testGetServiceStatusList(){
        List<ServiceType> serviceTypesList = null;
        try {
            serviceTypesList = serviceTypesService.getAllTypes();
        } catch (MoikaDaoException e) {
            Assert.fail( e.getMessage());
        }
        Assert.assertNotNull("Service types list is null",serviceTypesList);
        Assert.assertFalse("Service types list is empty", serviceTypesList.isEmpty());
        boolean isCode = false;
        for (ServiceType item : serviceTypesList) {
            if (item.getTypeCode().equals("WASH")) {
                isCode = true;
            }
        }
        Assert.assertTrue("Service types list not contain type code WASH",isCode);
    }

    @Test
    @Transactional
    public void testGetServiceTypeByCode(){
        ServiceType servicetype = null;
        try {
            servicetype = (ServiceType)serviceTypesService.getTypeByCode("WASH");
        } catch (MoikaDaoException e) {
            Assert.fail( e.getMessage());
        }
        Assert.assertNotNull("Service status  is null",servicetype);
        Assert.assertTrue("Service status not code ON",servicetype.getTypeCode().equals("WASH"));
    }

}
