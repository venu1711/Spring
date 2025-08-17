package com.venu.database.controllers;

import com.venu.database.domain.dto.AuthorDto;
import com.venu.database.domain.dto.BookDto;
import com.venu.database.domain.entities.AuthorEntity;
import com.venu.database.domain.entities.BookEntity;
import com.venu.database.mappers.Mapper;
import com.venu.database.services.BookService;
import org.hibernate.bytecode.internal.bytebuddy.PassThroughInterceptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {
    private final BookService bookService;
    Mapper<BookEntity, BookDto> bookMapper;

    public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping(path = "/books")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBook = bookService.createBook(bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBook);
        return new ResponseEntity<>(savedBookDto,HttpStatus.CREATED);
    }

    @GetMapping(path = "/books")
    public List<BookDto> getAllBooks() {
        List<BookEntity> bookEntities = bookService.getAllBooks();
        return bookEntities.stream()
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }
}



//Build controller then DTOs, then mappers, then services, then repositories