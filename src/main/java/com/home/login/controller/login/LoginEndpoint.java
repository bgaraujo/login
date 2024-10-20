package com.home.login.controller.login;

import com.home.dtos.token.TokenReturnDTO;
import com.home.dtos.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public interface LoginEndpoint {

    @PostMapping
    ResponseEntity<TokenReturnDTO> login(UserDTO loginDTO);
}
