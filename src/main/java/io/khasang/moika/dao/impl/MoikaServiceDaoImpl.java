package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.BaseMoikaConcreatServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.MoikaServiceDao;
import io.khasang.moika.entity.ABaseMoikaServiceAdditionalInfo;
import io.khasang.moika.entity.IBaseMoikaServiceAddInfo;
import io.khasang.moika.entity.MoikaService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository("moikaServiceDao")
public class MoikaServiceDaoImpl extends MoikaDaoCrudImpl<MoikaService> implements MoikaServiceDao {

    @Autowired
    private MoikaServiceAddInfoDaoFabrica moikaServiceAddInfoDaoFabrica;

    public MoikaServiceDaoImpl() {
    }

    public MoikaServiceDaoImpl(SessionFactory sessionFactory ) {
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

    @Override
    public MoikaService delete(MoikaService entity) throws MoikaDaoException {
        List<? extends IBaseMoikaServiceAddInfo> addServiceInfoList = entity.getServiceAddInfo();
        for (IBaseMoikaServiceAddInfo child : addServiceInfoList) {
            BaseMoikaConcreatServiceDao concreatServiceDao = moikaServiceAddInfoDaoFabrica.
                    getMoikaConcreatServiceDao(entity.getTypeCode());
            concreatServiceDao.delete((ABaseMoikaServiceAdditionalInfo) child);
        }
        getCurrentSession().delete(entity);
        return entity;
    }


    @Override
    public MoikaService create(MoikaService entity) throws MoikaDaoException {
        List<? extends IBaseMoikaServiceAddInfo> addServiceInfoList = entity.getServiceAddInfo();
        getCurrentSession().save(entity);
        for (IBaseMoikaServiceAddInfo child : addServiceInfoList) {
            BaseMoikaConcreatServiceDao concreatServiceDao = moikaServiceAddInfoDaoFabrica.
                    getMoikaConcreatServiceDao(entity.getTypeCode());
            ((ABaseMoikaServiceAdditionalInfo)child).setIdService(entity.getId());
            concreatServiceDao.create((ABaseMoikaServiceAdditionalInfo) child);
        }
        //DRS session.flush();
        return entity;
    }

    @Override
    public MoikaService get(long id) throws MoikaDaoException {
        MoikaService entity = getCurrentSession().get(MoikaService.class, id);
        entity.setServiceAddInfo(moikaServiceAddInfoDaoFabrica.getListOfServiceAddInfo(entity.getId(), entity.getTypeCode()));
        return getCurrentSession().get(MoikaService.class, id);
    }

    @Override
    public List<MoikaService> getAll() throws MoikaDaoException {
        List<MoikaService> services = super.getAll();//dataAccessUtil.getQueryOfEntity(MoikaService.class).getResultList();
        for (MoikaService entity : services) {
            List<IBaseMoikaServiceAddInfo> list = moikaServiceAddInfoDaoFabrica.getListOfServiceAddInfo(entity.getId(), entity.getTypeCode());
            entity.setServiceAddInfo(list);
        }
        return services;
    }


    @Override
    public List<MoikaService> getServicesByStatus(int idStatus) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from services where idStatus="+ idStatus);
        List<MoikaService> services = query.getResultList();
        for (MoikaService entity : services) {
            entity.setServiceAddInfo(moikaServiceAddInfoDaoFabrica.getListOfServiceAddInfo(entity.getId(), entity.getTypeCode()));
        }
        return services;
    }

    @Override
    public List<MoikaService> getServicesByType(int idType) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from services where idType="+ idType);
        List<MoikaService> services = query.getResultList();
        for (MoikaService entity : services) {
            entity.setServiceAddInfo(moikaServiceAddInfoDaoFabrica.getListOfServiceAddInfo(entity.getId(), entity.getTypeCode()));
        }
        return services;
    }

    @Override
    public List<MoikaService> getActualServices() throws MoikaDaoException {
        return getServicesByStatus(0);
    }

    @Override
    public List<MoikaService> getServicesByStatus(String statusCode) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from services s join service_status t on s.idStatus = t.id where t.code="+ statusCode);
        List<MoikaService> services = query.getResultList();

        for (MoikaService entity : services) {
            entity.setServiceAddInfo(moikaServiceAddInfoDaoFabrica.getListOfServiceAddInfo(entity.getId(), entity.getTypeCode()));
        }
        return services;
    }

    @Override
    public List<MoikaService> getServicesByType(String typeCode) throws MoikaDaoException {
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from services s join service_types t on s.idType = t.id where t.code="+ typeCode);
        List<MoikaService> services = query.getResultList();
        for (MoikaService entity : services) {
            entity.setServiceAddInfo(moikaServiceAddInfoDaoFabrica.getListOfServiceAddInfo(entity.getId(), entity.getTypeCode()));
        }
        return services;
    }
}
