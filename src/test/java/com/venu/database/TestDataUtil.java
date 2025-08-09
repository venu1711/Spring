package com.venu.database;

import com.venu.database.domain.Author;
import com.venu.database.domain.Book;

public final class TestDataUtil {

    private TestDataUtil(){
    }


    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Venu Madhav")
                .age(20)
                .build();
    }


    public static Book createTestBook() {
        return Book.builder()
                .isbn("1A")
                .title("Harry Potter")
                .authorId(1L)
                .build();
    }
}
