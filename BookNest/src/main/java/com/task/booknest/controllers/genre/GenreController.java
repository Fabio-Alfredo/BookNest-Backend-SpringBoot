package com.task.booknest.controllers.genre;

import com.task.booknest.domains.dtos.GeneralResponse;
import com.task.booknest.domains.dtos.Genre.CreateGenreDto;
import com.task.booknest.domains.models.Genre;
import com.task.booknest.exceptions.HttpError;
import com.task.booknest.services.contract.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse>findAllGenres(){
        try{
            List<Genre>genres = genreService.findAllGenres();
            return GeneralResponse.getResponse(HttpStatus.OK, "success all", genres);
        }catch (HttpError e){
            return GeneralResponse.getResponse(e.getHttpStatus(), e.getMessage());
        }
    }

    @GetMapping("/by-id/{genreId}")
    public ResponseEntity<GeneralResponse>findGenreById(@PathVariable String genreId){
        try {
            Genre genre = genreService.findById(genreId);
            return GeneralResponse.getResponse(HttpStatus.OK, "success", genre);
        }catch (HttpError e){
            return GeneralResponse.getResponse(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse>createGenre(@RequestBody @Valid CreateGenreDto genreDto){
        try {
            genreService.createGenre(genreDto);
            return GeneralResponse.getResponse(HttpStatus.CREATED, "Created new genre");
        }catch (HttpError e){
            return GeneralResponse.getResponse(e.getHttpStatus(), e.getMessage());
        }
    }

}
