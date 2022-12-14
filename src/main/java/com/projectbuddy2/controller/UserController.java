package com.projectbuddy2.controller;


import com.projectbuddy2.entities.Budget;
import com.projectbuddy2.entities.User;
import com.projectbuddy2.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


// REST API um Budgets eines Users zu erhalten
@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    //Budgets eines User ausgeben
    @GetMapping(value = "/{userId}/budgets")
    public ResponseEntity<Set<Budget>> getBudgetsFromUser(@PathVariable final Long userId){
        User user = customUserDetailsService.getUser(userId);
        Set<Budget> budgetList = user.getBudgetList();
        return new ResponseEntity<>(budgetList, HttpStatus.OK);
    }
}
