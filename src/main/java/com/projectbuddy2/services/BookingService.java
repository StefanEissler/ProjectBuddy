package com.projectbuddy2.services;

import com.projectbuddy2.Exceptions.BookingNotFoundException;
import com.projectbuddy2.dto.BookingDto;
import com.projectbuddy2.entities.Booking;
import com.projectbuddy2.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/*
Buisness Logik für Bookings
 */
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BudgetService budgetService;

    @Autowired
    public BookingService(BookingRepository bookingRepository,@Lazy BudgetService budgetService){
        this.bookingRepository = bookingRepository;
        this.budgetService = budgetService;
    }

    // Booking nach id ausgeben
    public Booking getBooking(Long id){
        return bookingRepository.findById(id).orElseThrow(()-> new BookingNotFoundException(id));
    }

    //alle Bookings ausgeben
    public List<Booking> getBookings() {
        return StreamSupport.stream(bookingRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    // Bookings nach Budget ausgeben
    public List<Booking> getBookingsFromBudget(Long budgetId){
        List<Booking> bookingList = budgetService.getBudget(budgetId).getBookings();
         return bookingList;
    }

    // Booking loeschen
    public Booking deleteBooking(Long id) {
        Booking booking = getBooking(id);
        bookingRepository.delete(booking);
        return booking;
    }

    // Booing bearbeiten
    @Transactional
    public Booking editBooking(Long id, BookingDto bookingDto) {
        Booking bookingEdit = getBooking(id);
        bookingEdit.setTitle(bookingDto.getTitle());
        bookingEdit.setCategory(bookingDto.getCategory());
        bookingEdit.setAmount(bookingDto.getAmount());
        return bookingEdit;
    }

    // Bookings mit Kosten uebergeben
    @Transactional
    public List<Booking> findCosts(Long budgetId){
        List<Booking> bookings = budgetService.getBudget(budgetId).getBookings();
        List<Booking> costs = new ArrayList<>();
        int i = 0;
        while(i < bookings.size()){
            Booking booking = bookings.get(i);
            if(booking.getCategory().equals("Cost"))
                costs.add(booking);
            i++;
        }
        return costs;
    }

    // Bookings mit Forecast uebergeben
    @Transactional
    public List<Booking> findForecast(Long budgetId){
        List<Booking> bookings = budgetService.getBudget(budgetId).getBookings();
        List<Booking> forecasts = new ArrayList<>();
        int i = 0;
        while(i < bookings.size()){
            Booking booking = bookings.get(i);
            if(booking.getCategory().equals("Forecast"))
                forecasts.add(booking);
            i++;        }
        return forecasts;
    }

}
