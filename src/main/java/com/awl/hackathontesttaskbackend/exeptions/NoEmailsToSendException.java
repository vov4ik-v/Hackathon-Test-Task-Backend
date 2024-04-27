package com.awl.hackathontesttaskbackend.exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoEmailsToSendException extends RuntimeException {
    public NoEmailsToSendException(String message){
        super(message);


    }
}
