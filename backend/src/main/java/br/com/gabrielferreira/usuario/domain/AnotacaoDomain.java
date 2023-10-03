package br.com.gabrielferreira.usuario.domain;

import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AnotacaoDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 4819830058841518744L;

    @EqualsAndHashCode.Include
    private Long id;

    private String titulo;

    private String descricao;

    private UsuarioDomain usuario;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
