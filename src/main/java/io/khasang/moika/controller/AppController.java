package io.khasang.moika.controller;

import io.khasang.moika.dao.CompanyDao;
import io.khasang.moika.entity.Company;
import io.khasang.moika.model.CreateTable;
import io.khasang.moika.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    private CreateTable createTable;

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyDao companyDao;

    @RequestMapping("/")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "Car washer") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping("/db/create")
    public String create(Model model) {
        model.addAttribute("create", createTable.createTableStatus());
        return "create";
    }

    @RequestMapping(value = {"/hello/{name}"}, method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }

    @RequestMapping(value = "company/add/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company addCompany(@RequestBody Company company, @PathVariable("id") String id) {
        company.setAmount(BigDecimal.valueOf(Long.parseLong(id)));
        companyService.addCompany(company);
        return company;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String getCompanyList(Model model) {
        model.addAttribute("companies", companyService.getCompanyGazpromList());
        return "companies";
    }

    @RequestMapping(value = "/company/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company updateCompany(@RequestBody Company company) {
        companyService.updateCompany(company);
        return company;
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCompany(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
      companyService.deleteCompany(Integer.parseInt(inputId));
      return "redirect:/company";
    }

    @RequestMapping("/restHql")
    public String testHql() {
        List<Company> companyList = companyDao.getCompanyHqlList();
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Company getCompanybyId(@PathVariable(value = "id") String inputId, Model model){
        Company company = companyService.getCompanyById(Integer.valueOf(inputId));
        return company;
    }
}
