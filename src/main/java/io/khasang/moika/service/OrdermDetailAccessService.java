package io.khasang.moika.service;

import io.khasang.moika.entity.Orderm;
import io.khasang.moika.entity.OrdermDetail;

import java.util.List;

public interface OrdermDetailAccessService {
    Orderm addOrdermDetail(OrdermDetail ordermDetail);
    Orderm updateOrdermDetail(OrdermDetail ordermDetail);
    void deleteOrdermDetail(OrdermDetail ordermDetail);
    Orderm getOrdermDetail(long id);
    List<OrdermDetail> getAllOrdermDetail();
}
