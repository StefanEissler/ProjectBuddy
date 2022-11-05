package com.projectbuddy2.dto;



import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
public class BudgetDto {

    private String name;
    private LocalDate date;
    private BigDecimal amount;

    public BudgetDto(String name, LocalDate date, BigDecimal amount, ArrayList<BookingDto> bookingDto) {
        this.name = name;
        this.date = date;
        this.amount = amount;
    }


//    public static BudgetDto from(Budget budget) {
//        BudgetDto budgetDto = new BudgetDto();
//        budgetDto.setName(budget.getName());
//        budgetDto.setDate(budget.getDate());
//        budgetDto.setAmount(budget.getAmount());
//        budgetDto.setBookingDto(budget.getBookings().stream().map(bookingDto::from).collect(Collectors.toList()));
//        return budgetDto;
//    }
}
