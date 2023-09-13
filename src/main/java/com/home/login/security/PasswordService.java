package com.home.login.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public String encryptPassword(String password){
        String generated = BCrypt.gensalt();
        return BCrypt.hashpw(password, generated);
    }
}
