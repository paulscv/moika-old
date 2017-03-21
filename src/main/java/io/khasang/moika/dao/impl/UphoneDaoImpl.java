package io.khasang.moika.dao.impl;


import io.khasang.moika.dao.UphoneDao;
import io.khasang.moika.entity.Uphone;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("uphoneDao")
public class UphoneDaoImpl extends MoikaDaoCrudImpl<Uphone> implements UphoneDao{



}
