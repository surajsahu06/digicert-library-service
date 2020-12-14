package com.digicert.library.digicertlibraryservice.api;

import java.util.Optional;

import com.digicert.library.digicertlibraryservice.BookNotFoundException;
import com.digicert.library.digicertlibraryservice.db.BookDetailEntity;
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
   * This method will update the qty of book if the entry is already present in database table, else create the new
   * entry for the book
   *
   * @param addBookRequest
   * @return
   */
  BookDetailEntity createBookDetailEntry(BookDetailAddRequest addBookRequest);

  void deleteBookDetailEntry(Integer bookId) throws BookNotFoundException;

  BookDetailEntity updateBookDetailEntry(Integer bookId, BookDetailUpdateRequest bookDetailUpdateRequest)
    throws BookNotFoundException;
}
