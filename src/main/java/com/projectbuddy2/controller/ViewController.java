package com.projectbuddy2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @Autowired
    AuthController authController;

    @RequestMapping("/budget")
    public String budgetPage(){
        return "frontend/budget/index";
    }
    @RequestMapping("/analysis")
    public String analysis(){
        return "frontend/analysis/index";
    }
    @RequestMapping("/login")
    public String login(){
        return "frontend/login/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

}
