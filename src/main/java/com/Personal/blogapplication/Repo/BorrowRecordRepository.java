package com.Personal.blogapplication.Repo;

import com.Personal.blogapplication.Entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByMemberId(Long memberId);
}
