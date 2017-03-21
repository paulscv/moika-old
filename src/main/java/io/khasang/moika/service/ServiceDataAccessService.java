package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaService;

import java.util.List;


public interface ServiceDataAccessService<T extends ABaseMoikaService> {
        T addService(T service) throws MoikaDaoException;
        void updateService(T service) throws MoikaDaoException;
        void deleteService(T service) throws MoikaDaoException;
        T getServiceByID(int id) throws MoikaDaoException;
        List<T> getAllServices() throws MoikaDaoException;
}
