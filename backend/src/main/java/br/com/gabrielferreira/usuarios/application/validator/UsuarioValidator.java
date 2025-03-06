package br.com.gabrielferreira.usuarios.application.validator;

import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;

public interface UsuarioValidator {

    void validarCampos(UsuarioDomain usuarioDomain);

    void validarEmailExistente(String email);

    void validarCpfExistente(String cpf);

    void validarPerfilUsuario(UsuarioDomain usuarioDomain, PerfilDomain perfilDomain, String mensagem);

    void validarSenha(String senha);
}
