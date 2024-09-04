package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindAnotacaoOutput;

public class FindAnotacaoUseCase implements FindAnotacaoInput {

    private static final String ANOTACAO_NAO_ENCONTRADA = "Anotação informado não encontrado";

    private final FindAnotacaoOutput findAnotacaoOutput;

    public FindAnotacaoUseCase(FindAnotacaoOutput findAnotacaoOutput) {
        this.findAnotacaoOutput = findAnotacaoOutput;
    }

    @Override
    public AnotacaoDomain findByIdTipoAnotacaoRascunho(Long id, Long idUsuario) {
        return findAnotacaoOutput.findByIdAndTipoAnotacaoAndIdUsuario(id, "RASCUNHO", idUsuario)
                .orElseThrow(() -> new NaoEncontradoException(ANOTACAO_NAO_ENCONTRADA));
    }

    @Override
    public AnotacaoDomain findByIdTipoAnotacaoEstudo(Long id, Long idUsuario) {
        return findAnotacaoOutput.findByIdAndTipoAnotacaoAndIdUsuario(id, "ESTUDO", idUsuario)
                .orElseThrow(() -> new NaoEncontradoException(ANOTACAO_NAO_ENCONTRADA));
    }

    @Override
    public AnotacaoDomain findByIdTipoAnotacaoLembrete(Long id, Long idUsuario) {
        return findAnotacaoOutput.findByIdAndTipoAnotacaoAndIdUsuario(id, "LEMBRETE", idUsuario)
                .orElseThrow(() -> new NaoEncontradoException(ANOTACAO_NAO_ENCONTRADA));
    }
}
