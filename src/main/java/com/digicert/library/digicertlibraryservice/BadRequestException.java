package com.digicert.library.digicertlibraryservice;

public class BadRequestException extends Exception {
  public BadRequestException(String error) {
    super(error);
  }
}
