package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas.AnotacaoHateoas;
import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.AnotacaoMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.PageInfoMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoResumidoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import br.com.gabrielferreira.usuarios.application.ports.in.FindAnotacaoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Anotação Controller", description = "Endpoints para realizar requisições de anotações do tipo genéricas")
@RestController
@RequestMapping("/v1/usuarios/{idUsuario}/anotacoes")
@RequiredArgsConstructor
public class AnotacaoController {

    private final FindAnotacaoInput findAnotacaoInput;

    private final PageInfoMapper pageInfoMapper;

    private final AnotacaoMapper anotacaoMapper;

    private final AnotacaoHateoas anotacaoHateoas;

    @Operation(summary = "Buscar anotações paginados")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Anotações encontrados"
            )
    })
    @GetMapping
    public ResponseEntity<PagedModel<AnotacaoResumidoDTO>> findAll(@PathVariable Long idUsuario,
                                                                   @ParameterObject @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                                   @RequestParam(required = false) String titulo,
                                                                   @RequestParam(required = false) String descricao){
        PageInfo pageInfo = pageInfoMapper.toPageInfo(pageable);
        List<AnotacaoResumidoDTO> anotacaoResumidoDtos = anotacaoMapper.toAnotacoesResumidosDtos(findAnotacaoInput.findAll(pageInfo, titulo, descricao, idUsuario));
        anotacaoHateoas.addLinkToGetAnotacoes(anotacaoResumidoDtos, idUsuario);

        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(pageInfo.getPageSize(), pageInfo.getPageNumber(), anotacaoResumidoDtos.size());
        return ResponseEntity.ok().body(PagedModel.of(anotacaoResumidoDtos, pageMetadata, anotacaoHateoas.getAnotacoes(idUsuario, pageable, titulo, descricao)));
    }

}
