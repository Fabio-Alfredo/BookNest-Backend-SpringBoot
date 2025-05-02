package com.task.booknest.domains.dtos.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @NotBlank(message = "Genre id is required")
    private String genreId;
    @NotEmpty(message = "Authors is required")
    private List<UUID> authorIds;
}
