package com.task.booknest.domains.dtos.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserDto {

    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "name is required")
    @Email(message = "Type for path is email")
    private String email;
    @NotBlank(message = "name is required")
    private String password;
}
