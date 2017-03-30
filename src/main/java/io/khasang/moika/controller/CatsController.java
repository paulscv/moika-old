package io.khasang.moika.controller;

import io.khasang.moika.entity.Cats;
import io.khasang.moika.entity.CatsColor;
import io.khasang.moika.service.CatsDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cats")
public class CatsController {
    @Autowired
    private CatsDataAccessService catsService;

    @RequestMapping("/list/view")
    public String getListCatsAndView(Model model) {
        model.addAttribute("cats", catsService.getAllCats());
        return "cats-list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Cats> getCatList() {
        return catsService.getAllCats();
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
    public Map addCat(@RequestBody Cats cat, Model model) {
        if (cat.getCatsColor() == null) {
           if (cat.getIdColor() != 0 ){
              CatsColor catsColor =  catsService.getCatsColorList().get(cat.getIdColor());
              cat.setCatsColor(catsColor);
           }
        }
        if (cat.getAdditionalInfo() == null){
            cat.setAdditionalInfo(new Date());
        }
        Cats resCat = catsService.addCat(cat);

        //  model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        //   model.addAttribute("resCat", resCat);
        //  System.out.println(cat.toString());
        return Collections.singletonMap("success", true);
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
