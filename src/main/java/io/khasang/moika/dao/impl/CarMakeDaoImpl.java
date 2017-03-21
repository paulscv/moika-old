package io.khasang.moika.dao.impl;

/**
 *
 */
import io.khasang.moika.dao.CarMakeDao;
import io.khasang.moika.entity.CarMake;
import io.khasang.moika.entity.CarModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;


/**
 *
 */
@Repository("carMakeDao")
@Transactional
public class CarMakeDaoImpl extends MoikaDaoCrudImpl<CarMake> implements CarMakeDao {
    private static final Logger logger = LoggerFactory.getLogger(CarMakeDaoImpl.class);

    @Override
    public CarMake getByCarModel(CarModel model) {

        try{
            return dataAccessUtil.
                    getQueryOfEntityWithSoleEqualCondition(CarMake.class, "model", model)
                    .getSingleResult();
        }catch(NoResultException e) {
            logger.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public CarMake addCarMake(CarMake carMake) {

        return create(carMake);
    }

    @Override
    public CarMake updateCarMake(CarMake carMake) {

        return update(carMake);
    }

    @Override
    public List<CarMake> getAllCarMake() {
        return null;
    }

    @Override
    public CarMake getCarMakeById(long id) {
        return null;
    }

    @Override
    public void deleteCarMakeById(long id) {

    }

    @Override
    public boolean containCarMakeById(long id) {
        return false;
    }


}

