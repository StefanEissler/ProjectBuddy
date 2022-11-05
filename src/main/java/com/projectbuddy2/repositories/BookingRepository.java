package com.projectbuddy2.repositories;


import com.projectbuddy2.entities.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    Optional<Booking> findById(Long id);
}
