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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class OrdermDetailDaoImplTest {
    @Qualifier("ordermDetailDaoImpl")
    @Autowired
    OrdermDetailDao ordermDetailDao;
    @Qualifier("ordermDaoImpl")
    @Autowired
    OrdermDao ordermDao;

    @Test
    public void commonOrdersDetail() throws Exception {
        OrdermDetail detail, temp, temp1;

        Orderm orderm = new Orderm("1");
        ordermDao.create(orderm);


        detail = new OrdermDetail(new BigDecimal(1), new BigDecimal(1000));
        temp1 = ordermDetailDao.create(orderm, detail);
        detail = new OrdermDetail(new BigDecimal(2), new BigDecimal(2000));
        temp = ordermDetailDao.create(orderm, detail);
        detail = new OrdermDetail(new BigDecimal(3), new BigDecimal(3000));
        ordermDetailDao.create(orderm, detail);
        List<OrdermDetail> list1 = ordermDetailDao.getOrdermDetailForOrderm(orderm);
        ordermDetailDao.delete(orderm, temp);
        list1 = ordermDetailDao.getOrdermDetailForOrderm(orderm);
        detail = ordermDetailDao.get(temp1.getId());
        detail.setSumOfWork(new BigDecimal(777));
        ordermDetailDao.update(detail);

    }
}