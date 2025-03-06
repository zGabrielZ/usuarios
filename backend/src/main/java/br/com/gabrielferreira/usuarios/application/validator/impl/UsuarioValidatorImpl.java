package br.com.gabrielferreira.usuarios.application.validator.impl;

import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.usuarios.application.ports.out.FindUsuarioOutput;
import br.com.gabrielferreira.usuarios.application.validator.UsuarioValidator;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.gabrielferreira.usuarios.common.utils.CaracteresUtils.*;

@Component
@RequiredArgsConstructor
public class UsuarioValidatorImpl implements UsuarioValidator {

    private final FindUsuarioOutput findUsuarioOutput;

    @Override
    public void validarCampos(UsuarioDomain usuarioDomain) {
        usuarioDomain.setNome(usuarioDomain.getNome().trim());

        if (!StringUtils.isBlank(usuarioDomain.getEmail())) {
            usuarioDomain.setEmail(usuarioDomain.getEmail().trim());
        }

        if (!StringUtils.isBlank(usuarioDomain.getCpf())) {
            usuarioDomain.setCpf(usuarioDomain.getCpf().trim());
        }
    }

    @Override
    public void validarEmailExistente(String email) {
        findUsuarioOutput.findByEmail(email).
                ifPresent(usuarioDomain -> {
                    throw new RegraDeNegocioException(String.format("Não vai ser possível cadastrar este usuário pois o e-mail '%s' já foi cadastrado", usuarioDomain.getEmail()));
                });
    }

    @Override
    public void validarCpfExistente(String cpf) {
        findUsuarioOutput.findByCpf(cpf)
                .ifPresent(usuarioDomain -> {
                    throw new RegraDeNegocioException(String.format("Não vai ser possível cadastrar este usuário pois o CPF '%s' já foi cadastrado", usuarioDomain.getCpfFormatado()));
                });
    }

    @Override
    public void validarPerfilUsuario(UsuarioDomain usuarioDomain, PerfilDomain perfilDomain, String mensagem) {
        List<Long> idsPerfis = usuarioDomain.getPerfis().stream().map(PerfilDomain::getId).toList();
        if (idsPerfis.contains(perfilDomain.getId())) {
            throw new RegraDeNegocioException(mensagem);
        }
    }

    @Override
    public void validarSenha(String senha) {
        if (!isPossuiCaracteresEspecias(senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos uma caractere especial");
        }

        if (!isPossuiCaractereMaiusculas(senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos uma caractere maiúsculas");
        }

        if (!isPossuiCaractereMinusculas(senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos uma caractere minúsculas");
        }

        if (!isPossuiCaractereDigito(senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos um caractere dígito");
        }
    }
}
