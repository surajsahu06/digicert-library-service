package com.digicert.library.digicertlibraryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailAddRequest {

  private int bookId;

  private String bookTitle;

  private String writer;
}
