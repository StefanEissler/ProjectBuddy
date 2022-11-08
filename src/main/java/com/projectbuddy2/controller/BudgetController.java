package com.projectbuddy2.controller;

import com.projectbuddy2.dto.BudgetDto;
import com.projectbuddy2.entities.Budget;
import com.projectbuddy2.repositories.BudgetRepository;
import com.projectbuddy2.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/budget")
@CrossOrigin
public class BudgetController {

    private final BudgetRepository budgetRepository;

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService, BudgetRepository budgetRepository) {
        this.budgetService = budgetService;
        this.budgetRepository = budgetRepository;
    }

    //alle Budgets ausgeben
    @GetMapping
    public ResponseEntity<List<Budget>> getBudgets(){
        List<Budget> budgets = budgetService.getBudgets();
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    // ein Budget mit ID ausgeben
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBudget(@PathVariable final Long id){
        if(!budgetRepository.existsById(id))
            return new ResponseEntity<>("id is not in use", HttpStatus.BAD_REQUEST);
        Optional<Budget> budget = budgetRepository.findById(id);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }


    // Neues Budget erstellen
    @PostMapping(value= "/add", consumes = {"application/json"})
    public ResponseEntity<Budget> createBudget(@RequestBody BudgetDto budgetDto) {
        Budget budget = new Budget();
        budget.setName(budgetDto.getName());
        budget.setAmount(budgetDto.getAmount());
        budget.setDate(budgetDto.getDate());
        budgetRepository.save(budget);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    // Budget loeschen
    @DeleteMapping(value={"{id}"})
    public ResponseEntity<Budget> deleteBudget(@PathVariable final Long id){
        Budget budget = budgetService.deleteBudget(id);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    // Budget bearbeiten
    @PutMapping(value={"{id}"}, consumes = "application/json")
    public ResponseEntity<Budget> editBudget(@PathVariable final Long id, @RequestBody final BudgetDto budgetDto){
        Budget budget = budgetService.editBudget(id, budgetDto);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    // Booking zu Budget hinzufuegen
    @PostMapping(value={"{budgetId}/booking/{bookingId}/add"})
    public ResponseEntity<Budget> addBookingToBudget(@PathVariable final Long budgetId, @PathVariable final Long bookingId){
        Budget budget = budgetService.addBookingtoBudget(budgetId, bookingId);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    // Booking von Budget wegnehmen
    @PostMapping(value={"{budgetId}/booking/{bookingId}/remove"})
    public ResponseEntity<Budget> removeBookingToBudget(@PathVariable final Long budgetId, @PathVariable final Long bookingId){
        Budget budget = budgetService.removeBookingFromBudget(budgetId, bookingId);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }


    // Budget an User anheften
    @PostMapping(value = {"{budgetId}/user/{userId}/add"})
    public ResponseEntity<Budget> addBudgetToUser(@PathVariable final Long budgetId, @PathVariable final Long userId){
        Budget budget = budgetService.addBudgettoUser(budgetId, userId);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    // Budget von User entfernen


}
