package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.AnotacaoMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoLembreteCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoLembreteDTO;
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

@Tag(name = "Anotação Lembrete Controller", description = "Endpoints para realizar requisições de anotações do tipo lembrete")
@RestController
@RequestMapping("/v1/usuarios/{idUsuario}/anotacoes/lembretes")
@RequiredArgsConstructor
public class AnotacaoLembreteController {

    private final CreateAnotacaoInput createAnotacaoInput;

    private final FindAnotacaoInput findAnotacaoInput;

    private final UpdateAnotacaoInput updateAnotacaoInput;

    private final AnotacaoMapper anotacaoMapper;

    @Operation(summary = "Cadastrar anotação lembrete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Anotação cadastrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnotacaoLembreteDTO.class)) })
    })
    @PostMapping
    public ResponseEntity<AnotacaoLembreteDTO> createLembrete(@PathVariable Long idUsuario, @Valid @RequestBody AnotacaoLembreteCreateDTO anotacaoLembreteCreateDTO){
        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoLembreteCreateDTO);
        anotacaoDomain = createAnotacaoInput.createLembrete(anotacaoDomain, idUsuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(anotacaoDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(anotacaoMapper.toAnotacaoLembreteDto(anotacaoDomain));
    }

    @Operation(summary = "Buscar anotação por lembrete por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anotação encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnotacaoLembreteDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Anotação não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoLembreteDTO> findLembreteById(@PathVariable Long idUsuario, @PathVariable Long id){
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);
        return ResponseEntity.ok(anotacaoMapper.toAnotacaoLembreteDto(anotacaoDomain));
    }

    @Operation(summary = "Finalizar anotação lembrete por id")
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
    public ResponseEntity<Void> finalizarAnotacaoLembreteById(@PathVariable Long idUsuario, @PathVariable Long id){
        updateAnotacaoInput.finalizarAnotacaoLembrete(id, idUsuario);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Reabrir anotação lembrete por id")
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
    public ResponseEntity<Void> reabrirAnotacaoLembreteById(@PathVariable Long idUsuario, @PathVariable Long id){
        updateAnotacaoInput.reabrirAnotacaoLembrete(id, idUsuario);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Editar anotação lembrete por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anotação atualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnotacaoLembreteDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Anotação não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Regra de negócio",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AnotacaoLembreteDTO> editarAnotacaoLembreteById(@PathVariable Long idUsuario, @PathVariable Long id, @Valid @RequestBody AnotacaoLembreteCreateDTO anotacaoLembreteCreateDTO){
        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoLembreteCreateDTO);
        anotacaoDomain = updateAnotacaoInput.updateAnotacaoLembrete(id, idUsuario, anotacaoDomain);
        return ResponseEntity.ok().body(anotacaoMapper.toAnotacaoLembreteDto(anotacaoDomain));
    }
}
