package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CatsDAO;
import io.khasang.moika.entity.Cats;
import io.khasang.moika.entity.CatsColor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CatsDAOImpl extends MoikaDaoCrudImpl<Cats> implements CatsDAO {

    @Override
    public List<Cats> getCatsByColor(CatsColor color) {
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("from Cats where catsColor = ?");
        query.setParameter(0, color);
        return query.list();
    }

    @Override
    public List<Cats> getCatsByAge(int ageFrom, int ageTo) {
        Session session  = sessionFactory.getCurrentSession();
        Query query  = session.createQuery("from Cats where age between ? and ?");
        query.setParameter(0, ageFrom);
        query.setParameter(1, ageTo);
        return query.list();
    }

    @Override
    public boolean containCat(Cats cat) {
        return  sessionFactory.getCurrentSession().
                createQuery("select exists (select name from Cats c where c.id = :id)", boolean.class).
                setParameter("id", cat).
                getSingleResult();
    }

    @Override
    public List<Cats> getCatsByName(String name) {
        return  sessionFactory.getCurrentSession().
                createQuery("from Cats c where c.name like :name", Cats.class).
                setParameter("name", name).getResultList();
    }
}
