package com.task.booknest.domains.dtos.Author;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class CreateAuthorDto {

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "biography is required")
    private String biography;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private MultipartFile file;
}
