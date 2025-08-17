package com.venu.database;

import com.venu.database.domain.entities.AuthorEntity;
import com.venu.database.domain.entities.BookEntity;

public final class TestDataUtil {

    private TestDataUtil(){
    }


    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
//                .id(1L)
                .name("Venu Madhav")
                .age(20)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
//                .id(2L)
                .name("Abhiram")
                .age(52)
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
//                .id(3L)
                .name("Arun Kaushik")
                .age(70)
                .build();
    }

    public static BookEntity createTestBookA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("1A")
                .title("Harry Potter")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("1B")
                .title("Percy jackson")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("1C")
                .title("The Great Gatsby")
                .authorEntity(authorEntity)
                .build();
    }
}
