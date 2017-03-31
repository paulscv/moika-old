package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CompanyDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.Butterfly;
import io.khasang.moika.entity.Company;
import io.khasang.moika.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CompanyServiceImpl")
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyDao companyDao;

    public CompanyServiceImpl() {
    }

    public void addCompany(Company company) {
        try {
            companyDao.create(company);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    public void addCompany(Company company, String name) {
        company.setName(name);
        try {
            companyDao.create(company);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    public Company getCompanyById(long id) {
        try {
            return companyDao.get(id);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCompany(Company company) {
        try {
            companyDao.update(company);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCompany(long id) {
        Company company = companyDao.get(id);
        if (company == null) {
            try {
                companyDao.delete(company);
            } catch (MoikaDaoException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Company> getCompanyGazpromList() {
        try {
            return companyDao.getAll();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addButterfly(Butterfly butterfly) {
        this.addCompany(butterfly);
    }
}
