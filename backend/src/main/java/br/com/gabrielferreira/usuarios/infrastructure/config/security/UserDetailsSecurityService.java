package br.com.gabrielferreira.usuarios.infrastructure.config.security;

import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsSecurityService implements UserDetailsService {

    private final FindUsuarioInput findUsuarioInput;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findUsuarioInput.findUserDetailsByEmail(email);
    }
}
