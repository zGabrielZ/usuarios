package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindTelefoneOutput;

public class FindTelefoneUseCase implements FindTelefoneInput {

    private final FindTelefoneOutput findTelefoneOutput;

    public FindTelefoneUseCase(FindTelefoneOutput findTelefoneOutput) {
        this.findTelefoneOutput = findTelefoneOutput;
    }

    @Override
    public TelefoneDomain findByUsuarioId(Long idUsuario) {
        return findTelefoneOutput.findByUsuarioId(idUsuario)
                .orElseThrow(() -> new NaoEncontradoException("Telefone informado não encontrado"));
    }
}
