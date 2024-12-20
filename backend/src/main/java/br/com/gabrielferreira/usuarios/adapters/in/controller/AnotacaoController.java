package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.AnotacaoMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.PageInfoMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoResumidoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoInput;
import br.com.gabrielferreira.usuarios.utils.exemplo.swagger.ExemploAnotacaoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Anotação Controller", description = "Endpoints para realizar requisições de anotações do tipo genéricas")
@RestController
@RequestMapping("/v1/usuarios/{idUsuario}/anotacoes")
@RequiredArgsConstructor
public class AnotacaoController {

    private final FindAnotacaoInput findAnotacaoInput;

    private final PageInfoMapper pageInfoMapper;

    private final AnotacaoMapper anotacaoMapper;

    private final PagedResourcesAssembler<AnotacaoResumidoDTO> pagedResourcesAssembler;

    @Operation(summary = "Buscar anotações paginados")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Anotações encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AnotacaoResumidoDTO.class),
                            examples = @ExampleObject(
                                    value = ExemploAnotacaoUtils.ANOTACOES_ENCONTRADOS
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<PagedModel<AnotacaoResumidoDTO>> findAll(@PathVariable Long idUsuario,
                                                                   @ParameterObject @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                                   @RequestParam(required = false) String titulo,
                                                                   @RequestParam(required = false) String descricao){
        PageInfo pageInfo = pageInfoMapper.toPageInfo(pageable);
        List<AnotacaoResumidoDTO> anotacaoResumidoDTOS = anotacaoMapper.toAnotacoesResumidosDtos(findAnotacaoInput.findAll(pageInfo, titulo, descricao, idUsuario));
        anotacaoResumidoDTOS.forEach(anotacaoResumidoDto -> {
            if (TipoAnotacaoEnum.RASCUNHO.name().equals(anotacaoResumidoDto.getTipoAnotacao().codigo())) {
                anotacaoResumidoDto.add(getAnotacaoRascunho(anotacaoResumidoDto.getId(), idUsuario));
            }

            if (TipoAnotacaoEnum.LEMBRETE.name().equals(anotacaoResumidoDto.getTipoAnotacao().codigo())) {
                anotacaoResumidoDto.add(getAnotacaoLembrete(anotacaoResumidoDto.getId(), idUsuario));
            }

            if (TipoAnotacaoEnum.ESTUDO.name().equals(anotacaoResumidoDto.getTipoAnotacao().codigo())) {
                anotacaoResumidoDto.add(getAnotacaoEstudo(anotacaoResumidoDto.getId(), idUsuario));
            }
        });

        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(pageInfo.getPageSize(), pageInfo.getPageNumber(), anotacaoResumidoDTOS.size());
        return ResponseEntity.ok().body(PagedModel.of(anotacaoResumidoDTOS, pageMetadata, getAnotacoes(idUsuario, pageable, titulo, descricao)));
    }
    
    private Link getAnotacoes(Long idUsuario, Pageable pageable, String titulo, String descricao) {
        return linkTo(methodOn(AnotacaoController.class).findAll(idUsuario, pageable, titulo, descricao))
                .withSelfRel().withType("GET");
    }

    private Link getAnotacaoRascunho(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoRascunhoController.class).findRascunhoById(idUsuario, id))
                .withSelfRel().withType("GET");
    }

    private Link getAnotacaoLembrete(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoLembreteController.class).findLembreteById(idUsuario, id))
                .withSelfRel().withType("GET");
    }

    private Link getAnotacaoEstudo(Long id, Long idUsuario) {
        return linkTo(methodOn(AnotacaoEstudoController.class).findEstudoById(idUsuario, id))
                .withSelfRel().withType("GET");
    }
}
