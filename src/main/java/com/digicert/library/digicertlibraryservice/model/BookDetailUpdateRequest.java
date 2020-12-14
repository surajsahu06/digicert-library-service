package com.digicert.library.digicertlibraryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailUpdateRequest {

  private String bookTitle;

  private String writer;
}
