package br.com.gabrielferreira.usuarios.infrastructure.config.security;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsSecurityService userDetailsSecurityService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String senha = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsSecurityService.loadUserByUsername(email);
        boolean isMatchSenha = passwordEncoder.matches(senha, userDetails.getPassword());
        if(!isMatchSenha){
            throw new UnauthorizedException("Senha inválida");
        }

        UsuarioDomain usuario = (UsuarioDomain) userDetails;
        return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
