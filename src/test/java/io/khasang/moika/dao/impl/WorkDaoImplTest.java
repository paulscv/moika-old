package io.khasang.moika.dao.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.WorkDao;
import io.khasang.moika.entity.Work;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class WorkDaoImplTest {
    @Qualifier("workDaoImpl")
    @Autowired
    WorkDao workDao;
    @Test
    public void commonWork() throws Exception {
        Work work = new Work("Мытье кузова", new BigDecimal("333.333"), 30);
        workDao.create(work);
        work = new Work("Чистка салона",new BigDecimal("222.225"),0);
        workDao.create(work);
        long id =work.getId();
        work=workDao.getWork("Мытье кузова");
        work.setPrice(new BigDecimal("444"));
        work=workDao.update(work);
        work = new Work("Массаж водителя",new BigDecimal("1000"),0);
        workDao.create(work);
        List<Work> l = workDao.getAll();
        work =workDao.get(id);
        work =workDao.getWork("Массаж водителя");
        work = workDao.delete(work);
    }

}