package com.task.booknest.respositories;

import com.task.booknest.domains.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, String> {
}
