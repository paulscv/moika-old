package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaConcreatServiceDao;
import io.khasang.moika.dao.ChemCleanServiceDao;
import io.khasang.moika.entity.ChemCleanService;
import io.khasang.moika.service.ChemCleanServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "chemCleanServiceDataAccessService")
@Transactional
public class ChemCleanServiceDataAccessServiceImpl extends AMoikaConcreatServiceDataAccessServiceImpl<ChemCleanService> implements ChemCleanServiceDataAccessService {
    @Autowired
    ChemCleanServiceDao chemCleanServiceDao;

    public ChemCleanServiceDataAccessServiceImpl() {
    }

    @Autowired
    public ChemCleanServiceDataAccessServiceImpl(@Qualifier("chemCleanServiceDao") BaseMoikaConcreatServiceDao<ChemCleanService> baseMoikaConcreatServiceDao) {
        super(baseMoikaConcreatServiceDao);
        this.chemCleanServiceDao = (ChemCleanServiceDao) baseMoikaConcreatServiceDao;
    }

}
