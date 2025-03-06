package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.ports.in.CreateUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindGeneroInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindPerfilInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTipoTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateUsuarioOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.PasswordEncoderOutput;
import br.com.gabrielferreira.usuarios.application.validator.TelefoneValidator;
import br.com.gabrielferreira.usuarios.application.validator.UsuarioValidator;

public class CreateUsuarioUseCase implements CreateUsuarioInput {

    private final CreateUsuarioOutput createUsuarioOutput;

    private final UsuarioValidator usuarioValidator;

    private final TelefoneValidator telefoneValidator;

    private final FindGeneroInput findGeneroInput;

    private final FindTipoTelefoneInput findTipoTelefoneInput;

    private final FindPerfilInput findPerfilInput;

    private final PasswordEncoderOutput passwordEncoderOutput;

    public CreateUsuarioUseCase(CreateUsuarioOutput createUsuarioOutput,
                                UsuarioValidator usuarioValidator,
                                TelefoneValidator telefoneValidator,
                                FindGeneroInput findGeneroInput,
                                FindTipoTelefoneInput findTipoTelefoneInput,
                                FindPerfilInput findPerfilInput,
                                PasswordEncoderOutput passwordEncoderOutput){
        this.createUsuarioOutput = createUsuarioOutput;
        this.usuarioValidator = usuarioValidator;
        this.telefoneValidator = telefoneValidator;
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

        usuarioValidator.validarCampos(usuarioDomain);
        usuarioValidator.validarCpfExistente(usuarioDomain.getCpf());
        usuarioValidator.validarEmailExistente(usuarioDomain.getEmail());
        usuarioValidator.validarSenha(usuarioDomain.getSenha());

        telefoneValidator.validarCampos(usuarioDomain.getTelefone());
        telefoneValidator.validarNumeroComTipoTelefone(usuarioDomain.getTelefone(), tipoTelefone);

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
