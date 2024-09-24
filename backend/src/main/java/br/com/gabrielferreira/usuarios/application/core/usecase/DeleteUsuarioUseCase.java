package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.DeleteUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.out.DeleteUsuarioOutput;

public class DeleteUsuarioUseCase implements DeleteUsuarioInput {

    private final DeleteUsuarioOutput deleteUsuarioOutput;

    private final FindUsuarioInput findUsuarioInput;

    public DeleteUsuarioUseCase(DeleteUsuarioOutput deleteUsuarioOutput,
                                FindUsuarioInput findUsuarioInput) {
        this.deleteUsuarioOutput = deleteUsuarioOutput;
        this.findUsuarioInput = findUsuarioInput;
    }

    @Override
    public void delete(Long id) {
        UsuarioDomain usuarioDomainEncontrado = findUsuarioInput.findById(id);
        deleteUsuarioOutput.delete(usuarioDomainEncontrado.getId());
    }
}
