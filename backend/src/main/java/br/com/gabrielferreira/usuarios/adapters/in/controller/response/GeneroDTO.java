package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "generos")
public class GeneroDTO extends RepresentationModel<GeneroDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 916085808967760872L;

    @Schema(description = "ID do gênero", example = "1")
    private Long id;

    @Schema(description = "Descrição do gênero", example = "Masculino")
    private String descricao;

    @Schema(description = "Código do gênero", example = "MASCULINO")
    private String codigo;

    @Schema(description = "Tipo do gênero")
    private TipoGeneroDTO tipo;
}
