package io.khasang.moika.dao.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.OrdermDao;
import io.khasang.moika.dao.OrdermDetailDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class OrdermDetailDaoImplTest {
    @Autowired
    OrdermDetailDao ordermDetailDao;
    @Autowired
    OrdermDao ordermDao;

    @Test
    public void commonOrdersDetail() throws Exception {
//        OrdermDetail ordermDetail;
//        Orderm orderm =new Orderm("1");
//        ordermDao.create(orderm);
//        long id =orderm.getId();
//
//        ordermDetail = new OrdermDetail( new BigDecimal("1"), new BigDecimal("1000"));
//        ordermDetail.setOrderm(orderm);
//        ordermDetailDao.create(ordermDetail);
//        ordermDetail = new OrdermDetail( new BigDecimal("2"), new BigDecimal("2000"));
//        ordermDetail.setOrderm(orderm);
//        ordermDetailDao.create(ordermDetail);
//        List<OrdermDetail> l = ordermDetailDao.getOrdermDetailForOrderm(orderm);
    }
}