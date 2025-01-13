package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.exception.ForbiddenException;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindPerfilInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UserCurrentInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindPerfilOutput;

import java.util.List;

public class FindPerfilUseCase implements FindPerfilInput {

    private static final String MSG_PERFIL_NAO_ENCONTRADO = "Perfil informado não encontrado";

    private final FindPerfilOutput findPerfilOutput;

    private final UserCurrentInput userCurrentInput;

    public FindPerfilUseCase(FindPerfilOutput findPerfilOutput,
                             UserCurrentInput userCurrentInput) {
        this.findPerfilOutput = findPerfilOutput;
        this.userCurrentInput = userCurrentInput;
    }

    @Override
    public PerfilDomain findById(Long id) {
        return findPerfilOutput.findById(id)
                .orElseThrow(() -> new NaoEncontradoException(MSG_PERFIL_NAO_ENCONTRADO));
    }

    @Override
    public List<PerfilDomain> findAll() {
        return findPerfilOutput.findAll();
    }

    @Override
    public PerfilDomain findByRole(String role) {
        return findPerfilOutput.findByRole(role)
                .orElseThrow(() -> new NaoEncontradoException(MSG_PERFIL_NAO_ENCONTRADO));
    }

    @Override
    public PerfilDomain findByIdAndIdUsuario(Long id, Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        return findPerfilOutput.findByIdAndIdUsuario(id, idUsuario)
                .orElseThrow(() -> new NaoEncontradoException(MSG_PERFIL_NAO_ENCONTRADO));
    }

    @Override
    public List<PerfilDomain> findAllByIdUsuario(Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        return findPerfilOutput.findAllByIdUsuario(idUsuario);
    }

    private void validarAdminOuProprioUsuario(Long idUsuario){
        UsuarioDomain usuario = userCurrentInput.getUserCurrent();
        if(!usuario.getId().equals(idUsuario) && usuario.isNaoContemPerfil(RoleEnum.ROLE_ADMIN)){
            throw new ForbiddenException("Você não tem a permissão de realizar esta consulta");
        }
    }
}
