package com.projectbuddy2.Exceptions;

import java.text.MessageFormat;

public class BudgetNotFoundException extends RuntimeException{
    public BudgetNotFoundException(final Long id){
        super(MessageFormat.format("Could not find Budget with id: ", id));
    }
}
