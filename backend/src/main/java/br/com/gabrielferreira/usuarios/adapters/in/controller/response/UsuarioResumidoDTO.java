package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "usuarios")
public class UsuarioResumidoDTO extends RepresentationModel<UsuarioResumidoDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2488994172740334011L;

    @Schema(description = "ID do usuário", example = "1")
    private Long id;

    @Schema(description = "Nome do usuário", example = "Gabriel Ferreira")
    private String nome;

    @Schema(description = "E-mail do usuário", example = "test@email.com")
    private String email;

    @Schema(description = "CPF do usuário", example = "46188190061")
    private String cpf;

    @Schema(description = "CPF formatado do usuário", example = "461.881.900-61")
    private String cpfFormatado;

    @Schema(description = "Renda do usuário", example = "2500.00")
    private BigDecimal renda;

    @Schema(description = "Renda formatada do usuário", example = "R$ 2.500,00")
    private String rendaFormatada;

    @Schema(description = "Data de nascimento do usuário (ANO/MÊS/DIA)", example = "1997-12-26")
    private LocalDate dataNascimento;

    @Schema(description = "Quantidade de filhos do usuário", example = "2")
    private Integer quantidadeFilhos;

    @Schema(description = "Criação do usuário", example = "2024-08-18T15:21:37.7822381-03:00")
    private ZonedDateTime createdAt;

    @Schema(description = "Edição do usuário", example = "2024-08-18T15:21:37.7822381-03:00")
    private ZonedDateTime updatedAt;
}
