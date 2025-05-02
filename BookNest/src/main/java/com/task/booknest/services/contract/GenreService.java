package com.task.booknest.services.contract;

import com.task.booknest.domains.dtos.Genre.CreateGenreDto;
import com.task.booknest.domains.models.Genre;

import java.util.List;

public interface GenreService {
    Genre findById(String id);
    void createGenre(CreateGenreDto genreDto);
    List<Genre>findAllGenres();
}
