package br.com.gabrielferreira.usuario.mapper;

import br.com.gabrielferreira.usuario.domain.TipoTelefoneDomain;
import br.com.gabrielferreira.usuario.dto.response.TipoTelefoneResponseDTO;
import br.com.gabrielferreira.usuario.entity.TipoTelefone;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoTelefoneMapper {

    TipoTelefoneDomain toTipoTelefoneDomain(TipoTelefone tipoTelefone);

    List<TipoTelefoneDomain> toTiposTelefonesDomains(List<TipoTelefone> tiposTelefones);

    TipoTelefoneResponseDTO toTipoTelefoneResponseDto(TipoTelefoneDomain tipoTelefoneDomain);

    List<TipoTelefoneResponseDTO> toTipoTelefonesResponsesDtos(List<TipoTelefoneDomain> tipoTelefoneDomains);
}
