package br.com.gabrielferreira.usuarios.application.core.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class AnotacaoDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = -5404147777130806787L;

    private Long id;

    private String titulo;

    private String descricao;

    private UsuarioDomain usuario;

    private DominioDomain tipoAnotacao;

    private ZonedDateTime dataLembrete;

    private ZonedDateTime dataEstudoInicio;

    private ZonedDateTime dataEstudoFim;

    private DominioDomain situacaoTipoAnotacao;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public AnotacaoDomain(Long id, String titulo, String descricao, UsuarioDomain usuario, DominioDomain tipoAnotacao, ZonedDateTime dataLembrete, ZonedDateTime dataEstudoInicio, ZonedDateTime dataEstudoFim, DominioDomain situacaoTipoAnotacao, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.tipoAnotacao = tipoAnotacao;
        this.dataLembrete = dataLembrete;
        this.dataEstudoInicio = dataEstudoInicio;
        this.dataEstudoFim = dataEstudoFim;
        this.situacaoTipoAnotacao = situacaoTipoAnotacao;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AnotacaoDomain() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UsuarioDomain getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDomain usuario) {
        this.usuario = usuario;
    }

    public DominioDomain getTipoAnotacao() {
        return tipoAnotacao;
    }

    public void setTipoAnotacao(DominioDomain tipoAnotacao) {
        this.tipoAnotacao = tipoAnotacao;
    }

    public ZonedDateTime getDataLembrete() {
        return dataLembrete;
    }

    public void setDataLembrete(ZonedDateTime dataLembrete) {
        this.dataLembrete = dataLembrete;
    }

    public ZonedDateTime getDataEstudoInicio() {
        return dataEstudoInicio;
    }

    public void setDataEstudoInicio(ZonedDateTime dataEstudoInicio) {
        this.dataEstudoInicio = dataEstudoInicio;
    }

    public ZonedDateTime getDataEstudoFim() {
        return dataEstudoFim;
    }

    public void setDataEstudoFim(ZonedDateTime dataEstudoFim) {
        this.dataEstudoFim = dataEstudoFim;
    }

    public DominioDomain getSituacaoTipoAnotacao() {
        return situacaoTipoAnotacao;
    }

    public void setSituacaoTipoAnotacao(DominioDomain situacaoTipoAnotacao) {
        this.situacaoTipoAnotacao = situacaoTipoAnotacao;
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

    @Override
    public String toString() {
        return "AnotacaoDomain{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataLembrete=" + dataLembrete +
                ", dataEstudoInicio=" + dataEstudoInicio +
                ", dataEstudoFim=" + dataEstudoFim +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnotacaoDomain that = (AnotacaoDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
