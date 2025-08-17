package com.venu.database.repositories;

import com.venu.database.TestDataUtil;
import com.venu.database.domain.entities.AuthorEntity;
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
public class AuthorEntityRepositoryIntegrationTests {

    private AuthorRepository underTest;

    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        underTest.save(authorEntity);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        assertThat(result).isPresent();
        AuthorEntity savedAuthorEntity = result.get();
        assertThat(savedAuthorEntity).isEqualTo(authorEntity);
        assertThat(savedAuthorEntity.getName()).isEqualTo(authorEntity.getName());
        assertThat(savedAuthorEntity.getAge()).isEqualTo(authorEntity.getAge());
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        AuthorEntity authorEntity1 = TestDataUtil.createTestAuthorA();
        AuthorEntity authorEntity2 = TestDataUtil.createTestAuthorB();
        AuthorEntity authorEntity3 = TestDataUtil.createTestAuthorC();
        underTest.save(authorEntity1);
        underTest.save(authorEntity2);
        underTest.save(authorEntity3);

        Iterable<AuthorEntity> results = underTest.findAll();
        assertThat(results).hasSize(3);
        assertThat(results).containsExactly(authorEntity1, authorEntity2, authorEntity3);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        underTest.save(authorEntity);
        authorEntity.setName("UPDATED");
        underTest.save(authorEntity);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorB();
        underTest.save(authorEntity);
        underTest.deleteById(authorEntity.getId());
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        assertThat(result).isNotPresent();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        AuthorEntity authorEntity1 = TestDataUtil.createTestAuthorA();
        AuthorEntity authorEntity2 = TestDataUtil.createTestAuthorB();
        AuthorEntity authorEntity3 = TestDataUtil.createTestAuthorC();
        underTest.save(authorEntity1);
        underTest.save(authorEntity2);
        underTest.save(authorEntity3);

        Iterable<AuthorEntity> results = underTest.ageLessThan(60);
        assertThat(results).containsExactlyInAnyOrder(authorEntity1, authorEntity2);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        AuthorEntity authorEntity1 = TestDataUtil.createTestAuthorA();
        AuthorEntity authorEntity2 = TestDataUtil.createTestAuthorB();
        AuthorEntity authorEntity3 = TestDataUtil.createTestAuthorC();
        underTest.save(authorEntity1);
        underTest.save(authorEntity2);
        underTest.save(authorEntity3);

        Iterable<AuthorEntity> results = underTest.findAuthorsWhoseAgeIsGreaterThan(60);
        assertThat(results).contains(authorEntity3);
    }
}
