package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaConcreatServiceDao;
import io.khasang.moika.dao.CleanServiceDao;
import io.khasang.moika.entity.CleanService;
import io.khasang.moika.service.CleanServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "cleanServiceDataAccessService")
@Transactional
public class CleanServiceDataAccessServiceImpl extends AMoikaConcreatServiceDataAccessServiceImpl<CleanService> implements CleanServiceDataAccessService {
    @Autowired
    CleanServiceDao cleanServiceDao;

    public CleanServiceDataAccessServiceImpl() {
    }

    @Autowired
    public CleanServiceDataAccessServiceImpl(@Qualifier("cleanServiceDao") BaseMoikaConcreatServiceDao<CleanService> baseMoikaConcreatServiceDao) {
        super(baseMoikaConcreatServiceDao);
        this.cleanServiceDao = (CleanServiceDao) baseMoikaConcreatServiceDao;
    }
 
}
