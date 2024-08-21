package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    @Mapping(source = "usuarioDomain.anotacoes", target = "anotacoes", ignore = true)
    UsuarioEntity createUsuarioEntity(UsuarioDomain usuarioDomain);

    @Mapping(source = "usuarioEntity.anotacoes", target = "anotacoes", ignore = true)
    UsuarioDomain toUsuarioDomain(UsuarioEntity usuarioEntity);

    @Mapping(source = "usuarioEntity.telefone", target = "telefone", ignore = true)
    @Mapping(source = "usuarioEntity.genero", target = "genero", ignore = true)
    @Mapping(source = "usuarioEntity.anotacoes", target = "anotacoes", ignore = true)
    UsuarioDomain toOnlyUsuarioDomain(UsuarioEntity usuarioEntity);

    @Mapping(source = "usuarioDomain.anotacoes", target = "anotacoes", ignore = true)
    UsuarioEntity updateUsuarioEntity(UsuarioDomain usuarioDomain);

    @Mapping(target = "id", source = "usuarioDomainEncontrado.id")
    @Mapping(target = "nome", source = "usuarioDomain.nome")
    @Mapping(target = "email", source = "usuarioDomainEncontrado.email")
    @Mapping(target = "cpf", source = "usuarioDomainEncontrado.cpf")
    @Mapping(target = "renda", source = "usuarioDomain.renda")
    @Mapping(target = "dataNascimento", source = "usuarioDomain.dataNascimento")
    @Mapping(target = "quantidadeFilhos", source = "usuarioDomain.quantidadeFilhos")
    @Mapping(target = "telefone", source = "usuarioDomainEncontrado.telefone")
    @Mapping(target = "genero", source = "genero")
    @Mapping(target = "anotacoes", source = "usuarioDomainEncontrado.anotacoes")
    @Mapping(target = "createdAt", source = "usuarioDomainEncontrado.createdAt")
    @Mapping(target = "updatedAt", ignore = true)
    UsuarioDomain updateUsuario(UsuarioDomain usuarioDomain, UsuarioDomain usuarioDomainEncontrado, DominioDomain genero);
}
