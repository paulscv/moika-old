package io.khasang.moika.dao.impl;

/**
 * 
 */
import io.khasang.moika.dao.CarModelDao;
import io.khasang.moika.entity.CarModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional
public class CarModelDaoImpl extends MoikaDaoCrudImpl<CarModel> implements CarModelDao {
    private static final Logger logger = LoggerFactory.getLogger(CarModelDaoImpl.class);
    @Override
    public CarModel addCarModel(CarModel carModel) {
        return create(carModel);
    }

    @Override
    public CarModel updateCarModel(CarModel carModel) {
        return update(carModel);
    }

    @Override
    public List<CarModel> getAllCarModel() {
        return null;
                //getAll().getCurrentSession().createQuery("from CarModel").list();
    }

    @Override
    public CarModel getCarModelById(long id) {
        try{
            return dataAccessUtil.
                    getQueryOfEntityWithSoleEqualCondition(CarModel.class, "id", id)
                    .getSingleResult();
        }catch(NoResultException e){
            logger.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteCarModelById(long id) {
        CarModel carModel = getCarModelById(id);
        delete(carModel);
    }


    @Override
    public boolean containCarModelById(long id) {
        return false;
    }

    
    
    /*   private final SessionFactory sessionFactory;

    @Autowired
    public CarModelDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addmodel(CarModel model) {
        sessionFactory.getCurrentSession().save(model);
    }

    @Override
    public void updatemodel(CarModel model) {
        sessionFactory.getCurrentSession().update(model);
    }

    @Override
    public List<CarModel> getAllCarModel() {
        return sessionFactory.getCurrentSession().createQuery("from CarModel").list();
    }

    @Override
    public CarModel getmodelById(long id) {
        return sessionFactory.getCurrentSession().get(CarModel.class,id);
    }

    @Override
    public void deletemodelById(CarModel model) {
        sessionFactory.getCurrentSession().delete(model);
    }

    @Override
    public boolean containmodelById(long id) {
        Long countClients = sessionFactory.getCurrentSession().
                createQuery("select COUNT(c.id) from CarModel c where c.id=:id", Long.class).
                setParameter("id", id).
                getSingleResult();
        return !countClients.equals(0);
    }*/
}

