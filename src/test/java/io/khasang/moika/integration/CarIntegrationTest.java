package io.khasang.moika.integration;

import io.khasang.moika.entity.Car;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class CarIntegrationTest {

    @Ignore
    @Test
    public void updateCar() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Car> responseEntity = restTemplate.exchange(
                "http://localhost:8080/car/{id}",
                HttpMethod.GET,
                null,
                Car.class,
                37
        );
        Car resultCar = responseEntity.getBody();
        Assert.assertNotNull(resultCar);

        HttpEntity<Car> httpEntity = new HttpEntity<>(resultCar, headers);
//        resultCar.setCarType("Красный таз");
        Car resultUpdCar = restTemplate.exchange
                ("http://localhost:8080/car/update",
                        HttpMethod.PUT,
                        httpEntity,
                        Car.class)
                .getBody();

        Assert.assertNotNull(resultUpdCar);
//        Assert.assertEquals("Красный таз", resultUpdCar.getCarType());
        Assert.assertNotNull(resultUpdCar.getId());
    }

    @Ignore
    @Test
    public void deleteCar() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8080/car/delete/{id}",
                HttpMethod.DELETE,
                null,
                String.class,
                38
        );
        String resultCar = responseEntity.getBody();
        Assert.assertNotNull(resultCar);
    }

}
