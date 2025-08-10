package com.venu.database.dao.impl;

import com.venu.database.TestDataUtil;
import com.venu.database.domain.Author;
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
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id,name,age) VALUES (?,?,?)"),
                eq(1L),eq("Venu Madhav"),eq(20)
        );
    }

    @Test
    public void testThatFindOneGeneratesCorrectSql(){
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id,name,age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindManyGeneratesCorrectSql() {
        underTest.findMany();

        verify(jdbcTemplate).query(
                eq("SELECT id,name,age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateAuthorGeneratesCorrectSQL(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.update(3L, author);

        verify(jdbcTemplate).update(
                "UPDATE authors SET id=?,name=?,age=? WHERE id=?",
                1L, "Venu Madhav", 20, 3L
        );
    }
}
