package com.spring.development.config;

import com.spring.development.dto.common.JWTPayload;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenManager {

    private JWTPayload jwtPayload;
    private String token;

    public void setJwtPayload(JWTPayload jwtPayload) {
        this.jwtPayload = jwtPayload;
    }

    public JWTPayload getJwtPayload() {
        return this.jwtPayload;
    }

    public String getToken() {
        return token;
    }

    public void setToken() {
        this.token = token;
    }
}
