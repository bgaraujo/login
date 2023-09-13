package com.home.login.controller.login;

import com.home.login.dto.login.LoginDTO;
import com.home.login.dto.login.TokenReturnDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
public interface LoginEndpoint {

    @PostMapping
    ResponseEntity<TokenReturnDTO> login(LoginDTO loginDTO);
}
