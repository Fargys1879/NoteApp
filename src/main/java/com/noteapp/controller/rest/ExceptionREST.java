package com.noteapp.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ExceptionREST extends Exception{
    public ExceptionREST(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionREST(String message) {
        super(message);
    }
}

