package br.com.gabrielferreira.usuario.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static br.com.gabrielferreira.usuario.utils.DataUtils.AMERICA_SAO_PAULO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"tipoTelefone"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_TELEFONE")
public class Telefone implements Serializable {

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
    private TipoTelefone tipoTelefone;

    @Column(name = "CREATED_AT", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private ZonedDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        createdAt = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
    }

}
