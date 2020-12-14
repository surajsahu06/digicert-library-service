package com.digicert.library.digicertlibraryservice.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "book")
public class BookDetailEntity {

  @Column(name = "book_id")
  @Id
  private Integer bookId;

  @Column(name = "book_title", nullable = false)
  private String bookTitle;

  private String writer;

  @Column(name = "create_date", nullable = false)
  private String createDate;
}

