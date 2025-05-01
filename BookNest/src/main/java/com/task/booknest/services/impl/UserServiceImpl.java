package com.task.booknest.services.impl;

import com.task.booknest.domains.models.Token;
import com.task.booknest.domains.models.User;
import com.task.booknest.respositories.TokenRepository;
import com.task.booknest.respositories.UserRepository;
import com.task.booknest.services.contract.UserService;
import com.task.booknest.utils.JWTTools;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JWTTools jwtTools;

    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, JWTTools jwtTools) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtTools = jwtTools;
    }

    @Override
    public User findByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email);

            if(user == null)
                throw new RuntimeException("User not found");

            return user;
        }catch (Exception e){
            throw  new RuntimeException("Error while fetching user: "+e.getMessage());
        }
    }

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
