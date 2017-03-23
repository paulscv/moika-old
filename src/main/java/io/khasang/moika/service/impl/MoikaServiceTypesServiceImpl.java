package io.khasang.moika.service.impl;

import io.khasang.moika.dao.ServiceTypeDao;
import io.khasang.moika.service.MoikaServiceTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "moikaServiceTypesServiceImpl")
@Transactional
public class MoikaServiceTypesServiceImpl extends  ATypeDataAccessServiceImpl implements MoikaServiceTypesService {
    final
    ServiceTypeDao serviceTypeDao;

    @Autowired()
    public MoikaServiceTypesServiceImpl(ServiceTypeDao serviceTypeDao) {
        this.serviceTypeDao = serviceTypeDao;
        setTypeDao( this.serviceTypeDao);
    }


}
