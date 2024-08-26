package br.com.gabrielferreira.usuarios.application.core.usecase;

import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.out.FindUsuarioOutput;

import java.math.BigDecimal;
import java.util.List;

public class FindUsuarioUseCase implements FindUsuarioInput {

    private static final String MSG_LOG = "Usuário informado não encontrado";

    private final FindUsuarioOutput findUsuarioOutput;

    public FindUsuarioUseCase(FindUsuarioOutput findUsuarioOutput) {
        this.findUsuarioOutput = findUsuarioOutput;
    }

    @Override
    public UsuarioDomain findByCpf(String cpf) {
        cpf = cpf.replaceAll("[.\\-]", "");
        return findUsuarioOutput.findByCpf(cpf).
                orElseThrow(() -> new NaoEncontradoException(MSG_LOG));
    }

    @Override
    public UsuarioDomain findByEmail(String email) {
        return findUsuarioOutput.findByEmail(email).
                orElseThrow(() -> new NaoEncontradoException(MSG_LOG));
    }

    @Override
    public UsuarioDomain findById(Long id) {
        return findUsuarioOutput.findById(id).
                orElseThrow(() -> new NaoEncontradoException(MSG_LOG));
    }

    @Override
    public List<UsuarioDomain> findAll(PageInfo pageInfo, String nome, String email, BigDecimal renda) {
        return findUsuarioOutput.findAll(pageInfo, nome, email, renda);
    }
}
