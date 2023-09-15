package com.home.login.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapper {

    @Bean
    public org.modelmapper.ModelMapper getMapper() {
        return new org.modelmapper.ModelMapper();
    }
}
