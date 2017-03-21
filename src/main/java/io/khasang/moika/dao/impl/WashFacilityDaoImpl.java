package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.WashFacilityDao;
import io.khasang.moika.entity.WashFacility;
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
/*
    @Override
    public List<WashBox> getWashBoxesOnFacility(WashFacility washFacility) {
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where idFacility = ?");
        query.setParameter(0, washFacility.getId());
        return query.list();
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFacility){
        Query query  = sessionFactory.getCurrentSession().createQuery("from wash_boxes where idFacility = ?");
        query.setParameter(0, idFacility);
        return query.list();
    }
    */
}
