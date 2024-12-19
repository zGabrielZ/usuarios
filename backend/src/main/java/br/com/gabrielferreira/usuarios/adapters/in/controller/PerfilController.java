package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.PerfilMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.PerfilDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindPerfilInput;
import br.com.gabrielferreira.usuarios.utils.exemplo.swagger.ExemploPerfilUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Perfil Controller", description = "Endpoints para realizar requisições de perfis")
@RestController
@RequestMapping("/v1/perfis")
@RequiredArgsConstructor
public class PerfilController {

    private final FindPerfilInput findPerfilInput;

    private final PerfilMapper perfilMapper;

    @Operation(summary = "Buscar perfil por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Perfil encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PerfilDTO.class),
                            examples = @ExampleObject(
                                    value = ExemploPerfilUtils.PERFIL_ENCONTRADO
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Perfil não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploPerfilUtils.PERFIL_NAO_ENCONTRADO
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> findById(@PathVariable Long id){
        PerfilDomain perfilDomain = findPerfilInput.findById(id);
        return ResponseEntity.ok(perfilMapper.toPerfilDto(perfilDomain));
    }

    @Operation(summary = "Buscar perfis")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Perfis encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PerfilDTO.class),
                            examples = @ExampleObject(
                                    value = ExemploPerfilUtils.PERFIS_ENCONTRADOS
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<CollectionModel<PerfilDTO>> findAll(){
        List<PerfilDomain> perfilDomains = findPerfilInput.findAll();
        List<PerfilDTO> perfisDtos = perfilMapper.toPerfisDtos(perfilDomains);
        perfisDtos.forEach(perfilDto -> perfilDto.add(getPerfil(perfilDto.getId())));
        return ResponseEntity.ok().body(CollectionModel.of(perfisDtos, getPerfis()));
    }

    private Link getPerfil(Long id) {
        return linkTo(methodOn(PerfilController.class).findById(id))
                .withSelfRel().withType("GET");
    }

    private Link getPerfis() {
        return linkTo(methodOn(PerfilController.class).findAll())
                .withSelfRel().withType("GET");
    }
}
