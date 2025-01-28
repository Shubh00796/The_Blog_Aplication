package com.Personal.blogapplication.Factory;

import com.Personal.blogapplication.Entity.BorrowRecord;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BorrowRecordFactory {

    public BorrowRecord createBorrowRecord(Long bookId , Long memberId){
        return  BorrowRecord.builder()
                .bookId(bookId)
                .memberId(memberId)
                .borrowDate(LocalDate.now())
                .returnDate(null)
                .build();
    }
}
