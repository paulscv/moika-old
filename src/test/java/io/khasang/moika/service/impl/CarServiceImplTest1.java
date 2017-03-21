//package io.khasang.Car;
//
//import io.khasang.moika.config.application.WebConfig;
//import io.khasang.moika.dao.CarDAO;
//import io.khasang.moika.entity.Car;
//import io.khasang.moika.service.CarService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = {WebConfig.class})
//public class CarServiceImplTest {
//    @Autowired
//    CarService carService;
//
//    @Autowired
//    CarDAO carDAO;
//
//    @Test
//    @Rollback
//    @Transactional
//    public void testAddCar() {
//        Car car = new Car();
//        car.setCarType("Седан");
//        car.setCarModel("Ford Mustang");
//        car.setCarNumber("555");
//        carService.addCar(car);
//
//        Car resultCar = carDAO.getCarByNumber("555");
//        assertEquals("Седан", resultCar.getCarType());
//
//        Car car1 = new Car();
//        car1.setCarType("Хэчбек");
//        car1.setCarModel("Нива 4х4");
//        car1.setCarNumber("777");
//        carService.addCar(car1);
//
//        Car resultCar1 = carDAO.getCarByNumber("777");
//        assertEquals("Хэчбек", resultCar1.getCarType());
//
//    }
//}
