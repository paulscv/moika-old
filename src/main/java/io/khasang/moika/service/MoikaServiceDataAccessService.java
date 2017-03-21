package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.MoikaService;

import java.util.List;

/**
 * Базовый интерфейс для всех моечных сервисов
 *
 */
public interface MoikaServiceDataAccessService {
    MoikaService addService(MoikaService service) throws MoikaDaoException;    //Create
    MoikaService getServiceById(int id)  throws MoikaDaoException;     //Read
    void updateService(MoikaService service)  throws MoikaDaoException;//Update
    MoikaService deleteService(MoikaService service)  throws MoikaDaoException; //Delete
    List<MoikaService> getAllServices()  throws MoikaDaoException;
    List<MoikaService> getAllervicesByStatus(int IdStatus) throws MoikaDaoException;
    List<MoikaService> getServicesByType(int IdType) throws MoikaDaoException;
    List<MoikaService> getActualServices() throws MoikaDaoException;
}
