package io.khasang.moika.service;

import io.khasang.moika.entity.Work;

import java.util.List;

public interface WorkAccessService {
    Work addWork(Work work);
    Work updateWork(Work work);
    Work deleteWork(Work work);
    Work getWork(long id);
    Work getWork(String name);
    List<Work> getAllWork();
}
