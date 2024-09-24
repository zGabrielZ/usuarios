package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;

public interface ValidCreateUsuarioInput {

    void validarCampos(UsuarioDomain usuarioDomain);

    void validarEmailExistente(String email);

    void validarCpfExistente(String cpf);
}
