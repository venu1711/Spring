package com.venu.database.dao.impl;


import com.venu.database.TestDataUtil;
import com.venu.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBook();
        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn,title,author_id) VALUES (?,?,?)"),
                eq("1A"),eq("Harry Potter"),eq(1L)
        );
    }

    @Test
    public void testThatFindOneGeneratesCorrectSql(){
        underTest.findOne("1A");

        verify(jdbcTemplate).query(
                eq("SELECT isbn,title,author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq(("1A"))
        );

    }
}
