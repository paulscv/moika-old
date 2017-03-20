package io.khasang.moika.integration;

import io.khasang.moika.dao.*;
import io.khasang.moika.entity.*;
import io.khasang.moika.service.MoikaServiceDataAccessService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WashServiceIntegrationalTest {


    @Autowired
    MoikaServiceDataAccessService moikaService;
    @Autowired
    ServiceStatusDao serviceStatusDao;
    @Autowired
    ServiceTypeDao serviceTypeDao;
    @Autowired
    CarTypeDao carTypeDao;

    final String serviceName = "Мойка силой мысли";
    final String testDescr = "к нам на полставки устроился Йода";

    final String serviceTypeCode = "WASH";
    final String carTypeCode1 = "CAR";
    final String carTypeCode2 = "SUV";

    @Ignore
    @Before
    public void initTests() {
        System.out.println("Tests adding wash service are beginning...");
    }


    @Ignore
    @Test
    @Transactional
    @Rollback
    public void createTestWashService() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity;

        MoikaService service = new MoikaService(); // подготовили объект для тестирования
        service.setName(serviceName);
        service.setIdFacility(3);
        service.setDescription(testDescr);


        ServiceStatus serviceStatus  = new ServiceStatus();

        httpEntity = new HttpEntity<>(serviceStatus, headers); //подготовили запрос для BoxStatus
        serviceStatus = restTemplate.exchange(
                "http://localhost:8080/serviceStatus/{id}/",
                HttpMethod.GET,
                httpEntity,
                ServiceStatus.class,"PLAN").getBody();
        Assert.assertNotNull("Could not get service status PLAN !",serviceStatus);
        service.setIdStatus((short)serviceStatus.getId());
        service.setServiceStatusEntity(serviceStatus);

        ServiceType serviceType = new  ServiceType();
        httpEntity = new HttpEntity<>(serviceType, headers); //подготовили запрос для BoxType
        serviceType = restTemplate.exchange(
                "http://localhost:8080/serviceType/{code}/",
                HttpMethod.GET,
                httpEntity,
                ServiceType.class,"WASH").getBody();
        Assert.assertNotNull("Could not get service type  WASH",serviceType);
        service.setServiceTypeEntity(serviceType);


        List<IBaseMoikaServiceAddInfo> serviceList = new ArrayList<>();
        WashService serviceAddInfo = new WashService();
        CarType carType = restTemplate.exchange(
                "http://localhost:8080/carType/{code}/",
                HttpMethod.GET,
                httpEntity,
                CarType.class,"CAR").getBody();
        Assert.assertNotNull("Could not get car type CAR",serviceType);
        serviceAddInfo.setCarTypeEntity(carType);
        serviceAddInfo.setServiceCost(new BigDecimal("3500.00"));
        serviceAddInfo.setServiceDuration(10);
        serviceList.add(serviceAddInfo);

        serviceAddInfo = new WashService();
        carType = restTemplate.exchange(
                "http://localhost:8080/carType/{code}/",
                HttpMethod.GET,
                httpEntity,
                CarType.class,"SUB").getBody();
        Assert.assertNotNull("Could not get car type CAR",serviceType);
        carType = carTypeDao.getEntityByCode("SUV");
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
        Assert.assertTrue("Service types list not contain name \"Мойка силой мысли\"", isWashCode);
        Assert.assertEquals("Service types list  name \"Мойка силой мысли\" not cost", new BigDecimal("3500.00").setScale(2), cost.setScale(2));
        Assert.assertEquals("Service types list  name \"Мойка силой мысли\" not last", 10, dur);
    }


    @Test
    public void getWashServiceList() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<List<MoikaService>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<MoikaService>> resultAll = restTemplate.exchange(
                "http://localhost:8080/washFacilitylist/",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<MoikaService>>() {
                });
        List<MoikaService> serviceList = resultAll.getBody();
        Assert.assertFalse("Request body does not contain Wash Services", serviceList.isEmpty());

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
    public void getServiceById() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);


        HttpEntity<List<MoikaService>> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<MoikaService>> resultAll = restTemplate.exchange(
                "http://localhost:8080/washFacility/{id}/",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<MoikaService>>() {
                }, 8);

        List<MoikaService> serviceList = resultAll.getBody();
        Assert.assertNotNull("Reques body is incorrect", resultAll);
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
}
