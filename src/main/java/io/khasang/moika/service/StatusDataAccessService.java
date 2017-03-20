package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaStatusReference;

import java.util.List;

public interface StatusDataAccessService<T extends ABaseMoikaStatusReference> {
    T addStatus(T entity) throws MoikaDaoException;
    T updateStatus(T entity) throws MoikaDaoException;
    boolean deleteStatus(ABaseMoikaStatusReference entity) throws MoikaDaoException;
    T getStatusByID(int id) throws MoikaDaoException;
    T getStatusByCode(String code) throws MoikaDaoException;
    List<T> getAllStatuses() throws MoikaDaoException;
}
