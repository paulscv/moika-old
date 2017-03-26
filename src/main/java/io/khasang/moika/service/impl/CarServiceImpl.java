package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import io.khasang.moika.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CarServiceImpl")
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;


    public CarServiceImpl() {
    }

    @Override
    public void addCar(Car car) {
        carDao.create(car);
    }

    @Override
    public Car getCarById(long id) {
        return carDao.get(id);
    }

    @Override
    public List getCarByType(String type) {
        return carDao.getByType(type);
    }

    @Override
    public List getCarByNumber(String number) {
        return carDao.getByNumber(number);
    }

    @Override
    public List getCarByModel(String model) {
        return carDao.getByModel(model);
    }

    @Override
    public List getCarList() {
        return carDao.getAll();
    }

    @Override
    public void deleteCar(long id) {
        Car car = carDao.get(id);
        if (car != null)  carDao.delete(car);
    }

    @Override
    public Car updateCar(Car car) {
        return carDao.update(car);
    }
}
