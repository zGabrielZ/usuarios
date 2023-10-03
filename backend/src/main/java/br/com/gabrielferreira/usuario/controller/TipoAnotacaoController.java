package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.domain.TipoAnotacaoDomain;
import br.com.gabrielferreira.usuario.dto.response.TipoAnotacaoResponseDTO;
import br.com.gabrielferreira.usuario.mapper.TipoAnotacaoMapper;
import br.com.gabrielferreira.usuario.service.TipoAnotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-anotacoes")
@RequiredArgsConstructor
public class TipoAnotacaoController {

    private final TipoAnotacaoService tipoAnotacaoService;

    private final TipoAnotacaoMapper tipoAnotacaoMapper;

    @GetMapping
    public ResponseEntity<List<TipoAnotacaoResponseDTO>> buscarTipoAnotacoes(){
        List<TipoAnotacaoDomain> tipoAnotacaoDomains = tipoAnotacaoService.buscarTipoAnotacoes();
        return ResponseEntity.ok().body(tipoAnotacaoMapper.toTipoAnotacoesResponsesDtos(tipoAnotacaoDomains));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAnotacaoResponseDTO> buscarTipoAnotacaoPorId(@PathVariable Long id){
        TipoAnotacaoDomain tipoAnotacaoDomain = tipoAnotacaoService.buscarTipoAnotacaoPorId(id);
        return ResponseEntity.ok().body(tipoAnotacaoMapper.toTipoAnotacaoResponseDto(tipoAnotacaoDomain));
    }

    @GetMapping("/buscar")
    public ResponseEntity<TipoAnotacaoResponseDTO> buscarGeneroPorCodigo(@RequestParam String codigo){
        TipoAnotacaoDomain tipoAnotacaoDomain = tipoAnotacaoService.buscarTipoAnotacaoPorCodigo(codigo);
        return ResponseEntity.ok().body(tipoAnotacaoMapper.toTipoAnotacaoResponseDto(tipoAnotacaoDomain));
    }
}
