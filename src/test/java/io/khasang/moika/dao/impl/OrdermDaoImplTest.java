package io.khasang.moika.dao.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.OrdermDao;
import io.khasang.moika.dao.OrdermDetailDao;
import io.khasang.moika.entity.Orderm;
import io.khasang.moika.entity.OrdermDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
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
        ordermDao.addOrderm(orderm);
        orderm =new Orderm("2");
        ordermDao.addOrderm(orderm);
        orderm = ordermDao.getOrderm(1l);
        orderm.setNumber("11");
        orderm=ordermDao.updateOrderm(orderm);

        OrdermDetail ordermDetail = new OrdermDetail();
        ordermDetail.setSumOfWork(new BigDecimal(111));
        ordermDetail.setQuantity(new BigDecimal(11));
        ordermDetail = ordermDetailDao.addOrdermDetail(ordermDetail,orderm.getId());

        ordermDetail.setSumOfWork(new BigDecimal(2000));
        ordermDetail.setQuantity(new BigDecimal(2));
        ordermDetail.setOrderm(orderm);
        ordermDetail = ordermDetailDao.addOrdermDetail(ordermDetail,orderm.getId());
        List<OrdermDetail> l= orderm.getOrdersDetails();
        ordermDao.deleteOrderm(orderm);
        orderm =new Orderm("33");
        orderm =ordermDao.addOrderm(orderm);
        ordermDao.deleteOrderm(orderm);
        List<Orderm> list =ordermDao.getAllOrderm();
    }
}