package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTipoTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UserCurrentInput;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateTelefoneOutput;
import br.com.gabrielferreira.usuarios.application.validator.TelefoneValidator;

public class UpdateTelefoneUseCase implements UpdateTelefoneInput {

    private final UpdateTelefoneOutput updateTelefoneOutput;

    private final TelefoneValidator telefoneValidator;

    private final FindTipoTelefoneInput findTipoTelefoneInput;

    private final FindTelefoneInput findTelefoneInput;

    private final UserCurrentInput userCurrentInput;

    public UpdateTelefoneUseCase(UpdateTelefoneOutput updateTelefoneOutput,
                                 TelefoneValidator telefoneValidator,
                                 FindTipoTelefoneInput findTipoTelefoneInput,
                                 FindTelefoneInput findTelefoneInput,
                                 UserCurrentInput userCurrentInput) {
        this.updateTelefoneOutput = updateTelefoneOutput;
        this.telefoneValidator = telefoneValidator;
        this.findTipoTelefoneInput = findTipoTelefoneInput;
        this.findTelefoneInput = findTelefoneInput;
        this.userCurrentInput = userCurrentInput;
    }

    @Override
    public TelefoneDomain update(TelefoneDomain telefoneDomain, Long idUsuario) {
        userCurrentInput.validarAdminOuProprioUsuario(idUsuario);
        TelefoneDomain telefoneDomainEncontrado = findTelefoneInput.findByIdAndUsuarioId(telefoneDomain.getId(), idUsuario);
        DominioDomain tipoTelefoneDomainEncontrado = findTipoTelefoneInput.findById(telefoneDomain.getTipoTelefone().getId());

        telefoneValidator.validarCampos(telefoneDomain);
        telefoneValidator.validarNumeroComTipoTelefone(telefoneDomain, tipoTelefoneDomainEncontrado);

        telefoneDomainEncontrado.setNumero(telefoneDomain.getNumero());
        telefoneDomainEncontrado.setDdd(telefoneDomain.getDdd());
        telefoneDomainEncontrado.setDescricao(telefoneDomain.getDescricao());
        telefoneDomainEncontrado.setTipoTelefone(tipoTelefoneDomainEncontrado);

        return updateTelefoneOutput.update(telefoneDomainEncontrado);
    }
}
