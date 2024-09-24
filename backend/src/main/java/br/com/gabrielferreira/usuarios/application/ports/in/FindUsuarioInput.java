package br.com.gabrielferreira.usuarios.application.ports.in;

import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;

import java.math.BigDecimal;
import java.util.List;

public interface FindUsuarioInput {

    UsuarioDomain findByCpf(String cpf);

    UsuarioDomain findByEmail(String email);

    UsuarioDomain findById(Long id);

    List<UsuarioDomain> findAll(PageInfo pageInfo, String nome, String email, BigDecimal renda);
}
