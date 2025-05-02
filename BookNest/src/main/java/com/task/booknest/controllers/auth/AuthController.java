package com.task.booknest.controllers.auth;

import com.task.booknest.domains.dtos.GeneralResponse;
import com.task.booknest.domains.dtos.auth.RegisterUserDto;
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
    public ResponseEntity<GeneralResponse> registerUsers(@RequestBody @Valid RegisterUserDto data){
        try{
            userService.registerUser(data);
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "User register");
        }catch (HttpError e){
            return  GeneralResponse.getResponse(e.getHttpStatus(), e.getMessage());
        }catch (Exception e){
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Error while fetching role: " +e.getMessage());
        }
    }
}
