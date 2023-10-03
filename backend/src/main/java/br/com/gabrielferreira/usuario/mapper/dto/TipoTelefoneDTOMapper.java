package br.com.gabrielferreira.usuario.mapper.dto;

import br.com.gabrielferreira.usuario.domain.TipoTelefoneDomain;
import br.com.gabrielferreira.usuario.dto.response.TipoTelefoneResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoTelefoneDTOMapper {

    TipoTelefoneResponseDTO toTipoTelefoneDto(TipoTelefoneDomain tipoTelefoneDomain);

    List<TipoTelefoneResponseDTO> toTipoTelefonesDtos(List<TipoTelefoneDomain> tipoTelefoneDomains);
}
