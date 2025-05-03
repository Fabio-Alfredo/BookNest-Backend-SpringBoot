package com.task.booknest.controllers.book;

import com.task.booknest.domains.dtos.GeneralResponse;
import com.task.booknest.domains.dtos.book.CreateBookDto;
import com.task.booknest.domains.models.Book;
import com.task.booknest.exceptions.HttpError;
import com.task.booknest.services.contract.BookService;
import com.task.booknest.services.impl.CloudinaryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
public class BootController {

    private final BookService bookService;
    private final CloudinaryService cloudinaryService;

    public BootController(BookService bookService, CloudinaryService cloudinaryService) {
        this.bookService = bookService;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createBoot(@ModelAttribute CreateBookDto bookDto){
        try {
            String image = cloudinaryService.uploadImage(bookDto.getFile(), "books");
            Book book =bookService.createBook(bookDto, image);
            return GeneralResponse.getResponse(HttpStatus.CREATED, "Create book success", book);
        }catch (HttpError e){
            return GeneralResponse.getResponse(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse>findAllBooks(){
        try{
            List<Book>books = bookService.findAllBooks();
            return GeneralResponse.getResponse(HttpStatus.OK, "success", books);
        }catch (Exception e){
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Error while fin all books");
        }
    }

    @GetMapping("/by-id/{bookId}")
    public ResponseEntity<GeneralResponse>findBookById(@PathVariable UUID bookId){
        try{
            Book book = bookService.findBookById(bookId);
            return GeneralResponse.getResponse(HttpStatus.OK, "success", book);
        }catch (HttpError e){
            return GeneralResponse.getResponse(e.getHttpStatus(), e.getMessage());
        }
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<GeneralResponse>deleteOneBook(@PathVariable UUID bookId){
        try {
            bookService.deleteOneBook(bookId);
            return GeneralResponse.getResponse(HttpStatus.OK, "Book deleted");
        }catch (HttpError e){
            return GeneralResponse.getResponse(e.getHttpStatus(), e.getMessage());
        }
    }
}
