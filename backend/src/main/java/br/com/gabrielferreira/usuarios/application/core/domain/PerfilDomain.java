package br.com.gabrielferreira.usuarios.application.core.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class PerfilDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 2471723976229504704L;

    private Long id;

    private String titulo;

    private String autoriedade;

    public PerfilDomain() {}

    public PerfilDomain(Long id, String titulo, String autoriedade) {
        this.id = id;
        this.titulo = titulo;
        this.autoriedade = autoriedade;
    }

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

    public String getAutoriedade() {
        return autoriedade;
    }

    public void setAutoriedade(String autoriedade) {
        this.autoriedade = autoriedade;
    }

    @Override
    public String toString() {
        return "PerfilDomain{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autoriedade='" + autoriedade + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerfilDomain that = (PerfilDomain) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
