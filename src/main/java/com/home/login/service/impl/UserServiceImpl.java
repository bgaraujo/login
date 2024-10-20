package com.home.login.service.impl;

import com.home.dtos.user.UserDTO;
import com.home.dtos.user.UserResponseDTO;
import com.home.login.configuration.ModelMapperConfig;
import com.home.login.entities.Profiles;
import com.home.login.entities.User;
import com.home.login.exception.LoginException;
import com.home.login.exception.UniqueException;
import com.home.login.repository.user.UserRepository;
import com.home.login.security.PasswordService;
import com.home.login.security.TokenService;
import com.home.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapperConfig modelMapper;
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
    public UsernamePasswordAuthenticationToken getUserAuthenticationToken(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername()).orElseThrow();
        return new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword(), user.getAuthorities());
    }

    @Override
    public List<Profiles> getAuthoritiesByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return user.getAuthorities();
    }
}
