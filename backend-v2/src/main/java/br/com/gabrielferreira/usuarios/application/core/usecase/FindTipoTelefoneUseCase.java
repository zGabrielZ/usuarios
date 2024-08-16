package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTipoTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindDominioOutput;

public class FindTipoTelefoneUseCase implements FindTipoTelefoneInput {

    private final FindDominioOutput findDominioOutput;

    public FindTipoTelefoneUseCase(FindDominioOutput findDominioOutput) {
        this.findDominioOutput = findDominioOutput;
    }

    @Override
    public DominioDomain findById(Long id) {
        return findDominioOutput.findByIdAndTipoCodigo(id, "TIPO_TELEFONE")
                .orElseThrow(() -> new NaoEncontradoException("Tipo de telefone informado não encontrado"));
    }
}
