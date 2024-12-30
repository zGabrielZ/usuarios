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
@Relation(collectionRelation = "perfis")
public class PerfilDTO extends RepresentationModel<PerfilDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = -7448476876939143727L;

    @Schema(description = "ID do perfil", example = "1")
    private Long id;

    @Schema(description = "Título do perfil", example = "Administrador")
    private String titulo;

    @Schema(description = "Autoriedade do perfil", example = "ROLE_ADMIN")
    private String autoriedade;
}
