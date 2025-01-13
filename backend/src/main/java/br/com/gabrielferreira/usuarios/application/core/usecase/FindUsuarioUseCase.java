package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.exception.ForbiddenException;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.exception.UnauthorizedException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UserCurrentInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindUsuarioOutput;

import java.math.BigDecimal;
import java.util.List;

public class FindUsuarioUseCase implements FindUsuarioInput {

    private static final String MSG_USUARIO = "Usuário informado não encontrado";

    private final FindUsuarioOutput findUsuarioOutput;

    private final UserCurrentInput userCurrentInput;

    public FindUsuarioUseCase(FindUsuarioOutput findUsuarioOutput,
                              UserCurrentInput userCurrentInput) {
        this.findUsuarioOutput = findUsuarioOutput;
        this.userCurrentInput = userCurrentInput;
    }

    @Override
    public UsuarioDomain findByCpf(String cpf) {
        cpf = cpf.replaceAll("[.\\-]", "");
        UsuarioDomain usuarioDomain = findUsuarioOutput.findByCpf(cpf).
                orElseThrow(() -> new NaoEncontradoException(MSG_USUARIO));
        validarAdminOuProprioUsuario(usuarioDomain.getId());
        return usuarioDomain;
    }

    @Override
    public UsuarioDomain findByEmail(String email) {
        UsuarioDomain usuarioDomain = findUsuarioOutput.findByEmail(email).
                orElseThrow(() -> new NaoEncontradoException(MSG_USUARIO));
        validarAdminOuProprioUsuario(usuarioDomain.getId());
        return usuarioDomain;
    }

    @Override
    public UsuarioDomain findById(Long id) {
        UsuarioDomain usuarioDomain = findUsuarioOutput.findById(id).
                orElseThrow(() -> new NaoEncontradoException(MSG_USUARIO));
        validarAdminOuProprioUsuario(usuarioDomain.getId());
        return usuarioDomain;
    }

    @Override
    public List<UsuarioDomain> findAll(PageInfo pageInfo, String nome, String email, BigDecimal renda) {
        return findUsuarioOutput.findAll(pageInfo, nome, email, renda);
    }

    @Override
    public UsuarioDomain findUserDetailsByEmail(String email) {
        return findUsuarioOutput.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException(MSG_USUARIO));
    }

    @Override
    public UsuarioDomain findUserCurrentById(Long id) {
        return findUsuarioOutput.findById(id).
                orElseThrow(() -> new UnauthorizedException(MSG_USUARIO));
    }

    private void validarAdminOuProprioUsuario(Long idUsuario){
        UsuarioDomain usuario = userCurrentInput.getUserCurrent();
        if(!usuario.getId().equals(idUsuario) && usuario.isNaoContemPerfil(RoleEnum.ROLE_ADMIN)){
            throw new ForbiddenException("Você não tem a permissão de realizar esta consulta");
        }
    }
}
