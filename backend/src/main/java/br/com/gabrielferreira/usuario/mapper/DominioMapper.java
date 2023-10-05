package br.com.gabrielferreira.usuario.mapper;

import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.dto.response.DominioResponseDTO;
import br.com.gabrielferreira.usuario.entity.Dominio;
import br.com.gabrielferreira.usuario.repository.projection.DominioProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = TipoDominioMapper.class)
public interface DominioMapper {


    DominioDomain toDominioDomain(Dominio dominio);

    @Mapping(source = "idTipoDominio", target = "tipo.id")
    @Mapping(source = "codigoTipoDominio", target = "tipo.codigo")
    @Mapping(source = "descricaoTipoDominio", target = "tipo.descricao")
    DominioDomain toDominioDomain(DominioProjection dominioProjection);

    List<DominioDomain> toDominiosDomains(List<DominioProjection> dominioProjections);

    DominioResponseDTO toDominioResponseDto(DominioDomain dominioDomain);

    List<DominioResponseDTO> toDominiosResponsesDtos(List<DominioDomain> dominioDomains);
}
