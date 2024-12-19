package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.TelefoneMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.TelefoneCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TelefoneDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateTelefoneInput;
import br.com.gabrielferreira.usuarios.utils.exemplo.swagger.ExemploTelefoneUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Telefone Controller", description = "Endpoints para realizar requisições de telefones")
@RestController
@RequestMapping("/v1/usuarios/{idUsuario}/telefones")
@RequiredArgsConstructor
public class TelefoneController {

    private final FindTelefoneInput findTelefoneInput;

    private final UpdateTelefoneInput updateTelefoneInput;

    private final TelefoneMapper telefoneMapper;

    @Operation(summary = "Buscar telefone por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Telefone encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TelefoneDTO.class),
                            examples = @ExampleObject(
                                    value = ExemploTelefoneUtils.TELEFONE_ENCONTRADO
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Telefone não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploTelefoneUtils.TELEFONE_NAO_ENCONTRADO
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<TelefoneDTO> findById(@PathVariable Long idUsuario){
        TelefoneDomain telefoneDomain = findTelefoneInput.findByUsuarioId(idUsuario);
        TelefoneDTO telefoneDto = telefoneMapper.toTelefoneDto(telefoneDomain);
        telefoneDto.getTipoTelefone().add(getTipoTelefone(telefoneDto.getTipoTelefone().getId()));
        telefoneDto.add(updateTelefone(telefoneDto.getId(), idUsuario));
        return ResponseEntity.ok(telefoneDto);
    }

    @Operation(summary = "Atualizar telefone")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Telefone atualizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TelefoneDTO.class),
                            examples = @ExampleObject(
                                    value = ExemploTelefoneUtils.TELEFONE_ATUALIZADO
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Telefone não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploTelefoneUtils.TELEFONE_ATUALIZAR_NAO_ENCONTRADO
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Regra de negócio",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploTelefoneUtils.TELEFONE_ATUALIZAR_ERRO
                            )
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> update(@PathVariable Long id, @PathVariable Long idUsuario, @Valid @RequestBody TelefoneCreateDTO telefoneCreateDTO){
        TelefoneDomain telefoneDomain = telefoneMapper.createTelefoneDomain(telefoneCreateDTO, id);
        telefoneDomain = updateTelefoneInput.update(telefoneDomain, idUsuario);
        TelefoneDTO telefoneDto = telefoneMapper.toTelefoneDto(telefoneDomain);
        telefoneDto.getTipoTelefone().add(getTipoTelefone(telefoneDto.getTipoTelefone().getId()));
        telefoneDto.add(getTelefone(idUsuario));
        return ResponseEntity.ok().body(telefoneDto);
    }

    private Link getTipoTelefone(Long id) {
        return linkTo(methodOn(TipoTelefoneController.class).findById(id))
                .withSelfRel().withType("GET");
    }

    private Link updateTelefone(Long id, Long idUsuario) {
        return linkTo(methodOn(TelefoneController.class).update(id,  idUsuario, null))
                .withSelfRel().withType("PUT");
    }

    private Link getTelefone(Long idUsuario) {
        return linkTo(methodOn(TelefoneController.class).findById(idUsuario))
                .withSelfRel().withType("GET");
    }
}
