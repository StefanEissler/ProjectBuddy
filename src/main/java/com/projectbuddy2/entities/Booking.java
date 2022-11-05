package com.projectbuddy2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking {

    public Booking(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "timestamp")
    private LocalDate timestamp;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "category")
    private String category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget.id", referencedColumnName = "id")
    @JsonIgnore
    private Budget budget;


}
