package com.venu.database.repositories;


import com.venu.database.TestDataUtil;
import com.venu.database.domain.entities.AuthorEntity;
import com.venu.database.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityRepositoryIntegrationTests {

    private BookRepository underTest;
    private AuthorRepository authorRepository;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository underTest, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;

        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorRepository.save(authorEntity);

        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        BookEntity savedBookEntity = result.get();
        assertThat(savedBookEntity).isEqualTo(bookEntity);
        assertThat(savedBookEntity.getTitle()).isEqualTo(bookEntity.getTitle());
        assertThat(savedBookEntity.getAuthorEntity().getName()).isEqualTo(authorEntity.getName());
        assertThat(savedBookEntity.getAuthorEntity().getAge()).isEqualTo(authorEntity.getAge());
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        AuthorEntity authorEntity1 = TestDataUtil.createTestAuthorA();
        authorRepository.save(authorEntity1);
        BookEntity bookEntity1 = TestDataUtil.createTestBookA(authorEntity1);
        underTest.save(bookEntity1);

        BookEntity bookEntity2 = TestDataUtil.createTestBookB(authorEntity1);
        underTest.save(bookEntity2);

        BookEntity bookEntity3 = TestDataUtil.createTestBookC(authorEntity1);
        underTest.save(bookEntity3);

        Iterable<BookEntity> results = underTest.findAll();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(bookEntity1, bookEntity2, bookEntity3);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        authorRepository.save(authorEntity);
        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        bookEntity.setTitle("Max Verstappen");
        underTest.save(bookEntity);

        underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorB();
        authorRepository.save(authorEntity);
        BookEntity bookEntity = TestDataUtil.createTestBookB(authorEntity);
        underTest.save(bookEntity);
        underTest.deleteById(bookEntity.getIsbn());
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isNotPresent();
    }
}
