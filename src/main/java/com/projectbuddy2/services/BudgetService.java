package com.projectbuddy2.services;


import com.projectbuddy2.Exceptions.BookingIsAlreadyAssignedException;
import com.projectbuddy2.Exceptions.BudgetNotFoundException;
import com.projectbuddy2.dto.BudgetDto;
import com.projectbuddy2.entities.Booking;
import com.projectbuddy2.entities.Budget;
import com.projectbuddy2.entities.User;
import com.projectbuddy2.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final BookingService bookingService;
    private final CustomUserDetailsService userService;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository, @Lazy BookingService bookingService, CustomUserDetailsService userService){
        this.budgetRepository = budgetRepository;
        this.bookingService = bookingService;
        this.userService = userService;
    }


    // Budget Liste erstellen
    public List<Budget> getBudgets(){
        return StreamSupport
                .stream(budgetRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    // Budget nach id ausgeben
    public Budget getBudget(Long id) {
        return budgetRepository.findById(id).orElseThrow(()-> new BudgetNotFoundException(id));
    }

    // Budget loeschen
    public Budget deleteBudget(Long id){
        Budget budget = getBudget(id);
        budgetRepository.delete(budget);
        return budget;
    }

    // Budget bearbeiten
    @Transactional
    public Budget editBudget(Long id, BudgetDto budget){
        Budget budgetEdit = getBudget(id);
        budgetEdit.setName(budget.getName());
        budgetEdit.setAmount(budget.getAmount());
        budgetEdit.setDate(budget.getDate());
        return budgetEdit;
    }

    // Booking zu Budget hinzufuegen
    @Transactional
    public Budget addBookingtoBudget(Long budgetId, Long bookingId){
        Budget budget = getBudget(budgetId);
        Booking booking = bookingService.getBooking(bookingId);
        if(Objects.nonNull(booking.getBudget())){
            throw new BookingIsAlreadyAssignedException(budgetId,
                    booking.getBudget().getId());
        }
        budget.addBooking(booking);
        booking.setBudget(budget);
        return budget;
    }

    // Booking von Budget wegnehmen
    @Transactional
    public Budget removeBookingFromBudget(Long budgetId, Long bookingId){
        Budget budget = getBudget(budgetId);
        Booking booking = bookingService.getBooking(bookingId);
        budget.removeBooking(booking);
        return budget;
    }

    // Budget zu User hinzufuegen
    @Transactional
    public Budget addBudgettoUser(Long budgetId, Long userId) {
        Budget budget = getBudget(budgetId);
        User user = userService.getUser(userId);
        user.addBudget(budget);
        return budget;
    }

    // Budget von User loeschen
    @Transactional
    public User removeBudgetFromUser(Long budgetId, Long userId){
        User user = userService.getUser(userId);
        user.removeBudget(budgetId);
        return user;
    }

}
