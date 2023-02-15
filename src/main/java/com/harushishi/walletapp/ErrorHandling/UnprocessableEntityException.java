package com.harushishi.walletapp.ErrorHandling;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends RuntimeException {

  public UnprocessableEntityException(String message) {
    super(message);
  }

  public HttpStatus getStatus() {
    return HttpStatus.UNPROCESSABLE_ENTITY;
  }
}
