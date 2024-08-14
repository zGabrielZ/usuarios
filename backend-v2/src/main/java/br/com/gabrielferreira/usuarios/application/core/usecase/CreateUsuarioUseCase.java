package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.CreateUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.ValidCreateTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.ValidCreateUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateUsuarioOutput;

public class CreateUsuarioUseCase implements CreateUsuarioInput {

    private final CreateUsuarioOutput createUsuarioOutput;

    private final ValidCreateUsuarioInput validCreateUsuarioInput;

    private final ValidCreateTelefoneInput validCreateTelefoneInput;

    public CreateUsuarioUseCase(CreateUsuarioOutput createUsuarioOutput,
                                ValidCreateUsuarioInput validCreateUsuarioInput,
                                ValidCreateTelefoneInput validCreateTelefoneInput) {
        this.createUsuarioOutput = createUsuarioOutput;
        this.validCreateUsuarioInput = validCreateUsuarioInput;
        this.validCreateTelefoneInput = validCreateTelefoneInput;
    }

    @Override
    public UsuarioDomain create(UsuarioDomain usuarioDomain) {
        validCreateUsuarioInput.validarCampos(usuarioDomain);
        validCreateUsuarioInput.validarCpfExistente(usuarioDomain.getCpf());
        validCreateUsuarioInput.validarEmailExistente(usuarioDomain.getEmail());
        validCreateUsuarioInput.validarGeneroExistente(usuarioDomain.getGenero());

        validCreateTelefoneInput.validarCampos(usuarioDomain.getTelefone());
        validCreateTelefoneInput.validarTipoTelefoneExistente(usuarioDomain.getTelefone().getTipoTelefone());
        validCreateTelefoneInput.validarNumeroComTipoTelefone(usuarioDomain.getTelefone(), usuarioDomain.getTelefone().getTipoTelefone());

        return createUsuarioOutput.create(usuarioDomain);
    }
}
