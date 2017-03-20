package io.khasang.moika.service.impl;


import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.BoxStatusDao;
import io.khasang.moika.dao.BoxTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashAddrDao;
import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;
import io.khasang.moika.service.PskvorWashBoxDaoService;
import io.khasang.moika.service.PskvorWashFacilityDaoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class WashFacilityImplTest {


    @Autowired
    PskvorWashFacilityDaoService fcltService;

    @Autowired
    PskvorWashBoxDaoService boxService;

    @Autowired
    BoxStatusDao boxStatusDao;

    @Autowired
    BoxTypeDao boxTypeDao;

    @Autowired
    WashAddrDao washAddr;

    final String testExistingFacilityName = "Мойка на Фонтанке";
    final String testNonExistFacilityName = "Мойка test";
    final String stausCode = "WORKING";
    final String typeCode = "MEDIUM";


    @Test
    @Transactional
    public void testWashFacilityServiceList() {

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
            if (item.getName().equalsIgnoreCase(testExistingFacilityName)) {
                isWashFacility = true;
                List<WashBox> boxList = item.getWashBoxes();
                for (WashBox box : boxList) {
                    if (box.getBoxName().equalsIgnoreCase("№ 1")) {
                        isBox = true;
                        break;
                    }
                }
            }
        }
        Assert.assertTrue("Facility  list not contain " + testExistingFacilityName, isWashFacility);
        Assert.assertTrue("Facility  list not contain box", isBox);
    }

    @Test
    @Transactional
    public void testAddWashFacility() {

        WashFacility fclt = new WashFacility(); // подготовили класс для тестирования

        fclt.setName(testNonExistFacilityName);
        fclt.setIdNet(1);
        fclt.setDescription("моет тех, кто чешется");
        fclt.setIdAddr(1); // setFacilityAddr(washAddr.get(1L));

   //     BoxStatus boxStatus = boxStatusDao.getEntityByCode(stausCode);
   //     BoxType boxType = boxTypeDao.getEntityByCode(typeCode);
        List<WashBox> boxList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            WashBox box = new WashBox();
            box.setBoxName("Бокс № " + i);
            box.setIdStatus((short) 1);
            box.setIdType(2);
            box.setDescription(box.getBoxName() + " " + fclt.getName());
        //    box.setBoxStatusEntity(boxStatus);
        //    box.setBoxTypeEntity(boxType);
            boxList.add(box);
        }

        fclt.setWashBoxes(boxList);

        WashFacility resFclt = new WashFacility();
        try {
            resFclt = fcltService.addWashFacility(fclt);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertNotNull("Facility is null", resFclt);
        boolean isBox = false;
        boolean isType;
        boolean isStatus;
        if (resFclt.getName().equalsIgnoreCase(testNonExistFacilityName)) {
            Assert.assertEquals("Facility  does not contain boxes", 4, resFclt.getWashBoxes().size());
            List<WashBox> resBoxList = resFclt.getWashBoxes();
            for (WashBox box : resBoxList) {
                if (box.getBoxName().equalsIgnoreCase("Бокс № 1")) {
                    isBox = true;
                    Assert.assertTrue("Facility  box status not " + stausCode, box.getBoxStatusEntity().getStatusCode().equalsIgnoreCase(stausCode));
                    Assert.assertTrue("Facility  box type not " + typeCode, box.getBoxTypeEntity().getTypeCode().equalsIgnoreCase(typeCode));
                    break;
                }
            }
        }
        Assert.assertTrue("Facility does not contain " + testNonExistFacilityName, resFclt.getName().equalsIgnoreCase(testNonExistFacilityName));
        Assert.assertTrue("Facility does not contain box", isBox);
    }

    @Test
    @Transactional
    public void testAddBoxToFacility() {
        BoxStatus boxStatus = boxStatusDao.getEntityByCode(stausCode);
        BoxType boxType = boxTypeDao.getEntityByCode(typeCode);

        WashBox box = new WashBox();
        box.setBoxName("Бокс № test");
        box.setDescription("Unit test box");
        box.setBoxStatusEntity(boxStatus);
        box.setBoxTypeEntity(boxType);

        try {
            List<WashFacility> fcltList = fcltService.getAllWashFacilities();
            box.setIdFacility(fcltList.get(0).getId());
            fcltList.get(0).getWashBoxes().add(box);
           // boxService.addWashBox(box);
            fcltService.updateWashFacility(fcltList.get(0));
            List<WashBox> boxList = fcltList.get(0).getWashBoxes();
            Assert.assertTrue("Facility does not contain box", boxList.contains(box));
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
    public void testDeleteBoxFromFacility() {
        try {
            List<WashFacility> fcltList = fcltService.getAllWashFacilities(); // подготовили класс для тестирования
            List<WashBox> boxList = fcltList.get(0).getWashBoxes();
            int prevBoxCnt = boxList.size();
            WashBox boxToDelete = boxList.get(0);
            boxList.remove(boxToDelete);
            fcltService.updateWashFacility(fcltList.get(0));
            boxList = fcltList.get(0).getWashBoxes();
            int postBoxCnt = boxList.size();
            Assert.assertFalse("Facility still contain box", boxList.contains(boxToDelete));
            Assert.assertNotEquals("Facility box list still the same size", prevBoxCnt,postBoxCnt);
        } catch (MoikaDaoException e) {
            Assert.fail(e.getMessage());
        }
    }

}
