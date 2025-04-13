package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.usuarios.application.ports.in.CreateUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindGeneroInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindPerfilInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTipoTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.out.CreateUsuarioOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindUsuarioOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.PasswordEncoderOutput;

public class CreateUsuarioUseCase implements CreateUsuarioInput {

    private final CreateUsuarioOutput createUsuarioOutput;

    private final FindUsuarioOutput findUsuarioOutput;

    private final FindGeneroInput findGeneroInput;

    private final FindTipoTelefoneInput findTipoTelefoneInput;

    private final FindPerfilInput findPerfilInput;

    private final PasswordEncoderOutput passwordEncoderOutput;

    public CreateUsuarioUseCase(CreateUsuarioOutput createUsuarioOutput,
                                FindUsuarioOutput findUsuarioOutput,
                                FindGeneroInput findGeneroInput,
                                FindTipoTelefoneInput findTipoTelefoneInput,
                                FindPerfilInput findPerfilInput,
                                PasswordEncoderOutput passwordEncoderOutput){
        this.createUsuarioOutput = createUsuarioOutput;
        this.findUsuarioOutput = findUsuarioOutput;
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

        usuarioDomain.validarCampos();
        validarCpfExistente(usuarioDomain.getCpf());
        validarEmailExistente(usuarioDomain.getEmail());
        usuarioDomain.validarSenha(usuarioDomain.getSenha());

        usuarioDomain.getTelefone().validarCampos();
        usuarioDomain.getTelefone().validarNumeroComTipoTelefone(tipoTelefone);

        String senhaCriptografada = passwordEncoderOutput.enconde(usuarioDomain.getSenha());

        createUsuario(usuarioDomain, genero, tipoTelefone, perfilDomain, senhaCriptografada);

        return createUsuarioOutput.create(usuarioDomain);
    }

    private void validarEmailExistente(String email) {
        findUsuarioOutput.findByEmail(email).
                ifPresent(usuarioDomain -> {
                    throw new RegraDeNegocioException(String.format("Não vai ser possível cadastrar este usuário pois o e-mail '%s' já foi cadastrado", usuarioDomain.getEmail()));
                });
    }

    private void validarCpfExistente(String cpf) {
        findUsuarioOutput.findByCpf(cpf)
                .ifPresent(usuarioDomain -> {
                    throw new RegraDeNegocioException(String.format("Não vai ser possível cadastrar este usuário pois o CPF '%s' já foi cadastrado", usuarioDomain.getCpfFormatado()));
                });
    }

    private void createUsuario(UsuarioDomain usuarioDomain, DominioDomain genero, DominioDomain tipoTelefone, PerfilDomain perfilDomain, String senhaCriptografada) {
        usuarioDomain.setGenero(genero);
        usuarioDomain.getTelefone().setTipoTelefone(tipoTelefone);
        usuarioDomain.getPerfis().add(perfilDomain);
        usuarioDomain.setSenha(senhaCriptografada);
    }
}
