package com.venu.database.repositories;

import com.venu.database.TestDataUtil;
import com.venu.database.domain.Author;
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
public class AuthorDaoImplIntegrationTests {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        Author author1 = TestDataUtil.createTestAuthorA();
        Author author2 = TestDataUtil.createTestAuthorB();
        Author author3 = TestDataUtil.createTestAuthorC();
        underTest.create(author1);
        underTest.create(author2);
        underTest.create(author3);

        List<Author> results = underTest.findMany();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(author1, author2, author3);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.create(author);
        author.setName("UPDATED");
        underTest.update(author.getId(),author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorB();
        underTest.create(author);
        underTest.delete(author.getId());
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isNotPresent();
    }
}
