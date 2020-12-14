package com.digicert.library.digicertlibraryservice.api;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digicert.library.digicertlibraryservice.db.BookDetailEntity;
import com.digicert.library.digicertlibraryservice.db.BookDetailRepository;
import com.digicert.library.digicertlibraryservice.exception.BookNotFoundException;
import com.digicert.library.digicertlibraryservice.model.BookDetailAddRequest;
import com.digicert.library.digicertlibraryservice.model.BookDetailUpdateRequest;

@Slf4j
@Service
public class LibraryServiceImpl implements LibraryService {

  private BookDetailRepository bookDetailRepository;

  LibraryServiceImpl(BookDetailRepository bookDetailRepository) {
    this.bookDetailRepository = bookDetailRepository;
  }

  @Override
  public Iterable<BookDetailEntity> findAllBookDetailEntry() {
    return bookDetailRepository.findAll();
  }

  @Override
  public Optional<BookDetailEntity> findBookById(Integer bookId) {
    return bookDetailRepository.findById(bookId);
  }

  @Override
  public BookDetailEntity createBookDetailEntry(BookDetailAddRequest bookDetailAddRequest) {
    if (bookDetailRepository.existsById(bookDetailAddRequest.getBookId())) {
      log.info("Book Detail already present in system  for bookId={}", bookDetailAddRequest.getBookId());
      return bookDetailRepository.findById(bookDetailAddRequest.getBookId()).get();
    }
    BookDetailEntity bookDetailEntity = BookDetailEntity.builder()
      .bookId(bookDetailAddRequest.getBookId())
      .bookTitle(bookDetailAddRequest.getBookTitle())
      .writer(bookDetailAddRequest.getWriter())
      .createDate(new Date().toString()).build();
    bookDetailRepository.save(bookDetailEntity);
    log.info("Created the Book entry in system for bookId={}", bookDetailEntity.getBookId());
    return bookDetailEntity;
  }

  @Override
  public void deleteBookDetailEntry(Integer bookId) throws BookNotFoundException {
    try {
      bookDetailRepository.deleteById(bookId);
      log.info("Book detail entry is deleted for bookId={}", bookId);
    }
    catch (Exception e) {
      String error = String.format("Book detail entry is not present in system for bookId=%s", bookId);
      throw new BookNotFoundException(error);
    }
  }

  @Override
  public BookDetailEntity updateBookDetailEntry(Integer bookId, BookDetailUpdateRequest bookDetailUpdateRequest)
    throws BookNotFoundException {
    Optional<BookDetailEntity> optionalBookDetailEntity = bookDetailRepository.findById(bookId);
    BookDetailEntity bookDetailEntity;
    if (optionalBookDetailEntity.isPresent()) {
      bookDetailEntity = optionalBookDetailEntity.get();
      bookDetailEntity.setBookTitle(bookDetailUpdateRequest.getBookTitle());
      bookDetailEntity.setWriter(bookDetailUpdateRequest.getWriter());
      bookDetailRepository.save(bookDetailEntity);
      log.info("Updated the book detail for bookId={}", bookId);
    }
    else {
      throw new BookNotFoundException(String.format("Book detail entry not found in system for BookId=%s", bookId));
    }
    return bookDetailEntity;
  }
}
