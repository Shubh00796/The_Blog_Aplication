package com.Personal.blogapplication.Utils;

import com.Personal.blogapplication.Dtos.BookDTO;
import com.Personal.blogapplication.Dtos.BorrowDTO;
import com.Personal.blogapplication.Dtos.MemberDTO;
import com.Personal.blogapplication.Dtos.StudentDTO;
import com.Personal.blogapplication.Entity.Book;
import com.Personal.blogapplication.Entity.BorrowRecord;
import com.Personal.blogapplication.Entity.Member;
import com.Personal.blogapplication.Entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DTOMapper {

    public BookDTO toBookDTO(Book book){
        log.info("getting the details of the book", book);
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genre(book.getGenre())
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .publicationYear(book.getPublicationYear())
                .build();
    }
    public Book toBookEntity(BookDTO bookDTO){
        log.info("getting the details of the book", bookDTO);
        return Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .genre(bookDTO.getGenre())
                .isbn(bookDTO.getGenre())
                .publicationYear(bookDTO.getPublicationYear())
                .build();


    }

    public Member toMemberEntity(MemberDTO memberDTO){
        log.info("getting the details of the book", memberDTO);

        return Member.builder()
                .id(memberDTO.getId())
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .membershipDate(memberDTO.getMembershipDate())
                .build();


    }


    public MemberDTO toMemberDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .membershipDate(member.getMembershipDate())
                .build();
    }
    public BorrowDTO toBorrowDTO(BorrowRecord borrowRecord){
        return BorrowDTO.builder()
                .id(borrowRecord.getId())
                .bookId(borrowRecord.getBookId())
                .memberId(borrowRecord.getMemberId())
                .borrowDate(borrowRecord.getBorrowDate())
                .returnDate(borrowRecord.getReturnDate())
                .build();
    }

    public StudentDTO toStudentDTO(Student student){
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .dateOfBirth(student.getDateOfBirth())
                .build();
    }
    public Student toStudentEntity(StudentDTO studentDTO){
        return Student.builder()
                .id(studentDTO.getId())
                .name(studentDTO.getName())
                .email(studentDTO.getEmail())
                .dateOfBirth(studentDTO.getDateOfBirth())
                .build();

    }

}
