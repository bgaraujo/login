package com.home.login.controller.user;

import com.home.dtos.user.UserDTO;
import com.home.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController implements UserEndPoint{

    @Autowired
    private UserService userService;
    @Override
    public ResponseEntity create(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @Override
    public ResponseEntity update(@RequestBody UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity get() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    public ResponseEntity get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
