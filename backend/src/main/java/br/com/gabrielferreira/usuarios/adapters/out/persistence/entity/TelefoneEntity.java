package br.com.gabrielferreira.usuarios.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

import static br.com.gabrielferreira.usuarios.utils.DataUtils.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"tipoTelefone"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_TELEFONE")
public class TelefoneEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -8862093994952790903L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "NUMERO", nullable = false)
    private String numero;

    @Column(name = "DDD", nullable = false)
    private String ddd;

    @Column(name = "DESCRICAO", columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_TELEFONE", nullable = false)
    private DominioEntity tipoTelefone;

    @Column(name = "CREATED_AT", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private ZonedDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        createdAt = ZonedDateTime.now(UTC);
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = ZonedDateTime.now(UTC);
    }

}
