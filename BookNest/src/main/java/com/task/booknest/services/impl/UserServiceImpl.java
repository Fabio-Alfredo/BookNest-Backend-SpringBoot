package com.task.booknest.services.impl;

import com.task.booknest.domains.dtos.auth.LoginDto;
import com.task.booknest.domains.dtos.auth.RegisterUserDto;
import com.task.booknest.domains.models.Token;
import com.task.booknest.domains.models.User;
import com.task.booknest.exceptions.HttpError;
import com.task.booknest.respositories.TokenRepository;
import com.task.booknest.respositories.UserRepository;
import com.task.booknest.services.contract.RoleService;
import com.task.booknest.services.contract.UserService;
import com.task.booknest.utils.JWTTools;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JWTTools jwtTools;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, JWTTools jwtTools, ModelMapper modelMapper, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtTools = jwtTools;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(RegisterUserDto user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User existUser = userRepository.findByEmail(user.getEmail());
            if(existUser!=null){
                throw  new HttpError(HttpStatus.CONFLICT,"User already exists");
            }

            User newUser = modelMapper.map(user, User.class);
            newUser.setRoles(List.of(roleService.findById("USER")));
            userRepository.save(newUser);

        }catch (HttpError e){
            HttpStatus status = e.getHttpStatus() != null ? e.getHttpStatus() : HttpStatus.BAD_REQUEST;
            String message = e.getMessage() != null ? e.getMessage() : "Error while role";
            throw new HttpError(status, message);
        }
    }

    @Override
    public Token loginUser(LoginDto auth) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email);

            if(user == null)
                throw new HttpError(HttpStatus.NOT_FOUND, "User not found");

            return user;
        }catch (HttpError e){
            HttpStatus status = e.getHttpStatus() != null ? e.getHttpStatus() : HttpStatus.BAD_REQUEST;
            String message = e.getMessage() != null ? e.getMessage() : "Error while role";
            throw new HttpError(status, message);
        }
    }

    //Method for auth user
    @Override
    public Token registerToken(User user) throws Exception {
        cleanToken(user);

        String stringToken = jwtTools.generateToken(user);
        Token token = new Token(user, stringToken);
        return tokenRepository.save(token);
    }

    @Override
    public Boolean isTokenValid(User user, String token) {
        try{
            cleanToken(user);
            List<Token>tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream()
                    .map(t -> t.getContent().equals(token))
                    .findAny()
                    .orElseThrow(()->new Exception());

            return  true;
        }catch (Exception e){
            throw new RuntimeException("Error while validating token: " + e.getMessage());
        }
    }

    @Override
    public void cleanToken(User user) throws Exception {
        List<Token>tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token ->{
            if(!jwtTools.verifyToken(token.getContent())){
                token.setActive(false);
                tokenRepository.save(token);
            }
        });
    }
}
