package br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas;

import br.com.gabrielferreira.usuarios.adapters.in.controller.GeneroController;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.GeneroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class GeneroHateoas {

    public void addLinkToGeneros(List<GeneroDTO> generoDtos) {
        generoDtos.forEach(generoDto -> generoDto.add(getGenero(generoDto.getId())));
    }

    public Link getGenero(Long id) {
        return linkTo(methodOn(GeneroController.class).findById(id))
                .withSelfRel().withType("GET");
    }

    public Link getGeneros() {
        return linkTo(methodOn(GeneroController.class).findAll())
                .withSelfRel().withType("GET");
    }
}
