package br.com.gabrielferreira.usuario.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"telefone"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = -8862093994952790903L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "CPF", nullable = false, unique = true)
    private String cpf;

    @Column(name = "RENDA", precision = 12, scale = 2)
    private BigDecimal renda;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "QUANTIDADE_FILHOS", nullable = false)
    private Integer quantidadeFilhos;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TELEFONE", nullable = false)
    private Telefone telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GENERO", nullable = false)
    private Genero genero;

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
