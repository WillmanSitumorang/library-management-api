package com.example.library_book.service;

import com.example.library_book.dto.BorrowRequest;
import com.example.library_book.model.Book;
import com.example.library_book.model.Borrow;
import com.example.library_book.model.Member;
import com.example.library_book.repository.BookRepository;
import com.example.library_book.repository.BorrowRepository;
import com.example.library_book.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private MemberRepository memberRepo;

    public ResponseEntity<String> borrowBook(BorrowRequest request) {
        Optional<Member> memberOpt = memberRepo.findById(request.getMemberId());
        Optional<Book> bookOpt = bookRepo.findById(request.getBookId());

        if (memberOpt.isEmpty() || bookOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book book = bookOpt.get();
        if (book.getStock() <= 0) {
            return ResponseEntity.badRequest().body("Book is out of stock.");
        }

        Optional<Borrow> existingBorrow = borrowRepo.findByMemberIdAndBookId(
                request.getMemberId(), request.getBookId());

        if (existingBorrow.isPresent()) {
            return ResponseEntity.badRequest().body("This book is already borrowed and not yet returned.");
        }

        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setMember(memberOpt.get());

        borrowRepo.save(borrow);

        book.setStock(book.getStock() - 1);
        bookRepo.save(book);

        return ResponseEntity.ok("Book borrowed successfully.");
    }

    public ResponseEntity<String> returnBook(BorrowRequest request) {
        Optional<Borrow> borrowOpt = borrowRepo.findByMemberIdAndBookId(
                request.getMemberId(), request.getBookId());

        if (borrowOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("No active borrowing record found.");
        }

        Borrow borrow = borrowOpt.get();
        borrowRepo.save(borrow);

        Book book = borrow.getBook();
        book.setStock(book.getStock() + 1);
        bookRepo.save(book);

        return ResponseEntity.ok("Book returned successfully.");
    }
}
