package br.com.gabrielferreira.usuarios.application.core.domain;

import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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

    public void validarCampos() {
        this.titulo = this.titulo.trim();
        this.descricao = this.descricao.trim();
    }

    public void validarDataInicioDataFimEstudo() {
        ZonedDateTime dataInicioEstudo = this.dataEstudoInicio;
        ZonedDateTime dataFimEstudo = this.dataEstudoFim;

        if (dataInicioEstudo.isAfter(dataFimEstudo) || dataInicioEstudo.equals(dataFimEstudo)) {
            throw new RegraDeNegocioException("A data início do estudo não pode ser antes ou igual ao data fim do estudo");
        }

        long horas = ChronoUnit.HOURS.between(dataInicioEstudo, dataFimEstudo);
        if (horas > 5) {
            throw new RegraDeNegocioException("O estudo não pode ultrapassar de 5 horas");
        }
    }

    public void validarSituacaoFinalizada(DominioDomain situacao, TipoAnotacaoEnum tipoAnotacaoEnum) {
        if (situacao.getCodigo().equals(tipoAnotacaoEnum.name())) {
            throw new RegraDeNegocioException("Não é possível finalizar a anotação pois já está finalizado");
        }
    }

    public void validarSituacaoAberto(DominioDomain situacao, TipoAnotacaoEnum tipoAnotacaoEnum) {
        if (situacao.getCodigo().equals(tipoAnotacaoEnum.name())) {
            throw new RegraDeNegocioException("Não é possível reabrir a anotação pois já está em aberto");
        }
    }

    public void validarSituacaoEditar(DominioDomain situacao, TipoAnotacaoEnum tipoAnotacaoEnum) {
        if (situacao.getCodigo().equals(tipoAnotacaoEnum.name())) {
            throw new RegraDeNegocioException("Não é possível editar a anotação pois já está finalizado");
        }
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
