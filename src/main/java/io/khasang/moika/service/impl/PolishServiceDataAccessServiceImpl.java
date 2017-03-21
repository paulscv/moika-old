package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BaseMoikaConcreatServiceDao;
import io.khasang.moika.dao.PolishServiceDao;
import io.khasang.moika.entity.PolishService;
import io.khasang.moika.service.PolishServiceDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "polishServiceDataAccessService")
@Transactional
public class PolishServiceDataAccessServiceImpl extends AMoikaConcreatServiceDataAccessServiceImpl<PolishService> implements PolishServiceDataAccessService {
    @Autowired
    PolishServiceDao polishServiceDao;

    public PolishServiceDataAccessServiceImpl() {
    }

    @Autowired
    public PolishServiceDataAccessServiceImpl(@Qualifier("polishServiceDao") BaseMoikaConcreatServiceDao<PolishService> baseMoikaServiceDao) {
        super(baseMoikaServiceDao);
        this.polishServiceDao = (PolishServiceDao) baseMoikaServiceDao;
    }

}
