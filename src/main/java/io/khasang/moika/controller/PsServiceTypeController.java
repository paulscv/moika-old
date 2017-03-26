package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ServiceType;
import io.khasang.moika.service.MoikaServiceTypesService;
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
 * Контроллер для типов услу моесного сервиса
 * @author Pauls
 */
@Controller
public class PsServiceTypeController {
    @Autowired
    MoikaServiceTypesService serviceTypeService;

    /**
     * Список типов услуг
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/serviceTypelist", method = RequestMethod.GET)
    public String getServiceTypeList(Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        List<ServiceType> serviceTypeList = null;
        try {
            serviceTypeList = serviceTypeService.getAllTypes();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("retList", serviceTypeList);
        model.addAttribute("nrows", serviceTypeList.size() + " rows affected");
        return "ps-dao-service-types";
    }

    /**
     * Добавление нового типа услуг
     *
     * @param serviceType
     * @param model
     * @return - новый тип
     */
    @RequestMapping(value = "/serviceType/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    //@ResponseBody
    public Object addServiceType(@RequestBody ServiceType serviceType, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            serviceTypeService.addType(serviceType);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        List<ServiceType> serviceTypeList = new ArrayList<>();
        serviceTypeList.add(serviceType);
        model.addAttribute("retList", serviceTypeList);
        model.addAttribute("nrows", "ID: " + serviceType.getId() + " added");
        return "ps-dao-service-types";
    }

    /**
     * Обновление информауии о типе услуг
     *
     * @param serviceType
     * @return
     */
    @RequestMapping(value = "/serviceType/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateServiceType(@RequestBody ServiceType serviceType) {
        try {
            serviceTypeService.updateType(serviceType);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        return serviceType;
    }

    /**
     * Вывод типа услуг по id
     *
     * @param inputId
     * @param model
     * @return
     */
    @RequestMapping(value = "/serviceType/{id}", method = RequestMethod.GET)
    public String getServiceType(@PathVariable(value = "id") String inputId, Model model) {
        ServiceType serviceType = null;
        try {
            serviceType = (ServiceType) serviceTypeService.getTypeByID(Integer.valueOf(inputId));
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        if (serviceType != null) {
            List<ServiceType> serviceTypeList = new ArrayList<>();
            serviceTypeList.add(serviceType);
            model.addAttribute("retList", serviceTypeList);
        } else {model.addAttribute("nrows", "ID: " + inputId + " doesn`t exists ");}
        return "ps-dao-service-types";
    }

    /**
     * Вывод типа услуг по коду
     *
     * @param code
     * @param model
     * @return
     */
    @RequestMapping(value = "/serviceType/{code}", method = RequestMethod.GET)
    public String getServiceTypeListbyType(@PathVariable(value = "type") String code, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        ServiceType serviceType = null;
        try {
            serviceType = (ServiceType) serviceTypeService.getTypeByCode(code);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        if (serviceType != null) {
            List<ServiceType> serviceTypeList = new ArrayList<>();
            serviceTypeList.add(serviceType);
            model.addAttribute("retList", serviceTypeList);
        } else {model.addAttribute("nrows", "code: " + code + " doesn`t exists ");}
        return "ps-dao-service-types";
    }

    /**
     * удаление типа  услуг
     *
     * @param inputId
     * @param response
     * @return
     */
    @RequestMapping(value = "/serviceType/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteServiceType(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        ServiceType serviceType = null;
        try {
            serviceType = (ServiceType) serviceTypeService.getTypeByID(Integer.valueOf(inputId));
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        if (serviceType != null) {
            int id = serviceType.getId();
            try {
                serviceTypeService.deleteType(serviceType);
            } catch (MoikaDaoException e) {
                e.printStackTrace();
            }
            return String.valueOf(response.SC_OK);
        } else {return String.valueOf(response.SC_NOT_FOUND);}
    }


}
