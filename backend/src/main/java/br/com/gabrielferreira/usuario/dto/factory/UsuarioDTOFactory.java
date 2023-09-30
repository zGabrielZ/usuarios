package br.com.gabrielferreira.usuario.dto.factory;

import br.com.gabrielferreira.usuario.dto.UsuarioDTO;
import br.com.gabrielferreira.usuario.entity.Usuario;
import org.springframework.data.domain.Page;

import static br.com.gabrielferreira.usuario.dto.factory.GeneroDTOFactory.*;
import static br.com.gabrielferreira.usuario.dto.factory.TelefoneDTOFactory.*;

public class UsuarioDTOFactory {

    private UsuarioDTOFactory(){}

    public static Page<UsuarioDTO> toUsuariosDtos(Page<Usuario> usuarios){
        return usuarios.map(UsuarioDTOFactory::toUsuarioDto);
    }

    public static UsuarioDTO toUsuarioDto(Usuario usuario){
        if(usuario != null){
            return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCpf(), usuario.getRenda(),
                    usuario.getDataNascimento(), usuario.getQuantidadeFilhos(), toTelefoneDto(usuario.getTelefone()), toGeneroDto(usuario.getGenero()),
                    usuario.getCreatedAt(), usuario.getUpdatedAt());
        }
        return null;
    }
}
