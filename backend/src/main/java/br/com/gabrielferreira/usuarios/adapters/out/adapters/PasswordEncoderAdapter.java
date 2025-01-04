package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.application.ports.out.PasswordEncoderOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderAdapter implements PasswordEncoderOutput {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String enconde(String senha) {
        return bCryptPasswordEncoder.encode(senha);
    }
}
