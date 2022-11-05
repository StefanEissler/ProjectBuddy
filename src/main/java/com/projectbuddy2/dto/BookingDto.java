package com.projectbuddy2.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingDto {

    private String title;
    private LocalDate timestamp;
    private BigDecimal amount;
    private String category;

    public BookingDto(String title, BigDecimal amount, String category) {
        this.title = title;
        this.amount = amount;
        this.category = category;

    }

    public BookingDto(){

    }

}
