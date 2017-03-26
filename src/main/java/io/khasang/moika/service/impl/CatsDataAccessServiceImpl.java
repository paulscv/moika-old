package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CatsColorDao;
import io.khasang.moika.dao.CatsDAO;
import io.khasang.moika.entity.Cats;
import io.khasang.moika.entity.CatsColor;
import io.khasang.moika.service.CatsDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(value = "catsDataAccessService")
@Transactional
public class CatsDataAccessServiceImpl implements CatsDataAccessService {
    @Autowired
    private CatsDAO catsDao;
    @Autowired
    private CatsColorDao catsColorDao;

    @Override
    public Cats addCat(Cats cat) {
        if (cat.getCatsColor() == null){
            cat.setCatsColor(new CatsColor("Серый"));
        }
        return catsDao.create(cat);
    }

    @Override
    public Cats getCatById(int id) {
        return catsDao.get(id);
    }

    @Override
    public void updateCat(Cats cat) {
        catsDao.update(cat);
    }

    @Override
    public void deleteCat(Cats cat) {
        catsDao.delete(cat);
    }

    @Override
    public boolean containCatById(Cats cat) {
        return catsDao.containCat(cat);
    }

    @Override
    public List<Cats> getAllCats() {
        return catsDao.getAll();
    }

    @Override
    public List<Cats> getCatsByColor(CatsColor color) {
        return catsDao.getCatsByColor(color);
    }

    @Override
    public List<Cats> getCatsByAge(int ageFrom, int ageTo) {
        return catsDao.getCatsByAge(ageFrom, ageTo);
    }

    @Override
    public List<Cats> getCatsByName(String name) {
        return catsDao.getCatsByName(name);
    }

    @Override
    public Map<Integer, CatsColor> getCatsColorList(){
        List<CatsColor> colorList= catsColorDao.getAll();
        Map<Integer, CatsColor>  colorMap = colorList.stream().collect(
                Collectors.toMap(x -> x.getId(), x -> x));
        return colorMap;
    }
}
