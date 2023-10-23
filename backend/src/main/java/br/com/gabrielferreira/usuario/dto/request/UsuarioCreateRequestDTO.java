package br.com.gabrielferreira.usuario.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -918570165334510853L;

    @NotBlank(message = "É necessário informar o nome do usuário")
    @Size(max = 155, message = "O nome do usuário deve ter no máximo 155 caracteres")
    private String nome;

    @Email(message = "E-mail do usuário é inválido")
    @NotBlank(message = "É necessário informar o e-mail do usuário")
    private String email;

    @Pattern(regexp = "^[0-9]+$", message = "Apenas números para o CPF do usuário")
    @CPF(message = "CPF do usuário é inválido")
    @NotBlank(message = "É necessário informar o CPF do usuário")
    @Size(max = 11, message = "O CPF do usuário deve ter no máximo 11 caracteres")
    private String cpf;

    @Digits(integer = 12, fraction = 2, message = "A renda do usuário deve ter no máximo 12 dígitos inteiros e 2 dígitos decimais")
    private BigDecimal renda;

    @NotNull(message = "É necessário informar a data de nascimento do usuário")
    @PastOrPresent(message = "A data de nascimento do usuário deve ser no passado ou presente")
    private LocalDate dataNascimento;

    private Integer quantidadeFilhos;

    @Valid
    @NotNull(message = "Telefone do usuário não pode ser vazio")
    private TelefoneCreateRequestDTO telefone;

    @Valid
    @NotNull(message = "Gênero do usuário não pode ser vazio")
    private GeneroCreateRequestDTO genero;
}
