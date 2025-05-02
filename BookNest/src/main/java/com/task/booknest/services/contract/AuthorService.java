package com.task.booknest.services.contract;


import com.task.booknest.domains.dtos.Author.CreateAuthorDto;
import com.task.booknest.domains.models.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    Author createAuthor(CreateAuthorDto authorDto, String photo);
    List<Author>findAllAuthors();
    List<Author>findAllAuthorsById(List<UUID>authorsId);
    Author findById(UUID id);
    Author findByName(String name);
}
