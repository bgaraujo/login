package com.home.login.controller.user;


import com.home.dtos.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
public interface UserEndPoint {

    @PostMapping
    public ResponseEntity create(UserDTO userDTO);

    @PutMapping
    public ResponseEntity update(UserDTO userDTO);

    @GetMapping
    public ResponseEntity get();

    @GetMapping("/{id}")
    public ResponseEntity get(Long id);
}
