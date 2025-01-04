package br.com.gabrielferreira.usuarios.application.ports.in;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface GenerateTokenInput {

    String generate(Authentication authentication);

    boolean isTokenValido(String token);

    Claims extrairClaims(String token);
}
