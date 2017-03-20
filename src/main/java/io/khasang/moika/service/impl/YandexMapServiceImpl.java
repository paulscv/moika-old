package io.khasang.moika.service.impl;

import io.khasang.moika.dao.YandexMapDAO;
import io.khasang.moika.entity.PlacemarkYandex;
import io.khasang.moika.service.YandexMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("YandexMapImpl")
@Transactional
public class YandexMapServiceImpl implements YandexMapService {
    @Autowired
    YandexMapDAO yandexMapDAO;

    public YandexMapServiceImpl() {
    }


    @Override
    public PlacemarkYandex add(PlacemarkYandex placemarkYandex) {
        return yandexMapDAO.create(placemarkYandex);
    }

    @Override
    public List<PlacemarkYandex> getAllPlacemark() {
        return yandexMapDAO.getAll();
    }
}
