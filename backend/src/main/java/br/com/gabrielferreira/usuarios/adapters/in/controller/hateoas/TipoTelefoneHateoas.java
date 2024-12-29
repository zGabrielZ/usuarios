package br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas;

import br.com.gabrielferreira.usuarios.adapters.in.controller.TipoTelefoneController;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TipoTelefoneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class TipoTelefoneHateoas {

    public void addLinkGetTipoTelefone(List<TipoTelefoneDTO> tiposTelefonesDtos) {
        tiposTelefonesDtos.forEach(tipoTelefone -> tiposTelefonesDtos.forEach(tipoTelefoneDto -> tipoTelefoneDto.add(getTipoTelefone(tipoTelefoneDto.getId()))));
    }

    public Link getTipoTelefone(Long id) {
        return linkTo(methodOn(TipoTelefoneController.class).findById(id))
                .withSelfRel().withType("GET");
    }

    public Link getTiposTelefones() {
        return linkTo(methodOn(TipoTelefoneController.class).findAll())
                .withSelfRel().withType("GET");
    }
}
