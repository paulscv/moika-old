package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaTypeReference;

import java.util.List;

public interface TypesDataAccessService<T extends ABaseMoikaTypeReference> {
    T addType(T entity) throws MoikaDaoException;
    T updateType(T entity) throws MoikaDaoException;
    boolean deleteType(T entity) throws MoikaDaoException;
    T getTypeByID(int id) throws MoikaDaoException;
    T getTypeByCode(String code) throws MoikaDaoException;
    List<T> getAllTypes() throws MoikaDaoException;
}
