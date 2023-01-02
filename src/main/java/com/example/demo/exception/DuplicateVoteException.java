package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateVoteException extends RuntimeException {

    public DuplicateVoteException(String exception) {

        super(exception);
    }
}
