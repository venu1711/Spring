package com.venu.database.dao;

import com.venu.database.domain.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String bookId);
}
