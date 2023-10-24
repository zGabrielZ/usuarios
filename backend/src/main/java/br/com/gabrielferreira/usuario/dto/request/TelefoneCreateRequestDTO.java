package br.com.gabrielferreira.usuario.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -918570165334510853L;

    @Pattern(regexp = "^[0-9]+$", message = "Apenas números para o número do telefone")
    @NotBlank(message = "É necessário informar o número do telefone")
    @Size(max = 9, message = "O número do telefone deve ter no máximo 9 caracteres")
    private String numero;

    @Pattern(regexp = "^[0-9]+$", message = "Apenas números para o DDD do telefone")
    @NotBlank(message = "É necessário informar o DDD do telefone")
    @Size(max = 2, message = "O DDD do telefone deve ter no máximo 2 caracteres")
    private String ddd;

    @Size(max = 255, message = "A descrição do telefone deve ter no máximo 255 caracteres")
    private String descricao;

    @Valid
    @NotNull(message = "Tipo de telefone não pode ser vazio")
    private TipoTelefoneCreateRequestDTO tipoTelefone;
}
