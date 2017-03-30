package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ServiceType;
import io.khasang.moika.service.MoikaServiceTypesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 *
 * @author Pauls
 */
@RequestMapping(value = "/serviceType")
@Controller
public class PsServiceTypeController {
    private static final Logger logger = LoggerFactory.getLogger(PsServiceTypeController.class);
    @Autowired
    MoikaServiceTypesService serviceTypeService;

    /**
     * Список типов услуг
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getServiceTypeList(Model model) {
        logger.info("Запрошен сервис по адресу \"/serviceType/list\". Готовим аттрибуты ");
        try {
            model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
            List<ServiceType> serviceTypeList = null;
            try {
                serviceTypeList = serviceTypeService.getAllTypes();
            } catch (MoikaDaoException e) {
                e.printStackTrace();
            }
            model.addAttribute("retList", serviceTypeList);
            model.addAttribute("nrows", serviceTypeList.size() + " rows affected");
        } catch (Exception e) {
            logger.error("Ошибка при запросе \"/serviceType/list\" ", e);
            e.printStackTrace();
        }
        return "ps-dao-service-types";
    }

    /**
     * Добавление нового типа услуг
     *
     * @param serviceType
     * @param model
     * @return - новый тип
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    //@ResponseBody
    public Object addServiceType(@RequestBody ServiceType serviceType, Model model) {
        logger.info("Запрошен сервис по адресу \"/serviceType/add\". Готовим аттрибуты ");
        try {
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
        } catch (Exception e) {
            logger.error("Ошибка при запросе \"/serviceType/add\" ", e);
            e.printStackTrace();
        }
        return "ps-dao-service-types";
    }

    /**
     * Обновление информауии о типе услуг
     *
     * @param serviceType
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateServiceType(@RequestBody ServiceType serviceType) {
        logger.info("Запрошен сервис по адресу \"/serviceType/update\". Готовим аттрибуты ");
        try {
            serviceTypeService.updateType(serviceType);
        } catch (MoikaDaoException e) {
            logger.error("Ошибка при запросе \"/serviceType/update\" ", e);
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
    @RequestMapping(value = "/byId/{id}", method = RequestMethod.GET)
    public String getServiceType(@PathVariable(value = "id") String inputId, Model model) {
        logger.info("Запрошен сервис по адресу \"/serviceType/byId/{" + inputId + "}\". Готовим аттрибуты ");
        ServiceType serviceType = null;
        try {
            serviceType = (ServiceType) serviceTypeService.getTypeByID(Integer.valueOf(inputId));
        } catch (MoikaDaoException e) {
            logger.error("Ошибка при запросе \"/serviceType/byId/{" + inputId + "}\" ", e);
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
    @RequestMapping(value = "/byCode/{code}", method = RequestMethod.GET)
    public String getServiceTypeListbyType(@PathVariable(value = "type") String code, Model model) {
        logger.info("Запрошен сервис по адресу \"/serviceType/byCode/{" + code + "}\". Готовим аттрибуты ");
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        ServiceType serviceType = null;
        try {
            serviceType = (ServiceType) serviceTypeService.getTypeByCode(code);
        } catch (MoikaDaoException e) {
            logger.error("Ошибка при запросе \"/serviceType/byCode/{" + code + "}\" ", e);
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
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteServiceType(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        logger.info("Запрошен сервис по адресу \"/serviceType/delete/{" + inputId + "}\". Готовим аттрибуты ");
        ServiceType serviceType = null;
        try {
            serviceType = (ServiceType) serviceTypeService.getTypeByID(Integer.valueOf(inputId));
        } catch (MoikaDaoException e) {
            logger.error("Ошибка при запросе \"/serviceType/delete/{" + inputId + "}\" ", e);
            e.printStackTrace();
        }
        if (serviceType != null) {
            int id = serviceType.getId();
            try {
                serviceTypeService.deleteType(serviceType);
            } catch (MoikaDaoException e) {
                logger.error("Ошибка при запросе \"/serviceType/delete/{" + inputId + "}\" ", e);
                e.printStackTrace();
            }
            return String.valueOf(response.SC_OK);
        } else {return String.valueOf(response.SC_NOT_FOUND);}
    }


}
