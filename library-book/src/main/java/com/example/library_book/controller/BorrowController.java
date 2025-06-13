package com.example.library_book.controller;

import com.example.library_book.dto.BorrowRequest;
import com.example.library_book.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestBody BorrowRequest request) {
        return borrowService.borrowBook(request);
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody BorrowRequest request) {
        return borrowService.returnBook(request);
    }
}
