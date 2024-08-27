package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.AnotacaoMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoRascunhoCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoRascunhoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.CreateAnotacaoRascunhoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Anotação Controller", description = "Endpoints para realizar requisições de anotações")
@RestController
@RequestMapping("/v1/usuarios/{idUsuario}/anotacoes")
@RequiredArgsConstructor
public class AnotacaoController {

    private final CreateAnotacaoRascunhoInput createAnotacaoRascunhoInput;

    private final AnotacaoMapper anotacaoMapper;

    @Operation(summary = "Cadastrar anotação rascunho")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Anotação cadastrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnotacaoRascunhoDTO.class)) })
    })
    @PostMapping("/rascunhos")
    public ResponseEntity<AnotacaoRascunhoDTO> createRascunho(@PathVariable Long idUsuario, @Valid @RequestBody AnotacaoRascunhoCreateDTO anotacaoRascunhoCreateDTO){
        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoRascunhoCreateDTO);
        anotacaoDomain = createAnotacaoRascunhoInput.create(anotacaoDomain, idUsuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(anotacaoDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(anotacaoMapper.toAnotacaoRascunhoDto(anotacaoDomain));
    }
}
