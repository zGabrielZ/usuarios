package br.com.gabrielferreira.usuarios.application.core.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.com.gabrielferreira.usuarios.utils.MascaraUtils.*;

public class UsuarioDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 8047442000735668935L;

    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private BigDecimal renda;

    private LocalDate dataNascimento;

    private Integer quantidadeFilhos;

    private TelefoneDomain telefone;

    private DominioDomain genero;

    private List<AnotacaoDomain> anotacoes = new ArrayList<>();

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public UsuarioDomain(Long id, String nome, String email, String cpf, BigDecimal renda, LocalDate dataNascimento, Integer quantidadeFilhos, TelefoneDomain telefone, DominioDomain genero, List<AnotacaoDomain> anotacoes, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.renda = renda;
        this.dataNascimento = dataNascimento;
        this.quantidadeFilhos = quantidadeFilhos;
        this.telefone = telefone;
        this.genero = genero;
        this.anotacoes = anotacoes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UsuarioDomain() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getQuantidadeFilhos() {
        return quantidadeFilhos;
    }

    public void setQuantidadeFilhos(Integer quantidadeFilhos) {
        this.quantidadeFilhos = quantidadeFilhos;
    }

    public TelefoneDomain getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneDomain telefone) {
        this.telefone = telefone;
    }

    public DominioDomain getGenero() {
        return genero;
    }

    public void setGenero(DominioDomain genero) {
        this.genero = genero;
    }

    public List<AnotacaoDomain> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<AnotacaoDomain> anotacoes) {
        this.anotacoes = anotacoes;
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

    public String getCpfFormatado(){
        return toCpfFormatado(this.cpf);
    }

    @Override
    public String toString() {
        return "UsuarioDomain{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", renda=" + renda +
                ", dataNascimento=" + dataNascimento +
                ", quantidadeFilhos=" + quantidadeFilhos +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDomain that = (UsuarioDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
