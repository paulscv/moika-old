package io.khasang.moika.dao;

import io.khasang.moika.entity.CarMake;
import io.khasang.moika.entity.CarModel;

import java.util.List;

/**
 *
 */
public interface CarMakeDao extends IMoikaDaoCrud<CarMake>{


        CarMake getByCarModel(CarModel model);
        CarMake addCarMake(CarMake carMake);
        CarMake updateCarMake(CarMake carMake);
        List<CarMake> getAllCarMake();
        CarMake getCarMakeById(long id);
        void deleteCarMakeById(long id);
        boolean containCarMakeById(long id);


}
