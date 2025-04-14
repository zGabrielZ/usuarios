package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas.TelefoneHateoas;
import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.TelefoneMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.TelefoneCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TelefoneDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateTelefoneInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UserCurrentInput;
import io.swagger.v3.oas.annotations.Operation;
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

    private final TelefoneHateoas telefoneHateoas;

    private final UserCurrentInput userCurrentInput;

    @Operation(summary = "Buscar telefone por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Telefone encontrado"
            )
    })
    @GetMapping
    public ResponseEntity<TelefoneDTO> findById(@PathVariable Long idUsuario){
        userCurrentInput.checkNaoContemPerfilAdmin(idUsuario);
        TelefoneDomain telefoneDomain = findTelefoneInput.findByUsuarioId(idUsuario);
        TelefoneDTO telefoneDto = telefoneMapper.toTelefoneDto(telefoneDomain);
        telefoneHateoas.addLinkGetTelefone(telefoneDto, idUsuario);
        return ResponseEntity.ok(telefoneDto);
    }

    @Operation(summary = "Atualizar telefone")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Telefone atualizado"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> update(@PathVariable Long id, @PathVariable Long idUsuario, @Valid @RequestBody TelefoneCreateDTO telefoneCreateDTO){
        userCurrentInput.checkNaoContemPerfilAdmin(idUsuario);
        TelefoneDomain telefoneDomain = telefoneMapper.createTelefoneDomain(telefoneCreateDTO, id);
        telefoneDomain = updateTelefoneInput.update(telefoneDomain, idUsuario);
        TelefoneDTO telefoneDto = telefoneMapper.toTelefoneDto(telefoneDomain);
        telefoneHateoas.addLinkPutTelefone(telefoneDto, idUsuario);
        return ResponseEntity.ok().body(telefoneDto);
    }
}
