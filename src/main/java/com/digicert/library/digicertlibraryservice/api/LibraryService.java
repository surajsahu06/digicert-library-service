package com.digicert.library.digicertlibraryservice.api;

import java.util.Optional;

import com.digicert.library.digicertlibraryservice.db.BookDetailEntity;
import com.digicert.library.digicertlibraryservice.exception.BookNotFoundException;
import com.digicert.library.digicertlibraryservice.model.BookDetailAddRequest;
import com.digicert.library.digicertlibraryservice.model.BookDetailUpdateRequest;

public interface LibraryService {

  Iterable<BookDetailEntity> findAllBookDetailEntry();

  /**
   * find the book by bookId.
   *
   * @param bookId representing the ISBN like unique identifier for the book
   * @return
   */
  Optional<BookDetailEntity> findBookById(Integer bookId);

  /**
   * This method will create the entry in database table, if the bookId already present, it will just response with
   * existing entry in the book detail table
   *
   * @param bookDetailAddRequest
   * @return
   */
  BookDetailEntity createBookDetailEntry(BookDetailAddRequest bookDetailAddRequest);

  /**
   * Delete the book detail entry from table if present
   * @param bookId
   * @throws BookNotFoundException
   */
  void deleteBookDetailEntry(Integer bookId) throws BookNotFoundException;

  /**
   * Update the existing entry in table by bookId
   * @param bookId
   * @param bookDetailUpdateRequest
   * @return
   * @throws BookNotFoundException
   */

  BookDetailEntity updateBookDetailEntry(Integer bookId, BookDetailUpdateRequest bookDetailUpdateRequest)
    throws BookNotFoundException;
}
