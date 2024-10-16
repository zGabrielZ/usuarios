package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateUsuarioOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.UsuarioMapperOutput;

public class UpdateUsuarioUseCase implements UpdateUsuarioInput {

    private final UpdateUsuarioOutput updateUsuarioOutput;

    private final FindUsuarioInput findUsuarioInput;

    private final ValidCreateUsuarioInput validCreateUsuarioInput;

    private final FindGeneroInput findGeneroInput;

    private final FindPerfilInput findPerfilInput;

    private final UsuarioMapperOutput usuarioMapperOutput;

    public UpdateUsuarioUseCase(UpdateUsuarioOutput updateUsuarioOutput,
                                FindUsuarioInput findUsuarioInput,
                                ValidCreateUsuarioInput validCreateUsuarioInput,
                                FindGeneroInput findGeneroInput,
                                UsuarioMapperOutput usuarioMapperOutput,
                                FindPerfilInput findPerfilInput) {
        this.updateUsuarioOutput = updateUsuarioOutput;
        this.findUsuarioInput = findUsuarioInput;
        this.validCreateUsuarioInput = validCreateUsuarioInput;
        this.findGeneroInput = findGeneroInput;
        this.usuarioMapperOutput = usuarioMapperOutput;
        this.findPerfilInput = findPerfilInput;
    }

    @Override
    public UsuarioDomain update(UsuarioDomain usuarioDomain) {
        UsuarioDomain usuarioDomainEncontrado = findUsuarioInput.findById(usuarioDomain.getId());
        DominioDomain generoDomainEncontrado = findGeneroInput.findById(usuarioDomain.getGenero().getId());

        validCreateUsuarioInput.validarCampos(usuarioDomain);

        UsuarioDomain usuarioDomainUpdate = usuarioMapperOutput.update(usuarioDomain, usuarioDomainEncontrado, generoDomainEncontrado);
        return updateUsuarioOutput.update(usuarioDomainUpdate);
    }

    @Override
    public void updateRoleAdmin(Long id) {
        UsuarioDomain usuarioDomainEncontrado = findUsuarioInput.findById(id);
        PerfilDomain perfilDomain = findPerfilInput.findByRole(RoleEnum.ROLE_ADMIN.name());

        validCreateUsuarioInput.validarPerfilUsuario(usuarioDomainEncontrado, perfilDomain, "Este usuário contém perfil admin");


        usuarioDomainEncontrado.getPerfis().clear();
        usuarioDomainEncontrado.getPerfis().add(perfilDomain);
        updateUsuarioOutput.update(usuarioDomainEncontrado);
    }

    @Override
    public void updateRoleClient(Long id) {
        UsuarioDomain usuarioDomainEncontrado = findUsuarioInput.findById(id);
        PerfilDomain perfilDomain = findPerfilInput.findByRole(RoleEnum.ROLE_CLIENT.name());

        validCreateUsuarioInput.validarPerfilUsuario(usuarioDomainEncontrado, perfilDomain, "Este usuário contém perfil cliente");

        usuarioDomainEncontrado.getPerfis().clear();
        usuarioDomainEncontrado.getPerfis().add(perfilDomain);
        updateUsuarioOutput.update(usuarioDomainEncontrado);
    }
}
