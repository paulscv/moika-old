package io.khasang.moika.service.impl;


import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.MoikaServiceDao;
import io.khasang.moika.entity.MoikaService;
import io.khasang.moika.service.MoikaServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(value = "moikaServiceDataAccessServiceImpl")
@Transactional
public  class MoikaServiceDataAccessServiceImpl implements MoikaServiceDataAccessService {
    @Autowired
    MoikaServiceDao moikaServiceDao;


    public MoikaServiceDataAccessServiceImpl(MoikaServiceDao moikaServiceDao) {
        this.moikaServiceDao = moikaServiceDao;
    }

    public MoikaServiceDataAccessServiceImpl() {
    }

    @Override
    public MoikaService addService(MoikaService service) throws MoikaDaoException {
        return moikaServiceDao.create(service);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public MoikaService getServiceById(int id) throws MoikaDaoException {
        return moikaServiceDao.get(id);
    }

    @Override
    public void updateService(MoikaService service) throws MoikaDaoException {
        moikaServiceDao.update(service);
    }

    @Override
    public MoikaService deleteService(MoikaService service)  throws MoikaDaoException {
        moikaServiceDao.delete(service);
        return service;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<MoikaService> getAllServices() throws MoikaDaoException {
        return moikaServiceDao.getAll();
    }


    @Override
    public List<MoikaService> getAllervicesByStatus(int idStatus) throws MoikaDaoException {
            return moikaServiceDao.getServicesByStatus(idStatus);
    }

    @Override
    public List<MoikaService> getAllervicesByStatus(String status) throws MoikaDaoException {
        return moikaServiceDao.getServicesByStatus(status);
    }
    @Override
    public List<MoikaService> getServicesByType(int idType) throws MoikaDaoException {
        return moikaServiceDao.getServicesByType(idType);
    }

    @Override
    public List<MoikaService> getServicesByType(String code) throws MoikaDaoException {
        return moikaServiceDao.getServicesByType(code);
    }

    @Override
    public List<MoikaService> getActualServices() throws MoikaDaoException {
        return moikaServiceDao.getActualServices();
    }

}
