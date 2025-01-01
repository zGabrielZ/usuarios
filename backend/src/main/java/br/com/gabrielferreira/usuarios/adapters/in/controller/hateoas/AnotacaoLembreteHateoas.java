package br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas;

import br.com.gabrielferreira.usuarios.adapters.in.controller.AnotacaoLembreteController;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoLembreteDTO;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AnotacaoLembreteHateoas {

    public void addLinkPostAnotacaoLembrete(AnotacaoLembreteDTO anotacaoLembrete, Long idUsuario) {
        anotacaoLembrete.add(getAnotacaoLembrete(anotacaoLembrete.getId(), idUsuario));
        anotacaoLembrete.add(putAnotacaoLembrete(anotacaoLembrete.getId(), idUsuario));
        anotacaoLembrete.add(putAnotacaoLembreteReabrir(anotacaoLembrete.getId(), idUsuario));
        anotacaoLembrete.add(putAnotacaoLembreteFinalizar(anotacaoLembrete.getId(), idUsuario));
    }

    public void addLinkGetAnotacaoLembrete(AnotacaoLembreteDTO anotacaoLembrete, Long idUsuario) {
        anotacaoLembrete.add(putAnotacaoLembrete(anotacaoLembrete.getId(), idUsuario));
        anotacaoLembrete.add(putAnotacaoLembreteReabrir(anotacaoLembrete.getId(), idUsuario));
        anotacaoLembrete.add(putAnotacaoLembreteFinalizar(anotacaoLembrete.getId(), idUsuario));
    }

    public void addLinkPutAnotacaoLembrete(AnotacaoLembreteDTO anotacaoLembrete, Long idUsuario) {
        anotacaoLembrete.add(getAnotacaoLembrete(anotacaoLembrete.getId(), idUsuario));
        anotacaoLembrete.add(putAnotacaoLembreteReabrir(anotacaoLembrete.getId(), idUsuario));
        anotacaoLembrete.add(putAnotacaoLembreteFinalizar(anotacaoLembrete.getId(), idUsuario));
    }

    public Link getAnotacaoLembrete(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoLembreteController.class).findLembreteById(idUsuario, id))
                .withSelfRel().withType("GET");
    }

    public Link putAnotacaoLembrete(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoLembreteController.class).editarAnotacaoLembreteById(idUsuario, id, null))
                .withSelfRel().withType("PUT");
    }

    public Link putAnotacaoLembreteReabrir(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoLembreteController.class).reabrirAnotacaoLembreteById(idUsuario, id))
                .withSelfRel().withType("PUT");
    }

    public Link putAnotacaoLembreteFinalizar(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoLembreteController.class).finalizarAnotacaoLembreteById(idUsuario, id))
                .withSelfRel().withType("PUT");
    }
}
