package io.khasang.Car;

import io.khasang.moika.entity.CarMake;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 
 */
public class CarMakeIntegrationTest {

    @Ignore
    @Test
    public void createCarMake() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        CarMake CarMake = new CarMake();
        CarMake.setName("Audi");

        HttpEntity<CarMake> httpEntity = new HttpEntity<>(CarMake, headers);
        RestTemplate restTemplate = new RestTemplate();
        CarMake result = restTemplate.exchange
                ("http://localhost:8080/CarMake/add/",
                        HttpMethod.POST,
                        httpEntity,
                        CarMake.class)
                .getBody();

        Assert.assertNotNull(result);
        Assert.assertEquals("Audi", result.getName());
        Assert.assertNotNull(result.getId());

        ResponseEntity<CarMake> responseEntity = restTemplate.exchange(
                "http://localhost:8080/CarMake/all/{id}",
                HttpMethod.GET,
                null,
                CarMake.class,
                result.getId()
        );
        CarMake resultCarMake = responseEntity.getBody();
        Assert.assertNotNull(resultCarMake);
        Assert.assertEquals(resultCarMake.getName(), result.getName());

        ResponseEntity<List<CarMake>> resultAll = restTemplate.exchange(
                "http://localhost:8080/CarMake/getAll/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarMake>>() {
                }
        );
        assertEquals(HttpStatus.OK, resultAll.getStatusCode());

        Assert.assertNotNull(resultAll.getBody());

    }
    @Ignore
    @Test
    public void updateCarMake() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CarMake> responseEntity = restTemplate.exchange(
                "http://localhost:8080/CarMake/{id}",
                HttpMethod.GET,
                null,
                CarMake.class,
                37
        );
        CarMake resultCarMake = responseEntity.getBody();
        Assert.assertNotNull(resultCarMake);

        HttpEntity<CarMake> httpEntity = new HttpEntity<>(resultCarMake, headers);
        resultCarMake.setName("Audi");
        CarMake resultUpdCarMake = restTemplate.exchange
                ("http://localhost:8080/CarMake/update",
                        HttpMethod.PUT,
                        httpEntity,
                        CarMake.class)
                .getBody();

        Assert.assertNotNull(resultUpdCarMake);
        Assert.assertEquals("Audi", resultUpdCarMake.getName());
        Assert.assertNotNull(resultUpdCarMake.getId());
    }

    @Ignore
    @Test
    public void deleteCarMake() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8080/CarMake/{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                38
        );
        String resultCarMake = responseEntity.getBody();
        Assert.assertNotNull(resultCarMake);
    }

}
