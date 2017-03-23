package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashFacilityDao;
import io.khasang.moika.entity.City;
import io.khasang.moika.entity.Coordinate;
import io.khasang.moika.entity.WashAddr;
import io.khasang.moika.entity.WashFacility;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("washFacilityDao")
public class WashFacilityDaoImpl extends MoikaDaoCrudImpl<WashFacility> implements WashFacilityDao {

    @Override
    public List<WashFacility> getWashFacilitiesOnNet(int idNet) {
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_facilities where idNet = ?");
        query.setParameter(0, idNet);
        return query.list();
    }

    @Override
    public List<WashFacility> getWashFacilitiesInCity(City city) throws MoikaDaoException {
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("from wash_facilities f inner join WashAddr a on  f.idAddr = a.id " +
                " inner join cities c on a.city.id = c.id where c.name = ?");
        query.setParameter(0, city.getName());
        return query.list();
    }

    @Override
    public WashFacility getWashFacilityByAddress(WashAddr washAddr) throws MoikaDaoException {
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("from wash_facilities f where f.facilityAddr = ?");
        query.setParameter(0,washAddr);
        return (WashFacility)query.getSingleResult();
    }

    @Override
    public WashFacility getWashFacilityByCoords(Coordinate coordinate) throws MoikaDaoException{
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("from wash_facilities f where f.facilityAddr.coordinate = ?");
        query.setParameter(0,coordinate);
        return (WashFacility)query.getSingleResult();
    }
}
