package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.IMoikaDaoCrud;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaEntity;
import io.khasang.moika.util.DataAccessUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


@Transactional
@Repository
public abstract class MoikaDaoCrudImpl<T extends ABaseMoikaEntity> implements IMoikaDaoCrud<T> {
    @Autowired
    protected DataAccessUtil dataAccessUtil;
    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<? extends T> daoType;


    /**
     * By defining this class as abstract, we prevent Spring from creating
     * instance of this class If not defined as abstract,
     * getClass().getGenericSuperClass() would return Object. There would be
     * exception because Object class does not hav a constructor with parameters.
     */

    public MoikaDaoCrudImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    public MoikaDaoCrudImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Class<? extends T> getDaoType() {
        return daoType;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public DataAccessUtil getDataAccessUtil() {
        return dataAccessUtil;
    }

    @Autowired
    public void setDataAccessUtil(DataAccessUtil dataAccessUtil) {
        this.dataAccessUtil = dataAccessUtil;
    }

    @Override
    public T create(T entity) throws MoikaDaoException {
        getCurrentSession().save(entity);
     //   getCurrentSession().flush();
        return entity;
    }

    @Override
    public T update(T entity) throws MoikaDaoException {
        getCurrentSession().update(entity);
        return entity;
    }

    @Override
    public T update(long id, Map<String, Object> fieldValueMap) throws MoikaDaoException {
        T entity = get(id);
        dataAccessUtil.setNewValuesToBean(entity, fieldValueMap);
        return update(entity);
    }


    @Override
    public T delete(T entity) throws MoikaDaoException {
        getCurrentSession().delete(entity);
        //DRS session.flush();
        return entity;
    }

    @Override
    public T get(long id) throws MoikaDaoException {
        return getCurrentSession().get(daoType, id);
    }

    @Override
    public T get(int id) throws MoikaDaoException {
        return getCurrentSession().get(daoType, id);
    }

    @Override
    public List<T> getAll() throws MoikaDaoException {
        return dataAccessUtil.getQueryOfEntity((Class<T>) daoType).getResultList();
    }

}
