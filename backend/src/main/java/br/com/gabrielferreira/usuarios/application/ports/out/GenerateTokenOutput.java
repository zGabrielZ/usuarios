package br.com.gabrielferreira.usuarios.application.ports.out;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import io.jsonwebtoken.Claims;

public interface GenerateTokenOutput {

    String generate(UsuarioDomain usuarioDomain);

    boolean isTokenValido(String token);

    Claims extrairClaims(String token);
}
