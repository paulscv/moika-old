package io.khasang.moika.service.impl;

import io.khasang.moika.dao.BoxTypeDao;
import io.khasang.moika.service.BoxTypesDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "boxTypesDataAccessService")
@Transactional
public class BoxTypesDataAccessServiceImpl extends  ATypeDataAccessServiceImpl implements BoxTypesDataAccessService {
    final
    BoxTypeDao boxTypeDao;

    @Autowired()
    public BoxTypesDataAccessServiceImpl(BoxTypeDao boxTypeDao) {
        this.boxTypeDao = boxTypeDao;
        setTypeDao(this.boxTypeDao);
    }

}
