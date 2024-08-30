package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoRascunhoInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindAnotacaoRascunhoOutput;

public class FindAnotacaoRascunhoUseCase implements FindAnotacaoRascunhoInput {

    private final FindAnotacaoRascunhoOutput findAnotacaoRascunhoOutput;

    public FindAnotacaoRascunhoUseCase(FindAnotacaoRascunhoOutput findAnotacaoRascunhoOutput) {
        this.findAnotacaoRascunhoOutput = findAnotacaoRascunhoOutput;
    }

    @Override
    public AnotacaoDomain findByIdTipoAnotacaoRascunho(Long id, Long idUsuario) {
        return findAnotacaoRascunhoOutput.findByIdAndTipoAnotacaoAndIdUsuario(id, "RASCUNHO", idUsuario)
                .orElseThrow(() -> new NaoEncontradoException("Anotação informado não encontrado"));
    }
}
