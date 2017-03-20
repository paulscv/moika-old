package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CityDao;
import io.khasang.moika.entity.City;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("cityDao")
public class CityDaoImpl extends MoikaDaoCrudImpl<City> implements CityDao {

    public CityDaoImpl() {
    }


    public CityDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
