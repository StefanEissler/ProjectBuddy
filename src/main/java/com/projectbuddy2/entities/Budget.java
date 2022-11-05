package com.projectbuddy2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table(name = "budgets")
@Getter
@Setter
public class Budget {

    public Budget() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "amount")
    private BigDecimal amount;

    @JsonIgnore
    @ManyToMany
//    @JoinTable(name = "user_budget_list",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "budget_id"))
    Set<User> users;

    // Booking verlinken
    @OneToMany(mappedBy = "budget")
    List<Booking> bookings;


    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public void removeBooking(Booking booking){
        bookings.remove(booking);
    }

}