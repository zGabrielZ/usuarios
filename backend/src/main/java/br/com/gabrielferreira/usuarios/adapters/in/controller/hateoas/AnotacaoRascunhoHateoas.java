package br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas;

import br.com.gabrielferreira.usuarios.adapters.in.controller.AnotacaoRascunhoController;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AnotacaoRascunhoHateoas {

    public Link getAnotacaoRascunho(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoRascunhoController.class).findRascunhoById(idUsuario, id))
                .withSelfRel().withType("GET");
    }

    public Link putAnotacaoRascunho(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoRascunhoController.class).editarAnotacaoRascunhoById(idUsuario, id, null))
                .withSelfRel().withType("PUT");
    }

    public Link putAnotacaoRascunhoReabrir(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoRascunhoController.class).reabrirAnotacaoRascunhoById(idUsuario, id))
                .withSelfRel().withType("PUT");
    }

    public Link putAnotacaoRascunhoFinalizar(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoRascunhoController.class).finalizarAnotacaoRascunhoById(idUsuario, id))
                .withSelfRel().withType("PUT");
    }
}
