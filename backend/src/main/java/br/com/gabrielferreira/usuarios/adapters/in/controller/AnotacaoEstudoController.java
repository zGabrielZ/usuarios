package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas.AnotacaoEstudoHateoas;
import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.AnotacaoMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoEstudoCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoEstudoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.CreateAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateAnotacaoInput;
import br.com.gabrielferreira.usuarios.utils.exemplo.swagger.ExemploAnotacaoEstudoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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

@Tag(name = "Anotação Estudo Controller", description = "Endpoints para realizar requisições de anotações do tipo estudo")
@RestController
@RequestMapping("/v1/usuarios/{idUsuario}/anotacoes/estudos")
@RequiredArgsConstructor
public class AnotacaoEstudoController {

    private final CreateAnotacaoInput createAnotacaoInput;

    private final FindAnotacaoInput findAnotacaoInput;

    private final UpdateAnotacaoInput updateAnotacaoInput;

    private final AnotacaoMapper anotacaoMapper;

    private final AnotacaoEstudoHateoas anotacaoEstudoHateoas;

    @Operation(summary = "Cadastrar anotação estudo")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Anotação cadastrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AnotacaoEstudoDTO.class),
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoEstudoUtils.ANOTACAO_ESTUDO_CRIADO
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<AnotacaoEstudoDTO> createEstudo(@PathVariable Long idUsuario, @Valid @RequestBody AnotacaoEstudoCreateDTO anotacaoEstudoCreateDTO){
        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoEstudoCreateDTO);
        anotacaoDomain = createAnotacaoInput.createEstudo(anotacaoDomain, idUsuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(anotacaoDomain.getId()).toUri();
        AnotacaoEstudoDTO anotacaoEstudoDto = anotacaoMapper.toAnotacaoEstudoDto(anotacaoDomain);
        anotacaoEstudoHateoas.addLinkPostAnotacaoEstudo(anotacaoEstudoDto, idUsuario);
        return ResponseEntity.created(uri).body(anotacaoEstudoDto);
    }

    @Operation(summary = "Buscar anotação por estudo por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Anotação encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AnotacaoEstudoDTO.class),
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoEstudoUtils.ANOTACAO_ESTUDO_ENCONTRADO
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Anotação não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoEstudoUtils.ANOTACAO_NAO_ENCONTRADA
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoEstudoDTO> findEstudoById(@PathVariable Long idUsuario, @PathVariable Long id){
        AnotacaoDomain anotacaoDomain = findAnotacaoInput.findByIdTipoAnotacaoEstudo(id, idUsuario);
        AnotacaoEstudoDTO anotacaoEstudoDto = anotacaoMapper.toAnotacaoEstudoDto(anotacaoDomain);
        anotacaoEstudoHateoas.addLinkGetAnotacaoEstudo(anotacaoEstudoDto, idUsuario);
        return ResponseEntity.ok(anotacaoEstudoDto);
    }

    @Operation(summary = "Finalizar anotação estudo por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Anotação atualizado",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Anotação não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoEstudoUtils.ANOTACAO_FINALIZAR_NAO_ENCONTRADA
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Regra de negócio",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoEstudoUtils.ANOTACAO_FINALIZAR_ERRO
                            )
                    )
            )
    })
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizarAnotacaoEstudoById(@PathVariable Long idUsuario, @PathVariable Long id){
        updateAnotacaoInput.finalizarAnotacaoEstudo(id, idUsuario);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Reabrir anotação estudo por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Anotação atualizado",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Anotação não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoEstudoUtils.ANOTACAO_REABRIR_NAO_ENCONTRADA
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Regra de negócio",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoEstudoUtils.ANOTACAO_REABRIR_ERRO
                            )
                    )
            )
    })
    @PutMapping("/{id}/reabrir")
    public ResponseEntity<Void> reabrirAnotacaoEstudoById(@PathVariable Long idUsuario, @PathVariable Long id){
        updateAnotacaoInput.reabrirAnotacaoEstudo(id, idUsuario);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Editar anotação estudo por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Anotação atualizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AnotacaoEstudoDTO.class),
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoEstudoUtils.ANOTACAO_EDITAR
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Anotação não encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoEstudoUtils.ANOTACAO_EDITAR_NAO_ENCONTRADA
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
                                    value = ExemploAnotacaoEstudoUtils.ANOTACAO_EDITAR_ERRO
                            )
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<AnotacaoEstudoDTO> editarAnotacaoEstudoById(@PathVariable Long idUsuario, @PathVariable Long id, @Valid @RequestBody AnotacaoEstudoCreateDTO anotacaoEstudoCreateDTO){
        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoEstudoCreateDTO);
        anotacaoDomain = updateAnotacaoInput.updateAnotacaoEstudo(id, idUsuario, anotacaoDomain);
        AnotacaoEstudoDTO anotacaoEstudoDto = anotacaoMapper.toAnotacaoEstudoDto(anotacaoDomain);
        anotacaoEstudoHateoas.addLinkPutAnotacaoEstudo(anotacaoEstudoDto, idUsuario);
        return ResponseEntity.ok().body(anotacaoEstudoDto);
    }
}
