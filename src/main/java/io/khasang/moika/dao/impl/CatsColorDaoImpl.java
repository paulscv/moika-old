package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CatsColorDao;
import io.khasang.moika.entity.CatsColor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("catsColorDao")
public class CatsColorDaoImpl extends MoikaDaoCrudImpl<CatsColor> implements CatsColorDao {

    public CatsColorDaoImpl() {
    }


    public CatsColorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
