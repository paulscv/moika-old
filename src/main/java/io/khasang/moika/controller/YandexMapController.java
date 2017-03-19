package io.khasang.moika.controller;

import io.khasang.moika.entity.PlacemarkYandex;
import io.khasang.moika.service.YandexMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("yandexmap/")
public class YandexMapController {
    @Autowired
    YandexMapService yandexMapService;


    @RequestMapping("show/")
    public String create(Model model) {
//        try {
           List<PlacemarkYandex> placemarkYandexList = new ArrayList<>();
           placemarkYandexList = yandexMapService.getAllPlacemark();
          model.addAttribute("placemarkYandexList", placemarkYandexList);
//        } catch (MoikaDaoException e) {
//            e.printStackTrace();
//        }
        return "yandexMap";
    }
    @RequestMapping(value = "admin/", method = RequestMethod.GET)
    public String List(Model model) {
        List<PlacemarkYandex> placemarkYandexList = new ArrayList<>();
        placemarkYandexList = yandexMapService.getAllPlacemark();
        model.addAttribute("placemarkYandexList", placemarkYandexList);
        model.addAttribute("nrows", "Количество мест на карте " + placemarkYandexList.size());
        return "placemark-yandex-list";
    }
}
