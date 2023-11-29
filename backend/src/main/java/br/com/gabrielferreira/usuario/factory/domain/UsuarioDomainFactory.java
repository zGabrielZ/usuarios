package br.com.gabrielferreira.usuario.factory.domain;

import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.dto.request.UsuarioCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.UsuarioUpdateRequestDTO;
import br.com.gabrielferreira.usuario.entity.Usuario;
import org.springframework.data.domain.Page;

import static br.com.gabrielferreira.usuario.factory.domain.DominioDomainFactory.*;
import static br.com.gabrielferreira.usuario.factory.domain.TelefoneDomainFactory.*;
import static br.com.gabrielferreira.usuario.utils.DataUtils.*;

public class UsuarioDomainFactory {

    private UsuarioDomainFactory(){}

    public static UsuarioDomain toCreateUsuario(UsuarioCreateRequestDTO usuarioCreateRequestDTO){
        if(usuarioCreateRequestDTO != null){
            return UsuarioDomain.builder()
                    .nome(usuarioCreateRequestDTO.getNome())
                    .email(usuarioCreateRequestDTO.getEmail())
                    .cpf(usuarioCreateRequestDTO.getCpf())
                    .renda(usuarioCreateRequestDTO.getRenda())
                    .dataNascimento(usuarioCreateRequestDTO.getDataNascimento())
                    .quantidadeFilhos(usuarioCreateRequestDTO.getQuantidadeFilhos())
                    .telefone(toCreateTelefoneDomain(usuarioCreateRequestDTO.getTelefone()))
                    .genero(toCreateGeneroDomain(usuarioCreateRequestDTO.getGenero()))
                    .build();
        }
        return null;
    }

    public static UsuarioDomain toUpdateUsuario(Long id, UsuarioUpdateRequestDTO usuarioUpdateRequestDTO){
        if(id != null && usuarioUpdateRequestDTO != null){
            return UsuarioDomain.builder()
                    .id(id)
                    .nome(usuarioUpdateRequestDTO.getNome())
                    .renda(usuarioUpdateRequestDTO.getRenda())
                    .quantidadeFilhos(usuarioUpdateRequestDTO.getQuantidadeFilhos())
                    .telefone(toCreateTelefoneDomain(usuarioUpdateRequestDTO.getTelefone()))
                    .genero(toCreateGeneroDomain(usuarioUpdateRequestDTO.getGenero()))
                    .build();
        }
        return null;
    }

    public static UsuarioDomain toUsuario(Usuario usuario){
        if(usuario != null){
            return UsuarioDomain.builder()
                    .id(usuario.getId())
                    .nome(usuario.getNome())
                    .email(usuario.getEmail())
                    .cpf(usuario.getCpf())
                    .renda(usuario.getRenda())
                    .dataNascimento(usuario.getDataNascimento())
                    .quantidadeFilhos(usuario.getQuantidadeFilhos())
                    .telefone(toTelefoneDomain(usuario.getTelefone()))
                    .genero(toDominioDomain(usuario.getGenero()))
                    .createdAt(toFusoPadraoSistema(usuario.getCreatedAt()))
                    .updatedAt(toFusoPadraoSistema(usuario.getUpdatedAt()))
                    .build();
        }
        return null;
    }

    public static Page<UsuarioDomain> toUsuariosDomains(Page<Usuario> usuarios){
        return usuarios.map(UsuarioDomainFactory::toUsuario);
    }
}
