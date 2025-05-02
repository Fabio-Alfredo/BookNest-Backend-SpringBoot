package com.task.booknest.controllers.auth;

import com.task.booknest.domains.dtos.GeneralResponse;
import com.task.booknest.domains.dtos.auth.LoginDto;
import com.task.booknest.domains.dtos.auth.RegisterUserDto;
import com.task.booknest.domains.dtos.auth.TokenDto;
import com.task.booknest.domains.models.Token;
import com.task.booknest.exceptions.HttpError;
import com.task.booknest.services.contract.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GeneralResponse> registerUser(@RequestBody @Valid RegisterUserDto data){
        try{
            userService.registerUser(data);
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "User register");
        }catch (HttpError e) {
            return GeneralResponse.getResponse(e.getHttpStatus(), e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse>loginUser(@RequestBody @Valid LoginDto auth){
        try{
            Token token = userService.loginUser(auth);
            return GeneralResponse.getResponse(HttpStatus.OK, "Login success", new TokenDto(token) );
        }catch (HttpError e){
            return GeneralResponse.getResponse(e.getHttpStatus(), e.getMessage());
        }
    }
}
