package io.khasang.moika.integration;

import io.khasang.moika.entity.Company;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

public class CompanyIntegrationalTest {

    @Ignore
    @Before
    public void initTests() {
        System.out.println("Tests are beginning...");
    }

    @Ignore
    @Test
    @Transactional
    @Rollback
    public void createCompany() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Company company = new Company(); // подготовили класс для тестирования
        company.setName("Рога и копыта");

        HttpEntity<Company> httpEntity = new HttpEntity<>(company, headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();
        Company resultCompany = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                "http://localhost:8080/company/add",
                HttpMethod.POST,
                httpEntity,
                Company.class).getBody();

        Assert.assertNotNull(resultCompany);
        Assert.assertEquals("Рога и копыта", resultCompany.getName());
    }

    @Ignore
    @Test
    public void getCompanyById() {
        HttpHeaders headers = new HttpHeaders(); //использовать именно из org.springframework.http.HttpHeaders
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        int id = 2;

        HttpEntity<Company> httpEntity = new HttpEntity<>(headers); //подготовили запрос
        RestTemplate restTemplate = new RestTemplate();
        Company resultCompany = restTemplate.exchange(    //отправли запрос через веб (т.е. снаружи приложения)
                "http://localhost:8080//company/{id}",
                HttpMethod.GET,
                httpEntity,
                Company.class, id).getBody();

        Assert.assertNotNull(resultCompany);
        Assert.assertEquals("Рога и копыта", resultCompany.getName());
    }
}
