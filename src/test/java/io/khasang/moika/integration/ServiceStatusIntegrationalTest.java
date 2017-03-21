package io.khasang.moika.integration;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ServiceStatus;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.equalTo;


public class ServiceStatusIntegrationalTest {

    @Test
    @Ignore
    public void testGetServiceStatusList(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/serviceStatuslist",
                String.class);
        Assert.assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
