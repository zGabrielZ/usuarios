package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindAnotacaoOutput;

public class FindAnotacaoUseCase implements FindAnotacaoInput {

    private final FindAnotacaoOutput findAnotacaoOutput;

    public FindAnotacaoUseCase(FindAnotacaoOutput findAnotacaoOutput) {
        this.findAnotacaoOutput = findAnotacaoOutput;
    }

    @Override
    public AnotacaoDomain findByIdTipoAnotacaoRascunho(Long id, Long idUsuario) {
        return findAnotacaoOutput.findByIdAndTipoAnotacaoAndIdUsuario(id, "RASCUNHO", idUsuario)
                .orElseThrow(() -> new NaoEncontradoException("Anotação informado não encontrado"));
    }

    @Override
    public AnotacaoDomain findByIdTipoAnotacaoEstudo(Long id, Long idUsuario) {
        return null;
    }

    @Override
    public AnotacaoDomain findByIdTipoAnotacaoLembrete(Long id, Long idUsuario) {
        return findAnotacaoOutput.findByIdAndTipoAnotacaoAndIdUsuario(id, "LEMBRETE", idUsuario)
                .orElseThrow(() -> new NaoEncontradoException("Anotação informado não encontrado"));
    }
}
