package com.home.login.configuration;

import com.home.security.SecurityConfigurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SecurityConfigurations.class)
public class ApplicationConfig{
}
