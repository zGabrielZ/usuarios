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
