package com.example.library_book.repository;

import com.example.library_book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//    List<Book> findByTitleContaining(String keyword);
}