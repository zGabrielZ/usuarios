package br.com.gabrielferreira.usuario.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoAnotacaoDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 4819830058841518744L;

    @EqualsAndHashCode.Include
    private Long id;

    private String descricao;

    private String codigo;
}
