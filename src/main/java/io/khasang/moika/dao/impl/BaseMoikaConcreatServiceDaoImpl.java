package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.BaseMoikaConcreatServiceDao;
import io.khasang.moika.entity.ABaseMoikaServiceAdditionalInfo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Абстрактный класс для всех потомков моечных услуг BaseMoikaService
 *
 * @param <T>
 */

@Repository
@Transactional
public abstract class BaseMoikaConcreatServiceDaoImpl<T extends ABaseMoikaServiceAdditionalInfo> extends MoikaDaoCrudImpl<T> implements BaseMoikaConcreatServiceDao<T> {

    public List<T> getConcreatServiceById(int idService) {
        final Session session = sessionFactory.getCurrentSession();
        Class entityClass = getDaoType();
        Criteria criteria = session.createCriteria(entityClass);
        criteria.add(Restrictions.eq("id", idService));
        List<T> list = criteria.list();
        return list;
    }

    public BaseMoikaConcreatServiceDaoImpl() {
    }

    public BaseMoikaConcreatServiceDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}