package com.spring.development.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spring.development.dto.common.JWTPayload;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class JWTHelper {

    public static final String JWT_BEARER = "Bearer ";

    public String authorizationHeaderToJWTString(String authorization) {
        if (StringUtils.isNotBlank(authorization)) {
            return authorization.substring(JWT_BEARER.length());
        }
        return null;
    }

    public JWTPayload decodeToken(String token) {
        JWTPayload jwtPayload = new JWTPayload();
        DecodedJWT jwt = JWT.decode(authorizationHeaderToJWTString(token));
        String id = jwt.getClaim("id").asString();
        jwtPayload.setId(id);
        jwtPayload.setToken(token);

        return jwtPayload;
    }
}
