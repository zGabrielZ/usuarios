package br.com.gabrielferreira.usuario.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnotacaoCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -918570165334510853L;

    private String titulo;

    private String descricao;

    private TipoAnotacaoCreateRequestDTO tipoAnotacao;

    private ZonedDateTime dataLembrete;

    private ZonedDateTime dataEstudoInicio;

    private ZonedDateTime dataEstudoFim;

    private AnotacaoUsuarioCreateRequestDTO usuario;
}
