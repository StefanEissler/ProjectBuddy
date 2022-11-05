package com.projectbuddy2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

//    @GetMapping("/")
//    public String budget() {
//        return "budget";
//    }


    @RequestMapping("/budget")
    public String index(){
        return "budget";
    }

}
