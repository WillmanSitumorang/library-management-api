package com.example.library_book.controller;

import com.example.library_book.model.Book;
import com.example.library_book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String title
    ){
        return service.getAllBooks(page, size, title);
    }
}
