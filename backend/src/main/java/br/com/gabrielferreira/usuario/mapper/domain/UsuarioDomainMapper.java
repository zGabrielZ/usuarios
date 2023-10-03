package br.com.gabrielferreira.usuario.mapper.domain;

import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.dto.request.UsuarioCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.UsuarioUpdateRequestDTO;
import br.com.gabrielferreira.usuario.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UsuarioDomainMapper {

    UsuarioDomain toUsuarioDomain(UsuarioCreateRequestDTO usuarioCreateRequestDTO);

    UsuarioDomain toUsuarioDomain(UsuarioUpdateRequestDTO usuarioUpdateRequestDTO);

    @Mapping(target = "anotacoes", ignore = true)
    UsuarioDomain toUsuarioDomain(Usuario usuario);

    default UsuarioDomain toUsuarioDomain(Long id, UsuarioUpdateRequestDTO usuarioUpdateRequestDTO){
        UsuarioDomain usuarioDomain = toUsuarioDomain(usuarioUpdateRequestDTO);
        usuarioDomain.setId(id);
        return usuarioDomain;
    }

    default void updateUsuarioDomain(UsuarioDomain usuarioDomainEncontrado, UsuarioDomain usuarioDomainUpdate){
        if(usuarioDomainEncontrado != null && usuarioDomainUpdate != null){
            usuarioDomainEncontrado.setNome(usuarioDomainUpdate.getNome());
            usuarioDomainEncontrado.setRenda(usuarioDomainUpdate.getRenda());
            usuarioDomainEncontrado.setQuantidadeFilhos(usuarioDomainUpdate.getQuantidadeFilhos());
            usuarioDomainEncontrado.getTelefone().setNumero(usuarioDomainUpdate.getTelefone().getNumero());
            usuarioDomainEncontrado.getTelefone().setDdd(usuarioDomainUpdate.getTelefone().getDdd());
            usuarioDomainEncontrado.getTelefone().setDescricao(usuarioDomainUpdate.getTelefone().getDescricao());
        }
    }

    default Page<UsuarioDomain> toUsuariosDomains(Page<Usuario> usuarios){
        return usuarios.map(this::toUsuarioDomain);
    }
}
