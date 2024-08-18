package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.GeneroMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.GeneroDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindGeneroInput;
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

@Tag(name = "Gênero Controller", description = "Endpoints para realizar requisições de gêneros")
@RestController
@RequestMapping("/v1/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final FindGeneroInput findGeneroInput;

    private final GeneroMapper generoMapper;

    @Operation(summary = "Buscar gênero por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneroDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> findById(@PathVariable Long id){
        DominioDomain dominioDomain = findGeneroInput.findById(id);
        return ResponseEntity.ok(generoMapper.toGeneroDto(dominioDomain));
    }

    @Operation(summary = "Buscar gêneros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gêneros encontrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneroDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<List<GeneroDTO>> findAll(){
        List<DominioDomain> dominioDomains = findGeneroInput.findAllByTipoCodigo();
        return ResponseEntity.ok(generoMapper.toGenerosDtos(dominioDomains));
    }
}
