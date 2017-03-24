package io.khasang.moika.service.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.OrdermDao;
import io.khasang.moika.entity.Orderm;
import io.khasang.moika.service.OrdermAccessService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("OrdermAccessServiceImpl")
@Transactional
public class OrdermAccessServiceImpl implements OrdermAccessService {
    @Qualifier("ordermDaoImpl")
    @Autowired
    OrdermDao ordermDao;

    @Override
    public Orderm getOrderm(String number) {
        return ordermDao.getOrderm(number);
    }

    @Override
    public Orderm create(Orderm entity) throws MoikaDaoException {
        return ordermDao.create(entity);
    }

    @Override
    public Orderm get(long id) throws MoikaDaoException {
        return ordermDao.get(id);
    }

    @Override
    public Orderm get(int id) throws MoikaDaoException {
        return ordermDao.get(id);
    }


    @Override
    public Orderm update(Orderm entity) throws MoikaDaoException {
        return ordermDao.update(entity);
    }

    @Override
    public Orderm update(long id, Map<String, Object> fieldValueMap) throws MoikaDaoException {
        return null;
    }

    @Override
    public Orderm delete(Orderm entity) throws MoikaDaoException {
        return ordermDao.delete(entity);
    }

    @Override
    public List<Orderm> getAll() throws MoikaDaoException {
        return ordermDao.getAll();
    }

    @Override
    public Session getCurrentSession() {
        return null;
    }
}
