package com.home.login.dto.user;

import com.home.login.entities.Profiles;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private List<Profiles> authorities;

}
