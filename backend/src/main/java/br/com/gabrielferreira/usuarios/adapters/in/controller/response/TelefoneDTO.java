package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TelefoneDTO extends RepresentationModel<TelefoneDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2913444801073559323L;

    @Schema(description = "ID de telefone do usuário", example = "1")
    private Long id;

    @Schema(description = "Número de telefone do usuário", example = "34655691")
    private String numero;

    @Schema(description = "DDD do número do telefone do usuário", example = "11")
    private String ddd;

    @Schema(description = "Número de telefone formatado", example = "(11) 3465-5691")
    private String telefoneFormatado;

    @Schema(description = "Descrição de telefone do usuário", example = "Número da tal pessoa...")
    private String descricao;

    @Schema(description = "Tipo de telefone do telefone do usuário")
    private TipoTelefoneDTO tipoTelefone;

    @Schema(description = "Criação do telefone", example = "2024-08-18T15:21:37.7822381Z")
    private ZonedDateTime createdAt;

    @Schema(description = "Edição do telefone", example = "2024-08-18T15:21:37.7822381Z")
    private ZonedDateTime updatedAt;
}
