package com.madhu.bookstore.repository;

import com.madhu.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Optionally add:
    // List<Author> findByNameContainingIgnoreCase(String name);
}