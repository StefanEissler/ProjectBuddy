package com.projectbuddy2.controller;

import com.projectbuddy2.dto.BookingDto;
import com.projectbuddy2.entities.Booking;
import com.projectbuddy2.repositories.BookingRepository;
import com.projectbuddy2.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


// REST Controller für erzeugen, löschen und ändern der Bookings
@RestController
@RequestMapping("/api/booking")
@CrossOrigin
public class BookingController {


    private final BookingRepository bookingRepository;

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingRepository bookingRepository, BookingService bookingService) {
        this.bookingRepository = bookingRepository;
        this.bookingService = bookingService;
    }

    // alle Bookings ausgeben
    @GetMapping
    public ResponseEntity<List<Booking>> getBookings() {
        List<Booking> bookingList = bookingService.getBookings();
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }


    // ein Booking nach id ausgeben
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBooking(@PathVariable final Long id) {
        if (!bookingRepository.existsById(id))
            return new ResponseEntity<>("No Booking with this id", HttpStatus.BAD_REQUEST);
        Optional<Booking> booking = bookingRepository.findById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    // Booking von Budget augeben
    @GetMapping(value = "/budget/{budgetId}")
    public ResponseEntity<?> getBookingsFromBudget(@PathVariable final Long budgetId) {
        List<Booking> bookingList = bookingService.getBookingsFromBudget(budgetId);
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    // neues Booking erstellen
    @PostMapping(value = "/add", consumes = {"application/json"})
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setTitle(bookingDto.getTitle());
        booking.setAmount(bookingDto.getAmount());
        booking.setCategory(bookingDto.getCategory());
        booking.setTimestamp(LocalDate.now());
        bookingRepository.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    // Booking loeschen
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable final Long id) {
        Booking booking = bookingService.deleteBooking(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    // Booking bearbeiten
    @PutMapping(value = "{id}")
    public ResponseEntity<Booking> editBooking(@PathVariable final Long id, @RequestBody final BookingDto bookingDto) {
        Booking booking = bookingService.editBooking(id, bookingDto);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    // Booking nach Cost ausgeben
    @GetMapping(value = "/costs/{budgetId}")
    public ResponseEntity<List<Booking>> getCostsList(@PathVariable final Long budgetId){
        List<Booking> bookingList = bookingService.findCosts(budgetId);
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    // Booking nach Forecast ausgeben
    @GetMapping(value = "/forecasts/{budgetId}")
    public ResponseEntity<List<Booking>> getForecastsList(@PathVariable final Long budgetId){
        List<Booking> bookingList = bookingService.findForecast(budgetId);
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }
}
