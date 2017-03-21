package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.WashAddrDao;
import io.khasang.moika.entity.WashAddr;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("washAddrDao")
public class WashAddrDaoImpl extends MoikaDaoCrudImpl<WashAddr> implements WashAddrDao {

    public WashAddrDaoImpl() {
    }


    public WashAddrDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
