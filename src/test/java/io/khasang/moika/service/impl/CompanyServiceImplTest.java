package io.khasang.moika.service.impl;

import io.khasang.moika.config.application.WebConfig;
import io.khasang.moika.dao.CompanyDao;
import io.khasang.moika.entity.Company;
import io.khasang.moika.service.CompanyService;
import io.khasang.moika.entity.Butterfly;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
public class CompanyServiceImplTest {
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyDao companyDao;


    @Test
    @Rollback
    @Transactional
    public void testAddCompany(){
        Company company = new Company();
        company.setName("aqua watchers");
        company.setAmount(BigDecimal.valueOf(2L));
        company.setDescription("We love aqua");
        companyService.addCompany(company);

        Company resultCompany = companyDao.getCompanyByName("aqua watchers");
        assertEquals(BigDecimal.valueOf(100L).setScale(0), resultCompany.getAmount());

        Company notA = new Company();
        notA.setName("stone watchers");
        notA.setAmount(BigDecimal.valueOf(2L));
        notA.setDescription("We love stone");
        companyService.addCompany(notA);
        Company resultNotACompany = companyDao.getCompanyByName("stone watchers");

        assertEquals(BigDecimal.valueOf(2L).setScale(0), resultNotACompany.getAmount());

        Company companyB = new Company();
        companyB.setName("butterfly");
        companyB.setAmount(BigDecimal.valueOf(10L));
        companyB.setDescription("We love butterfly");
        companyService.addCompany(companyB);

        Company resultCompanyB = companyDao.getCompanyByName("butterfly");
        assertEquals(BigDecimal.valueOf(50).setScale(0), resultCompanyB.getAmount());

        assertNotNull(new Butterfly());
        Butterfly butterfly = new Butterfly();
        butterfly.setName("butterfly");
        butterfly.setAmount(BigDecimal.valueOf(10L));
        butterfly.setDescription("We love butterfly");
        companyService.addButterfly(butterfly);
        Butterfly resultButterfly = companyDao.getButterflyByName("butterfly");
        assertEquals(BigDecimal.valueOf(50).setScale(0), resultButterfly.getAmount());
    }

    @Ignore
    @Test(timeout = 10L)
    public void testTime(){
    }
}
