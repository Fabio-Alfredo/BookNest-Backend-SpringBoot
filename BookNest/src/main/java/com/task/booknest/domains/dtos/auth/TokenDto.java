package com.task.booknest.domains.dtos.auth;

import com.task.booknest.domains.models.Token;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDto {
    private String content;

    public TokenDto(Token token){
        this.content = token.getContent();
    }
}
