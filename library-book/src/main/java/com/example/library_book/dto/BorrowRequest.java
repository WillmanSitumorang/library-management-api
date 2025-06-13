package com.example.library_book.dto;

import lombok.Data;

@Data
public class BorrowRequest {

        private Long memberId;
        private Long bookId;
        // Getters and Setters
}
