package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BoxStatusDao;
import io.khasang.moika.service.BoxStatusDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "boxStatusDataAccessService")
@Transactional
public class BoxStatusDataAccessServiceImpl extends  AStatusDataAccessServiceImpl implements BoxStatusDataAccessService {

    BoxStatusDao boxStatusDao;

    @Autowired()
    public BoxStatusDataAccessServiceImpl(BoxStatusDao boxStatusDao) {
        this.boxStatusDao = boxStatusDao;
        setStatusDao(this.boxStatusDao);
    }

}
