package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas.TipoTelefoneHateoas;
import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.TipoTelefoneMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TipoTelefoneDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.FindTipoTelefoneInput;
import br.com.gabrielferreira.usuarios.utils.exemplo.swagger.ExemploTipoTelefoneUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Tipo Telefone Controller", description = "Endpoints para realizar requisições de tipos telefones")
@RestController
@RequestMapping("/v1/tipos-telefones")
@RequiredArgsConstructor
public class TipoTelefoneController {

    private final FindTipoTelefoneInput findTipoTelefoneInput;

    private final TipoTelefoneMapper tipoTelefoneMapper;

    private final TipoTelefoneHateoas tipoTelefoneHateoas;

    @Operation(summary = "Buscar tipo telefone por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipo telefone encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tipo telefone não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = ExemploTipoTelefoneUtils.TIPO_TELEFONE_NAO_ENCONTRADO
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TipoTelefoneDTO> findById(@PathVariable Long id){
        DominioDomain dominioDomain = findTipoTelefoneInput.findById(id);
        return ResponseEntity.ok(tipoTelefoneMapper.toTipoTelefoneDto(dominioDomain));
    }

    @Operation(summary = "Buscar tipos telefones")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tipos telefones encontrados"
            )
    })
    @GetMapping
    public ResponseEntity<CollectionModel<TipoTelefoneDTO>> findAll(){
        List<DominioDomain> dominioDomains = findTipoTelefoneInput.findAllByTipoCodigo();
        List<TipoTelefoneDTO> tiposTelefonesDtos = tipoTelefoneMapper.toTiposTelefonesDtos(dominioDomains);
        tipoTelefoneHateoas.addLinkGetTipoTelefone(tiposTelefonesDtos);
        return ResponseEntity.ok(CollectionModel.of(tiposTelefonesDtos, tipoTelefoneHateoas.getTiposTelefones()));
    }
}
