package io.khasang.moika.controller;

import io.khasang.moika.entity.Cats;
import io.khasang.moika.entity.CatsColor;
import io.khasang.moika.service.CatsDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/cats")
public class CatsController {
    @Autowired
    private CatsDataAccessService catsService;

    @RequestMapping("/list")
    public String getListClients(Model model) {
        model.addAttribute("cats", catsService.getAllCats());
        return "cats-list";
    }

    @RequestMapping(value = "/catAdd", method = RequestMethod.GET)
    //   public ModelAndView showCatAddForm() {
    //      return new ModelAndView("catAdd", "cats", new Cats());
    //   }
    public String showCatAddForm(Model model) {
        Map<Integer, CatsColor> catColors = catsService.getCatsColorList();
        model.addAttribute("catsColorList", catColors);
        return "catAdd";
    }

    @PostMapping(value = "/catAdd", produces = "application/json;charset=UTF-8")
    // @ResponseBody
    public Object addCat(@RequestBody Cats cat, Model model) {
        if (cat.getCatsColor() == null) {
           if (cat.getIdColor() != 0 ){
              CatsColor catsColor =  catsService.getCatsColorList().get(cat.getIdColor());
              cat.setCatsColor(catsColor);
           }
        }
        Cats resCat = catsService.addCat(cat);
        return "redirect:/cats/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCatById(@PathVariable(value = "id") int inputId, Model model) {
        Cats cat = catsService.getCatById(inputId);
        if (cat != null) {
            model.addAttribute("cat", cat);
        } else {
            cat = new Cats();
            cat.setName("Неизвестный системе кот");
        }
        return "cat";
    }


}
