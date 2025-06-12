package com.example.library_book.controller;

import com.example.library_book.model.Book;
import com.example.library_book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Page<Book> getBooks(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String title
    ){
        return bookService.getBooks(page, size, title);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,  @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        var book = bookService.deleteBook(id);

//        return ResponseEntity.ok().build();
        return "Berhasil menghapus buku dengan Judul: " + book.getTitle();
    }
}
