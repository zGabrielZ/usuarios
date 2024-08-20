package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.TelefoneMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.TelefoneCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TelefoneDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateTelefoneInput;
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
            @ApiResponse(responseCode = "200", description = "Telefone encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TelefoneDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Telefone não encontrado",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<TelefoneDTO> findById(@PathVariable Long idUsuario){
        TelefoneDomain telefoneDomain = findTelefoneInput.findByUsuarioId(idUsuario);
        return ResponseEntity.ok(telefoneMapper.toTelefoneDto(telefoneDomain));
    }

    @Operation(summary = "Atualizar telefone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Telefone atualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TelefoneDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Regra de negócio",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> update(@PathVariable Long id, @PathVariable Long idUsuario, @Valid @RequestBody TelefoneCreateDTO telefoneCreateDTO){
        TelefoneDomain telefoneDomain = telefoneMapper.createTelefoneDomain(telefoneCreateDTO, id);
        telefoneDomain = updateTelefoneInput.update(telefoneDomain, idUsuario);
        return ResponseEntity.ok().body(telefoneMapper.toTelefoneDto(telefoneDomain));
    }
}
