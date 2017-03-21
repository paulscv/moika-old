package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.ChemCleanServiceDao;
import io.khasang.moika.entity.ChemCleanService;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("chemCleanServiceDao")
public class ChemCleanServiceDaoImpl extends BaseMoikaConcreatServiceDaoImpl<ChemCleanService> implements ChemCleanServiceDao {

    public ChemCleanServiceDaoImpl() {
    }

    public ChemCleanServiceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
