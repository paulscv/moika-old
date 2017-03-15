package io.khasang.moika.dao.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
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

    @Test
    public void commonOrdersDetail() throws Exception {
        //       addOrdermDetail(ordermDetail, второй параметр);
//        OrdermDetail ordermDetail = new OrdermDetail( new BigDecimal("1"), new BigDecimal("1000"));
//        ordermDetailDao.addOrdermDetail(ordermDetail);
//        ordermDetail = new OrdermDetail( new BigDecimal("2"), new BigDecimal("2000"));
//        ordermDetailDao.addOrdermDetail(ordermDetail);
//        ordermDetail = ordermDetailDao.getOrdermDetail(1l);
//        ordermDetail.setSumOfWork(new BigDecimal("1111"));
//        ordermDetailDao.updateOrdermDetail(ordermDetail);
//        ordermDetail = new OrdermDetail( new BigDecimal("3"), new BigDecimal("3000"));
//        ordermDetailDao.addOrdermDetail(ordermDetail);
//        ordermDetailDao.deleteOrdermDetailk(ordermDetail);
//        List<OrdermDetail> l = ordermDetailDao.getAllOrdermDetail();
    }

}