package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateUsuarioOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.PasswordEncoderOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.UsuarioMapperOutput;

public class CreateUsuarioUseCase implements CreateUsuarioInput {

    private final CreateUsuarioOutput createUsuarioOutput;

    private final ValidCreateUsuarioInput validCreateUsuarioInput;

    private final ValidCreateTelefoneInput validCreateTelefoneInput;

    private final FindGeneroInput findGeneroInput;

    private final FindTipoTelefoneInput findTipoTelefoneInput;

    private final FindPerfilInput findPerfilInput;

    private final UsuarioMapperOutput usuarioMapperOutput;

    private final PasswordEncoderOutput passwordEncoderOutput;

    public CreateUsuarioUseCase(CreateUsuarioOutput createUsuarioOutput,
                                ValidCreateUsuarioInput validCreateUsuarioInput,
                                ValidCreateTelefoneInput validCreateTelefoneInput,
                                FindGeneroInput findGeneroInput,
                                FindTipoTelefoneInput findTipoTelefoneInput,
                                UsuarioMapperOutput usuarioMapperOutput,
                                FindPerfilInput findPerfilInput,
                                PasswordEncoderOutput passwordEncoderOutput){
        this.createUsuarioOutput = createUsuarioOutput;
        this.validCreateUsuarioInput = validCreateUsuarioInput;
        this.validCreateTelefoneInput = validCreateTelefoneInput;
        this.findGeneroInput = findGeneroInput;
        this.findTipoTelefoneInput = findTipoTelefoneInput;
        this.usuarioMapperOutput = usuarioMapperOutput;
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
        UsuarioDomain usuarioDomainCreate = usuarioMapperOutput.createUsuarioDomain(usuarioDomain, genero, tipoTelefone, perfilDomain, senhaCriptografada);
        return createUsuarioOutput.create(usuarioDomainCreate);
    }
}
