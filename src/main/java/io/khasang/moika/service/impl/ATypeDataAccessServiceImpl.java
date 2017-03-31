package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaTypeReference;
import io.khasang.moika.service.TypesDataAccessService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public abstract class ATypeDataAccessServiceImpl<T extends ABaseMoikaTypeReference> implements TypesDataAccessService {

    private BaseMoikaTypeDao TypeDao;

    public BaseMoikaTypeDao getTypeDao() {
        return TypeDao;
    }

    public void setTypeDao(BaseMoikaTypeDao TypeDao) {
        this.TypeDao = TypeDao;
    }

    @Override
    public ABaseMoikaTypeReference addType(ABaseMoikaTypeReference entity) throws MoikaDaoException {
        return (ABaseMoikaTypeReference) TypeDao.create(entity);
    }

    @Override
    public ABaseMoikaTypeReference updateType(ABaseMoikaTypeReference entity) throws MoikaDaoException {
        return (ABaseMoikaTypeReference) TypeDao.update(entity);
    }

    @Override
    public boolean deleteType(ABaseMoikaTypeReference entity) throws MoikaDaoException {
        try {
            TypeDao.delete(entity);
            return true;
        } catch (MoikaDaoException e) {
            return false;
        }
    }
    
    @Override
    public ABaseMoikaTypeReference getTypeByID(int id) throws MoikaDaoException {
        return (ABaseMoikaTypeReference) TypeDao.get(id);
    }

    @Override
    public ABaseMoikaTypeReference getTypeByCode(String code) throws MoikaDaoException {
        return TypeDao.getEntityByCode(code);
    }

    @Override
    public List<ABaseMoikaTypeReference> getAllTypes() throws MoikaDaoException {
        return TypeDao.getAll();
    }
}
