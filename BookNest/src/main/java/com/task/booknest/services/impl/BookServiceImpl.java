package com.task.booknest.services.impl;

import com.task.booknest.domains.dtos.book.CreateBookDto;
import com.task.booknest.domains.models.Author;
import com.task.booknest.domains.models.Book;
import com.task.booknest.exceptions.HttpError;
import com.task.booknest.respositories.BookRepository;
import com.task.booknest.respositories.GenreRepository;
import com.task.booknest.services.contract.AuthorService;
import com.task.booknest.services.contract.BookService;
import com.task.booknest.services.contract.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final GenreService genreService;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, GenreService genreService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @Override
    public Book createBook(CreateBookDto bookDto, String photo) {
       try{
           Book existBook = bookRepository.findByTitle(bookDto.getTitle());
           if(existBook != null){
               throw new HttpError(HttpStatus.CONFLICT,"Book already exists");
           }

           Book newBook = modelMapper.map(bookDto, Book.class);
           newBook.setCoverImage(photo);
           newBook.setGenre(genreService.findById(bookDto.getGenreId()));
           newBook.setAuthors(authorService.findAllAuthorsById(bookDto.getAuthorIds()));

           return bookRepository.save(newBook);
       }catch (HttpError e){
           throw e;
       }
    }

    @Override
    public List<Book> findAllBooks() {
        List<Book>books = bookRepository.findAll();
        return books;
    }

    @Override
    public Book findBookById(UUID id) {
        try{
            Book book = bookRepository.findById(id).orElse(null);
            if(book == null)
                throw new HttpError(HttpStatus.NOT_FOUND, "Book not exist");

            return book;
        }catch (HttpError e){
            throw e;
        }
    }
}
