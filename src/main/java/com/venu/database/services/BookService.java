package com.venu.database.services;

import com.venu.database.domain.entities.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity createBook(BookEntity bookEntity);

    List<BookEntity> getAllBooks();
}
