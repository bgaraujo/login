package com.home.login.service;

import com.home.dtos.login.LoginRequest;
import com.home.dtos.login.LoginResponse;
import com.home.dtos.user.RoleDTO;
import com.home.dtos.user.UserRequestDTO;
import com.home.dtos.user.UserResponseDTO;
import com.home.login.entities.Address;
import com.home.login.entities.Document;
import com.home.login.entities.Role;
import com.home.login.entities.User;
import com.home.login.exception.UniqueException;
import com.home.login.repository.AddressRepository;
import com.home.login.repository.DocumentRepository;
import com.home.login.repository.RoleRepository;
import com.home.login.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtEncoder jwtEncoder;

    @Value("${security.expires-at}")
    private Long expiresAt;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if(userRepository.findByUsername(userRequestDTO.getUsername()).isPresent()){
            throw new UniqueException();
        }
        User user = modelMapper.map(userRequestDTO, User.class);

        Set<Role> basicRoles =
                roleRepository.findAllByNameIn(userRequestDTO.getRoles().stream().map(RoleDTO::getName).toList()).orElse(null);

        user.setRoles(basicRoles);
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.username());

        if(user.isEmpty() || !user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)){
            throw new BadCredentialsException("Login or password is invalid!");
        }

        return new LoginResponse(getToken(user.get()), 36000L);
    }

    private String getToken(User user){
        //var scopes = user.getRoles().stream().map(Role::getName).collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("security_model")
                .subject(user.getId().toString())
                .subject(user.getTownHousesId().toString())
                .expiresAt(Instant.now().plusSeconds(expiresAt))
                //.claim("scopes", scopes)
                .issuedAt(Instant.now()).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
