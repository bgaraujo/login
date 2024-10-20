package com.home.login.service;

import com.home.dtos.user.UserDTO;
import com.home.dtos.user.UserResponseDTO;
import com.home.login.entities.Profiles;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserDTO userDTO);

    List<UserResponseDTO> getUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO getUserByUsername(String username);

    UsernamePasswordAuthenticationToken getUserAuthenticationToken(UserDTO userDTO);

    List<Profiles> getAuthoritiesByUsername(String username);
}
