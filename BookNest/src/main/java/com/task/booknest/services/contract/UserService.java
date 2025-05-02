package com.task.booknest.services.contract;


import com.task.booknest.domains.dtos.auth.LoginDto;
import com.task.booknest.domains.dtos.auth.RegisterUserDto;
import com.task.booknest.domains.models.Token;
import com.task.booknest.domains.models.User;

public interface UserService {
    void registerUser(RegisterUserDto user);
    Token loginUser(LoginDto auth);

    User findByEmail(String email);


    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token) ;
    void cleanToken(User user) throws Exception;
}
