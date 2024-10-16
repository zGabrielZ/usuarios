package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.PerfilMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.PerfilDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindPerfilInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Perfil Controller", description = "Endpoints para realizar requisições de perfis")
@RestController
@RequestMapping("/v1/perfis")
@RequiredArgsConstructor
public class PerfilController {

    private final FindPerfilInput findPerfilInput;

    private final PerfilMapper perfilMapper;

    @Operation(summary = "Buscar perfil por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PerfilDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Perfil não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> findById(@PathVariable Long id){
        PerfilDomain perfilDomain = findPerfilInput.findById(id);
        return ResponseEntity.ok(perfilMapper.toPerfilDto(perfilDomain));
    }

    @Operation(summary = "Buscar perfis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfis encontrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PerfilDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<List<PerfilDTO>> findAll(){
        List<PerfilDomain> perfilDomains = findPerfilInput.findAll();
        return ResponseEntity.ok().body(perfilMapper.toPerfisDtos(perfilDomains));
    }
}
