package io.khasang.moika.dao;

import io.khasang.moika.entity.Work;

public interface WorkDao extends IMoikaDaoCrud<Work> {
    Work getWork(String name);
}

