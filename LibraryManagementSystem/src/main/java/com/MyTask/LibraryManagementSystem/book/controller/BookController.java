package com.MyTask.LibraryManagementSystem.book.controller;

import com.MyTask.LibraryManagementSystem.book.dto.AddBookRequest;
import com.MyTask.LibraryManagementSystem.book.dto.BookResponse;
import com.MyTask.LibraryManagementSystem.book.dto.UpdateBookRequest;
import com.MyTask.LibraryManagementSystem.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @GetMapping("/title")
    public ResponseEntity<BookResponse> getBookByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @GetMapping("/author")
    public ResponseEntity<List<BookResponse>> getBooksByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAllBooks(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody AddBookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(request));
    }

    @PatchMapping("/update/{bookId}")
    public ResponseEntity<BookResponse> updateBookById(@PathVariable Long bookId, @RequestBody UpdateBookRequest request) {
        return ResponseEntity.ok(bookService.updateBookById(bookId, request));
    }
}
