package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PautaNotFoundException extends RuntimeException {

    public PautaNotFoundException(String exception) {

        super(exception);
    }
}
