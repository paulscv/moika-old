package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CarMakeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.CarMake;
import io.khasang.moika.service.CarMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service("CarMakeServiceImpl")
@Transactional
public class CarMakeServiceImpl implements CarMakeService {

    private final CarMakeDao carMakeDao;

    @Autowired
    public CarMakeServiceImpl(CarMakeDao carMakeDao) {
        this.carMakeDao = carMakeDao;
    }

    @Override
    public CarMake addCarMake(CarMake carMake) {

            return carMakeDao.create(carMake);

    }

    @Override
    public CarMake getCarMakeById(long id) {
        try {
            return carMakeDao.getCarMakeById(id);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<CarMake> getCarMakeList() {
        try {
            return carMakeDao.getAllCarMake();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CarMake getCarMakeByCarModel(String type) {
        return null;
    }

    @Override
    public void deleteCar(long id) {

    }

    @Override
    public CarMake updateCar(CarMake carMake) {
        return null;
    }
}
