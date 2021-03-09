package com.phoebus.library.librarymicroservicepurchase.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserLibraryNotFoundException extends RuntimeException{
    public UserLibraryNotFoundException(String message){
        super(message);
    }

}
