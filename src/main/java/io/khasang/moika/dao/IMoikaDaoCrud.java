package io.khasang.moika.dao;

import io.khasang.moika.entity.ABaseMoikaEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;

/**
 * БАЗОВЫЙ Интерфейс DAO для CRUD операций
 *
 * @author Skvortsov Pavel, Rostislav Dublin
 * @since March 2017
 */
public interface IMoikaDaoCrud<T extends ABaseMoikaEntity> {
    /**
     * Создаёт сущность в БД
     *
     * @param entity создаваемая сущность
     * @return созданная сущность
     */
    T create(T entity) throws MoikaDaoException;

    /**
     * Возвращает экземпляр сущности её Id
     *
     * @param id id
     * @return экземпляр сущности
     */
    T get(long id) throws MoikaDaoException;
    T get(int id) throws MoikaDaoException;

    /**
     * Обновляет имеющуюся сущность в БД
     *
     * @param entity сущность
     * @return обновлённая сущность
     */
    T update(T entity) throws MoikaDaoException;

    /**
     * Обновляет имеющуюся сущность в БД
     *
     * @param id            id обновялемой сущности
     * @param fieldValueMap - карта поле->новое значение
     * @return обновлённая сущность
     */
    T update(long id, Map<String, Object> fieldValueMap) throws MoikaDaoException;

    /**
     * Удаляет имеющуюся сущность из БД
     *
     * @param entity удаляемая сущность
     * @return удалённая сущность
     */
    T delete(T entity) throws MoikaDaoException;

    /**
     * @return полный список сущностей
     */
    List<T> getAll() throws MoikaDaoException;

    /**
     * @return текущая Hibernate-сессия
     */
    Session getCurrentSession();
}
