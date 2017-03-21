package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WashServiceDao;
import io.khasang.moika.entity.WashService;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("washServiceDao")
public class WashServiceDaoImpl extends BaseMoikaConcreatServiceDaoImpl<WashService> implements WashServiceDao {

    public WashServiceDaoImpl() {
    }


    public WashServiceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
