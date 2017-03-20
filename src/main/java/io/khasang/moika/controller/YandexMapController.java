package io.khasang.moika.controller;

import io.khasang.moika.entity.PlacemarkYandex;
import io.khasang.moika.service.YandexMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping(value = "list/", method = RequestMethod.GET)
    public String List(Model model) {
        List<PlacemarkYandex> placemarkYandexList = new ArrayList<>();
        placemarkYandexList = yandexMapService.getAllPlacemark();
        model.addAttribute("placemarkYandexList", placemarkYandexList);
        model.addAttribute("nrows", "Количество мест на карте " + placemarkYandexList.size());
        return "placemark-yandex-list";
    }
    @RequestMapping(value = "add/", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("placemark-yandex-edit", "PlacemarkYandex", new PlacemarkYandex());
    }

    @RequestMapping(value = "add/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("PlacemarkYandex") final PlacemarkYandex placemarkYandex,
                         final BindingResult result, final ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        yandexMapService.add(placemarkYandex);

        return "redirect:/yandexmap/list/";
    }
    @RequestMapping(value = "ya2/", method = RequestMethod.GET)
    public String showMap(Model model) {
        return "yandex2";
    }
}
