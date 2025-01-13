package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.exception.UnauthorizedException;
import br.com.gabrielferreira.usuarios.application.ports.in.UserCurrentInput;
import br.com.gabrielferreira.usuarios.application.ports.out.UserCurrentOutput;

public class UserCurrentUseCase implements UserCurrentInput {

    private final UserCurrentOutput userCurrentOutput;

    public UserCurrentUseCase(UserCurrentOutput userCurrentOutput) {
        this.userCurrentOutput = userCurrentOutput;
    }

    @Override
    public UsuarioDomain getUserCurrent() {
        UsuarioDomain usuarioDomain = userCurrentOutput.getUserCurrent();
        if (usuarioDomain == null) {
            throw new UnauthorizedException("Usuário inválido");
        }
        return usuarioDomain;
    }
}
