package br.com.gabrielferreira.usuario.mapper;

import br.com.gabrielferreira.usuario.domain.TipoAnotacaoDomain;
import br.com.gabrielferreira.usuario.dto.response.TipoAnotacaoResponseDTO;
import br.com.gabrielferreira.usuario.entity.TipoAnotacao;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoAnotacaoMapper {

    TipoAnotacaoDomain toTipoAnotacaoDomain(TipoAnotacao tipoAnotacao);

    List<TipoAnotacaoDomain> toTipoAnotacoesDomains(List<TipoAnotacao> tipoAnotacoes);

    TipoAnotacaoResponseDTO toTipoAnotacaoResponseDto(TipoAnotacaoDomain tipoAnotacaoDomain);

    List<TipoAnotacaoResponseDTO> toTipoAnotacoesResponsesDtos(List<TipoAnotacaoDomain> tipoAnotacaoDomains);
}
