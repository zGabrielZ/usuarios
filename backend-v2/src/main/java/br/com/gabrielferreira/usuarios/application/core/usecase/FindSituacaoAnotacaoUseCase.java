package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindSituacaoAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindDominioOutput;

public class FindSituacaoAnotacaoUseCase implements FindSituacaoAnotacaoInput {

    private final FindDominioOutput findDominioOutput;

    public FindSituacaoAnotacaoUseCase(FindDominioOutput findDominioOutput) {
        this.findDominioOutput = findDominioOutput;
    }

    @Override
    public DominioDomain findByCodigo(String codigo) {
        return findDominioOutput.findByCodigoAndTipoCodigo(codigo, "SITUACAO_TIPO_ANOTACAO")
                .orElseThrow(() -> new NaoEncontradoException("Situação da anotação informado não encontrado"));
    }
}
