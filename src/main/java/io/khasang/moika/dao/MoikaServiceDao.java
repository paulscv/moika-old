package io.khasang.moika.dao;

import io.khasang.moika.entity.MoikaService;

import java.util.List;

public interface MoikaServiceDao extends IMoikaDaoCrud<MoikaService>{
    List<MoikaService> getServicesByType(int idType) throws MoikaDaoException;
    List<MoikaService> getServicesByType(String typeCode) throws MoikaDaoException;
    List<MoikaService> getServicesByStatus(String statusCode) throws MoikaDaoException;
    List<MoikaService> getServicesByStatus(int idStatus) throws MoikaDaoException;
    List<MoikaService> getActualServices() throws MoikaDaoException;
}
