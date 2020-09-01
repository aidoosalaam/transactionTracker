package com.clickcharm.transactoinapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpTrackNotFoundException extends RuntimeException {

    public ExpTrackNotFoundException(String message) {
        super(message);
    }
}
