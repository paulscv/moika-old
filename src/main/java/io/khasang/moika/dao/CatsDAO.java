package io.khasang.moika.dao;

import io.khasang.moika.entity.Cats;
import io.khasang.moika.entity.CatsColor;

import java.util.List;

public interface CatsDAO extends IMoikaDaoCrud<Cats>{
    boolean containCat(Cats cat);
    List<Cats> getCatsByName(String name);
    List<Cats> getCatsByColor(CatsColor color);
    List<Cats> getCatsByAge(int ageFrom, int ageTo);
}
