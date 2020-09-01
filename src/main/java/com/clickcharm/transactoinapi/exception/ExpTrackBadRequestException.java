package com.clickcharm.transactoinapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExpTrackBadRequestException extends RuntimeException {
    public ExpTrackBadRequestException(String message) {
        super(message);
    }
}
