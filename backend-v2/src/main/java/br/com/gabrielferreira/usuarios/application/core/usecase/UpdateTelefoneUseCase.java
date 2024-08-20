package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTipoTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.ValidCreateTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.out.TelefoneMapperOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateTelefoneOutput;

public class UpdateTelefoneUseCase implements UpdateTelefoneInput {

    private final UpdateTelefoneOutput updateTelefoneOutput;

    private final TelefoneMapperOutput telefoneMapperOutput;

    private final ValidCreateTelefoneInput validCreateTelefoneInput;

    private final FindTipoTelefoneInput findTipoTelefoneInput;

    private final FindTelefoneInput findTelefoneInput;

    public UpdateTelefoneUseCase(UpdateTelefoneOutput updateTelefoneOutput,
                                 ValidCreateTelefoneInput validCreateTelefoneInput,
                                 FindTipoTelefoneInput findTipoTelefoneInput,
                                 FindTelefoneInput findTelefoneInput,
                                 TelefoneMapperOutput telefoneMapperOutput) {
        this.updateTelefoneOutput = updateTelefoneOutput;
        this.validCreateTelefoneInput = validCreateTelefoneInput;
        this.findTipoTelefoneInput = findTipoTelefoneInput;
        this.findTelefoneInput = findTelefoneInput;
        this.telefoneMapperOutput = telefoneMapperOutput;
    }

    @Override
    public TelefoneDomain update(TelefoneDomain telefoneDomain, Long idUsuario) {
        TelefoneDomain telefoneDomainEncontrado = findTelefoneInput.findByIdAndUsuarioId(telefoneDomain.getId(), idUsuario);
        DominioDomain tipoTelefoneDomainEncontrado = findTipoTelefoneInput.findById(telefoneDomain.getTipoTelefone().getId());
        validCreateTelefoneInput.validarCampos(telefoneDomain);
        validCreateTelefoneInput.validarNumeroComTipoTelefone(telefoneDomain, tipoTelefoneDomainEncontrado);

        TelefoneDomain telefoneDomainUpdate = telefoneMapperOutput.update(telefoneDomain, telefoneDomainEncontrado, tipoTelefoneDomainEncontrado);
        return updateTelefoneOutput.update(telefoneDomainUpdate);
    }
}
