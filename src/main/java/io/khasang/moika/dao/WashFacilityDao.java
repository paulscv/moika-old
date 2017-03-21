package io.khasang.moika.dao;

import io.khasang.moika.entity.WashFacility;

import java.util.List;

/**
 * Интерфейс DAO для автомоек (состоящих из боксов)
 * @author Skvortsov Pavel
 *
 */
public interface WashFacilityDao extends  IMoikaDaoCrud<WashFacility>{

    List<WashFacility> getWashFacilitiesOnNet(int idNet) throws MoikaDaoException;
  //  List<WashBox> getWashBoxesOnFacility(WashFacility washFacility) throws MoikaDaoException;
  //  List<WashBox> getWashBoxesOnFacility(int idFacility) throws MoikaDaoException;
}
