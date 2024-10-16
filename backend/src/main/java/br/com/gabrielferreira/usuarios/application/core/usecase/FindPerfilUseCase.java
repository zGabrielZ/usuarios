package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindPerfilInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindPerfilOutput;

import java.util.List;

public class FindPerfilUseCase implements FindPerfilInput {

    private final FindPerfilOutput findPerfilOutput;

    public FindPerfilUseCase(FindPerfilOutput findPerfilOutput) {
        this.findPerfilOutput = findPerfilOutput;
    }

    @Override
    public PerfilDomain findById(Long id) {
        return findPerfilOutput.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Perfil informado não encontrado"));
    }

    @Override
    public List<PerfilDomain> findAll() {
        return findPerfilOutput.findAll();
    }
}
