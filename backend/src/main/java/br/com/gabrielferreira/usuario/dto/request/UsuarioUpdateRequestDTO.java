package br.com.gabrielferreira.usuario.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -918570165334510853L;

    @NotBlank(message = "É necessário informar o nome do usuário")
    @Size(max = 155, message = "O nome do usuário deve ter no máximo 155 caracteres")
    private String nome;

    @Digits(integer = 12, fraction = 2, message = "A renda do usuário deve ter no máximo 12 dígitos inteiros e 2 dígitos decimais")
    private BigDecimal renda;

    private Integer quantidadeFilhos;

    @Valid
    @NotNull(message = "Telefone do usuário não pode ser vazio")
    private TelefoneCreateRequestDTO telefone;

    @Valid
    @NotNull(message = "Gênero do usuário não pode ser vazio")
    private GeneroCreateRequestDTO genero;
}
