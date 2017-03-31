package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.CarTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.ServiceStatusDao;
import io.khasang.moika.dao.ServiceTypeDao;
import io.khasang.moika.entity.*;
import io.khasang.moika.service.MoikaServiceDataAccessService;
import io.khasang.moika.service.WashServiceDataAccessService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class WashServiceImplTest {


    @Autowired
    MoikaServiceDataAccessService moikaService;
    @Autowired
    ServiceTypeDao serviceTypeDao;
    @Autowired
    ServiceStatusDao serviceStatusDao;

    @Autowired
    WashServiceDataAccessService washService;

    @Autowired
    CarTypeDao carTypeDao;

    final String serviceName = "Мойка силой мысли";
    final String testDescr = "к нам на полставки устроился Йода";

    @Test
    @Transactional
    public void testWashServiceListFromService() {
        List<MoikaService> serviceList = null;
        try {
            serviceList = moikaService.getServicesByType(1);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Service  list is null", serviceList);
        Assert.assertFalse("Service  list is empty", serviceList.isEmpty());
        boolean isWashCode = false;
        BigDecimal cost = null;
        int dur = 0;
        for (MoikaService item : serviceList) {
            if (item.getId() == 11) {
                isWashCode = true;
                List<IBaseMoikaServiceAddInfo> addInfo = item.getServiceAddInfo();
                for (IBaseMoikaServiceAddInfo serviceInfo : addInfo) {
                    if (((WashService) serviceInfo).getCarTypeEntity().getTypeCode().equals("CAR")) {
                        cost = serviceInfo.getServiceCost();
                        dur = serviceInfo.getServiceDuration();
                        break;
                    }
                }
            }
        }
        Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", isWashCode);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("420.00").setScale(2), cost);
        Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 300, dur);
    }

    @Test
    @Transactional
    public void testWashServiceList() {
        List<WashService> serviceList = null;
        try {
            serviceList = washService.getConcreatServiceById(3);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Wash Service  list is null", serviceList);
        Assert.assertFalse("Wash Service  list is empty", serviceList.isEmpty());
        BigDecimal cost = null;
        int dur = 0;
        for (WashService item : serviceList) {
            Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"",
                    item.getServiceEntity().getTypeCode().equalsIgnoreCase("WASH"));
            if (item.getCarTypeEntity().getTypeCode().equals("CAR")) {
                cost = item.getServiceCost();
                dur = item.getServiceDuration();
            }
            Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("350.00").setScale(2), cost);
            Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 1200, dur);
        }
    }

        @Test
        @Transactional
        public void testAddWashService () {

            MoikaService service = new MoikaService(); // подготовили объект для тестирования

            service.setName(serviceName);
            service.setIdFacility(3);
            service.setDescription(testDescr);
            ServiceType stEntity = serviceTypeDao.getEntityByCode("WASH");
            service.setServiceTypeEntity(stEntity);
            ServiceStatus stsEntity = serviceStatusDao.getEntityByCode("PLAN");
            service.setIdStatus((short) 1);

            CarType carType = carTypeDao.getEntityByCode("CAR");
            List<IBaseMoikaServiceAddInfo> serviceList = new ArrayList<>();
            WashService serviceAddInfo = new WashService();
            serviceAddInfo.setCarTypeEntity(carType);
            serviceAddInfo.setServiceCost(new BigDecimal("3500.00"));
            serviceAddInfo.setServiceDuration(10);
            serviceList.add(serviceAddInfo);

            carType = carTypeDao.getEntityByCode("SUV");
            serviceAddInfo = new WashService();
            serviceAddInfo.setCarTypeEntity(carType);
            serviceAddInfo.setServiceCost(new BigDecimal("5500.00"));
            serviceAddInfo.setServiceDuration(20);
            serviceList.add(serviceAddInfo);

            service.setServiceAddInfo(serviceList);

            MoikaService testService = new MoikaService(); // подготовили объект для возврата
            try {
                testService = moikaService.addService(service);
            } catch (MoikaDaoException e) {
                Assert.fail(e.getMessage());
            }
            Assert.assertNotNull("Wash servise is null", testService);
            boolean isWashCode = false;
            BigDecimal cost = null;
            int dur = 0;
            if (testService.getName().equalsIgnoreCase(serviceName)) {
                List<IBaseMoikaServiceAddInfo> addInfo = testService.getServiceAddInfo();
                Assert.assertEquals("Was service  list not contain ", 2, addInfo.size());
                isWashCode = true;
                for (IBaseMoikaServiceAddInfo serviceInfo : addInfo) {
                    if (((WashService) serviceInfo).getCarTypeEntity().getTypeCode().equals("CAR")) {
                        cost = serviceInfo.getServiceCost();
                        dur = serviceInfo.getServiceDuration();
                        break;
                    }
                }
            }
            Assert.assertTrue("Service types list not contain name \"Ручная мойка машины\"", isWashCode);
            Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not cost", new BigDecimal("3500.00").setScale(2), cost.setScale(2));
            Assert.assertEquals("Service types list  name \"Ручная мойка машины\" not last", 10, dur);
        }
    }

