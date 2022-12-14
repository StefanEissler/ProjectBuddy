package com.projectbuddy2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*
API um die Webseiten im Browser über Thymeleaf Framework abzurufen
 */
@Controller
public class ViewController {

    @Autowired
    AuthController authController;

    @RequestMapping(path = "/budget")
    public String budgetPage(){
        return "frontend/budget/index";
    }

    @RequestMapping(path = "/dashboard")
    public String analysis(){
        return "frontend/analysis/index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "frontend/login/index";
    }

}
