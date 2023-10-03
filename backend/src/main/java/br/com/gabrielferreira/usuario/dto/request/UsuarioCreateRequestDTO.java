package br.com.gabrielferreira.usuario.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private String nome;

    private String email;

    private String cpf;

    private BigDecimal renda;

    private LocalDate dataNascimento;

    private Integer quantidadeFilhos;

    private TelefoneCreateRequestDTO telefone;

    private GeneroCreateRequestDTO genero;
}
