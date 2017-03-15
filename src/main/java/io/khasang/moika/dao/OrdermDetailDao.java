package io.khasang.moika.dao;

import io.khasang.moika.entity.OrdermDetail;
import io.khasang.moika.entity.Work;

import java.math.BigDecimal;
import java.util.List;

public interface OrdermDetailDao {
    OrdermDetail addOrdermDetail(OrdermDetail ordermDetail, long idOrderm);
    OrdermDetail updateOrdermDetail(OrdermDetail ordermDetail);
    void deleteOrdermDetailk(OrdermDetail ordermDetail);
    OrdermDetail getOrdermDetail(long id);
    OrdermDetail fillOrdermDetail(OrdermDetail ordermDetail, Work work, BigDecimal quantity);
    List<OrdermDetail> getOrdermDetailForOrder(long idOrder);
    List<OrdermDetail> getAllOrdermDetail();
}
