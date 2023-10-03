package br.com.gabrielferreira.usuario.dto.request;

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

    private String nome;

    private BigDecimal renda;

    private Integer quantidadeFilhos;

    private TelefoneCreateRequestDTO telefone;

    private GeneroCreateRequestDTO genero;
}
