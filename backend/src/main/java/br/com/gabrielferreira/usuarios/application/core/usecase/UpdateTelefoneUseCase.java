package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTipoTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateTelefoneOutput;

public class UpdateTelefoneUseCase implements UpdateTelefoneInput {

    private final UpdateTelefoneOutput updateTelefoneOutput;

    private final FindTipoTelefoneInput findTipoTelefoneInput;

    private final FindTelefoneInput findTelefoneInput;

    public UpdateTelefoneUseCase(UpdateTelefoneOutput updateTelefoneOutput,
                                 FindTipoTelefoneInput findTipoTelefoneInput,
                                 FindTelefoneInput findTelefoneInput) {
        this.updateTelefoneOutput = updateTelefoneOutput;
        this.findTipoTelefoneInput = findTipoTelefoneInput;
        this.findTelefoneInput = findTelefoneInput;
    }

    @Override
    public TelefoneDomain update(TelefoneDomain telefoneDomain, Long idUsuario) {
        TelefoneDomain telefoneDomainEncontrado = findTelefoneInput.findByIdAndUsuarioId(telefoneDomain.getId(), idUsuario);
        DominioDomain tipoTelefoneDomainEncontrado = findTipoTelefoneInput.findById(telefoneDomain.getTipoTelefone().getId());

        telefoneDomain.validarCampos();
        telefoneDomain.validarNumeroComTipoTelefone(tipoTelefoneDomainEncontrado);

        telefoneDomainEncontrado.setNumero(telefoneDomain.getNumero());
        telefoneDomainEncontrado.setDdd(telefoneDomain.getDdd());
        telefoneDomainEncontrado.setDescricao(telefoneDomain.getDescricao());
        telefoneDomainEncontrado.setTipoTelefone(tipoTelefoneDomainEncontrado);

        return updateTelefoneOutput.update(telefoneDomainEncontrado);
    }
}
