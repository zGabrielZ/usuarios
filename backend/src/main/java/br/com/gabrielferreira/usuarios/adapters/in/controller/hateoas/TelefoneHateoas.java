package br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas;

import br.com.gabrielferreira.usuarios.adapters.in.controller.TelefoneController;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TelefoneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class TelefoneHateoas {

    private final TipoTelefoneHateoas tipoTelefoneHateoas;

    public void addLinkGetTelefone(TelefoneDTO telefone, Long idUsuario) {
        telefone.getTipoTelefone().add(tipoTelefoneHateoas.getTipoTelefone(telefone.getTipoTelefone().getId()));
        telefone.add(updateTelefone(telefone.getId(), idUsuario));
    }

    public void addLinkPutTelefone(TelefoneDTO telefone, Long idUsuario) {
        telefone.getTipoTelefone().add(tipoTelefoneHateoas.getTipoTelefone(telefone.getTipoTelefone().getId()));
        telefone.add(getTelefone(idUsuario));
    }

    public Link getTelefone(Long idUsuario) {
        return linkTo(methodOn(TelefoneController.class).findById(idUsuario))
                .withSelfRel().withType("GET");
    }

    private Link updateTelefone(Long id, Long idUsuario) {
        return linkTo(methodOn(TelefoneController.class).update(id,  idUsuario, null))
                .withSelfRel().withType("PUT");
    }
}
