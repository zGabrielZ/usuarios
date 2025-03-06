package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateUsuarioOutput;
import br.com.gabrielferreira.usuarios.application.validator.UsuarioValidator;

public class UpdateUsuarioUseCase implements UpdateUsuarioInput {

    private final UpdateUsuarioOutput updateUsuarioOutput;

    private final FindUsuarioInput findUsuarioInput;

    private final UsuarioValidator usuarioValidator;

    private final FindGeneroInput findGeneroInput;

    private final FindPerfilInput findPerfilInput;

    private final UserCurrentInput userCurrentInput;

    public UpdateUsuarioUseCase(UpdateUsuarioOutput updateUsuarioOutput,
                                FindUsuarioInput findUsuarioInput,
                                UsuarioValidator usuarioValidator,
                                FindGeneroInput findGeneroInput,
                                FindPerfilInput findPerfilInput,
                                UserCurrentInput userCurrentInput) {
        this.updateUsuarioOutput = updateUsuarioOutput;
        this.findUsuarioInput = findUsuarioInput;
        this.usuarioValidator = usuarioValidator;
        this.findGeneroInput = findGeneroInput;
        this.findPerfilInput = findPerfilInput;
        this.userCurrentInput = userCurrentInput;
    }

    @Override
    public UsuarioDomain update(UsuarioDomain usuarioDomain) {
        userCurrentInput.validarAdminOuProprioUsuario(usuarioDomain.getId());
        UsuarioDomain usuarioDomainEncontrado = findUsuarioInput.findById(usuarioDomain.getId());
        DominioDomain generoDomainEncontrado = findGeneroInput.findById(usuarioDomain.getGenero().getId());

        usuarioValidator.validarCampos(usuarioDomain);

        usuarioDomainEncontrado.setNome(usuarioDomain.getNome());
        usuarioDomainEncontrado.setRenda(usuarioDomain.getRenda());
        usuarioDomainEncontrado.setDataNascimento(usuarioDomain.getDataNascimento());
        usuarioDomainEncontrado.setQuantidadeFilhos(usuarioDomain.getQuantidadeFilhos());
        usuarioDomainEncontrado.setGenero(generoDomainEncontrado);

        return updateUsuarioOutput.update(usuarioDomainEncontrado);
    }

    @Override
    public void updateRoleAdmin(Long id) {
        UsuarioDomain usuarioDomainEncontrado = findUsuarioInput.findById(id);
        PerfilDomain perfilDomain = findPerfilInput.findByRole(RoleEnum.ROLE_ADMIN.name());

        usuarioValidator.validarPerfilUsuario(usuarioDomainEncontrado, perfilDomain, "Este usuário contém perfil admin");

        usuarioDomainEncontrado.getPerfis().clear();
        usuarioDomainEncontrado.getPerfis().add(perfilDomain);
        updateUsuarioOutput.update(usuarioDomainEncontrado);
    }

    @Override
    public void updateRoleClient(Long id) {
        UsuarioDomain usuarioDomainEncontrado = findUsuarioInput.findById(id);
        PerfilDomain perfilDomain = findPerfilInput.findByRole(RoleEnum.ROLE_CLIENT.name());

        usuarioValidator.validarPerfilUsuario(usuarioDomainEncontrado, perfilDomain, "Este usuário contém perfil cliente");

        usuarioDomainEncontrado.getPerfis().clear();
        usuarioDomainEncontrado.getPerfis().add(perfilDomain);
        updateUsuarioOutput.update(usuarioDomainEncontrado);
    }
}
