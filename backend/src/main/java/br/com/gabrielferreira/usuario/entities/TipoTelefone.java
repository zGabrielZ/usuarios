package br.com.gabrielferreira.usuario.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_TIPO_TELEFONE")
public class TipoTelefone implements Serializable {

    @Serial
    private static final long serialVersionUID = -8862093994952790903L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "DESCRICAO", nullable = false, unique = true)
    private String descricao;

    @Column(name = "CODIGO", nullable = false, unique = true)
    private String codigo;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", name = "CREATED_AT", nullable = false)
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", name = "UPDATED_AT")
    private Instant updatedAt;

    @PrePersist
    public void prePersist(){
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = Instant.now();
    }

}
