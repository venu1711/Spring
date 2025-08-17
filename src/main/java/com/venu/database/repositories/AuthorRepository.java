package com.venu.database.repositories;

import com.venu.database.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    Iterable<AuthorEntity> ageLessThan(int age);

    @Query("select a FROM AuthorEntity a WHERE a.age>?1") //HQL query
    Iterable<AuthorEntity> findAuthorsWhoseAgeIsGreaterThan(int age);
}
