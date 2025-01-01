package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindPerfilInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindPerfilOutput;

import java.util.List;

public class FindPerfilUseCase implements FindPerfilInput {

    private static final String MSG_PERFIL_NAO_ENCONTRADO = "Perfil informado não encontrado";

    private final FindPerfilOutput findPerfilOutput;

    public FindPerfilUseCase(FindPerfilOutput findPerfilOutput) {
        this.findPerfilOutput = findPerfilOutput;
    }

    @Override
    public PerfilDomain findById(Long id) {
        return findPerfilOutput.findById(id)
                .orElseThrow(() -> new NaoEncontradoException(MSG_PERFIL_NAO_ENCONTRADO));
    }

    @Override
    public List<PerfilDomain> findAll() {
        return findPerfilOutput.findAll();
    }

    @Override
    public PerfilDomain findByRole(String role) {
        return findPerfilOutput.findByRole(role)
                .orElseThrow(() -> new NaoEncontradoException(MSG_PERFIL_NAO_ENCONTRADO));
    }

    @Override
    public PerfilDomain findByIdAndIdUsuario(Long id, Long idUsuario) {
        return findPerfilOutput.findByIdAndIdUsuario(id, idUsuario)
                .orElseThrow(() -> new NaoEncontradoException(MSG_PERFIL_NAO_ENCONTRADO));
    }

    @Override
    public List<PerfilDomain> findAllByIdUsuario(Long idUsuario) {
        return findPerfilOutput.findAllByIdUsuario(idUsuario);
    }
}
