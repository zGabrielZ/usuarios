package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateUsuarioOutput;

public class CreateUsuarioUseCase implements CreateUsuarioInput {

    private final CreateUsuarioOutput createUsuarioOutput;

    private final ValidCreateUsuarioInput validCreateUsuarioInput;

    private final ValidCreateTelefoneInput validCreateTelefoneInput;

    private final FindGeneroInput findGeneroInput;

    private final FindTipoTelefoneInput findTipoTelefoneInput;

    public CreateUsuarioUseCase(CreateUsuarioOutput createUsuarioOutput,
                                ValidCreateUsuarioInput validCreateUsuarioInput,
                                ValidCreateTelefoneInput validCreateTelefoneInput,
                                FindGeneroInput findGeneroInput,
                                FindTipoTelefoneInput findTipoTelefoneInput){
        this.createUsuarioOutput = createUsuarioOutput;
        this.validCreateUsuarioInput = validCreateUsuarioInput;
        this.validCreateTelefoneInput = validCreateTelefoneInput;
        this.findGeneroInput = findGeneroInput;
        this.findTipoTelefoneInput = findTipoTelefoneInput;
    }

    @Override
    public UsuarioDomain create(UsuarioDomain usuarioDomain) {
        DominioDomain genero = findGeneroInput.findById(usuarioDomain.getGenero().getId());
        DominioDomain tipoTelefone = findTipoTelefoneInput.findById(usuarioDomain.getTelefone().getTipoTelefone().getId());

        validCreateUsuarioInput.validarCampos(usuarioDomain);
        validCreateUsuarioInput.validarCpfExistente(usuarioDomain.getCpf());
        validCreateUsuarioInput.validarEmailExistente(usuarioDomain.getEmail());

        validCreateTelefoneInput.validarCampos(usuarioDomain.getTelefone());
        validCreateTelefoneInput.validarNumeroComTipoTelefone(usuarioDomain.getTelefone(), tipoTelefone);

        usuarioDomain.setGenero(genero);
        usuarioDomain.getTelefone().setTipoTelefone(tipoTelefone);

        return createUsuarioOutput.create(usuarioDomain);
    }
}
