package io.khasang.moika.service;

import io.khasang.moika.entity.Butterfly;
import io.khasang.moika.entity.Company;

import java.util.List;

public interface CompanyService {
    void addCompany(Company company);
    void addCompany(Company company, String name);
    Company getCompanyById(long id);
    void updateCompany(Company company);
    void deleteCompany(long id);
    List<Company> getCompanyGazpromList();
    void addButterfly(Butterfly butterfly);
}