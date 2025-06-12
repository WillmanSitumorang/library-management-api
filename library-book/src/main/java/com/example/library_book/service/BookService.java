package com.example.library_book.service;

import com.example.library_book.model.Book;
import com.example.library_book.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Page<Book> getBooks(int page, int size, String title) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());

        if (title != null && !title.isBlank()) {
            return repo.findByTitleContainingIgnoreCase(title, pageable);
        } else {
            return repo.findAll(pageable);
        }
    }

    public Book updateBook(Long id, Book updated) {
        Book existing = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        existing.setTitle(updated.getTitle());
        existing.setAuthor(updated.getAuthor());
        existing.setYear(updated.getYear());
        existing.setStock(updated.getStock());

        return repo.save(existing);
    }

    public Book deleteBook(Long id) {
        Book book = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        repo.delete(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Book saveBook(Book book) {
        return repo.save(book);
    }

    public Book getBookById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }
}