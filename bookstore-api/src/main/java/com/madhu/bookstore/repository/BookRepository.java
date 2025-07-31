package com.madhu.bookstore.repository;

import com.madhu.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // You already get paging, sorting, and basic CRUD from JpaRepository

    // Optional filter methods for future expansion:
    // List<Book> findByTitleContainingIgnoreCase(String title);
    // List<Book> findByAuthor_NameContainingIgnoreCase(String name);
}