package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.exception.ForbiddenException;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateTelefoneOutput;

public class UpdateTelefoneUseCase implements UpdateTelefoneInput {

    private final UpdateTelefoneOutput updateTelefoneOutput;

    private final ValidCreateTelefoneInput validCreateTelefoneInput;

    private final FindTipoTelefoneInput findTipoTelefoneInput;

    private final FindTelefoneInput findTelefoneInput;

    private final UserCurrentInput userCurrentInput;

    public UpdateTelefoneUseCase(UpdateTelefoneOutput updateTelefoneOutput,
                                 ValidCreateTelefoneInput validCreateTelefoneInput,
                                 FindTipoTelefoneInput findTipoTelefoneInput,
                                 FindTelefoneInput findTelefoneInput,
                                 UserCurrentInput userCurrentInput) {
        this.updateTelefoneOutput = updateTelefoneOutput;
        this.validCreateTelefoneInput = validCreateTelefoneInput;
        this.findTipoTelefoneInput = findTipoTelefoneInput;
        this.findTelefoneInput = findTelefoneInput;
        this.userCurrentInput = userCurrentInput;
    }

    @Override
    public TelefoneDomain update(TelefoneDomain telefoneDomain, Long idUsuario) {
        validarAdminOuProprioUsuario(idUsuario);
        TelefoneDomain telefoneDomainEncontrado = findTelefoneInput.findByIdAndUsuarioId(telefoneDomain.getId(), idUsuario);
        DominioDomain tipoTelefoneDomainEncontrado = findTipoTelefoneInput.findById(telefoneDomain.getTipoTelefone().getId());

        validCreateTelefoneInput.validarCampos(telefoneDomain);
        validCreateTelefoneInput.validarNumeroComTipoTelefone(telefoneDomain, tipoTelefoneDomainEncontrado);

        telefoneDomainEncontrado.setNumero(telefoneDomain.getNumero());
        telefoneDomainEncontrado.setDdd(telefoneDomain.getDdd());
        telefoneDomainEncontrado.setDescricao(telefoneDomain.getDescricao());
        telefoneDomainEncontrado.setTipoTelefone(tipoTelefoneDomainEncontrado);

        return updateTelefoneOutput.update(telefoneDomainEncontrado);
    }

    private void validarAdminOuProprioUsuario(Long idUsuario){
        UsuarioDomain usuario = userCurrentInput.getUserCurrent();
        if(!usuario.getId().equals(idUsuario) && usuario.isNaoContemPerfil(RoleEnum.ROLE_ADMIN)){
            throw new ForbiddenException("Você não tem a permissão de realizar esta atualização");
        }
    }
}
