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
@Relation(collectionRelation = "tiposTelefones")
public class TipoTelefoneDTO extends RepresentationModel<TipoTelefoneDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2805458891273188123L;

    @Schema(description = "ID do tipo de telefone", example = "4")
    private Long id;

    @Schema(description = "Descrição do tipo de telefone", example = "Residencial")
    private String descricao;

    @Schema(description = "Código do tipo de telefone", example = "RESIDENCIAL")
    private String codigo;

    @Schema(description = "Tipo do telefone")
    private TipoTipoTelefoneDTO tipo;
}
