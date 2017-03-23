package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaTypeReference;

import java.util.List;

/**
 * Базовый итерфейс ждя запроса ко всем сущностям, определяющим различные стравочники типов
 * @param <T> наследует от ABaseMoikaTypeReference из io.khasang.moika.entiеy
 *           наличие такого интерфеса позволяет определить абстрактный класс ATypeDataAccessServiceImpl,
 *           от которого наследуют все реализации различных справочников типов. Соответственно создание нового сервиса
 *           для доступа к какому-нибудь справочнику, это практичски копирование классы наследника с "пустым" телом.
 *           А также позволяет "стандартизовать все их вызовы
 * */

public interface TypesDataAccessService<T extends ABaseMoikaTypeReference> {
    /**
     * Добавить новый тип в справочник
     * @param entity
     * @return добавленный объект
     * @throws MoikaDaoException
     */
    T addType(T entity) throws MoikaDaoException;

    /**
     *  Обновить значение в сръправочнике
     * @param entity
     * @return
     * @throws MoikaDaoException
     */
    T updateType(T entity) throws MoikaDaoException;

    /**
     * Удалить значение из справочника. Могут действовать ограничения Foreign key
     * @param entity
     * @return
     * @throws MoikaDaoException
     */
    boolean deleteType(T entity) throws MoikaDaoException;

    /**
     * Получить значение из справоника по ID
     * @param id
     * @return
     * @throws MoikaDaoException
     */
    T getTypeByID(int id) throws MoikaDaoException;

    /**
     * Получить значение из справочника по Code
     * @param code
     * @return
     * @throws MoikaDaoException
     */
    T getTypeByCode(String code) throws MoikaDaoException;

    /**
     * Получить весь справочник
     * @return
     * @throws MoikaDaoException
     */
    List<T> getAllTypes() throws MoikaDaoException;
}
