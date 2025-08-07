package com.home.login.service;

import com.home.dtos.login.LoginRequest;
import com.home.dtos.login.LoginResponse;
import com.home.dtos.login.ServiceDTO;
import com.home.dtos.user.RoleDTO;
import com.home.dtos.user.UserRequestDTO;
import com.home.dtos.user.UserResponseDTO;
import com.home.login.entities.RoleEntity;
import com.home.login.entities.UserEntity;
import com.home.login.exception.UniqueException;
import com.home.login.exception.UserNotFoundException;
import com.home.login.repository.RoleRepository;
import com.home.login.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

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
        if (userRepository.findByUsername(userRequestDTO.getUsername()).isPresent()) {
            throw new UniqueException();
        }
        UserEntity userEntity = modelMapper.map(userRequestDTO, UserEntity.class);

        Set<RoleEntity> basicRoleEntities =
                roleRepository.findAllByNameIn(userRequestDTO.getRoles().stream().map(RoleDTO::getName).toList()).orElse(null);

        userEntity.setRoleEntities(basicRoleEntities);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));
        return modelMapper.map(userRepository.save(userEntity), UserResponseDTO.class);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("Login or password is invalid!");
        }

        var userRoles = user.get().getRoleEntities();

        var services = userRoles.stream()
                .flatMap(roleEntity -> roleEntity.getServices().stream())
                .distinct()
                .map(serviceEntity -> new ServiceDTO(
                        serviceEntity.getId(),
                        serviceEntity.getName(),
                        serviceEntity.getDescription(),
                        serviceEntity.getIcon()))
                .toList();

        return new LoginResponse(getToken(user.get()), 36000L, services);
    }

    private String getToken(UserEntity userEntity) {
        var claims = JwtClaimsSet.builder()
                .issuer("security_model")
                .subject(userEntity.getId().toString())
                .expiresAt(Instant.now().plusSeconds(expiresAt))
                .claim("townHousesId", userEntity.getTownHousesId().toString())
                .issuedAt(Instant.now()).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public UserResponseDTO getUserById(String id) {
        var userEntity = userRepository.findById(UUID.fromString(id))
                .orElseThrow(UserNotFoundException::new);
        return modelMapper.map(userEntity, UserResponseDTO.class);
    }
}
