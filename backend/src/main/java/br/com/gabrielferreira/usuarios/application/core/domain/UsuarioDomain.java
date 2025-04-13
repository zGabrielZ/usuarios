package br.com.gabrielferreira.usuarios.application.core.domain;

import br.com.gabrielferreira.usuarios.application.core.domain.enums.RoleEnum;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
import io.micrometer.common.util.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static br.com.gabrielferreira.usuarios.common.utils.CaracteresUtils.*;
import static br.com.gabrielferreira.usuarios.common.utils.CaracteresUtils.isPossuiCaractereDigito;
import static br.com.gabrielferreira.usuarios.common.utils.MascaraUtils.toCpfFormatado;
import static br.com.gabrielferreira.usuarios.common.utils.MascaraUtils.toValorMonetarioBrasil;

public class UsuarioDomain implements Serializable, UserDetails {

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

    private List<PerfilDomain> perfis = new ArrayList<>();

    private String senha;

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

    public String getRendaFormatada(){
        return toValorMonetarioBrasil(this.renda);
    }

    public List<PerfilDomain> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<PerfilDomain> perfis) {
        this.perfis = perfis;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isContemPerfil(RoleEnum roleEnum) {
        return this.perfis.stream().anyMatch(pe -> pe.getAutoriedade().equals(roleEnum.name()));
    }

    public boolean isNaoContemPerfil(RoleEnum roleEnum) {
        return !isContemPerfil(roleEnum);
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void validarCampos() {
        this.nome = this.nome.trim();

        if (!StringUtils.isBlank(this.email)) {
            this.email = this.email.trim();
        }

        if (!StringUtils.isBlank(this.cpf)) {
            this.cpf = this.cpf.trim();
        }
    }

    public void validarPerfilUsuario(PerfilDomain perfilDomain, String mensagem) {
        List<Long> idsPerfis = this.perfis.stream().map(PerfilDomain::getId).toList();
        if (idsPerfis.contains(perfilDomain.getId())) {
            throw new RegraDeNegocioException(mensagem);
        }
    }

    public void validarSenha(String senha) {
        if (!isPossuiCaracteresEspecias(senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos uma caractere especial");
        }

        if (!isPossuiCaractereMaiusculas(senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos uma caractere maiúsculas");
        }

        if (!isPossuiCaractereMinusculas(senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos uma caractere minúsculas");
        }

        if (!isPossuiCaractereDigito(senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos um caractere dígito");
        }
    }
}
