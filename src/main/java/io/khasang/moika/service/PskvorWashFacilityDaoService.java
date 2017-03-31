package io.khasang.moika.service;

import io.khasang.moika.entity.City;
import io.khasang.moika.entity.WashAddr;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;

import java.util.List;

public interface PskvorWashFacilityDaoService {
    WashFacility addWashFacility(WashFacility washFacility);
    WashFacility updateWashFacility(WashFacility washFacility);
    void deleteWashFacility(WashFacility washFacility);
    WashFacility getWashFacilityByID(int id);
    List<WashFacility> getAllWashFacilities();
    List<WashFacility> getWashFacilitiesOnNet(int idNet);
    List<WashBox> getWashBoxesOnFacility(int idFacility);
    List<WashBox> getWashBoxesOnFacility(WashFacility washFacility);
    List<WashFacility> getWashFacilitiesInCity(City city);
    WashFacility getWashFacilityByAddres(WashAddr washAddr);
}
