package io.khasang.moika.service.impl;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.OrdermDetailDao;
import io.khasang.moika.entity.Orderm;
import io.khasang.moika.entity.OrdermDetail;
import io.khasang.moika.service.OrdermDetailAccessService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("OrdermDetailAccessServiceImpl")
@Transactional
public class OrdermDetailAccessServiceImpl implements OrdermDetailAccessService {
    @Qualifier("ordermDetailDaoImpl")
    @Autowired
    OrdermDetailDao ordermDetailDao;

    public OrdermDetailAccessServiceImpl() {
    }

    @Override
    public OrdermDetail create(Orderm orderm, OrdermDetail detail) {
        return ordermDetailDao.create(orderm, detail);
    }

    @Override
    public OrdermDetail delete(Orderm orderm, OrdermDetail detail) {
        return ordermDetailDao.delete(orderm, detail);
    }

    @Override
    public List<OrdermDetail> getOrdermDetailForOrderm(Orderm idOrder) {
        return ordermDetailDao.getOrdermDetailForOrderm(idOrder);
    }

    @Override
    public OrdermDetail create(OrdermDetail entity) throws MoikaDaoException {
        return ordermDetailDao.create(entity);
    }

    @Override
    public OrdermDetail get(long id) throws MoikaDaoException {
        return ordermDetailDao.get(id);
    }

    @Override
    public OrdermDetail get(int id) throws MoikaDaoException {
        return ordermDetailDao.get(id);
    }

    @Override
    public OrdermDetail update(OrdermDetail entity) throws MoikaDaoException {
        return ordermDetailDao.update(entity);
    }

    @Override
    public OrdermDetail update(long id, Map<String, Object> fieldValueMap) throws MoikaDaoException {
        return ordermDetailDao.update(id, fieldValueMap);
    }

    @Override
    public OrdermDetail delete(OrdermDetail entity) throws MoikaDaoException {
        return ordermDetailDao.delete(entity);
    }

    @Override
    public List<OrdermDetail> getAll() throws MoikaDaoException {
        return ordermDetailDao.getAll();
    }

    @Override
    public Session getCurrentSession() {
        return ordermDetailDao.getCurrentSession();
    }
}
