package com.venu.database.repositories;

/**
import com.venu.database.TestDataUtil;
import com.venu.database.dao.AuthorDao;
import com.venu.database.domain.Author;
import com.venu.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {

    private AuthorDao authorDao;
    private BookDaoImpl underTest;

    @Autowired
    public BookDaoImplIntegrationTests(BookDaoImpl underTest,AuthorDao authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);
        Book book = TestDataUtil.createTestBookA();
        book.setAuthorId(author.getId());
        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author1 = TestDataUtil.createTestAuthorA();
        authorDao.create(author1);
        Book book1= TestDataUtil.createTestBookA();
        book1.setAuthorId(author1.getId());
        underTest.create(book1);

//        Author author2 = TestDataUtil.createTestAuthorB();
//        authorDao.create(author2);
        Book book2 = TestDataUtil.createTestBookB();
        book2.setAuthorId(author1.getId());
        underTest.create(book2);

//        Author author3 = TestDataUtil.createTestAuthorC();
//        authorDao.create(author3);
        Book book3 = TestDataUtil.createTestBookC();
        book3.setAuthorId(author1.getId());
        underTest.create(book3);

        List<Book> results = underTest.findMany();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(book1,book2,book3);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);
        Book book = TestDataUtil.createTestBookA();
        book.setTitle("Max Verstappen");
        underTest.create(book);

        underTest.update(book.getIsbn(), book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorB();
        authorDao.create(author);
        Book book = TestDataUtil.createTestBookB();
        book.setAuthorId(author.getId());
        underTest.create(book);
        underTest.delete(book.getIsbn());
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isNotPresent();
    }
}
**/