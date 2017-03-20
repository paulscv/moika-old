package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.BaseMoikaConcreatServiceDao;
import io.khasang.moika.entity.IBaseMoikaServiceAddInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("MoikaServiceAddInfoDaoFabrica")
public class MoikaServiceAddInfoDaoFabrica {
    @Autowired
    protected SessionFactory sessionFactory;

    private List<IBaseMoikaServiceAddInfo> serviceAddInfo;

    MoikaServiceAddInfoDaoFabrica(){
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<IBaseMoikaServiceAddInfo> getListOfServiceAddInfo(int idService, String codeTypeService){
       return getMoikaConcreatServiceDao(codeTypeService).getConcreatServiceById(idService);
    }

    public BaseMoikaConcreatServiceDao getMoikaConcreatServiceDao( String codeTypeService){
        BaseMoikaConcreatServiceDao concreatServiceDao = null;
        switch ( codeTypeService ) {
            case "WASH":
                concreatServiceDao = new WashServiceDaoImpl(sessionFactory);
                break;
            case "CLEAN":
                concreatServiceDao = new CleanServiceDaoImpl(sessionFactory);
                break;
            case "CHEM_CLEAN":
                concreatServiceDao = new ChemCleanServiceDaoImpl(sessionFactory);
                break;
            case "POLISH":
                concreatServiceDao = new PolishServiceDaoImpl(sessionFactory);
                break;
            case "COMPLEX":
                // concreatServiceDao = new ComplexServiceDaoImpl();
                break;
            case "OTHER":
                concreatServiceDao = new OtherServiceDaoImpl(sessionFactory);
                break;
        }
        return concreatServiceDao;
    }

}
