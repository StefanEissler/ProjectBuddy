package com.projectbuddy2.Exceptions;

import java.text.MessageFormat;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(final long id){
        super(MessageFormat.format("Could not find Booking with id: ", id));
    }
}
