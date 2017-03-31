package io.khasang.moika.service.impl;

import io.khasang.moika.config.AppConfig;
import io.khasang.moika.config.HibernateConfig;
import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.entity.Orderm;
import io.khasang.moika.entity.OrdermDetail;
import io.khasang.moika.service.OrdermAccessService;
import io.khasang.moika.service.OrdermDetailAccessService;
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
public class OrdermDetailAccessServiceImplTest {
    @Autowired
    OrdermAccessService ordermAccessService;
    @Autowired
    OrdermDetailAccessService ordermDetailAccessService;

    @Test
    public void commonOrderm() throws Exception {
        Orderm orderm = new Orderm("1");
        ordermAccessService.create(orderm);
        OrdermDetail detail, temp, temp1;
        detail = new OrdermDetail(new BigDecimal(1), new BigDecimal(1000));
        temp1 = ordermDetailAccessService.create(orderm, detail);
        detail = new OrdermDetail(new BigDecimal(2), new BigDecimal(2000));
        temp = ordermDetailAccessService.create(orderm, detail);
        detail = new OrdermDetail(new BigDecimal(3), new BigDecimal(3000));
        ordermDetailAccessService.create(orderm, detail);
        List<OrdermDetail> list1 = ordermDetailAccessService.getOrdermDetailForOrderm(orderm);
        ordermDetailAccessService.delete(orderm, temp);
        list1 = ordermDetailAccessService.getOrdermDetailForOrderm(orderm);
        detail = ordermDetailAccessService.get(temp1.getId());
        detail.setSumOfWork(new BigDecimal(777));
        ordermDetailAccessService.update(detail);
    }

}