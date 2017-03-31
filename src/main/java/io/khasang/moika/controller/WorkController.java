package io.khasang.moika.controller;

import io.khasang.moika.entity.Work;
import io.khasang.moika.service.WorkAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("work/")
public class WorkController {
    @Autowired
    WorkAccessService workAccessService;

    @RequestMapping(value = "list/", method = RequestMethod.GET)
    public String List(Model model) {
        List<Work> listWork = workAccessService.getAll();
        model.addAttribute("listWork", listWork);
        model.addAttribute("nrows", "Количество записей " + listWork.size());
        return "work-list";
    }
    @RequestMapping(value = "add/", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("work-edit-view-form", "work", new Work());
    }

    @RequestMapping(value = "add/", method = RequestMethod.POST)
    public String submit(@ModelAttribute("work") final Work work,
                         final BindingResult result, final ModelMap model) {
//        if (result.hasErrors()) {
//            return "error";
//        }
        workAccessService.create(work);
        return "redirect:/work/list/";
    }
}
