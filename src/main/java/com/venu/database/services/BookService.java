package com.venu.database.services;

import com.venu.database.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createBook(BookEntity bookEntity);

    List<BookEntity> getAllBooks();

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookEntity update(String isbn, BookEntity bookEntity);
}
