package com.projectbuddy2.Exceptions;

import java.text.MessageFormat;

public class BookingIsAlreadyAssignedException extends RuntimeException{
    public BookingIsAlreadyAssignedException(final long bookingId, final long budgetId){
        super(MessageFormat.format("Booking is already assigned", bookingId, budgetId));
    }
}
