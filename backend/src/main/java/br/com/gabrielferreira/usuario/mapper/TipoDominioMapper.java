package br.com.gabrielferreira.usuario.mapper;

import br.com.gabrielferreira.usuario.domain.TipoDominioDomain;
import br.com.gabrielferreira.usuario.dto.response.TipoDominioResponseDTO;
import br.com.gabrielferreira.usuario.entity.TipoDominio;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoDominioMapper {

    TipoDominioDomain toTipoDominioDomain(TipoDominio tipoDominio);

    List<TipoDominioDomain> toTipoDominiosDomains(List<TipoDominio> tipoDominios);

    TipoDominioResponseDTO toTipoDominioResponseDto(TipoDominioDomain tipoDominioDomain);

    List<TipoDominioResponseDTO> toTipoDominiosResponsesDtos(List<TipoDominioDomain> tipoDominioDomains);
}
