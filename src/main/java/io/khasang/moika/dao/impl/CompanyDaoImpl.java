package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CompanyDao;
import io.khasang.moika.entity.Butterfly;
import io.khasang.moika.entity.Company;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("company")
public class CompanyDaoImpl extends MoikaDaoCrudImpl<Company> implements CompanyDao {

    @Override
    public Company getCompanyByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().
                createCriteria(Company.class);
        criteria.add(Restrictions.eq("name", name));
        return (Company) criteria.uniqueResult();
    }

    @Override
    public List<Company> getCompanyHqlList() {
        return (List<Company>) sessionFactory.getCurrentSession().createQuery("FROM Company").list();
    }

    @Override
    public Butterfly getButterflyByName(String butterfly) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Butterfly.class);
        criteria.add(Restrictions.eq("name", butterfly));
        return (Butterfly) criteria.uniqueResult();
    }
}
