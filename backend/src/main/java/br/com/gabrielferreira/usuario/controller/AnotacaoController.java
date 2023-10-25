package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoUpdateRequestDTO;
import br.com.gabrielferreira.usuario.dto.response.AnotacaoResponseDTO;
import br.com.gabrielferreira.usuario.service.AnotacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

import static br.com.gabrielferreira.usuario.utils.PageUtils.*;
import static br.com.gabrielferreira.usuario.factory.dto.AnotacaoDTOFactory.*;
import static br.com.gabrielferreira.usuario.factory.domain.AnotacaoDomainFactory.*;

@RestController
@RequestMapping("/anotacoes")
@RequiredArgsConstructor
public class AnotacaoController {

    private final AnotacaoService anotacaoService;

    @PostMapping
    public ResponseEntity<AnotacaoResponseDTO> cadastrarAnotacao(@Valid @RequestBody AnotacaoCreateRequestDTO anotacaoCreateRequestDTO){
        AnotacaoDomain anotacaoDomain = anotacaoService.cadastrarAnotacao(toCreateAnotacao(anotacaoCreateRequestDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(anotacaoDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(toAnotacaoResponseDto(anotacaoDomain));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoResponseDTO> buscarAnotacao(@PathVariable Long id){
        AnotacaoDomain anotacaoDomain = anotacaoService.buscarAnotacaoPorId(id);
        return ResponseEntity.ok().body(toAnotacaoResponseDto(anotacaoDomain));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnotacaoResponseDTO> atualizarAnotacao(@PathVariable Long id, @Valid @RequestBody AnotacaoUpdateRequestDTO anotacaoUpdateRequestDTO){
        AnotacaoDomain anotacaoDomain = anotacaoService.atualizarAnotacao(toUpdateAnotacao(id, anotacaoUpdateRequestDTO));
        return ResponseEntity.ok().body(toAnotacaoResponseDto(anotacaoDomain));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnotacao(@PathVariable Long id){
        anotacaoService.deletarAnotacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<AnotacaoResponseDTO>> buscarAnotacoes(@RequestParam Long idUsuario, @PageableDefault(size = 5) Pageable pageable){
        validarPropriedades(pageable.getSort(), AnotacaoResponseDTO.class);
        Page<AnotacaoDomain> anotacoesDomains = anotacaoService.buscarAnotacoes(idUsuario, pageable);
        return ResponseEntity.ok().body(toAnotacoesResponsesDtos(anotacoesDomains));
    }
}
