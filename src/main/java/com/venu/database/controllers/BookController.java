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
import java.util.Optional;
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

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable String isbn){
        Optional<BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> updateBook(
            @PathVariable String isbn,
            @RequestBody BookDto bookDto) {


        if(!bookService.isExists(isbn)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity updatedBook = bookService.update(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(updatedBook),HttpStatus.OK);
    }
}



//Build controller then DTOs, then mappers, then services, then repositories