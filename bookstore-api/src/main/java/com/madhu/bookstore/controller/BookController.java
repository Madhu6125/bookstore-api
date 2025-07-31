package com.madhu.bookstore.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import com.madhu.bookstore.dto.BookDto;
import com.madhu.bookstore.entity.Author;
import com.madhu.bookstore.entity.Book;
import com.madhu.bookstore.repository.AuthorRepository;
import com.madhu.bookstore.repository.BookRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        if (bookDto.getAuthorId() == null) {
            return ResponseEntity.badRequest().body("Author ID must not be null");
        }

        Optional<Author> authorOpt = authorRepository.findById(bookDto.getAuthorId());
        if (authorOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Author not found with ID: " + bookDto.getAuthorId());
        }

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());
        book.setAuthor(authorOpt.get());

        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }
    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book, @RequestParam Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            book.setAuthor(author.get());
            return ResponseEntity.ok(bookRepository.save(book));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        if (bookDto.getAuthorId() == null) {
            return ResponseEntity.badRequest().body("Author ID must not be null");
        }

        Optional<Author> authorOpt = authorRepository.findById(bookDto.getAuthorId());
        if (authorOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Author not found with ID: " + bookDto.getAuthorId());
        }

        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDto.getTitle());
            book.setPrice(bookDto.getPrice());
            book.setAuthor(authorOpt.get());
            return ResponseEntity.ok(bookRepository.save(book));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}