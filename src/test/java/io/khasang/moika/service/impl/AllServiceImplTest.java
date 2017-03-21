package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.IBaseMoikaServiceAddInfo;
import io.khasang.moika.entity.MoikaService;
import io.khasang.moika.entity.WashService;
import io.khasang.moika.service.MoikaServiceDataAccessService;
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
public class AllServiceImplTest {

    @Autowired
    MoikaServiceDataAccessService moikaService;


    @Test
    @Transactional
    public void testAllServiceList() {
        List<MoikaService> serviceList = null;
        try {
            serviceList = moikaService.getAllServices();
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isWashCode = false;
        BigDecimal cost = null;
        int dur = 0;
        for (MoikaService item : serviceList) {
            if (item.getServiceName().equalsIgnoreCase("Ручная мойка машины")) {
                isWashCode = true;
                List<IBaseMoikaServiceAddInfo> addInfo = item.getServiceAddInfo();
                for (IBaseMoikaServiceAddInfo serviceInfo : addInfo) {
                    if (((WashService)serviceInfo).getCarTypeEntity().getTypeCode().equals("CAR")) {
                       cost = serviceInfo.getServiceCost();
                       dur = serviceInfo.getServiceDuration();
                    }
                }
            }
        }
        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", isWashCode);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("1000.00").setScale(2), cost);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 300, dur);
    }
}
