package com.example.library_book.repository;

import com.example.library_book.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Optional<Borrow> findByMemberIdAndBookId(Long memberId, Long bookId);
    List<Borrow> findByMemberId(Long memberId);
}
