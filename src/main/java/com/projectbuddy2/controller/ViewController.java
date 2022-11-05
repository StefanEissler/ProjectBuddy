package com.projectbuddy2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/budget")
    public String budgetPage(){
        return "frontend/budget/index";
    }
    @RequestMapping("/analysis")
    public String index(){
        return "frontend/analysis/index";
    }
}
