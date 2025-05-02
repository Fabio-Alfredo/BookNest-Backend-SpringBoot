package com.task.booknest.services.impl;

import com.task.booknest.domains.dtos.book.CreateBookDto;
import com.task.booknest.domains.models.Book;
import com.task.booknest.exceptions.HttpError;
import com.task.booknest.respositories.BookRepository;
import com.task.booknest.respositories.GenreRepository;
import com.task.booknest.services.contract.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Book createBook(CreateBookDto bookDto) {
       try{
           Book existBook = bookRepository.findByTitle(bookDto.getTitle());
           if(existBook != null){
               throw new HttpError(HttpStatus.CONFLICT,"Book already exists");
           }

           Book newBook = modelMapper.map(bookDto, Book.class);
           //TODO: Falta buscar el genero y asignarlo al libro
           return bookRepository.save(newBook);
       }catch (HttpError e){
           throw e;
       }
    }

    @Override
    public List<Book> findAllBooks() {
        return List.of();
    }

    @Override
    public Book findBookById(String id) {
        return null;
    }
}
