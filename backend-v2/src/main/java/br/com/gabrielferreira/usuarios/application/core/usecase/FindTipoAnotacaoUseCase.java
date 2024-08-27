package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTipoAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindDominioOutput;

public class FindTipoAnotacaoUseCase implements FindTipoAnotacaoInput {

    private final FindDominioOutput findDominioOutput;

    public FindTipoAnotacaoUseCase(FindDominioOutput findDominioOutput) {
        this.findDominioOutput = findDominioOutput;
    }

    @Override
    public DominioDomain findByCodigo(String codigo) {
        return findDominioOutput.findByCodigoAndTipoCodigo(codigo, "TIPO_ANOTACAO")
                .orElseThrow(() -> new NaoEncontradoException("Tipo de anotação informado não encontrado"));
    }
}
