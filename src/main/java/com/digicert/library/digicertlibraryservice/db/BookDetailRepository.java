package com.digicert.library.digicertlibraryservice.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailRepository extends CrudRepository<BookDetailEntity, Integer> {
}
