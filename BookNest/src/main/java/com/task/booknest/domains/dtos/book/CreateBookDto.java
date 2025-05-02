package com.task.booknest.domains.dtos.book;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class CreateBookDto {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Summary is required")
    private String summary;
    @NotBlank(message = "Publication year is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationYear;
    private MultipartFile file;
    private String coverImage;
    @NotBlank(message = "Author id is required")
    private String genreId;
}
