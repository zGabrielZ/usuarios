package br.com.gabrielferreira.usuarios.application.core.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import static br.com.gabrielferreira.usuarios.utils.MascaraUtils.*;

public class TelefoneDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 7827339093508634735L;

    private Long id;

    private String numero;

    private String ddd;

    private String descricao;

    private DominioDomain tipoTelefone;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public TelefoneDomain(Long id, String numero, String ddd, String descricao, DominioDomain tipoTelefone, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.numero = numero;
        this.ddd = ddd;
        this.descricao = descricao;
        this.tipoTelefone = tipoTelefone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TelefoneDomain() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public DominioDomain getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(DominioDomain tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTelefoneFormatado(){
        return toTelefoneFormatado(this.ddd, this.numero);
    }

    @Override
    public String toString() {
        return "TelefoneDomain{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", ddd='" + ddd + '\'' +
                ", descricao='" + descricao + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelefoneDomain that = (TelefoneDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
