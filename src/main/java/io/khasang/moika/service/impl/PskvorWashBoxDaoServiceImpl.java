package io.khasang.moika.service.impl;


import io.khasang.moika.dao.BoxStatusDao;
import io.khasang.moika.dao.BoxTypeDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashBoxDao;
import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.service.PskvorWashBoxDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("PskvorWashBoxDaoServiceImpl")
@Transactional
public class PskvorWashBoxDaoServiceImpl implements PskvorWashBoxDaoService {
    @Autowired
    private WashBoxDao washBoxDao;
    @Autowired
    private BoxStatusDao boxStatusDao;
    @Autowired
    private BoxTypeDao boxTypeDao;

    public PskvorWashBoxDaoServiceImpl() {
    }

    @Override
    public void addWashBox(WashBox washBox) {
        try {
            washBoxDao.create(washBox);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWashBox(WashBox washBox) {
        try {
            washBoxDao.update(washBox);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWashBox(WashBox washBox) {
        try {
            washBoxDao.delete(washBox);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WashBox getWashBoxByID(int id) {
        try {
            return washBoxDao.get(id);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public WashBox getWashBox(int idFclt, String name) {
        try {
            return washBoxDao.getWashBox(idFclt, name);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesByType(int boxType) {
        try {
            return washBoxDao.getWashBoxesByType(boxType);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesByStatus(int boxStatus) {
        try {
            return washBoxDao.getWashBoxesByStatus(boxStatus);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getAllWashBoxes() {
        try {
            return washBoxDao.getAll();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFclt) {
        try {
            return washBoxDao.getWashBoxesOnFacility(idFclt);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<BoxStatus> getWashBoxesStatuses() {
        try {
            return boxStatusDao.getAll();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public BoxStatus getWashBoxesStatusByCode(String code) {
        try {
            return boxStatusDao.getEntityByCode(code);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BoxType> getWashBoxesTypes() {
        try {
            return boxTypeDao.getAll();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public BoxType getWashBoxesTypeByCode(String code) {
        try {
            return boxTypeDao.getEntityByCode(code);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }
}