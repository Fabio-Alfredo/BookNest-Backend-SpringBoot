package com.task.booknest.services.contract;

import com.task.booknest.domains.dtos.book.CreateBookDto;
import com.task.booknest.domains.models.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    Book createBook(CreateBookDto bookDto);
    List<Book>findAllBooks();
    Book findBookById(UUID id);
}
