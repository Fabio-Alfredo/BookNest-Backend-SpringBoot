package com.task.booknest.domains.dtos.Genre;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateGenreDto {
    @NotBlank(message = "id is required")
    private String id;
    @NotBlank(message = "name for genre is required")
    private String genre;
}
