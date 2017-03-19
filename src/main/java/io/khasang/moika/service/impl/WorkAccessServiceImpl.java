package io.khasang.moika.service.impl;

import io.khasang.moika.dao.WorkDao;
import io.khasang.moika.entity.Work;
import io.khasang.moika.service.WorkAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("WorkAccessServiceImpl")
@Transactional
public class WorkAccessServiceImpl implements WorkAccessService {
    @Autowired
    private WorkDao workDao;

    public WorkAccessServiceImpl() {
    }

    @Override
    public Work addWork(Work work) {
        return workDao.create(work);
    }

    @Override
    public Work updateWork(Work work) {
        return workDao.update(work);
    }

    @Override
    public Work deleteWork(Work work) {
        return workDao.delete(work);
    }

    @Override
    public Work getWork(long id) {
        return workDao.get(id);
    }

    @Override
    public Work getWork(String name) {
        return workDao.getWork(name);
    }

    @Override
    public List<Work> getAllWork() {
        return workDao.getAll();
    }
}
