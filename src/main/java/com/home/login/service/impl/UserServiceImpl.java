package com.home.login.service.impl;

import com.home.login.configuration.ModelMapper;
import com.home.login.dto.user.UserDTO;
import com.home.login.dto.user.UserResponseDTO;
import com.home.login.entities.User;
import com.home.login.exception.UniqueException;
import com.home.login.repository.user.UserRepository;
import com.home.login.security.PasswordService;
import com.home.login.security.TokenService;
import com.home.login.service.UserService;
import org.modelmapper.ValidationException;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private TokenService tokenService;

    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {
        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()){
            throw new UniqueException();
        }
        User user = modelMapper.getMapper().map(userDTO, User.class);
        user.setPassword(passwordService.encryptPassword(userDTO.getPassword()));
        return modelMapper.getMapper().map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll().stream().map(user -> modelMapper.getMapper().map(user,UserResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return modelMapper.getMapper().map(userRepository.getReferenceById(id), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        return modelMapper.getMapper().map(userRepository.findByUsername(username), UserResponseDTO.class);
    }

    @Override
    public UsernamePasswordAuthenticationToken getUserAuthenticationToken(String tokenJWT) {
        var subject = tokenService.getSubject(tokenJWT);
        User user = userRepository.findByUsername(subject).orElseThrow();
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
}
