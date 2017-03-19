package io.khasang.moika.service;

import io.khasang.moika.entity.PlacemarkYandex;

import java.util.List;

public interface YandexMapService {
    List<PlacemarkYandex> getAllPlacemark();
}
