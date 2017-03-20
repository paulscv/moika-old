package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaStatusDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaStatusReference;
import io.khasang.moika.service.StatusDataAccessService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public abstract class AStatusDataAccessServiceImpl<T extends ABaseMoikaStatusReference> implements StatusDataAccessService {

    private BaseMoikaStatusDao statusDao;

    public AStatusDataAccessServiceImpl() {
    }

    public AStatusDataAccessServiceImpl(BaseMoikaStatusDao statusDao) {
        this.statusDao = statusDao;
    }

    public BaseMoikaStatusDao getStatusDao() {
        return statusDao;
    }

    public void setStatusDao(BaseMoikaStatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    public ABaseMoikaStatusReference addStatus(ABaseMoikaStatusReference entity) throws MoikaDaoException {
        return (ABaseMoikaStatusReference) statusDao.create(entity);
    }

    @Override
    public ABaseMoikaStatusReference updateStatus(ABaseMoikaStatusReference entity) throws MoikaDaoException {
        return (ABaseMoikaStatusReference) statusDao.update(entity);
    }

    @Override
    public boolean deleteStatus(ABaseMoikaStatusReference entity) throws MoikaDaoException {
        try {
            statusDao.delete(entity);
            return true;
        } catch (MoikaDaoException e) {
            return false;
        }
    }

    @Override
    public ABaseMoikaStatusReference getStatusByID(int id) throws MoikaDaoException {
        return (ABaseMoikaStatusReference) statusDao.get(id);
    }

    @Override
    public ABaseMoikaStatusReference getStatusByCode(String code) throws MoikaDaoException {
        return statusDao.getEntityByCode(code);
    }

    @Override
    public List<ABaseMoikaStatusReference> getAllStatuses() throws MoikaDaoException {
        return statusDao.getAll();
    }
}
