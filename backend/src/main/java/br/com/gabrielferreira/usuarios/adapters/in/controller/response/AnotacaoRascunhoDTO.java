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
@Relation(collectionRelation = "anotacoesRascunhos")
public class AnotacaoRascunhoDTO extends RepresentationModel<AnotacaoRascunhoDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = -1972813319217023697L;

    @Schema(description = "ID da anotação", example = "1")
    private Long id;

    @Schema(description = "Título da anotação", example = "Anotação #123")
    private String titulo;

    @Schema(description = "Descrição da anotação", example = "Tal anotação.....")
    private String descricao;

    @Schema(description = "Tipo anotação")
    private TipoAnotacaoRascunhoDTO tipoAnotacao;

    @Schema(description = "Situação da anotação")
    private SituacaoAnotacaoRascunhoDTO situacaoTipoAnotacao;

    @Schema(description = "Criação da anotação", example = "2024-08-18T15:21:37.7822381Z")
    private ZonedDateTime createdAt;

    @Schema(description = "Edição da anotação", example = "2024-08-18T15:21:37.7822381Z")
    private ZonedDateTime updatedAt;
}
