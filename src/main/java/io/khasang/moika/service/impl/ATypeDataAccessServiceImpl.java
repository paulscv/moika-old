package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaTypeReference;
import io.khasang.moika.service.TypesDataAccessService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public abstract class ATypeDataAccessServiceImpl<T extends ABaseMoikaTypeReference> implements TypesDataAccessService {

    private BaseMoikaTypeDao typeDao;

    public BaseMoikaTypeDao getTypeDao() {
        return typeDao;
    }

    public void setTypeDao(BaseMoikaTypeDao TypeDao) {
        this.typeDao = TypeDao;
    }

    @Override
    public T addType(ABaseMoikaTypeReference entity) throws MoikaDaoException {
        return (T)typeDao.create(entity);
    }

    @Override
    public T updateType(ABaseMoikaTypeReference entity) throws MoikaDaoException {
        return (T) typeDao.update(entity);
    }

    @Override
    public boolean deleteType(ABaseMoikaTypeReference entity) throws MoikaDaoException {
        try {
            typeDao.delete(entity);
            return true;
        } catch (MoikaDaoException e) {
            return false;
        }
    }
    
    @Override
    public T getTypeByID(int id) throws MoikaDaoException {
        return (T) typeDao.get(id);
    }

    @Override
    public T getTypeByCode(String code) throws MoikaDaoException {
        return (T)typeDao.getEntityByCode(code);
    }

    @Override
    public List<T> getAllTypes() throws MoikaDaoException {
        List<T> resList = typeDao.getAll();
        return resList;
    }
}
