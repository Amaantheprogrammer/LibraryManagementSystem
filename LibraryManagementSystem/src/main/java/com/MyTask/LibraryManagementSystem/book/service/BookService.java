package com.MyTask.LibraryManagementSystem.book.service;

import com.MyTask.LibraryManagementSystem.book.dto.AddBookRequest;
import com.MyTask.LibraryManagementSystem.book.dto.BookResponse;
import com.MyTask.LibraryManagementSystem.book.dto.UpdateBookRequest;
import com.MyTask.LibraryManagementSystem.book.entity.Book;
import com.MyTask.LibraryManagementSystem.book.repository.BookRepository;
import com.MyTask.LibraryManagementSystem.exception.FieldAlreadyExists;
import com.MyTask.LibraryManagementSystem.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public BookResponse getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));
        return modelMapper.map(book, BookResponse.class);
    }

    @Transactional(readOnly = true)
    public BookResponse getBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with title: " + title));
        return modelMapper.map(book, BookResponse.class);
    }

    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return books.stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> getAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(book -> modelMapper.map(book, BookResponse.class));
    }

    @Transactional
    public BookResponse addBook(AddBookRequest request) {
        if (bookRepository.existsByTitle(request.getTitle())) {
            throw new FieldAlreadyExists("Book already exists with title: " + request.getTitle());
        }
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .description(request.getDescription())
                .build();
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookResponse.class);
    }

    @Transactional
    public BookResponse updateBookById(Long bookId, UpdateBookRequest request) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));
        if (request.getTitle() != null
                && !request.getTitle().equals(book.getTitle())
                && bookRepository.existsByTitle(request.getTitle())) {
            throw new FieldAlreadyExists(
                    "Book already exists with title: " + request.getTitle());
        }
        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }
        if (request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }
        if (request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookResponse.class);
    }

}
