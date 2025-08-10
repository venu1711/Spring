package com.venu.database;

import com.venu.database.domain.Author;
import com.venu.database.domain.Book;

public final class TestDataUtil {

    private TestDataUtil(){
    }


    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Venu Madhav")
                .age(20)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Abhiram")
                .age(22)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Arun Kaushik")
                .age(20)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("1A")
                .title("Harry Potter")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("1B")
                .title("Percy jackson")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("1C")
                .title("The Great Gatsby")
                .authorId(1L)
                .build();
    }
}
