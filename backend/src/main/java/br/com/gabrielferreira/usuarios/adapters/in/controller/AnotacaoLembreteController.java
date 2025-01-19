package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas.AnotacaoLembreteHateoas;
import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.AnotacaoMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoLembreteCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoLembreteDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.CreateAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateAnotacaoInput;
import br.com.gabrielferreira.usuarios.utils.exemplo.swagger.ExemploAnotacaoLembreteUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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

    private final AnotacaoLembreteHateoas anotacaoLembreteHateoas;

    @Operation(summary = "Cadastrar anotação lembrete")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Anotação cadastrada"
            )
    })
    @PostMapping
    public ResponseEntity<AnotacaoLembreteDTO> createLembrete(@PathVariable Long idUsuario, @Valid @RequestBody AnotacaoLembreteCreateDTO anotacaoLembreteCreateDTO){
        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoLembreteCreateDTO);
        anotacaoDomain = createAnotacaoInput.createLembrete(anotacaoDomain, idUsuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(anotacaoDomain.getId()).toUri();
        AnotacaoLembreteDTO anotacaoLembreteDto = anotacaoMapper.toAnotacaoLembreteDto(anotacaoDomain);
        anotacaoLembreteHateoas.addLinkPostAnotacaoLembrete(anotacaoLembreteDto, idUsuario);
        return ResponseEntity.created(uri).body(anotacaoLembreteDto);
    }

    @Operation(summary = "Buscar anotação por lembrete por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Anotação encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Anotação não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoLembreteUtils.ANOTACAO_NAO_ENCONTRADA
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoLembreteDTO> findLembreteById(@PathVariable Long idUsuario, @PathVariable Long id){
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoLembrete(id, idUsuario);
        AnotacaoLembreteDTO anotacaoLembreteDto = anotacaoMapper.toAnotacaoLembreteDto(anotacaoDomain);
        anotacaoLembreteHateoas.addLinkGetAnotacaoLembrete(anotacaoLembreteDto, idUsuario);
        return ResponseEntity.ok(anotacaoLembreteDto);
    }

    @Operation(summary = "Finalizar anotação lembrete por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Anotação atualizado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Anotação não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoLembreteUtils.ANOTACAO_FINALIZAR_NAO_ENCONTRADA
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Regra de negócio",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoLembreteUtils.ANOTACAO_FINALIZAR_ERRO
                            )
                    )
            )
    })
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizarAnotacaoLembreteById(@PathVariable Long idUsuario, @PathVariable Long id){
        updateAnotacaoInput.finalizarAnotacaoLembrete(id, idUsuario);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Reabrir anotação lembrete por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Anotação atualizado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Anotação não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoLembreteUtils.ANOTACAO_REABRIR_NAO_ENCONTRADA
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Regra de negócio",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoLembreteUtils.ANOTACAO_REABRIR_ERRO
                            )
                    )
            )
    })
    @PutMapping("/{id}/reabrir")
    public ResponseEntity<Void> reabrirAnotacaoLembreteById(@PathVariable Long idUsuario, @PathVariable Long id){
        updateAnotacaoInput.reabrirAnotacaoLembrete(id, idUsuario);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Editar anotação lembrete por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Anotação atualizado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Anotação não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoLembreteUtils.ANOTACAO_EDITAR_NAO_ENCONTRADA
                            )
                    )
            )
            ,
            @ApiResponse(
                    responseCode = "400",
                    description = "Regra de negócio",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoLembreteUtils.ANOTACAO_EDITAR_ERRO
                            )
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<AnotacaoLembreteDTO> editarAnotacaoLembreteById(@PathVariable Long idUsuario, @PathVariable Long id, @Valid @RequestBody AnotacaoLembreteCreateDTO anotacaoLembreteCreateDTO){
        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoLembreteCreateDTO);
        anotacaoDomain = updateAnotacaoInput.updateAnotacaoLembrete(id, idUsuario, anotacaoDomain);
        AnotacaoLembreteDTO anotacaoLembreteDto = anotacaoMapper.toAnotacaoLembreteDto(anotacaoDomain);
        anotacaoLembreteHateoas.addLinkPutAnotacaoLembrete(anotacaoLembreteDto, idUsuario);
        return ResponseEntity.ok().body(anotacaoLembreteDto);
    }
}
