package io.khasang.moika.controller;

import io.khasang.moika.dao.CityDao;
import io.khasang.moika.entity.*;
import io.khasang.moika.service.BoxStatusDataAccessService;
import io.khasang.moika.service.BoxTypesDataAccessService;
import io.khasang.moika.service.PskvorWashFacilityDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Pauls
 */
@RequestMapping(value = "/washFacility")
@Controller
public class PsWashFacilityController {

    @Autowired
    private PskvorWashFacilityDaoService pskvorWashFacilityDaoService;
    @Autowired
    private CityDao cityDaol;

    @Autowired
    private BoxStatusDataAccessService boxStatusDataAccessService;
    @Autowired
    private BoxTypesDataAccessService boxTypesDataAccessService;


    /**
     * Список всех моек
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    //@ResponseBody
    public Object getWashFacilityList(Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        List<WashFacility> washFacilityList = pskvorWashFacilityDaoService.getAllWashFacilities();
       List<City>  citiesList = cityDaol.getAll();
       List<BoxStatus> boxStatusList = boxStatusDataAccessService.getAllStatuses();
       List<BoxType> boxTypeList = boxTypesDataAccessService.getAllTypes();
        model.addAttribute("fcltlist", washFacilityList);
        model.addAttribute("citiesList", citiesList);
        model.addAttribute("boxStatusList", boxStatusList);
        model.addAttribute("boxTypeList", boxTypeList);
        return "ps-dao-carwashfacilities"; //washFacilityList;
    }

    /**
     * Добавление новой мойки
     * @param washFacility
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public WashFacility addWashFacility(@RequestBody WashFacility washFacility, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        washFacility = pskvorWashFacilityDaoService.addWashFacility(washFacility);
        return washFacility; //"ps-dao-carwashfacilities";
    }

    /**
     * Обновление информации о мойке
     * @param washFacility
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateWashFacility(@RequestBody WashFacility washFacility) {
        washFacility =  pskvorWashFacilityDaoService.updateWashFacility(washFacility);
        return washFacility;
    }

    /**
     * вывод мойки по id
     * @param idFclt
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<WashFacility> getWashFacility(@PathVariable(value = "id") String idFclt, Model model) {
        WashFacility washFacility = pskvorWashFacilityDaoService.getWashFacilityByID(Integer.valueOf(idFclt));
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        List<WashFacility> washFacilityList = new ArrayList<>();
        if (washFacility != null) {
            washFacilityList.add(washFacility);
            model.addAttribute("fcltlist", washFacilityList);
        } else {model.addAttribute("nrows", "ID: "+idFclt + " doesn`t exists ");}
        return washFacilityList;//"ps-dao-carwashfacilities";
    }

    /**
     * Вывод списка моек в моечной сети
     * @param idNet - id Сети
     * @param model
     * @return
     */
    @RequestMapping(value = "/OnNet/{idNet}", method = RequestMethod.GET)
    public String getWashFacilityesOnNet(@PathVariable(value = "idNet") String idNet, Model model) {
        List<WashFacility> washFacilityList = pskvorWashFacilityDaoService.getWashFacilitiesOnNet(Integer.valueOf(idNet));
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        if (washFacilityList != null) {
            model.addAttribute("fcltlist", washFacilityList);
        } else {model.addAttribute("nrows", "There are no: facilities on net Id: "+idNet);}
        return "ps-dao-carwashfacilities";
    }

    /**
     * Удалени мойки по id
     * @param inputId
     * @param response
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteWashFacility(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        WashFacility washFacility = pskvorWashFacilityDaoService.getWashFacilityByID(Integer.valueOf(inputId));
        if (washFacility != null) {
            int id = washFacility.getId();
            pskvorWashFacilityDaoService.deleteWashFacility(washFacility);
            return String.valueOf(response.SC_OK);
        } else {return  String.valueOf(response.SC_NOT_FOUND);}
    }

    /**
     * Вывод списка боксов мойки
     * @param city
     * @param model
     * @return
     */
    @RequestMapping(value = "/inCity/{city}", method = RequestMethod.GET)
    public String getFacilitiesInCity(@PathVariable(value = "city") City city, Model model) {
   //     model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
   //     List<WashBox> washBoxesList = pskvorWashFacilityDaoService.getWashBoxesOnFacility(Integer.valueOf(idFclt));
   //     model.addAttribute("boxlist", washBoxesList);
   //     model.addAttribute("nrows", washBoxesList.size() + " rows affected");
        return "ps-dao-carwashbox";
    }


    /**
     * Вывод списка моек в городе
     * @param washAddr
     * @param model
     * @return
     */
    @RequestMapping(value = "/onAddr/{addr}", method = RequestMethod.GET)
    public String getFacilityByAddr(@PathVariable(value = "addr") WashAddr washAddr, Model model) {
    //    model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
    //    List<WashBox> washBoxesList = pskvorWashFacilityDaoService.getWashBoxesOnFacility(Integer.valueOf(idFclt));
     //   model.addAttribute("boxlist", washBoxesList);
    //    model.addAttribute("nrows", washBoxesList.size() + " rows affected");
        return "ps-dao-carwashbox";
    }

    /**
     * Вывод списка моек в городе
     * @param coord
     * @param model
     * @return
     */
    @RequestMapping(value = "/byCoord/{coord}", method = RequestMethod.GET)
    public String getFacilityByAddr(@PathVariable(value = "coord") Coordinate coord, Model model) {
     //   model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
     //   List<WashBox> washBoxesList = pskvorWashFacilityDaoService.getWashBoxesOnFacility(Integer.valueOf(idFclt));
     //   model.addAttribute("boxlist", washBoxesList);
      //  model.addAttribute("nrows", washBoxesList.size() + " rows affected");
        return "ps-dao-carwashbox";
    }
}
