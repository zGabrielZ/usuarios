package br.com.gabrielferreira.usuarios.config.security;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.GenerateTokenInput;
import io.jsonwebtoken.Claims;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTValidatorTokenFilter extends OncePerRequestFilter {

    private final GenerateTokenInput generateTokenInput;

    private final FindUsuarioInput findUsuarioInput;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);

        if (generateTokenInput.isTokenValido(token)) {
            Claims claims = generateTokenInput.extrairClaims(token);

            if (claims != null) {
                autenticarUsuario(claims);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request){
        String headerToken = request.getHeader("Authorization");
        if(StringUtils.isBlank(headerToken) || !headerToken.startsWith("Bearer ")){
            return null;
        }
        return headerToken.substring(7);
    }

    private void autenticarUsuario(Claims claims) {
        Long idUsuario = Long.valueOf(String.valueOf(claims.get("idUsuario")));
        UsuarioDomain usuarioDomain = findUsuarioInput.findUserCurrentById(idUsuario);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDomain, null, usuarioDomain.getPerfis());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
