package io.khasang.moika.service;

import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;

import java.util.List;

public interface PskvorWashBoxDaoService {
    void addWashBox(WashBox washBox);

    void updateWashBox(WashBox washBox);

    void deleteWashBox(WashBox washBox);

    WashBox getWashBoxByID(int id);

    WashBox getWashBox(int idFclt, String name);

    List<WashBox> getAllWashBoxes();

    List<WashBox> getWashBoxesOnFacility(int idFclt);

    List<WashBox> getWashBoxesByType(int boxType);

    List<WashBox> getWashBoxesByStatus(int boxStatus);

    List<BoxStatus> getWashBoxesStatuses();

    BoxStatus getWashBoxesStatusByCode(String code);

    List<BoxType> getWashBoxesTypes();

    BoxType getWashBoxesTypeByCode(String code);

}
