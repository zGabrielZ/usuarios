package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindUsuarioOutput;

public class FindUsuarioUseCase implements FindUsuarioInput {

    private final FindUsuarioOutput findUsuarioOutput;

    public FindUsuarioUseCase(FindUsuarioOutput findUsuarioOutput) {
        this.findUsuarioOutput = findUsuarioOutput;
    }

    @Override
    public UsuarioDomain findByCpf(String cpf) {
        return findUsuarioOutput.findByCpf(cpf).orElse(null);
    }

    @Override
    public UsuarioDomain findByEmail(String email) {
        return findUsuarioOutput.findByEmail(email).orElse(null);
    }

    @Override
    public UsuarioDomain findById(Long id) {
        return findUsuarioOutput.findById(id).
                orElseThrow(() -> new NaoEncontradoException("Usuário informado não encontrado"));
    }
}
