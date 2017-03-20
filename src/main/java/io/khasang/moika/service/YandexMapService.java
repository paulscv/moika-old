package io.khasang.moika.service;

import io.khasang.moika.entity.PlacemarkYandex;

import java.util.List;

public interface YandexMapService {
    PlacemarkYandex add(PlacemarkYandex placemarkYandex);
    List<PlacemarkYandex> getAllPlacemark();
}
