package com.example.library_book.controller;

import com.example.library_book.model.Book;
import com.example.library_book.model.Member;
import com.example.library_book.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@Validated
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){this.memberService=memberService;}

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id){
        Member member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member){
        return ResponseEntity.ok(memberService.saveMember(member));
    }

    @GetMapping("/{id}/borrowed-books")
    public ResponseEntity<List<Book>> getBorrowedBooks(@PathVariable Long id) {
        List<Book> borrowedBooks = memberService.getBorrowedBooksByMemberId(id);
        return ResponseEntity.ok(borrowedBooks);
    }
}
