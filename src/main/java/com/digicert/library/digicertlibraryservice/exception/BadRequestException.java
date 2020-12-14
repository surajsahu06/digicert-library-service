package com.digicert.library.digicertlibraryservice.exception;

public class BadRequestException extends Exception {
  public BadRequestException(String error) {
    super(error);
  }
}
