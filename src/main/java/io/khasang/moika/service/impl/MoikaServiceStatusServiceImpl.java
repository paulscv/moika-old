package io.khasang.moika.service.impl;

import io.khasang.moika.dao.ServiceStatusDao;
import io.khasang.moika.service.MoikaServiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "moikaServiceStatussServiceImpl")
@Transactional
public class MoikaServiceStatusServiceImpl extends  AStatusDataAccessServiceImpl implements MoikaServiceStatusService {
    @Autowired()
    ServiceStatusDao serviceStatusDao;


    public MoikaServiceStatusServiceImpl() {
    }


    public MoikaServiceStatusServiceImpl(ServiceStatusDao serviceStatusDao) {
        this.serviceStatusDao = serviceStatusDao;
        setStatusDao(this.serviceStatusDao);
    }

}
