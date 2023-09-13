package com.home.login.controller.login;

import com.home.login.dto.login.LoginDTO;
import com.home.login.dto.login.TokenReturnDTO;
import com.home.login.entities.User;
import com.home.login.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login implements LoginEndpoint{

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<TokenReturnDTO> login(@RequestBody @Validated LoginDTO loginDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());
        var authentication =  manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.createToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenReturnDTO(tokenJWT));
    }
}
