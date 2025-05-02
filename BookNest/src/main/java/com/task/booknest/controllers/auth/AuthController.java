package com.task.booknest.controllers.auth;

import com.task.booknest.domains.dtos.auth.RegisterUserDto;
import com.task.booknest.services.contract.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUsers(@RequestBody @Valid RegisterUserDto data){
        try{
            userService.registerUser(data);
            return "Usuario registrado";
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
