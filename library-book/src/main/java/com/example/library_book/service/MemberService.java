package com.example.library_book.service;

import com.example.library_book.model.Book;
import com.example.library_book.model.Borrow;
import com.example.library_book.model.Member;
import com.example.library_book.repository.BorrowRepository;
import com.example.library_book.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepo;
    private final BorrowRepository borrowRepo;

    public MemberService(MemberRepository memberRepo, BorrowRepository borrowRepo){
        this.memberRepo = memberRepo;
        this.borrowRepo = borrowRepo;
    }

    public Member getMemberById(Long id){
        return memberRepo.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    public Member saveMember(Member member){
        return memberRepo.save(member);
    }

    public List<Book> getBorrowedBooksByMemberId(Long memberId) {
        List<Borrow> borrows = borrowRepo.findByMemberId(memberId);
        return borrows.stream()
                .map(Borrow::getBook)
                .toList();
    }
}
