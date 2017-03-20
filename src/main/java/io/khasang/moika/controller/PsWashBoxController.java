package io.khasang.moika.controller;

import io.khasang.moika.entity.BoxStatus;
import io.khasang.moika.entity.BoxType;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.service.PskvorWashBoxDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PsWashBoxController {

    @Autowired
    PskvorWashBoxDaoService pskvorWashBoxDaoService;



    @RequestMapping(value = "/washBoxlist", method = RequestMethod.GET)
    public String getWashBoxList(Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        List<WashBox> washBoxList = pskvorWashBoxDaoService.getAllWashBoxes();
        model.addAttribute("boxlist", washBoxList);
        model.addAttribute("nrows", washBoxList.size() + " rows affected");
        return "ps-dao-carwashbox";
    }

    @RequestMapping(value = "/washBox/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    //@ResponseBody
    public Object addWashBox(@RequestBody WashBox washBox, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        pskvorWashBoxDaoService.addWashBox(washBox);
        List<WashBox> washBoxList = new ArrayList<>();
        washBoxList.add(washBox);
        model.addAttribute("boxlist", washBoxList);
        model.addAttribute("nrows", "ID: " + washBox.getId() + " added");
        return "ps-dao-carwashbox";
    }

    @RequestMapping(value = "/washBox/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateWashBox(@RequestBody WashBox washBox) {
        pskvorWashBoxDaoService.updateWashBox(washBox);
        return washBox;
    }

    @RequestMapping(value = "/washBox/{id}", method = RequestMethod.GET)
    public String getWashBox(@PathVariable(value = "id") String inputId, Model model) {
        WashBox washBox = pskvorWashBoxDaoService.getWashBoxByID(Integer.valueOf(inputId));
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        if (washBox != null) {
            List<WashBox> washBoxList = new ArrayList<>();
            washBoxList.add(washBox);
            model.addAttribute("boxlist", washBoxList);
        } else {model.addAttribute("nrows", "ID: "+inputId + " doesn`t exists ");}
        return "ps-dao-carwashbox";
    }

    @RequestMapping(value = "/washBox/{idFacility}/{boxName}", method = RequestMethod.GET)
    public String getWashBoxesOnFacility(@PathVariable(value = "idFacility") String idFclt, @PathVariable(value = "boxName") String boxName,
                                         HttpServletResponse response, Model model) {
        WashBox washBox = pskvorWashBoxDaoService.getWashBox(Integer.valueOf(idFclt), boxName);
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        if (washBox != null) {
            List<WashBox> washBoxList = new ArrayList<>();
            washBoxList.add(washBox);
            model.addAttribute("boxlist", washBoxList);
        } else {model.addAttribute("nrows", "Box name: "+boxName + "on facility "+idFclt+" doesn`t exists ");}
        return "ps-dao-carwashbox";
    }


    @RequestMapping(value = "/washBox/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteWashBox(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        WashBox washBox = pskvorWashBoxDaoService.getWashBoxByID(Integer.valueOf(inputId));
        if (washBox != null) {
            int id = washBox.getId();
            pskvorWashBoxDaoService.deleteWashBox(washBox);
            return String.valueOf(response.SC_OK);
        } else {return  String.valueOf(response.SC_NOT_FOUND);}
    }

    @RequestMapping(value = "/wasBoxByType/{type}", method = RequestMethod.GET)
    public String getWashBoxListbyType(@PathVariable(value = "type") String typeId, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        List<WashBox> washBoxList = pskvorWashBoxDaoService.getWashBoxesByType(Integer.valueOf(typeId));
        model.addAttribute("boxlist", washBoxList);
        model.addAttribute("nrows", washBoxList.size() + " rows affected");
        return "ps-dao-carwashbox";
    }

    @RequestMapping(value = "/wasBoxByStatus/{status}", method = RequestMethod.GET)
    public String getWashBoxListbyStatus(@PathVariable(value = "status") String status, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        List<WashBox> washBoxList = pskvorWashBoxDaoService.getWashBoxesByStatus(Integer.valueOf(status));
        model.addAttribute("boxlist", washBoxList);
        model.addAttribute("nrows", washBoxList.size() + " rows affected");
        return "ps-dao-carwashbox";
    }

    @RequestMapping(value = "/boxStatus/list/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<BoxStatus> getBoxStatusList(@RequestBody BoxStatus boxStatus) {
        return pskvorWashBoxDaoService.getWashBoxesStatuses();//"ps-dao-carwashfacilities";
    }

    @RequestMapping(value = "/boxType/list/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<BoxType> getBoxTypesList(@RequestBody BoxType boxtypel) {
        return pskvorWashBoxDaoService.getWashBoxesTypes();//"ps-dao-carwashfacilities";
    }

    @RequestMapping(value = "/boxStatus/{code}/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxStatus getBoxStatusByCode(@PathVariable(value = "code") String code) {
        return pskvorWashBoxDaoService.getWashBoxesStatusByCode(code);//"ps-dao-carwashfacilities";
    }

    @RequestMapping(value = "/boxType/{code}/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BoxType getBoxTypesList(@PathVariable(value = "code") String code) {
        return pskvorWashBoxDaoService.getWashBoxesTypeByCode(code);//"ps-dao-carwashfacilities";
    }


}
