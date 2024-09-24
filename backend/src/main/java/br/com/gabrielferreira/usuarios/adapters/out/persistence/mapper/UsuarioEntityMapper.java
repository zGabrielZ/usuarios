package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

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

    default UsuarioDomain updateUsuario(UsuarioDomain usuarioDomain, UsuarioDomain usuarioDomainEncontrado, DominioDomain genero){
        usuarioDomainEncontrado.setNome(usuarioDomain.getNome());
        usuarioDomainEncontrado.setRenda(usuarioDomain.getRenda());
        usuarioDomainEncontrado.setDataNascimento(usuarioDomain.getDataNascimento());
        usuarioDomainEncontrado.setQuantidadeFilhos(usuarioDomain.getQuantidadeFilhos());
        usuarioDomainEncontrado.setGenero(genero);
        return usuarioDomainEncontrado;
    }

    default List<UsuarioDomain> toUsuariosDomains(Page<UsuarioEntity> usuarioEntities){
        return usuarioEntities.stream().map(this::toOnlyUsuarioDomain).toList();
    }

    default UsuarioDomain createUsuarioDomain(UsuarioDomain usuarioDomain, DominioDomain generoDomain, DominioDomain tipoTelefoneDomain){
        usuarioDomain.setGenero(generoDomain);
        usuarioDomain.getTelefone().setTipoTelefone(tipoTelefoneDomain);
        return usuarioDomain;
    }
}
