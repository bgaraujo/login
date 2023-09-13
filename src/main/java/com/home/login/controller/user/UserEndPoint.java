package com.home.login.controller.user;

import com.home.login.dto.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
public interface UserEndPoint {

    @PostMapping
    public ResponseEntity create(UserDTO userDTO);

    @PutMapping
    public ResponseEntity update(UserDTO userDTO);

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity get();

    @GetMapping("/{id}")
    @Secured("ROLE_USER")
    public ResponseEntity get(Long id);
}
