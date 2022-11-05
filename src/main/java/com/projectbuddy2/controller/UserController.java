package com.projectbuddy2.controller;


import com.projectbuddy2.entities.Budget;
import com.projectbuddy2.entities.User;
import com.projectbuddy2.repositories.UserRepository;
import com.projectbuddy2.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    //Budgets eines User ausgeben
    @GetMapping(value = "/{userId}/budgets")
    public ResponseEntity<Set<Budget>> getBudgetsFromUser(@PathVariable final Long userId){
        User user = customUserDetailsService.getUser(userId);
        Set<Budget> budgetList = user.getBudgetList();
        return new ResponseEntity<>(budgetList, HttpStatus.OK);
    }
}
