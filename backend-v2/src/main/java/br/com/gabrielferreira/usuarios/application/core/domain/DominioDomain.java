package br.com.gabrielferreira.usuarios.application.core.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class DominioDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 2471723976229504704L;

    private Long id;

    private String descricao;

    private String codigo;

    private TipoDominioDomain tipo;

    public DominioDomain(Long id, String descricao, String codigo, TipoDominioDomain tipo) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public DominioDomain() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoDominioDomain getTipo() {
        return tipo;
    }

    public void setTipo(TipoDominioDomain tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "DominioDomain{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DominioDomain that = (DominioDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
