package com.task.booknest.respositories;

import com.task.booknest.domains.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Book findByTitle(String title);
}
