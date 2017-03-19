package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OrdermDao;
import io.khasang.moika.entity.Orderm;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrdermDaoImpl extends MoikaDaoCrudImpl<Orderm> implements OrdermDao {
    private SessionFactory sessionFactory;

    public OrdermDaoImpl() {
    }

    @Autowired
    public OrdermDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Orderm getOrderm(String number) {
        return  sessionFactory.getCurrentSession().bySimpleNaturalId(Orderm.class).load(number);
    }
}
