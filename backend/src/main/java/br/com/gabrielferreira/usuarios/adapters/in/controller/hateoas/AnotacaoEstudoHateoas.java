package br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas;

import br.com.gabrielferreira.usuarios.adapters.in.controller.AnotacaoEstudoController;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoEstudoDTO;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AnotacaoEstudoHateoas {

    public void addLinkPostAnotacaoEstudo(AnotacaoEstudoDTO anotacaoEstudo, Long idUsuario) {
        anotacaoEstudo.add(getAnotacaoEstudo(anotacaoEstudo.getId(), idUsuario));
        anotacaoEstudo.add(putAnotacaoEstudo(anotacaoEstudo.getId(), idUsuario));
        anotacaoEstudo.add(putAnotacaoEstudoReabrir(anotacaoEstudo.getId(), idUsuario));
        anotacaoEstudo.add(putAnotacaoEstudoFinalizar(anotacaoEstudo.getId(), idUsuario));
    }

    public void addLinkGetAnotacaoEstudo(AnotacaoEstudoDTO anotacaoEstudo, Long idUsuario) {
        anotacaoEstudo.add(putAnotacaoEstudo(anotacaoEstudo.getId(), idUsuario));
        anotacaoEstudo.add(putAnotacaoEstudoReabrir(anotacaoEstudo.getId(), idUsuario));
        anotacaoEstudo.add(putAnotacaoEstudoFinalizar(anotacaoEstudo.getId(), idUsuario));
    }

    public void addLinkPutAnotacaoEstudo(AnotacaoEstudoDTO anotacaoEstudo, Long idUsuario) {
        anotacaoEstudo.add(getAnotacaoEstudo(anotacaoEstudo.getId(), idUsuario));
        anotacaoEstudo.add(putAnotacaoEstudoReabrir(anotacaoEstudo.getId(), idUsuario));
        anotacaoEstudo.add(putAnotacaoEstudoFinalizar(anotacaoEstudo.getId(), idUsuario));
    }

    public Link getAnotacaoEstudo(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoEstudoController.class).findEstudoById(idUsuario, id))
                .withSelfRel().withType("GET");
    }

    public Link putAnotacaoEstudo(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoEstudoController.class).editarAnotacaoEstudoById(idUsuario, id, null))
                .withSelfRel().withType("PUT");
    }

    public Link putAnotacaoEstudoReabrir(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoEstudoController.class).reabrirAnotacaoEstudoById(idUsuario, id))
                .withSelfRel().withType("PUT");
    }

    public Link putAnotacaoEstudoFinalizar(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoEstudoController.class).finalizarAnotacaoEstudoById(idUsuario, id))
                .withSelfRel().withType("PUT");
    }
}
