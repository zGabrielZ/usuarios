package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.exception.ForbiddenException;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
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

    @Override
    public void validarAdminOuProprioUsuario(Long idUsuario) {
        UsuarioDomain usuario = getUserCurrent();
        if (!usuario.getId().equals(idUsuario) && usuario.isNaoContemPerfil(RoleEnum.ROLE_ADMIN)) {
            throw new ForbiddenException("Você não tem a permissão de realizar este recurso");
        }
    }

    @Override
    public void validarAdminExclusao(Long idUsuario) {
        UsuarioDomain usuario = getUserCurrent();
        if (usuario.getId().equals(idUsuario)) {
            throw new RegraDeNegocioException("Você não tem a permissão de realizar este recurso");
        }
    }
}
