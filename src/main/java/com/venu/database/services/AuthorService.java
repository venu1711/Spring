package com.venu.database.services;

import com.venu.database.domain.entities.AuthorEntity;
import com.venu.database.domain.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity update(Long id, AuthorEntity authorEntity);

    void delete(Long id);
}
