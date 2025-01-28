package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.BookDTO;
import com.Personal.blogapplication.Entity.Book;
import com.Personal.blogapplication.Repo.BookRepository;
import com.Personal.blogapplication.Utils.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private DTOMapper dtoMapper;

    public BookDTO addBook(BookDTO bookDTO) {
        Book book = dtoMapper.toBookEntity(bookDTO);
        return dtoMapper.toBookDTO(bookRepository.save(book));
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(dtoMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return dtoMapper.toBookDTO(book); // Use dtoMapper instance
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setPublicationYear(bookDTO.getPublicationYear());
        existingBook.setGenre(bookDTO.getGenre());
        BookDTO updatedBooks = dtoMapper.toBookDTO(bookRepository.save(existingBook));
        return updatedBooks;
    }
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
