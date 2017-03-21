package io.khasang.moika.service;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ABaseMoikaStatusReference;

import java.util.List;

/**
 * Базовый итерфейс ждя запроса ко всем сущностям, определяющим различные стравочники статусов
 *
 * @param <T> наследует от ABaseMoikaStatusReference из io.khasang.moika.entiеy
 *            наличие такого интерфеса позволяет определить абстрактный класс AStatusDataAccessServiceImpl,
 *            от которого наследуют все реализации различных справочников статусов. Соответственно создание нового сервиса
 *            для доступа к какому-нибудь справочнику, это практичски копирование классы наследника с "пустым" телом.
 *            А также позволяет "стандартизовать все их вызовы
 */

public interface StatusDataAccessService<T extends ABaseMoikaStatusReference> {
    /**
     * Добавить новый статус в справочник
     * @param entity
     * @return
     * @throws MoikaDaoException
     */
    T addStatus(T entity) throws MoikaDaoException;

    /**
     * обновить статус в справочнике
     * @param entity
     * @return
     * @throws MoikaDaoException
     */
    T updateStatus(T entity) throws MoikaDaoException;

    /**
     * удалить статус из справочника
     * @param entity
     * @return
     * @throws MoikaDaoException
     */
    boolean deleteStatus(ABaseMoikaStatusReference entity) throws MoikaDaoException;

    /**
     * получить статуус по ID
     * @param id
     * @return
     * @throws MoikaDaoException
     */
    T getStatusByID(int id) throws MoikaDaoException;

    /**
     * получить статцус по его коду Code
     * @param code
     * @return
     * @throws MoikaDaoException
     */
    T getStatusByCode(String code) throws MoikaDaoException;

    /**
     * получить весь справочник статусов
     * @return
     * @throws MoikaDaoException
     */
    List<T> getAllStatuses() throws MoikaDaoException;
}
