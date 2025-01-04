package br.com.gabrielferreira.usuarios.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.com.gabrielferreira.usuarios.utils.DataUtils.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"telefone", "anotacoes", "genero", "perfis", "senha"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_USUARIO")
public class UsuarioEntity implements Serializable {

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

    @Column(name = "QUANTIDADE_FILHOS")
    private Integer quantidadeFilhos;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_TELEFONE", nullable = false)
    private TelefoneEntity telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GENERO", nullable = false)
    private DominioEntity genero;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "usuario")
    private List<AnotacaoEntity> anotacoes = new ArrayList<>();

    @Column(name = "CREATED_AT", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private ZonedDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TB_USUARIO_PERFIL",
            joinColumns = @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", table = "TB_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID", table = "TB_PERFIL"))
    private List<PerfilEntity> perfis = new ArrayList<>();

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @PrePersist
    public void prePersist(){
        createdAt = ZonedDateTime.now(UTC);
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = ZonedDateTime.now(UTC);
    }

}
