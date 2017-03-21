package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ServiceStatus;
import io.khasang.moika.service.MoikaServiceStatusService;
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
public class PsServiceStatusController {
    @Autowired
    MoikaServiceStatusService serviceStatusService;



    @RequestMapping(value = "/serviceStatuslist", method = RequestMethod.GET)
    public String getServiceStatusList(Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        List<ServiceStatus> serviceStatusList = null;
        try {
            serviceStatusList = serviceStatusService.getAllStatuses();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("retList", serviceStatusList);
        model.addAttribute("nrows", serviceStatusList.size() + " rows affected");
        return "ps-dao-service-status";
    }

    @RequestMapping(value = "/serviceStatus/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    //@ResponseBody
    public Object addServiceStatus(@RequestBody ServiceStatus serviceStatus, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        try {
            serviceStatusService.addStatus(serviceStatus);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        List<ServiceStatus> serviceStatusList = new ArrayList<>();
        serviceStatusList.add(serviceStatus);
        model.addAttribute("retList", serviceStatusList);
        model.addAttribute("nrows", "ID: " + serviceStatus.getId() + " added");
        return "ps-dao-service-status";
    }

    @RequestMapping(value = "/serviceStatus/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateServiceStatus(@RequestBody ServiceStatus serviceStatus) {
        try {
            serviceStatusService.updateStatus(serviceStatus);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        return serviceStatus;
    }

    @RequestMapping(value = "/serviceStatus/{id}", method = RequestMethod.GET)
    public String getServiceStatus(@PathVariable(value = "id") String inputId, Model model) {
        ServiceStatus serviceStatus = null;
        try {
            serviceStatus = (ServiceStatus)serviceStatusService.getStatusByID(Integer.valueOf(inputId));
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        if (serviceStatus != null) {
            List<ServiceStatus> serviceStatusList = new ArrayList<>();
            serviceStatusList.add(serviceStatus);
            model.addAttribute("retList", serviceStatusList);
        } else {model.addAttribute("nrows", "ID: "+inputId + " doesn`t exists ");}
        return "ps-dao-serivce-status";
    }
    

    @RequestMapping(value = "/serviceStatus/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteServiceStatus(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        ServiceStatus serviceStatus = null;
        try {
            serviceStatus = (ServiceStatus)serviceStatusService.getStatusByID(Integer.valueOf(inputId));
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        if (serviceStatus != null) {
            int id = serviceStatus.getId();
            try {
                serviceStatusService.deleteStatus(serviceStatus);
            } catch (MoikaDaoException e) {
                e.printStackTrace();
            }
            return String.valueOf(response.SC_OK);
        } else {return  String.valueOf(response.SC_NOT_FOUND);}
    }

    @RequestMapping(value = "/serviceStatus/{code}", method = RequestMethod.GET)
    public String getServiceStatusListbyType(@PathVariable(value = "type") String code, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        ServiceStatus serviceStatus = null;
        try {
            serviceStatus = (ServiceStatus)serviceStatusService.getStatusByCode(code);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        if (serviceStatus != null) {
            List<ServiceStatus> serviceStatusList = new ArrayList<>();
            serviceStatusList.add(serviceStatus);
            model.addAttribute("retList", serviceStatusList);
        } else {model.addAttribute("nrows", "code: "+code + " doesn`t exists ");}
        return "ps-dao-serivce-status";
    }



}
