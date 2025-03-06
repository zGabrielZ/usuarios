package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.DeleteUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UserCurrentInput;
import br.com.gabrielferreira.usuarios.application.ports.out.DeleteUsuarioOutput;

public class DeleteUsuarioUseCase implements DeleteUsuarioInput {

    private final DeleteUsuarioOutput deleteUsuarioOutput;

    private final FindUsuarioInput findUsuarioInput;

    private final UserCurrentInput userCurrentInput;

    public DeleteUsuarioUseCase(DeleteUsuarioOutput deleteUsuarioOutput,
                                FindUsuarioInput findUsuarioInput,
                                UserCurrentInput userCurrentInput) {
        this.deleteUsuarioOutput = deleteUsuarioOutput;
        this.findUsuarioInput = findUsuarioInput;
        this.userCurrentInput = userCurrentInput;
    }

    @Override
    public void delete(Long id) {
        userCurrentInput.validarAdminExclusao(id);
        UsuarioDomain usuarioDomainEncontrado = findUsuarioInput.findById(id);
        deleteUsuarioOutput.delete(usuarioDomainEncontrado.getId());
    }
}
