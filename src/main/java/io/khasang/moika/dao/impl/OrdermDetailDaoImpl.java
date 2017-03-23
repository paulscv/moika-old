package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OrdermDetailDao;
import io.khasang.moika.entity.Orderm;
import io.khasang.moika.entity.OrdermDetail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrdermDetailDaoImpl extends MoikaDaoCrudImpl<OrdermDetail> implements OrdermDetailDao {
    private SessionFactory sessionFactory;

    public OrdermDetailDaoImpl() {
    }

    @Autowired
    public OrdermDetailDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<OrdermDetail> getOrdermDetailForOrderm(Orderm idOrder) {
        return idOrder.getOrdersDetails();
//        Query query  = sessionFactory.getCurrentSession().createQuery(
//            "from ordermdetail where orderm = ?");
//        query.setParameter(0, idOrder);
//        return query.list();
    }
}
