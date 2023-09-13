package com.home.login.service.impl;

import com.home.login.configuration.ModelMapperConfig;
import com.home.login.dto.user.UserDTO;
import com.home.login.dto.user.UserResponseDTO;
import com.home.login.entities.User;
import com.home.login.repository.user.UserRepository;
import com.home.login.security.PasswordService;
import com.home.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapperConfig modelMapper;

    @Autowired
    private PasswordService passwordService;

    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {
        User user = modelMapper.getModelMapper().map(userDTO, User.class);
        user.setPassword(passwordService.encryptPassword(userDTO.getPassword()));
        return modelMapper.getModelMapper().map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        return modelMapper.getModelMapper().map(userRepository.findByUsername(username), UserResponseDTO.class);
    }
}
