package io.khasang.moika.dao;

import io.khasang.moika.entity.Orderm;
import io.khasang.moika.entity.OrdermDetail;

import java.util.List;

public interface OrdermDetailDao  extends IMoikaDaoCrud<OrdermDetail> {
    OrdermDetail create(Orderm orderm, OrdermDetail detail);
    OrdermDetail delete (Orderm orderm, OrdermDetail detail);
    List<OrdermDetail> getOrdermDetailForOrderm(Orderm idOrder);
//    OrdermDetail fillOrdermDetail(OrdermDetail ordermDetail, Work work, BigDecimal quantity);
}
