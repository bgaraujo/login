package com.home.login.dto.token;

import lombok.Data;

@Data
public class TokenReturnDTO{
    private String token;

    public TokenReturnDTO(String token) {
        this.token = token;
    }
}
