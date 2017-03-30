package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.*;
import io.khasang.moika.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Pauls
 */

@Controller
@RequestMapping("/MoikaService/service")
public class PsServicesController {
    private static final Logger logger = LoggerFactory.getLogger(PsServicesController.class);
    @Autowired
    MoikaServiceDataAccessService allService;
    @Autowired
    WashServiceDataAccessService washService;
    @Autowired
    PolishServiceDataAccessService polishService;
    @Autowired
    OtherServiceDataAccessService otherService;
    @Autowired
    CleanServiceDataAccessService cleanService;
    @Autowired
    ChemCleanServiceDataAccessService chemCleanService;
    @Autowired
    MoikaServiceTypesService moikaServiceTypes;
    @Autowired
    MoikaServiceStatusService moikaServiceStatus;

    /**
     * Запрос всех услуг автомойки
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/allServiceList", method = RequestMethod.GET)
    public String getBaseServiceList(Model model) {
        logger.info("Запрошен сервис по адресу \"/MoikaService/service/allServiceList\". Готовим аттрибуты ");
        List<MoikaService> serviceList = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            serviceList = allService.getAllServices();
        } catch (MoikaDaoException e) {
            logger.error("Ошибка при запросе \"/MoikaService/service/allServiceList\" ", e);
            e.printStackTrace();
        }
        model.addAttribute("servicelist", serviceList);
        model.addAttribute("nrows", serviceList.size() + " rows affected");
        return "ps-dao-services";
    }

    /**
     * Добавление новой услуги
     *
     * @param moikaService
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MoikaService addMoikaService(@RequestBody MoikaService moikaService) {
        logger.info("Запрошен сервис по адресу \"/MoikaService/service/add\". Готовим аттрибуты ");
        try {
            moikaService = allService.addService(moikaService);
            return moikaService; //"ps-dao-carwashfacilities";
        } catch (Exception e) {
            logger.error("Ошибка при запросе \"/MoikaService/service/add\" ", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Обновление услуги
     *
     * @param moikaService
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MoikaService updateWashService(@RequestBody MoikaService moikaService) {
        logger.info("Запрошен сервис по адресу \"/MoikaService/service/update\". Готовим аттрибуты ");
        try {
            allService.updateService(moikaService);
            MoikaService resService = allService.getServiceById(moikaService.getId());
            return resService; //"ps-dao-carwashfacilities";
        } catch (Exception e) {
            logger.error("Ошибка при запросе \"/MoikaService/service/update\" ", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Удаление услуги
     *
     * @param id - услуги
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MoikaService deleteWashService(@PathVariable(value = "id") int id) {
        logger.info("Запрошен сервис по адресу \"/MoikaService/delete/{" + id + "}\". Готовим аттрибуты ");
        try {
            MoikaService moikaService = allService.getServiceById(id);
            moikaService = allService.addService(moikaService);
            return moikaService; //"ps-dao-carwashfacilities";
        } catch (Exception e) {
            logger.error("Ошибка при запросе \"/MoikaService/delete/{" + id + "}\" ", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Услуги конкретного вида услуг c подробностями во вложенных entity
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/concreatServiceList/{code}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getWashServiceList(@PathVariable(value = "code") String code, Model model) {
        logger.info("Запрошен сервис по адресу \"/MoikaService/concreatServiceList/{" + code + "}\". Готовим аттрибуты ");
        try {
            List<? extends IBaseMoikaServiceAddInfo> servicesList = new ArrayList<>();
            model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
            switch (code) {
                case "WASH":
                    servicesList = washService.getAllConcreatServices();
                    break;
                case "CLEAN":
                    servicesList = cleanService.getAllConcreatServices();
                    break;
                case "CHEM_CLEAN":
                    servicesList = chemCleanService.getAllConcreatServices();
                    break;
                case "POLISH":
                    servicesList = polishService.getAllConcreatServices();
                    break;
                case "COMPLEX":
                    //servicesList = washService.getAllConcreatServices();
                    break;
                case "OTHER":
                    servicesList = otherService.getAllConcreatServices();
                    break;
            }
            model.addAttribute("servicelist", servicesList);
            model.addAttribute("nrows", servicesList.size() + " rows affected");
            return servicesList; //"ps-dao-wash-services";
        } catch (Exception e) {
            logger.error("Ошибка при запросе \"/MoikaService/concreatServiceList/{" + code + "}\" ", e);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Услуги конкретного вида услуг с ценой и длительностью подробностями в виде строки
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/serviceListByCode/{code}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getWashServiceListByService(@PathVariable(value = "code") String code, Model model) {
        logger.info("Запрошен сервис по адресу \"/MoikaService/serviceListByCode/{" + code + "}\". Готовим аттрибуты ");
        List<MoikaService> servicesList = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            servicesList = allService.getServicesByType(code);
        } catch (MoikaDaoException e) {
            logger.error("Ошибка при запросе \"/MoikaService/serviceListByCode/{" + code + "}\" ", e);
            e.printStackTrace();
        }
        model.addAttribute("servicelist", servicesList);
        model.addAttribute("nrows", servicesList.size() + " rows affected");
        return servicesList; //"ps-dao-wash-services";
    }

    /**
     * Услуги конкретного вида услуг с ценой и длительностью подробностями в виде строки
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/serviceListByStatus/{status}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getWashServiceListByStatus(@PathVariable(value = "status") String status, Model model) {
        logger.info("Запрошен сервис по адресу \"/MoikaService/serviceListByStatus/{" + status + "}\". Готовим аттрибуты ");
        List<MoikaService> servicesList = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            servicesList = allService.getAllervicesByStatus(status);
        } catch (MoikaDaoException e) {
            logger.error("Ошибка при запросе \"/MoikaService/serviceListByStatus/{" + status + "}\" ", e);
            e.printStackTrace();
        }
        model.addAttribute("servicelist", servicesList);
        model.addAttribute("nrows", servicesList.size() + " rows affected");
        return servicesList; //"ps-dao-wash-services";
    }

    /**
     * Все действующие услуги
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/actualServiceList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getActualServiceListByStatus(Model model) {
        logger.info("Запрошен сервис по адресу \"/MoikaService/actualServiceList/\". Готовим аттрибуты ");
        List<MoikaService> servicesList = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            servicesList = allService.getActualServices();
        } catch (MoikaDaoException e) {
            logger.error("Ошибка при запросе \"/MoikaService/actualServiceList/\" ", e);
            e.printStackTrace();
        }
        model.addAttribute("servicelist", servicesList);
        model.addAttribute("nrows", servicesList.size() + " rows affected");
        return servicesList; //"ps-dao-wash-services";
    }

    /**
     * Список типов услуг
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/ServiceTypesList", method = RequestMethod.GET)
    public String getServiceTypeList(Model model) {
        logger.info("Запрошен сервис по адресу \"/MoikaService/ServiceTypesList/\". Готовим аттрибуты ");
        List<ServiceType> allServicesTypes = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            allServicesTypes = moikaServiceTypes.getAllTypes();
        } catch (MoikaDaoException e) {
            logger.error("Ошибка при запросе \"/MoikaService/ServiceTypesList/\" ", e);
            e.printStackTrace();
        }
        model.addAttribute("serviceTypesList", allServicesTypes);
        model.addAttribute("nrows", allServicesTypes.size() + " rows affected");
        return "ps-dao-service-types";
    }

    /**
     * Список статусов услуг
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/ServiceStatusList", method = RequestMethod.GET)
    public String getServiceStatusList(Model model) {
        logger.info("Запрошен сервис по адресу \"/MoikaService/ServiceStatusList/\". Готовим аттрибуты ");
        List<ServiceStatus> serviceStatus = new ArrayList<>();
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            serviceStatus = moikaServiceStatus.getAllStatuses();
        } catch (MoikaDaoException e) {
            logger.error("Ошибка при запросе \"/MoikaService/ServiceStatusList/\" ", e);
            e.printStackTrace();
        }
        model.addAttribute("serviceStatusList", serviceStatus);
        model.addAttribute("nrows", serviceStatus.size() + " rows affected");
        return "ps-dao-service-status";
    }

}
