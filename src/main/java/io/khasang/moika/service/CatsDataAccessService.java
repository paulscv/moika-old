package io.khasang.moika.service;

import io.khasang.moika.entity.Cats;
import io.khasang.moika.entity.CatsColor;

import java.util.List;
import java.util.Map;

public interface CatsDataAccessService {
    Cats addCat(Cats cat);
    Cats getCatById(int id);
    void updateCat(Cats cat);
    void deleteCat(Cats cat);
    boolean containCatById(Cats cat);
    List<Cats> getAllCats();
    List<Cats> getCatsByColor(CatsColor color);
    List<Cats> getCatsByAge(int ageFrom, int ageTo);
    List<Cats> getCatsByName(String name);
    Map<Integer, CatsColor> getCatsColorList();
}
