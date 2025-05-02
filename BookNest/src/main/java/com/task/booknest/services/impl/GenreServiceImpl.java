package com.task.booknest.services.impl;

import com.task.booknest.domains.dtos.Genre.CreateGenreDto;
import com.task.booknest.domains.models.Genre;
import com.task.booknest.exceptions.HttpError;
import com.task.booknest.respositories.GenreRepository;
import com.task.booknest.services.contract.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    public GenreServiceImpl(GenreRepository genreRepository, ModelMapper modelMapper) {
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Genre findById(String id) {
        try{
            Genre genre = genreRepository.findById(id).orElse(null);
            if(genre == null)
                throw new HttpError(HttpStatus.NOT_FOUND, "Genre not exist");

            return genre;
        }catch (HttpError e){
            throw  e;
        }
    }

    @Override
    public void createGenre(CreateGenreDto genreDto) {
        try{
            Genre genre = genreRepository.findById(genreDto.getId()).orElse(null);
            if(genre != null)
                throw new HttpError(HttpStatus.CONFLICT, "Genre already exists");
            Genre newGere = modelMapper.map(genreDto, Genre.class);

            genreRepository.save(newGere);
        }catch (HttpError e){
            throw e;
        }
    }

    @Override
    public List<Genre> findAllGenres() {
        List<Genre>genres = genreRepository.findAll();
        return genres;
    }
}
