package com.example.SoccerGame.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.SoccerGame.models.AdminTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String getToken(AdminTime user) {
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("AgendamentoComSchedule")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getAdminId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    // metodo para verificar se o token está válido e devolver o subject q está dentro do token
    public String getSubject(String tokenJWT) {
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    // specify any specific claim validations
                    .withIssuer("AgendamentoComSchedule")
                    // reusable verifier instance
                    .build()
                    .verify(tokenJWT) // .verify => verifica se o token q chegou ali está válido
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
