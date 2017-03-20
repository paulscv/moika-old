package io.khasang.moika.dao;

import io.khasang.moika.entity.OrdermDetail;

import java.util.List;

public interface OrdermDetailDao  extends IMoikaDaoCrud<OrdermDetail> {
    List<OrdermDetail> getOrdermDetailForOrderm(long idOrder);
//    OrdermDetail fillOrdermDetail(OrdermDetail ordermDetail, Work work, BigDecimal quantity);
}
