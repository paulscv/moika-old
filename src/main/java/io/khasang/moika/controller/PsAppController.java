package io.khasang.moika.controller;

import io.khasang.moika.service.impl.PskvorDataAccessServiceImpl;
import org.springframework.stereotype.Controller;
import io.khasang.moika.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * тестовые методы для запросов jdbc
 * @author Pauls
 */
@Controller
public class PsAppController {
    @Autowired
    CreateTable createTable;
    @Autowired
    PskvorDataAccessServiceImpl pskvorDataAccessService;

    /**
     * jdbc запрос к  статусам боксов
     * @param model
     * @return
     */
    @RequestMapping(value = "/ps-queries", method = RequestMethod.GET)
    public String SelectAll(Model model) {
        String res = pskvorDataAccessService.select("public.box_status");
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-queries2", method = RequestMethod.GET)
    public String SelectWhere(Model model) {
        String res = pskvorDataAccessService.select("public.box_status", " id_status = ?", new Object[]{2});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-create2", method = RequestMethod.GET)
    public String createTable(Model model) {
        String res = pskvorDataAccessService.createData("public.test", new String[]{"id", "Name1", "Name2"},
                new String[]{"serial", "varchar (20)", "varchar(255)"}, " id_pkey PRIMARY KEY (id)");
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-change", method = RequestMethod.GET)
    public String updateTable(Model model) {
        String res = pskvorDataAccessService.changeData("public.test", new String[]{"Name1"}, " id = ?", new Object[]{"DDDDD", 1});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-insert", method = RequestMethod.GET)
    public String insertIntoTable(Model model) {
        String res = pskvorDataAccessService.addData("public.test", new String[]{"AAAAAA", "BBBBB"});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-selectjoin", method = RequestMethod.GET)
    public String selectJoin(Model model) {
        // TODO: 22.02.2017         String res = pskvorDataAccessService.joinSelect("public.cars", "public.car_types", "id_type", "carnum = ?", new Object[]{"DDDDD"}); // поменять на № машины
//        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-delete", method = RequestMethod.GET)
    public String deleteData(Model model) {
        String res = pskvorDataAccessService.deleteData("public.test", " id = ?", new Object[]{1});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-backup", method = RequestMethod.GET)
    public String backupData(Model model) {
        // TODO: 22.02.2017   String res = pskvorDataAccessService.backupData("c:\\backup\\carwash.sql");
//        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }
}
