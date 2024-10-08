package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.AnotacaoMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoRascunhoCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoRascunhoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.CreateAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateAnotacaoInput;
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

@Tag(name = "Anotação Rascunho Controller", description = "Endpoints para realizar requisições de anotações do tipo rascunho")
@RestController
@RequestMapping("/v1/usuarios/{idUsuario}/anotacoes/rascunhos")
@RequiredArgsConstructor
public class AnotacaoRascunhoController {

    private final CreateAnotacaoInput createAnotacaoInput;

    private final FindAnotacaoInput findAnotacaoInput;

    private final UpdateAnotacaoInput updateAnotacaoInput;

    private final AnotacaoMapper anotacaoMapper;

    @Operation(summary = "Cadastrar anotação rascunho")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Anotação cadastrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnotacaoRascunhoDTO.class)) })
    })
    @PostMapping
    public ResponseEntity<AnotacaoRascunhoDTO> createRascunho(@PathVariable Long idUsuario, @Valid @RequestBody AnotacaoRascunhoCreateDTO anotacaoRascunhoCreateDTO){
        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoRascunhoCreateDTO);
        anotacaoDomain = createAnotacaoInput.createRascunho(anotacaoDomain, idUsuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(anotacaoDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(anotacaoMapper.toAnotacaoRascunhoDto(anotacaoDomain));
    }

    @Operation(summary = "Buscar anotação por rascunho por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anotação encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnotacaoRascunhoDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Anotação não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoRascunhoDTO> findRascunhoById(@PathVariable Long idUsuario, @PathVariable Long id){
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoRascunho(id, idUsuario);
        return ResponseEntity.ok(anotacaoMapper.toAnotacaoRascunhoDto(anotacaoDomain));
    }

    @Operation(summary = "Finalizar anotação rascunho por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anotação atualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Void.class)) }),
            @ApiResponse(responseCode = "404", description = "Anotação não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Regra de negócio",
                    content = @Content)
    })
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizarAnotacaoRascunhoById(@PathVariable Long idUsuario, @PathVariable Long id){
        updateAnotacaoInput.finalizarAnotacaoRascunho(id, idUsuario);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Reabrir anotação rascunho por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anotação atualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Void.class)) }),
            @ApiResponse(responseCode = "404", description = "Anotação não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Regra de negócio",
                    content = @Content)
    })
    @PutMapping("/{id}/reabrir")
    public ResponseEntity<Void> reabrirAnotacaoRascunhoById(@PathVariable Long idUsuario, @PathVariable Long id){
        updateAnotacaoInput.reabrirAnotacaoRascunho(id, idUsuario);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Editar anotação rascunho por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anotação atualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnotacaoRascunhoDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Anotação não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Regra de negócio",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AnotacaoRascunhoDTO> editarAnotacaoRascunhoById(@PathVariable Long idUsuario, @PathVariable Long id, @Valid @RequestBody AnotacaoRascunhoCreateDTO anotacaoRascunhoCreateDTO){
        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoRascunhoCreateDTO);
        anotacaoDomain = updateAnotacaoInput.updateAnotacaoRascunho(id, idUsuario, anotacaoDomain);
        return ResponseEntity.ok().body(anotacaoMapper.toAnotacaoRascunhoDto(anotacaoDomain));
    }
}
