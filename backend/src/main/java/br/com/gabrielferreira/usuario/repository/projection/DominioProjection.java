package br.com.gabrielferreira.usuario.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DominioProjection {

    private Long id;

    private String codigo;

    private String descricao;

    private Long idTipoDominio;

    private String codigoTipoDominio;

    private String descricaoTipoDominio;
}
