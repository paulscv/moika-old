package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaConcreatServiceDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaServiceAdditionalInfo;
import io.khasang.moika.service.IMoikaConcreatServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public abstract class AMoikaConcreatServiceDataAccessServiceImpl<T extends ABaseMoikaServiceAdditionalInfo> implements IMoikaConcreatServiceDataAccessService<T> {
    @Autowired
    BaseMoikaConcreatServiceDao<T> baseMoikaConcreatServiceDao;


    public AMoikaConcreatServiceDataAccessServiceImpl(BaseMoikaConcreatServiceDao<T> baseMoikaConcreatServiceDao) {
        this.baseMoikaConcreatServiceDao = baseMoikaConcreatServiceDao;
    }

    public AMoikaConcreatServiceDataAccessServiceImpl() {
    }

    @Override
    public T addConcreatService(T service) throws MoikaDaoException {
        return baseMoikaConcreatServiceDao.create(service);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T getConcreatServiceById(int id) throws MoikaDaoException {
        return baseMoikaConcreatServiceDao.get(id);
    }

    @Override
    public void updateConcreatService(T service) throws MoikaDaoException {
        baseMoikaConcreatServiceDao.update(service);
    }

    @Override
    public void deleteConcreatService(T service) throws MoikaDaoException {
        baseMoikaConcreatServiceDao.delete(service);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> getAllConcreatServices() throws MoikaDaoException {
        return baseMoikaConcreatServiceDao.getAll();
    }

}
