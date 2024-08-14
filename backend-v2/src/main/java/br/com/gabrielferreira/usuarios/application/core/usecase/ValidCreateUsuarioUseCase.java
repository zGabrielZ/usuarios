package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.usuarios.application.ports.in.ValidCreateUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindDominioOutput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindUsuarioOutput;
import io.micrometer.common.util.StringUtils;

public class ValidCreateUsuarioUseCase implements ValidCreateUsuarioInput {

    private final FindUsuarioOutput findUsuarioOutput;

    private final FindDominioOutput findDominioOutput;

    public ValidCreateUsuarioUseCase(FindUsuarioOutput findUsuarioOutput,
                                     FindDominioOutput findDominioOutput) {
        this.findUsuarioOutput = findUsuarioOutput;
        this.findDominioOutput = findDominioOutput;
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
        findUsuarioOutput.findByEmail(email)
                .ifPresent(usuarioDomain -> {
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
    public void validarGeneroExistente(DominioDomain genero) {
        if(findDominioOutput.findByIdAndTipoCodigo(genero.getId(), "GENERO").isEmpty()){
            throw new NaoEncontradoException("Gênero informado não encontrado");
        }
    }
}
