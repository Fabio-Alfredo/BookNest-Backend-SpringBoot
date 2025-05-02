package com.task.booknest.controllers.author;

import com.task.booknest.domains.dtos.Author.CreateAuthorDto;
import com.task.booknest.domains.dtos.GeneralResponse;
import com.task.booknest.domains.models.Author;
import com.task.booknest.exceptions.HttpError;
import com.task.booknest.services.contract.AuthorService;
import com.task.booknest.services.impl.CloudinaryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;
    private final CloudinaryService cloudinaryService;

    public AuthorController(AuthorService authorService, CloudinaryService cloudinaryService) {
        this.authorService = authorService;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse>createAuthor(@ModelAttribute @Valid CreateAuthorDto authorDto){
        try{
            String photo = cloudinaryService.uploadImage(authorDto.getFile(), "authors");
            Author author = authorService.createAuthor(authorDto, photo);

            return GeneralResponse.getResponse(HttpStatus.OK, "success", author);
        }catch (HttpError e){
            return  GeneralResponse.getResponse(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse>findAllAuthors(){
        try{
            List<Author> authors = authorService.findAllAuthors();
            return  GeneralResponse.getResponse(HttpStatus.OK, "success", authors);
        }catch (Exception e){
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Error while find all authors");
        }
    }

}
