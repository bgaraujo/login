package com.home.login.controller.login;

import com.home.login.dto.token.TokenReturnDTO;
import com.home.login.dto.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public interface LoginEndpoint {

    @PostMapping
    ResponseEntity<TokenReturnDTO> login(UserDTO loginDTO);
}
