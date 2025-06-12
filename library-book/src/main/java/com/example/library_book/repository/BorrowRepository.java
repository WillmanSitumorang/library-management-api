package com.example.library_book.repository;

import com.example.library_book.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByMemberIdAndReturnDateIsNull(Long memberId);
}
