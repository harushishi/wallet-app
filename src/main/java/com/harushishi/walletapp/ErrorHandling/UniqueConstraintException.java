package com.harushishi.walletapp.ErrorHandling;

import org.springframework.http.HttpStatus;

public class UniqueConstraintException extends RuntimeException {

    public UniqueConstraintException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
