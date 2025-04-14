package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateUsuarioOutput;

public class UpdateUsuarioUseCase implements UpdateUsuarioInput {

    private final UpdateUsuarioOutput updateUsuarioOutput;

    private final FindUsuarioInput findUsuarioInput;

    private final FindGeneroInput findGeneroInput;

    private final FindPerfilInput findPerfilInput;

    public UpdateUsuarioUseCase(UpdateUsuarioOutput updateUsuarioOutput,
                                FindUsuarioInput findUsuarioInput,
                                FindGeneroInput findGeneroInput,
                                FindPerfilInput findPerfilInput) {
        this.updateUsuarioOutput = updateUsuarioOutput;
        this.findUsuarioInput = findUsuarioInput;
        this.findGeneroInput = findGeneroInput;
        this.findPerfilInput = findPerfilInput;
    }

    @Override
    public UsuarioDomain update(UsuarioDomain usuarioDomain) {
        UsuarioDomain usuarioDomainEncontrado = findUsuarioInput.findById(usuarioDomain.getId());
        DominioDomain generoDomainEncontrado = findGeneroInput.findById(usuarioDomain.getGenero().getId());

        usuarioDomain.validarCampos();

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

        usuarioDomainEncontrado.validarPerfilUsuario(perfilDomain, "Este usuário contém perfil admin");

        usuarioDomainEncontrado.getPerfis().clear();
        usuarioDomainEncontrado.getPerfis().add(perfilDomain);
        updateUsuarioOutput.update(usuarioDomainEncontrado);
    }

    @Override
    public void updateRoleClient(Long id) {
        UsuarioDomain usuarioDomainEncontrado = findUsuarioInput.findById(id);
        PerfilDomain perfilDomain = findPerfilInput.findByRole(RoleEnum.ROLE_CLIENT.name());

        usuarioDomainEncontrado.validarPerfilUsuario(perfilDomain, "Este usuário contém perfil cliente");

        usuarioDomainEncontrado.getPerfis().clear();
        usuarioDomainEncontrado.getPerfis().add(perfilDomain);
        updateUsuarioOutput.update(usuarioDomainEncontrado);
    }
}
