package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.ValidCreateUsuarioInput;
import io.micrometer.common.util.StringUtils;

public class ValidCreateUsuarioUseCase implements ValidCreateUsuarioInput {

    private final FindUsuarioInput findUsuarioInput;

    public ValidCreateUsuarioUseCase(FindUsuarioInput findUsuarioInput) {
        this.findUsuarioInput = findUsuarioInput;
    }

    @Override
    public void validarCampos(UsuarioDomain usuarioDomain) {
        usuarioDomain.setNome(usuarioDomain.getNome().trim());

        if(!StringUtils.isBlank(usuarioDomain.getEmail())){
            usuarioDomain.setEmail(usuarioDomain.getEmail().trim());
        }

        if(!StringUtils.isBlank(usuarioDomain.getCpf())){
            usuarioDomain.setCpf(usuarioDomain.getCpf().trim());
        }
    }

    @Override
    public void validarEmailExistente(String email) {
        UsuarioDomain usuarioDomain = findUsuarioInput.findByEmail(email);
        if(usuarioDomain != null){
            throw new RegraDeNegocioException(String.format("Não vai ser possível cadastrar este usuário pois o e-mail '%s' já foi cadastrado", usuarioDomain.getEmail()));
        }
    }

    @Override
    public void validarCpfExistente(String cpf) {
        UsuarioDomain usuarioDomain = findUsuarioInput.findByCpf(cpf);
        if(usuarioDomain != null){
            throw new RegraDeNegocioException(String.format("Não vai ser possível cadastrar este usuário pois o CPF '%s' já foi cadastrado", usuarioDomain.getCpfFormatado()));
        }
    }
}
