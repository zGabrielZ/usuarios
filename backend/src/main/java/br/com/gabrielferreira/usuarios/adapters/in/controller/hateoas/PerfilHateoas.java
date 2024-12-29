package br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas;

import br.com.gabrielferreira.usuarios.adapters.in.controller.PerfilController;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.PerfilDTO;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PerfilHateoas {

    public void addLinkToPerfis(List<PerfilDTO> perfisDtos) {
        perfisDtos.forEach(perfilDto -> perfilDto.add(getPerfil(perfilDto.getId())));
    }

    public Link getPerfil(Long id) {
        return linkTo(methodOn(PerfilController.class).findById(id))
                .withSelfRel().withType("GET");
    }

    public Link getPerfis() {
        return linkTo(methodOn(PerfilController.class).findAll())
                .withSelfRel().withType("GET");
    }
}
