package com.home.login.service;

import com.home.login.dto.user.UserDTO;
import com.home.login.dto.user.UserResponseDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserDTO userDTO);

    List<UserResponseDTO> getUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO getUserByUsername(String username);

    UsernamePasswordAuthenticationToken getUserAuthenticationToken(String tokenJWT);
}
