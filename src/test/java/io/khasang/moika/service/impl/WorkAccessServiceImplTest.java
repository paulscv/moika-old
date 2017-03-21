package io.khasang.moika.service.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.WorkDao;
import io.khasang.moika.entity.Work;
import io.khasang.moika.service.WorkAccessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class WorkAccessServiceImplTest {
    @Autowired
    WorkAccessService workAccessService;
    @Autowired
    WorkDao workDao;

//    @Before
//    public void setUp() throws Exception {
//    }
//    @After
//    public void tearDown() throws Exception {
//
//    }
//
    @Test
    @Rollback
    @Transactional
    public void commonWork() throws Exception {
        Work work = new Work("Мытье кузова", new BigDecimal("333.333"), 30);
        workAccessService.addWork(work);
        work=new Work("Чистка салона",new BigDecimal("222.225"),0);
        workAccessService.addWork(work);
        work = new Work("Массаж водителя",new BigDecimal("1000"),0);
        workAccessService.addWork(work);

        Work work1= workAccessService.getWork("Мытье кузова");
        work1.setPrice(new BigDecimal("555.50"));
        workAccessService.updateWork(work1);

        List<Work> list = workAccessService.getAllWork();
        work = workAccessService.deleteWork(workAccessService.getWork("Массаж водителя"));
        work = workAccessService.getWork("Массаж водителя");
    }
}