package io.khasang.moika.dao;

import io.khasang.moika.entity.City;
import io.khasang.moika.entity.Coordinate;
import io.khasang.moika.entity.WashAddr;
import io.khasang.moika.entity.WashFacility;

import java.util.List;

/**
 * Интерфейс DAO для автомоек (состоящих из боксов)
 * @author Skvortsov Pavel
 *
 */
public interface WashFacilityDao extends  IMoikaDaoCrud<WashFacility>{

    List<WashFacility> getWashFacilitiesOnNet(int idNet) throws MoikaDaoException;
    List<WashFacility> getWashFacilitiesInCity(City city) throws MoikaDaoException;
    WashFacility getWashFacilityByAddress(WashAddr washAddr) throws MoikaDaoException;
    WashFacility getWashFacilityByCoords(Coordinate coordinate) throws MoikaDaoException;
}
