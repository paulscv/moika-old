package io.khasang.moika.dao.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.OrdermDao;
import io.khasang.moika.dao.OrdermDetailDao;
import io.khasang.moika.entity.Orderm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class OrdermDaoImplTest {
    @Autowired
    OrdermDao ordermDao;
    @Autowired
    OrdermDetailDao ordermDetailDao;

    @Test
    public void commonOrderm() throws Exception {
        Orderm orderm =new Orderm("1");
        ordermDao.create(orderm);
        orderm =new Orderm("2");
        ordermDao.create(orderm);
        orderm =new Orderm("3");
        ordermDao.create(orderm);
        long id = orderm.getId();
        orderm = ordermDao.getOrderm("2");
        orderm.setIs_made(true);
        orderm = ordermDao.update(orderm);
        List<Orderm> list =ordermDao.getAll();
        orderm = ordermDao.getOrderm("3");
        orderm = ordermDao.delete(orderm);
    }
}