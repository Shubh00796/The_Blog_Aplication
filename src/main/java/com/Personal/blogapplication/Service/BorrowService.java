package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.BorrowDTO;
import com.Personal.blogapplication.Entity.BorrowRecord;
import com.Personal.blogapplication.Factory.BorrowRecordFactory;
import com.Personal.blogapplication.Repo.BorrowRecordRepository;
import com.Personal.blogapplication.Utils.DTOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BorrowService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BorrowRecordFactory borrowRecordFactory;

    @Autowired
    private DTOMapper dtoMapper;

    public BorrowDTO borrowBook(Long bookId, Long memberId) {
        List<BorrowRecord> activeBorrows = borrowRecordRepository.findByBookIdAndReturnDateIsNull(bookId);
        if (!activeBorrows.isEmpty()) {
            throw new IllegalArgumentException("Book is already borrowed.");
        }
        BorrowRecord borrowRecord = borrowRecordFactory.createBorrowRecord(bookId, memberId);
        BorrowRecord savedRecord = borrowRecordRepository.save(borrowRecord);
        return dtoMapper.toBorrowDTO(savedRecord);
    }

    public BorrowDTO returnBook(Long borrowRecordId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId)
                .orElseThrow(() -> new IllegalArgumentException("Borrow record not found"));

        if (borrowRecord.getReturnDate() != null) {
            throw new IllegalArgumentException("Book is already returned.");

        }
        borrowRecord.setReturnDate(LocalDate.now());
        BorrowRecord savedRecords = borrowRecordRepository.save(borrowRecord);
        return dtoMapper.toBorrowDTO(savedRecords);
    }
    public List<BorrowDTO> getBorrowRecordsByMember(Long memberId) {
        return borrowRecordRepository.findByMemberId(memberId).stream()
                .map(dtoMapper::toBorrowDTO)
                .collect(Collectors.toList());
    }
}
