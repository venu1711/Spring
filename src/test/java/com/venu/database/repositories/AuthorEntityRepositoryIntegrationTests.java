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
public class AuthorRepositoryIntegrationTests {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        Author savedAuthor = result.get();
        assertThat(savedAuthor).isEqualTo(author);
        assertThat(savedAuthor.getName()).isEqualTo(author.getName());
        assertThat(savedAuthor.getAge()).isEqualTo(author.getAge());
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        Author author1 = TestDataUtil.createTestAuthorA();
        Author author2 = TestDataUtil.createTestAuthorB();
        Author author3 = TestDataUtil.createTestAuthorC();
        underTest.save(author1);
        underTest.save(author2);
        underTest.save(author3);

        Iterable<Author> results = underTest.findAll();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(author1, author2, author3);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        author.setName("UPDATED");
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorB();
        underTest.save(author);
        underTest.deleteById(author.getId());
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isNotPresent();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        Author author1 = TestDataUtil.createTestAuthorA();
        Author author2 = TestDataUtil.createTestAuthorB();
        Author author3 = TestDataUtil.createTestAuthorC();
        underTest.save(author1);
        underTest.save(author2);
        underTest.save(author3);

        Iterable<Author> results = underTest.ageLessThan(60);
        assertThat(results).containsExactlyInAnyOrder(author1, author2);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        Author author1 = TestDataUtil.createTestAuthorA();
        Author author2 = TestDataUtil.createTestAuthorB();
        Author author3 = TestDataUtil.createTestAuthorC();
        underTest.save(author1);
        underTest.save(author2);
        underTest.save(author3);

        Iterable<Author> results = underTest.findAuthorsWhoseAgeIsGreaterThan(60);
        assertThat(results).contains(author3);
    }
}
