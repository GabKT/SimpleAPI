package com.gabkt.WebService_Angular1.security;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gabkt.WebService_Angular1.model.User;

@Service
public class TokenService {
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("exemplo");
            String token = JWT.create()
                    .withIssuer("simple-api")
                    .withSubject(user.getEmail())
                    .withClaim("roles", user.getRole().getRole())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(3600))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro enquanto criava o token: ", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("exemplo");
            return JWT.require(algorithm)
                    .withIssuer("simple-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
}
