package com.ericnascimet.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ericnascimet.security.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {


    @Value("${security.client-secret}")
    private String secret;

    @Value("${security.jwt-duration}")
    private Integer duration;

    public String generateToken(User user){

        Algorithm algorithm = Algorithm.HMAC256(secret);


        return JWT.create()
                .withClaim("userId",user.getId())
                .withClaim("roles", user.getRoles().stream().map(Enum::name).toList())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(duration))
                .withIssuedAt(Instant.now())
                .sign(algorithm);

    }

    public Optional<JwtUserData> validationToken(String token) {
           try{
               Algorithm algorithm = Algorithm.HMAC256(secret);

               DecodedJWT decode = JWT.require(algorithm).build().verify(token);


               return Optional.of(JwtUserData.builder()
                       .userId(decode.getClaim("userId").asLong())
                       .email(decode.getSubject())
                               .role(decode.getClaim("roles").asList(String.class))
                       .build());

           }
           catch (JWTVerificationException e){
                return Optional.empty();
           }
    }
}
