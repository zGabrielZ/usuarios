package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.out.GenerateTokenOutput;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Date;

import static br.com.gabrielferreira.usuarios.common.utils.DataUtils.UTC;

@Component
public class GenerateTokenAdapter implements GenerateTokenOutput {

    private final String chave;

    private final String expiracao;

    public GenerateTokenAdapter(@Value("${jwt.secret}") String chave, @Value("${jwt.expiration}") String expiracao) {
        this.chave = chave;
        this.expiracao = expiracao;
    }

    @Override
    public String generate(UsuarioDomain usuarioDomain) {
        SecretKey secretKey = getSecret();
        ZonedDateTime dataAtual = ZonedDateTime.now(UTC);
        ZonedDateTime dataExpiracao = dataAtual.plus(Duration.ofMillis(Long.parseLong(expiracao)));

        return Jwts.builder()
                .issuer("API Usuário")
                .subject("API de Usuários")
                .issuedAt(Date.from(dataAtual.toInstant()))
                .expiration(Date.from(dataExpiracao.toInstant()))
                .claim("idUsuario", usuarioDomain.getId())
                .signWith(secretKey)
                .compact();
    }

    @Override
    public boolean isTokenValido(String token) {
        try {
            extrairClaims(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Claims extrairClaims(String token) {
        return Jwts.parser().verifyWith(getSecret()).build().parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecret() {
        return Keys.hmacShaKeyFor(chave.getBytes(StandardCharsets.UTF_8));
    }
}
