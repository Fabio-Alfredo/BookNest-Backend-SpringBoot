package com.task.booknest.services.contract;

import com.task.booknest.domains.dtos.book.CreateBookDto;
import com.task.booknest.domains.models.Book;

import java.util.List;

public interface BookService {
    Book createBook(CreateBookDto bookDto);
    List<Book>findAllBooks();
    Book findBookById(String id);
}
