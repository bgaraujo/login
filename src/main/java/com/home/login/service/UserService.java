package com.home.login.service;

import com.home.login.dto.user.UserDTO;
import com.home.login.entities.User;

import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);

    List<User> getUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);
}
