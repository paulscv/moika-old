package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.ServiceStatusDao;
import io.khasang.moika.entity.ServiceStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository("serviceStatusDao")
public class ServiceStatusDaoImpl extends AllStatusDaoImpl<ServiceStatus> implements ServiceStatusDao {

    public ServiceStatusDaoImpl() {
    }




}
