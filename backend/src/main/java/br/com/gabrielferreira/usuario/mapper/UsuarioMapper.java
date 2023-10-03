package br.com.gabrielferreira.usuario.mapper;

import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.dto.request.UsuarioCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.UsuarioUpdateRequestDTO;
import br.com.gabrielferreira.usuario.dto.response.UsuarioResponseDTO;
import br.com.gabrielferreira.usuario.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = {GeneroMapper.class, TipoTelefoneMapper.class})
public interface UsuarioMapper {

    Usuario toUsuario(UsuarioDomain usuarioDomain);

    @Mapping(target = "anotacoes", ignore = true)
    UsuarioDomain toUsuarioWithoutAnotacoesDomain(Usuario usuario);

    UsuarioDomain toUsuarioDomain(UsuarioCreateRequestDTO usuarioCreateRequestDTO);

    UsuarioResponseDTO toUsuarioResponseDto(UsuarioDomain usuarioDomain);

    UsuarioDomain toUsuarioDomain(UsuarioUpdateRequestDTO usuarioUpdateRequestDTO);

    default UsuarioDomain toUsuarioDomain(Long id, UsuarioUpdateRequestDTO usuarioUpdateRequestDTO){
        UsuarioDomain usuarioDomain = toUsuarioDomain(usuarioUpdateRequestDTO);
        usuarioDomain.setId(id);
        return usuarioDomain;
    }

    default Page<UsuarioDomain> toUsuariosDomains(Page<Usuario> usuarios){
        return usuarios.map(this::toUsuarioWithoutAnotacoesDomain);
    }

    default Page<UsuarioResponseDTO> toUsuariosResponsesDtos(Page<UsuarioDomain> usuarioDomains){
        return usuarioDomains.map(this::toUsuarioResponseDto);
    }

}
