package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindGeneroInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindDominioOutput;

public class FindGeneroUseCase implements FindGeneroInput {

    private final FindDominioOutput findDominioOutput;

    public FindGeneroUseCase(FindDominioOutput findDominioOutput) {
        this.findDominioOutput = findDominioOutput;
    }

    @Override
    public DominioDomain findById(Long id) {
        return findDominioOutput.findByIdAndTipoCodigo(id, "GENERO")
                .orElseThrow(() -> new NaoEncontradoException("Gênero informado não encontrado"));
    }
}
