package br.com.gabrielferreira.usuario.factory.dto;

import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.dto.response.UsuarioResponseDTO;
import org.springframework.data.domain.Page;

import static br.com.gabrielferreira.usuario.factory.dto.DominioDTOFactory.*;
import static br.com.gabrielferreira.usuario.factory.dto.TelefoneDTOFactory.*;

public class UsuarioDTOFactory {

    private UsuarioDTOFactory(){}

    public static UsuarioResponseDTO toUsuarioResponseDto(UsuarioDomain usuarioDomain){
        if(usuarioDomain != null){
            return new UsuarioResponseDTO(usuarioDomain.getId(), usuarioDomain.getNome(), usuarioDomain.getEmail(), usuarioDomain.getCpf(),
                    usuarioDomain.getCpfFormatado(), usuarioDomain.getRenda(), usuarioDomain.getRendaFormatada(), usuarioDomain.getDataNascimento(),
                    usuarioDomain.getQuantidadeFilhos(), toTelefoneDomain(usuarioDomain.getTelefone()), toDominioResponseDto(usuarioDomain.getGenero()), usuarioDomain.getCreatedAt(),
                    usuarioDomain.getUpdatedAt());
        }
        return null;
    }

    public static Page<UsuarioResponseDTO> toUsuariosResponsesDtos(Page<UsuarioDomain> usuarioDomains){
        return usuarioDomains.map(UsuarioDTOFactory::toUsuarioResponseDto);
    }
}
