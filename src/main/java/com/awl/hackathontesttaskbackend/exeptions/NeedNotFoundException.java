package com.awl.hackathontesttaskbackend.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NeedNotFoundException extends RuntimeException {
    public NeedNotFoundException(String message) {
        super(message);
    }
}
