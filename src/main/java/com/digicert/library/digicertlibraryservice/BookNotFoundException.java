package com.digicert.library.digicertlibraryservice;

public class BookNotFoundException extends Exception {
  public BookNotFoundException(String error) {
    super(error);
  }
}
