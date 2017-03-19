package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.YandexMapDAO;
import io.khasang.moika.entity.PlacemarkYandex;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("QueYandexMapDAO")
public class YandexMapDAOImpl extends MoikaDaoCrudImpl<PlacemarkYandex>  implements YandexMapDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public YandexMapDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
