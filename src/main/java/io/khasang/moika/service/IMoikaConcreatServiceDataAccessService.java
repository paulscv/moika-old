package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaServiceAdditionalInfo;

import java.util.List;

/**
 * Базовый интерфейс для всех моечных сервисов
 * @param <T>
 */
public interface IMoikaConcreatServiceDataAccessService<T extends ABaseMoikaServiceAdditionalInfo>{
    T addConcreatService(T entity) throws MoikaDaoException;    //Create
    T getConcreatServiceById(int id)  throws MoikaDaoException;     //Read
    void updateConcreatService(T entity)  throws MoikaDaoException;//Update
    void deleteConcreatService(T entity)  throws MoikaDaoException; //Delete
    List<T> getAllConcreatServices()  throws MoikaDaoException;
}
