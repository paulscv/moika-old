package io.khasang.moika.service.impl;

import io.khasang.moika.dao.DirtTypeDao;
import io.khasang.moika.service.DirtTypesDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для получения справочника загрязнений
 * Т.к. наследует от абстрактного класса, в конструкто передает соответвующее DAO
 */

@Service(value = "dirtTypesDataAccessService")
@Transactional
public class DirtTypesDataAccessServiceImpl extends  ATypeDataAccessServiceImpl implements DirtTypesDataAccessService {
    final
    DirtTypeDao dirtTypeDao;

    @Autowired()
    public DirtTypesDataAccessServiceImpl(DirtTypeDao dirtTypeDao) {
        this.dirtTypeDao = dirtTypeDao;
        setTypeDao(this.dirtTypeDao);
    }

}
