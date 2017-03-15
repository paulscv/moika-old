package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.OrdermDetailDao;
import io.khasang.moika.entity.Orderm;
import io.khasang.moika.entity.OrdermDetail;
import io.khasang.moika.entity.Work;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
@Transactional
public class OrdermDetailDaoImpl implements OrdermDetailDao {
    private SessionFactory sessionFactory;

    public OrdermDetailDaoImpl() {
    }

    @Autowired
    public OrdermDetailDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public OrdermDetail addOrdermDetail(OrdermDetail ordermDetail, long idOrderm) {
        Session session =sessionFactory.getCurrentSession();
        session.save(ordermDetail);

        Orderm orderm =(Orderm) session.get(Orderm.class, idOrderm);
        orderm.getOrdersDetails().add(ordermDetail);
        session.save(orderm);
        return ordermDetail;
    }

    @Override
    public OrdermDetail updateOrdermDetail(OrdermDetail ordermDetail) {
        sessionFactory.getCurrentSession().update(ordermDetail);
        return ordermDetail;
    }

    @Override
    public void deleteOrdermDetailk(OrdermDetail ordermDetail) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(ordermDetail);
        session.flush();
    }

    @Override
    public OrdermDetail getOrdermDetail(long id) {
        return sessionFactory.getCurrentSession().byId(OrdermDetail.class).load(id);
    }

    @Override
    public OrdermDetail fillOrdermDetail(OrdermDetail ordermDetail, Work work, BigDecimal quantity) {
// TODO: 09.03.2017 этот метод надо править, а отсюда скорее всего выбросить
        ordermDetail.setQuantity(quantity);
        ordermDetail.setSumOfWork(work.getPrice().multiply(quantity));
//        ordermDetail.setWork(work);
//        work.getOrdersDetails().add(ordermDetail);
        return ordermDetail;
    }

    @Override
    // TODO: 09.03.2017 править
    public List<OrdermDetail> getOrdermDetailForOrder(long idOrder) {
//        Query query  = sessionFactory.getCurrentSession().createQuery("from OrdermDetail where order = ?");
//        query.setParameter(0, idOrder);
//        return query.list();
        return null;
    }

    @Override
    public List<OrdermDetail> getAllOrdermDetail() {
        return  sessionFactory.getCurrentSession().createQuery("from OrdermDetail ").list();
    }
}
