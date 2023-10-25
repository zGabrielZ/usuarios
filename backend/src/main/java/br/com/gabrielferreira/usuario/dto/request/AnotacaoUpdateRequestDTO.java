package br.com.gabrielferreira.usuario.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class AnotacaoUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -918570165334510853L;

    @NotBlank(message = "É necessário informar o título da anotação")
    @Size(max = 155, message = "O título da anotação deve ter no máximo 155 caracteres")
    private String titulo;

    @NotBlank(message = "É necessário informar a descrição da anotação")
    @Size(max = 255, message = "A descrição da anotação deve ter no máximo 255 caracteres")
    private String descricao;

    @Valid
    @NotNull(message = "Tipo da anotação não pode ser vazio")
    private TipoAnotacaoCreateRequestDTO tipoAnotacao;

    private ZonedDateTime dataLembrete;

    private ZonedDateTime dataEstudoInicio;

    private ZonedDateTime dataEstudoFim;
}
