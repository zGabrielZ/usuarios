package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "anotacoesLembretes")
public class AnotacaoLembreteDTO extends RepresentationModel<AnotacaoLembreteDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 3829229715305413480L;

    @Schema(description = "ID da anotação", example = "1")
    private Long id;

    @Schema(description = "Título da anotação", example = "Anotação #123")
    private String titulo;

    @Schema(description = "Descrição da anotação", example = "Tal anotação.....")
    private String descricao;

    @Schema(description = "Tipo anotação")
    private TipoAnotacaoLembreteDTO tipoAnotacao;

    @Schema(description = "Situação da anotação")
    private SituacaoAnotacaoLembreteDTO situacaoTipoAnotacao;

    @Schema(description = "Data lembrete da anotação", example = "2024-08-18T15:21:37.7822381-03:00")
    private ZonedDateTime dataLembrete;

    @Schema(description = "Criação da anotação", example = "2024-08-18T15:21:37.7822381-03:00")
    private ZonedDateTime createdAt;

    @Schema(description = "Edição da anotação", example = "2024-08-18T15:21:37.7822381-03:00")
    private ZonedDateTime updatedAt;
}
