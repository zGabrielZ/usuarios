package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.exception.ForbiddenException;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UserCurrentInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindTelefoneOutput;

public class FindTelefoneUseCase implements FindTelefoneInput {

    private final FindTelefoneOutput findTelefoneOutput;

    private final UserCurrentInput userCurrentInput;

    public FindTelefoneUseCase(FindTelefoneOutput findTelefoneOutput,
                               UserCurrentInput userCurrentInput) {
        this.findTelefoneOutput = findTelefoneOutput;
        this.userCurrentInput = userCurrentInput;
    }

    @Override
    public TelefoneDomain findByUsuarioId(Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        return findTelefoneOutput.findByUsuarioId(idUsuario)
                .orElseThrow(() -> new NaoEncontradoException("Telefone informado não encontrado"));
    }

    @Override
    public TelefoneDomain findByIdAndUsuarioId(Long id, Long idUsuario) {
        return findTelefoneOutput.findByIdAndUsuarioId(id, idUsuario)
                .orElseThrow(() -> new NaoEncontradoException("Telefone informado não encontrado"));
    }

    private void validarAdminOuProprioUsuario(Long idUsuario){
        UsuarioDomain usuario = userCurrentInput.getUserCurrent();
        if(!usuario.getId().equals(idUsuario) && usuario.isNaoContemPerfil(RoleEnum.ROLE_ADMIN)){
            throw new ForbiddenException("Você não tem a permissão de realizar esta consulta");
        }
    }
}
