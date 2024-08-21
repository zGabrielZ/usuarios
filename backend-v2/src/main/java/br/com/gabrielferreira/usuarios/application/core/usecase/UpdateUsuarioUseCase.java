package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindGeneroInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.ValidCreateUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.out.UpdateUsuarioOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.UsuarioMapperOutput;

public class UpdateUsuarioUseCase implements UpdateUsuarioInput {

    private final UpdateUsuarioOutput updateUsuarioOutput;

    private final FindUsuarioInput findUsuarioInput;

    private final ValidCreateUsuarioInput validCreateUsuarioInput;

    private final FindGeneroInput findGeneroInput;

    private final UsuarioMapperOutput usuarioMapperOutput;

    public UpdateUsuarioUseCase(UpdateUsuarioOutput updateUsuarioOutput,
                                FindUsuarioInput findUsuarioInput,
                                ValidCreateUsuarioInput validCreateUsuarioInput,
                                FindGeneroInput findGeneroInput,
                                UsuarioMapperOutput usuarioMapperOutput) {
        this.updateUsuarioOutput = updateUsuarioOutput;
        this.findUsuarioInput = findUsuarioInput;
        this.validCreateUsuarioInput = validCreateUsuarioInput;
        this.findGeneroInput = findGeneroInput;
        this.usuarioMapperOutput = usuarioMapperOutput;
    }

    @Override
    public UsuarioDomain update(UsuarioDomain usuarioDomain) {
        UsuarioDomain usuarioDomainEncontrado = findUsuarioInput.findById(usuarioDomain.getId());
        DominioDomain generoDomainEncontrado = findGeneroInput.findById(usuarioDomain.getGenero().getId());

        validCreateUsuarioInput.validarCampos(usuarioDomain);

        UsuarioDomain usuarioDomainUpdate = usuarioMapperOutput.update(usuarioDomain, usuarioDomainEncontrado, generoDomainEncontrado);
        return updateUsuarioOutput.update(usuarioDomainUpdate);
    }
}
