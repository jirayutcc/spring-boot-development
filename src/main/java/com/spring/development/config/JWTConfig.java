package com.spring.development.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class JWTConfig {

    @Bean
    @RequestScope
    public TokenManager getTokenManager() {
        return new TokenManager();
    }
}
