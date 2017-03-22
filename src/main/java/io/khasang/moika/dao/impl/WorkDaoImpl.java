package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WorkDao;
import io.khasang.moika.entity.Work;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class WorkDaoImpl  extends MoikaDaoCrudImpl<Work> implements WorkDao{
    private SessionFactory sessionFactory;

    public WorkDaoImpl() {
    }
    @Autowired
    public WorkDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Work getWork(String name) {
        return  sessionFactory.getCurrentSession().bySimpleNaturalId(Work.class).load(name);
    }
}
