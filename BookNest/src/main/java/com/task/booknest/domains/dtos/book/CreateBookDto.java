package com.task.booknest.domains.dtos.book;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class CreateBookDto {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Summary is required")
    private String summary;
    @NotBlank(message = "Publication year is required")
    private Date publicationYear;
    @NotBlank(message = "Cover image is required")
    private String coverImage;
    @NotBlank(message = "Author id is required")
    private String genreId;
}
