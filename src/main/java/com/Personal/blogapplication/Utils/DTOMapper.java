package com.Personal.blogapplication.Utils;

import com.Personal.blogapplication.Dtos.BookDTO;
import com.Personal.blogapplication.Entity.Book;
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
}
