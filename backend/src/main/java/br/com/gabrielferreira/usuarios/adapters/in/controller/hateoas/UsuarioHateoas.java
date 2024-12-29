package br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas;

import br.com.gabrielferreira.usuarios.adapters.in.controller.UsuarioController;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioResumidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class UsuarioHateoas {

    private final TelefoneHateoas telefoneHateoas;

    private final TipoTelefoneHateoas tipoTelefoneHateoas;

    private final GeneroHateoas generoHateoas;

    public void addLinkToPost(UsuarioDTO usuario) {
        usuario.add(getUsuario(usuario.getId()));
        usuario.add(putUsuario(usuario.getId()));
        usuario.add(deleteUsuario(usuario.getId()));
        usuario.add(putUsuarioClient(usuario.getId()));
        usuario.add(putUsuarioAdmin(usuario.getId()));
        usuario.add(getUsuarioEmail(usuario.getEmail()));
        usuario.add(getUsuarioCpf(usuario.getCpf()));
        usuario.getTelefone().add(telefoneHateoas.getTelefone(usuario.getId()));
        usuario.getTelefone().getTipoTelefone().add(tipoTelefoneHateoas.getTipoTelefone(usuario.getTelefone().getTipoTelefone().getId()));
        usuario.getGenero().add(generoHateoas.getGenero(usuario.getGenero().getId()));
    }

    public void addLinkToGet(UsuarioDTO usuario) {
        usuario.add(putUsuario(usuario.getId()));
        usuario.add(deleteUsuario(usuario.getId()));
        usuario.add(putUsuarioClient(usuario.getId()));
        usuario.add(putUsuarioAdmin(usuario.getId()));
        usuario.add(getUsuarioEmail(usuario.getEmail()));
        usuario.add(getUsuarioCpf(usuario.getCpf()));
        usuario.getTelefone().add(telefoneHateoas.getTelefone(usuario.getId()));
        usuario.getTelefone().getTipoTelefone().add(tipoTelefoneHateoas.getTipoTelefone(usuario.getTelefone().getTipoTelefone().getId()));
        usuario.getGenero().add(generoHateoas.getGenero(usuario.getGenero().getId()));
    }

    public void addLinkToGetCpf(UsuarioResumidoDTO usuario) {
        usuario.add(getUsuario(usuario.getId()));
        usuario.add(putUsuario(usuario.getId()));
        usuario.add(deleteUsuario(usuario.getId()));
        usuario.add(putUsuarioClient(usuario.getId()));
        usuario.add(putUsuarioAdmin(usuario.getId()));
        usuario.add(getUsuarioEmail(usuario.getEmail()));
    }

    public void addLinkToGetEmail(UsuarioResumidoDTO usuario) {
        usuario.add(getUsuario(usuario.getId()));
        usuario.add(putUsuario(usuario.getId()));
        usuario.add(deleteUsuario(usuario.getId()));
        usuario.add(putUsuarioClient(usuario.getId()));
        usuario.add(putUsuarioAdmin(usuario.getId()));
        usuario.add(getUsuarioCpf(usuario.getCpf()));
    }

    public void addLinkToPut(UsuarioDTO usuario) {
        usuario.add(getUsuario(usuario.getId()));
        usuario.add(deleteUsuario(usuario.getId()));
        usuario.add(putUsuarioClient(usuario.getId()));
        usuario.add(putUsuarioAdmin(usuario.getId()));
        usuario.add(getUsuarioEmail(usuario.getEmail()));
        usuario.add(getUsuarioCpf(usuario.getCpf()));
        usuario.getTelefone().add(telefoneHateoas.getTelefone(usuario.getId()));
        usuario.getTelefone().getTipoTelefone().add(tipoTelefoneHateoas.getTipoTelefone(usuario.getTelefone().getTipoTelefone().getId()));
        usuario.getGenero().add(generoHateoas.getGenero(usuario.getGenero().getId()));
    }

    public void addLinkToGetUsuarios(List<UsuarioResumidoDTO> usuarios) {
        usuarios.forEach(usuario -> {
            usuario.add(getUsuario(usuario.getId()));
            usuario.add(putUsuario(usuario.getId()));
            usuario.add(deleteUsuario(usuario.getId()));
            usuario.add(putUsuarioClient(usuario.getId()));
            usuario.add(putUsuarioAdmin(usuario.getId()));
            usuario.add(getUsuarioEmail(usuario.getEmail()));
            usuario.add(getUsuarioCpf(usuario.getCpf()));
        });
    }

    public Link getUsuarios(Pageable pageable, String nome, String email, BigDecimal renda) {
        return linkTo(methodOn(UsuarioController.class).findAll(pageable, nome, email, renda))
                .withSelfRel().withType("GET");
    }

    public Link getUsuario(Long id) {
        return linkTo(methodOn(UsuarioController.class).findById(id))
                .withSelfRel().withType("GET");
    }

    public Link putUsuario(Long id) {
        return linkTo(methodOn(UsuarioController.class).update(id, null))
                .withSelfRel().withType("PUT");
    }

    public Link deleteUsuario(Long id) {
        return linkTo(methodOn(UsuarioController.class).delete(id))
                .withSelfRel().withType("DELETE");
    }

    public Link putUsuarioClient(Long id) {
        return linkTo(methodOn(UsuarioController.class).updateRoleClient(id))
                .withSelfRel().withType("PUT");
    }

    public Link putUsuarioAdmin(Long id) {
        return linkTo(methodOn(UsuarioController.class).updateRoleAdmin(id))
                .withSelfRel().withType("PUT");
    }

    public Link getUsuarioEmail(String email) {
        return linkTo(methodOn(UsuarioController.class).findByEmail(email))
                .withSelfRel().withType("GET");
    }

    public Link getUsuarioCpf(String cpf) {
        return linkTo(methodOn(UsuarioController.class).findByCpf(cpf))
                .withSelfRel().withType("GET");
    }
}
