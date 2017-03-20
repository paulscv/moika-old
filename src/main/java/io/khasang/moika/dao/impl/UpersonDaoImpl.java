package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.UpersonDao;
import io.khasang.moika.entity.Uperson;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("upersonDao")
public class UpersonDaoImpl extends MoikaDaoCrudImpl<Uperson> implements UpersonDao {



}
