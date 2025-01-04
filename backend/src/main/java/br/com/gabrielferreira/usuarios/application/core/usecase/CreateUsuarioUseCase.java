package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateUsuarioOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.PasswordEncoderOutput;

public class CreateUsuarioUseCase implements CreateUsuarioInput {

    private final CreateUsuarioOutput createUsuarioOutput;

    private final ValidCreateUsuarioInput validCreateUsuarioInput;

    private final ValidCreateTelefoneInput validCreateTelefoneInput;

    private final FindGeneroInput findGeneroInput;

    private final FindTipoTelefoneInput findTipoTelefoneInput;

    private final FindPerfilInput findPerfilInput;

    private final PasswordEncoderOutput passwordEncoderOutput;

    public CreateUsuarioUseCase(CreateUsuarioOutput createUsuarioOutput,
                                ValidCreateUsuarioInput validCreateUsuarioInput,
                                ValidCreateTelefoneInput validCreateTelefoneInput,
                                FindGeneroInput findGeneroInput,
                                FindTipoTelefoneInput findTipoTelefoneInput,
                                FindPerfilInput findPerfilInput,
                                PasswordEncoderOutput passwordEncoderOutput){
        this.createUsuarioOutput = createUsuarioOutput;
        this.validCreateUsuarioInput = validCreateUsuarioInput;
        this.validCreateTelefoneInput = validCreateTelefoneInput;
        this.findGeneroInput = findGeneroInput;
        this.findTipoTelefoneInput = findTipoTelefoneInput;
        this.findPerfilInput = findPerfilInput;
        this.passwordEncoderOutput = passwordEncoderOutput;
    }

    @Override
    public UsuarioDomain create(UsuarioDomain usuarioDomain) {
        DominioDomain genero = findGeneroInput.findById(usuarioDomain.getGenero().getId());
        DominioDomain tipoTelefone = findTipoTelefoneInput.findById(usuarioDomain.getTelefone().getTipoTelefone().getId());
        PerfilDomain perfilDomain = findPerfilInput.findByRole(RoleEnum.ROLE_CLIENT.name());

        validCreateUsuarioInput.validarCampos(usuarioDomain);
        validCreateUsuarioInput.validarCpfExistente(usuarioDomain.getCpf());
        validCreateUsuarioInput.validarEmailExistente(usuarioDomain.getEmail());
        validCreateUsuarioInput.validarSenha(usuarioDomain.getSenha());

        validCreateTelefoneInput.validarCampos(usuarioDomain.getTelefone());
        validCreateTelefoneInput.validarNumeroComTipoTelefone(usuarioDomain.getTelefone(), tipoTelefone);

        String senhaCriptografada = passwordEncoderOutput.enconde(usuarioDomain.getSenha());

        createUsuario(usuarioDomain, genero, tipoTelefone, perfilDomain, senhaCriptografada);

        return createUsuarioOutput.create(usuarioDomain);
    }

    private void createUsuario(UsuarioDomain usuarioDomain, DominioDomain genero, DominioDomain tipoTelefone, PerfilDomain perfilDomain, String senhaCriptografada) {
        usuarioDomain.setGenero(genero);
        usuarioDomain.getTelefone().setTipoTelefone(tipoTelefone);
        usuarioDomain.getPerfis().add(perfilDomain);
        usuarioDomain.setSenha(senhaCriptografada);
    }
}
