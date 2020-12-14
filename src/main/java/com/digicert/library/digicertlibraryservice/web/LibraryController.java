package com.digicert.library.digicertlibraryservice.web;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digicert.library.digicertlibraryservice.BookNotFoundException;
import com.digicert.library.digicertlibraryservice.api.LibraryService;
import com.digicert.library.digicertlibraryservice.db.BookDetailEntity;
import com.digicert.library.digicertlibraryservice.model.BookDetailAddRequest;
import com.digicert.library.digicertlibraryservice.model.BookDetailUpdateRequest;

@Slf4j
@RestController
@RequestMapping(value = "/library")
public class LibraryController {

  private LibraryService libraryService;

  public LibraryController(LibraryService libraryService) {
    this.libraryService = libraryService;
  }

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity createBookDetailEntry(@RequestBody BookDetailAddRequest bookDetailAddRequest) {
    log.info("Request received to add book entry in system. {}", bookDetailAddRequest);
    BookDetailEntity bookEntity = libraryService.createBookDetailEntry(bookDetailAddRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(bookEntity);
  }

  @GetMapping(value = "/find/{bookId}")
  public ResponseEntity findBookDetailEntry(@PathVariable("bookId") Integer bookId) {
    Optional<BookDetailEntity> optionalBookEntity = libraryService.findBookById(bookId);
    if (optionalBookEntity.isPresent()) {
      return ResponseEntity.ok(optionalBookEntity.get());
    }
    String message = String.format("Book not found in system for bookId {%s}", bookId);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }

  @PutMapping(value = "/update/{bookId}")
  public ResponseEntity updateBookDetailEntry(@PathVariable("bookId") Integer bookId,
    @RequestBody BookDetailUpdateRequest updateBookDetailRequest) {
    log.info("Request received to update the book detail for bookId={}", bookId);
    BookDetailEntity bookDetailEntry = null;
    try {
      bookDetailEntry = libraryService.updateBookDetailEntry(bookId, updateBookDetailRequest);
    }
    catch (BookNotFoundException e) {
      String errorMessage = String.format("BookId %s not found in system", bookId);
      return ResponseEntity.badRequest().body(errorMessage);
    }
    return ResponseEntity.ok(bookDetailEntry);
  }

  @DeleteMapping(path = "/delete/{bookId}")
  public ResponseEntity deleteBookDetailEntry(@PathVariable("bookId") Integer bookId) {
    try {
      libraryService.deleteBookDetailEntry(bookId);
    }
    catch (BookNotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity findAll() {
    return ResponseEntity.ok(libraryService.findAllBookDetailEntry());
  }
}
