package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import io.jsonwebtoken.Claims;

public interface GenerateTokenInput {

    String generate(UsuarioDomain usuarioDomain);

    boolean isTokenValido(String token);

    Claims extrairClaims(String token);

    String refreshToken(UsuarioDomain usuarioDomain);
}
