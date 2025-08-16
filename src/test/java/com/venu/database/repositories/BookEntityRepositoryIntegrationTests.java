package com.venu.database.repositories;


import com.venu.database.TestDataUtil;
import com.venu.database.domain.Author;
import com.venu.database.domain.Book;
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
public class BookRepositoryIntegrationTests {

    private BookRepository underTest;
    private AuthorRepository authorRepository;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;

        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorRepository.save(author);

        Book book = TestDataUtil.createTestBookA(author);
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        Book savedBook = result.get();
        assertThat(savedBook).isEqualTo(book);
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getAuthor().getName()).isEqualTo(author.getName());
        assertThat(savedBook.getAuthor().getAge()).isEqualTo(author.getAge());
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author1 = TestDataUtil.createTestAuthorA();
        authorRepository.save(author1);
        Book book1= TestDataUtil.createTestBookA(author1);
        underTest.save(book1);

        Book book2 = TestDataUtil.createTestBookB(author1);
        underTest.save(book2);

        Book book3 = TestDataUtil.createTestBookC(author1);
        underTest.save(book3);

        Iterable<Book> results = underTest.findAll();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(book1,book2,book3);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author author = TestDataUtil.createTestAuthorA();
        authorRepository.save(author);
        Book book = TestDataUtil.createTestBookA(author);
        book.setTitle("Max Verstappen");
        underTest.save(book);

        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorB();
        authorRepository.save(author);
        Book book = TestDataUtil.createTestBookB(author);
        underTest.save(book);
        underTest.deleteById(book.getIsbn());
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isNotPresent();
    }
}
