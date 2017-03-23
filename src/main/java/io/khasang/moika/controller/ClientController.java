package io.khasang.moika.controller;

import io.khasang.moika.entity.Client;
import io.khasang.moika.validator.CarValidator;
import io.khasang.moika.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/client")
public class ClientController {
//    @Autowired
//    private ClientDao clientDAO;
    @Autowired
    private ClientValidator clientValidator;
    @Autowired
    private CarValidator carValidator;

    @RequestMapping(method =  RequestMethod.GET)
    public String getClient(){
        return "client";
    }

//    @RequestMapping("/list")
//    public String getListClients(Model model){
//        try {
//            model.addAttribute("clients",clientDAO.getAll());
//            model.addAttribute("contClientId",clientDAO.get(2));
//        } catch (MoikaDaoException e) {
//            e.printStackTrace();
//        }
//        return "clientlist";
//    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String crateClient(){
        return "client";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String addClient(@RequestBody Client client, Model model){
        if(client!=null){
            model.addAttribute("client",client);
        }
        return "client";
    }

}
