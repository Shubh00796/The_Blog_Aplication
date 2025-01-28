package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.BorrowDTO;
import com.Personal.blogapplication.Service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    // Borrow a book
    @PostMapping
    public ResponseEntity<BorrowDTO> borrowBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        BorrowDTO borrowDTO = borrowService.borrowBook(bookId, memberId);
        return new ResponseEntity<>(borrowDTO, HttpStatus.CREATED);
    }

    // Return a book
    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowDTO> returnBook(@PathVariable Long id) {
        BorrowDTO borrowDTO = borrowService.returnBook(id);
        return new ResponseEntity<>(borrowDTO, HttpStatus.OK);
    }

    // Get all borrow records for a member
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<BorrowDTO>> getBorrowRecordsByMember(@PathVariable Long memberId) {
        List<BorrowDTO> borrowRecords = borrowService.getBorrowRecordsByMember(memberId);
        return new ResponseEntity<>(borrowRecords, HttpStatus.OK);
    }
}