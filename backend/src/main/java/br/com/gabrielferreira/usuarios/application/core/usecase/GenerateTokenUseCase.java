package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.GenerateTokenInput;
import br.com.gabrielferreira.usuarios.application.ports.out.GenerateTokenOutput;
import io.jsonwebtoken.Claims;

public class GenerateTokenUseCase implements GenerateTokenInput {

    private final GenerateTokenOutput generateTokenOutput;

    public GenerateTokenUseCase(GenerateTokenOutput generateTokenOutput) {
        this.generateTokenOutput = generateTokenOutput;
    }

    @Override
    public String generate(UsuarioDomain usuarioDomain) {
        return generateTokenOutput.generate(usuarioDomain);
    }

    @Override
    public boolean isTokenValido(String token) {
        return generateTokenOutput.isTokenValido(token);
    }

    @Override
    public Claims extrairClaims(String token) {
        return generateTokenOutput.extrairClaims(token);
    }

    @Override
    public String refreshToken(UsuarioDomain usuarioDomain) {
        return generate(usuarioDomain);
    }
}
