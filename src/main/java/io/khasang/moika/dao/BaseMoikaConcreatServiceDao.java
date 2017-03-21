package io.khasang.moika.dao;

import io.khasang.moika.entity.ABaseMoikaServiceAdditionalInfo;

import java.util.List;

/**
 * Базовый интерфейс DAO для всех услуг
 * @author Skvortsov Pavel
 *
 */
public interface BaseMoikaConcreatServiceDao<T extends ABaseMoikaServiceAdditionalInfo> extends IMoikaDaoCrud<T> {
     List<T> getConcreatServiceById(int idService);
}
