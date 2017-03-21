package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.CleanService;
import io.khasang.moika.entity.IBaseMoikaServiceAddInfo;
import io.khasang.moika.entity.MoikaService;
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
public class CleanServiceImplTest {

    @Autowired
    MoikaServiceDataAccessService moikaService;


    @Test
    @Transactional
    public void testCleanServiceList() {
        List<MoikaService> serviceList = null;
        try {
            serviceList = moikaService.getServicesByType(2);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isCode = false;
        BigDecimal cost = null;
        int dur = 0;
        for (MoikaService item : serviceList) {
            if (item.getServiceName().equalsIgnoreCase("Чиска салона")) {
                isCode = true;
                List<IBaseMoikaServiceAddInfo> addInfo = item.getServiceAddInfo();
                for (IBaseMoikaServiceAddInfo serviceInfo : addInfo) {
                    if (((CleanService) serviceInfo).getDirtTypeEntity().getTypeCode().equals("NORM")) {
                        cost = serviceInfo.getServiceCost();
                        dur = serviceInfo.getServiceDuration();
                        break;
                    }
                }
            }
        }
        Assert.assertTrue("Service types list not contain name \"Чиска салона\"", isCode);
        Assert.assertEquals("Service types list not contain name \"Чиска салона\" загрязннеие \"NORM\"", new BigDecimal("500.00").setScale(2), cost);
        Assert.assertEquals("Service types list  name \"Чиска салона\" not last", 300, dur);
    }
}
