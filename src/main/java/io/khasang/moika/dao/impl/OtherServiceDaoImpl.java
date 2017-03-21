package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OtherServiceDao;
import io.khasang.moika.entity.OtherService;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("otherServiceDao")
public class OtherServiceDaoImpl extends BaseMoikaConcreatServiceDaoImpl<OtherService> implements OtherServiceDao{

    public OtherServiceDaoImpl() {
        super();
    }

    public OtherServiceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
