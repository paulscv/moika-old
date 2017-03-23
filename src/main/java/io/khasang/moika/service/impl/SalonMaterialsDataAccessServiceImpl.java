package io.khasang.moika.service.impl;

import io.khasang.moika.dao.SalonMaterialDao;
import io.khasang.moika.service.SalonMaterialsDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "salonMaterialsDataAccessService")
@Transactional
public class SalonMaterialsDataAccessServiceImpl extends  ATypeDataAccessServiceImpl implements SalonMaterialsDataAccessService {
    final
    SalonMaterialDao materialsTypeDao;

    @Autowired()
    public SalonMaterialsDataAccessServiceImpl(SalonMaterialDao materialsTypeDao) {
        this.materialsTypeDao = materialsTypeDao;
        setTypeDao(this.materialsTypeDao );
    }

}
