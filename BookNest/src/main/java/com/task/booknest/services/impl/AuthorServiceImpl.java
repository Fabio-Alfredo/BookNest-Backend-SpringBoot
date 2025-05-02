package com.task.booknest.services.impl;

import com.task.booknest.domains.dtos.Author.CreateAuthorDto;
import com.task.booknest.domains.models.Author;
import com.task.booknest.exceptions.HttpError;
import com.task.booknest.respositories.AuthorRepository;
import com.task.booknest.services.contract.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Author createAuthor(CreateAuthorDto authorDto, String photo) {
        try{
            Author author = authorRepository.findByName(authorDto.getName());
            if(author != null)
                throw new HttpError(HttpStatus.CONFLICT, "Author ready exists");
            Author newAuthor = modelMapper.map(authorDto, Author.class);
            newAuthor.setPhoto(photo);

            return  authorRepository.save(newAuthor);
        }catch (HttpError e){
            throw  e;
        }
    }

    @Override
    public List<Author> findAllAuthors() {
        List<Author>authors = authorRepository.findAll();
        return  authors;
    }

    @Override
    public List<Author> findAllAuthorsById(List<UUID> authorsId) {
        try{
            List<Author>authors = authorRepository.findAllById(authorsId);
            if(authors.size() != authorsId.size())
                throw  new HttpError(HttpStatus.NOT_FOUND, "Some authors do not exist");

            return authors;
        }catch (HttpError e){
            throw e;
        }
    }

    @Override
    public Author findById(UUID id) {
        try{
            Author author = authorRepository.findById(id).orElse(null);
            if(author == null)
                throw  new HttpError(HttpStatus.NOT_FOUND, "Author not exist");

            return  author;
        }catch (HttpError e){
            throw  e;
        }
    }

    @Override
    public Author findByName(String name) {
        try{
            Author author = authorRepository.findByName(name);
            if(author == null)
                throw new HttpError(HttpStatus.NOT_FOUND, "Author not exist");

            return author;
        }catch (HttpError e){
            throw e;
        }
    }
}
