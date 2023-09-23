package com.home.login.controller.login;

import com.home.login.dto.token.TokenReturnDTO;
import com.home.login.dto.user.UserDTO;
import com.home.login.dto.user.UserResponseDTO;
import com.home.login.entities.Profiles;
import com.home.login.entities.User;
import com.home.login.security.TokenService;
import com.home.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Login implements LoginEndpoint{

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<TokenReturnDTO> login(@RequestBody @Validated UserDTO userDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = userService.getUserAuthenticationToken(userDTO);
        manager.authenticate(authenticationToken);
        TokenReturnDTO tokenJWT = new TokenReturnDTO(tokenService.createToken(authenticationToken));
        return ResponseEntity.ok(new TokenReturnDTO(tokenJWT.getToken()));
    }
}
