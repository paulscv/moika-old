package io.khasang.moika.service;

import io.khasang.moika.entity.CarMake;

import java.util.List;

/**
 * Created by alyubarev on 15.03.2017.
 */
public interface CarMakeService {

    CarMake addCarMake(CarMake car);

    CarMake getCarMakeById(long id);

    CarMake getCarMakeByCarModel(String type);

    void deleteCar(long id);

    CarMake updateCar(CarMake carMake);

    List<CarMake> getCarMakeList();
}
