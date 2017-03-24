package io.khasang.moika.dao;

import io.khasang.moika.entity.Orderm;


public interface OrdermDao  extends IMoikaDaoCrud<Orderm>   {
    Orderm getOrderm(String number);
}
