package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoUpdateRequestDTO;
import br.com.gabrielferreira.usuario.dto.response.AnotacaoResponseDTO;
import br.com.gabrielferreira.usuario.dto.response.AnotacaoResumidaResponseDTO;
import br.com.gabrielferreira.usuario.mapper.domain.AnotacaoDomainMapper;
import br.com.gabrielferreira.usuario.mapper.dto.AnotacaoDTOMapper;
import br.com.gabrielferreira.usuario.service.AnotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

import static br.com.gabrielferreira.usuario.utils.PageUtils.*;

@RestController
@RequestMapping("/anotacoes")
@RequiredArgsConstructor
public class AnotacaoController {

    private final AnotacaoService anotacaoService;

    private final AnotacaoDomainMapper anotacaoDomainMapper;

    private final AnotacaoDTOMapper anotacaoDTOMapper;

    @PostMapping
    public ResponseEntity<AnotacaoResponseDTO> cadastrarAnotacao(@RequestBody AnotacaoCreateRequestDTO anotacaoCreateRequestDTO){
        AnotacaoDomain anotacaoDomain = anotacaoService.cadastrarAnotacao(anotacaoDomainMapper.toAnotacaoDomain(anotacaoCreateRequestDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(anotacaoDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(anotacaoDTOMapper.toAnotacaoDto(anotacaoDomain));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoResponseDTO> buscarAnotacao(@PathVariable Long id){
        AnotacaoDomain anotacaoDomain = anotacaoService.buscarAnotacaoPorId(id);
        return ResponseEntity.ok().body(anotacaoDTOMapper.toAnotacaoDto(anotacaoDomain));
    }

    @GetMapping("/resumida/{id}")
    public ResponseEntity<AnotacaoResumidaResponseDTO> buscarAnotacaoResumida(@PathVariable Long id){
        AnotacaoDomain anotacaoDomain = anotacaoService.buscarAnotacaoResumidaPorId(id);
        return ResponseEntity.ok().body(anotacaoDTOMapper.toAnotacaoResumidaDto(anotacaoDomain));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnotacaoResponseDTO> atualizarAnotacao(@PathVariable Long id, @RequestBody AnotacaoUpdateRequestDTO anotacaoUpdateRequestDTO){
        AnotacaoDomain anotacaoDomain = anotacaoService.atualizarAnotacao(anotacaoDomainMapper.toAnotacaoDomain(id, anotacaoUpdateRequestDTO));
        return ResponseEntity.ok().body(anotacaoDTOMapper.toAnotacaoDto(anotacaoDomain));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnotacao(@PathVariable Long id){
        anotacaoService.deletarAnotacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/resumida")
    public ResponseEntity<Page<AnotacaoResumidaResponseDTO>> buscarAnotacoes(@RequestParam Long idUsuario, @PageableDefault(size = 5) Pageable pageable){
        validarPropriedades(pageable.getSort(), AnotacaoResumidaResponseDTO.class);
        Page<AnotacaoDomain> anotacoesDomains = anotacaoService.buscarAnotacoes(idUsuario, pageable);
        return ResponseEntity.ok().body(anotacaoDTOMapper.toAnotacoesResumidaDtos(anotacoesDomains));
    }
}
