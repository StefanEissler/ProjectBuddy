package com.projectbuddy2.Exceptions;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long id){
        super(MessageFormat.format("Could not find user with id: ", id));
    }
}
