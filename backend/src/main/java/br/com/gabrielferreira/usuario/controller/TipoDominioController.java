package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.domain.TipoDominioDomain;
import br.com.gabrielferreira.usuario.dto.response.TipoDominioResponseDTO;
import br.com.gabrielferreira.usuario.mapper.TipoDominioMapper;
import br.com.gabrielferreira.usuario.service.TipoDominioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-dominios")
@RequiredArgsConstructor
public class TipoDominioController {

    private final TipoDominioService tipoDominioService;

    private final TipoDominioMapper tipoDominioMapper;

    @GetMapping
    public ResponseEntity<List<TipoDominioResponseDTO>> buscarTipoDominios(){
        List<TipoDominioDomain> tipoAnotacaoDomains = tipoDominioService.buscarTipoDominios();
        return ResponseEntity.ok().body(tipoDominioMapper.toTipoDominiosResponsesDtos(tipoAnotacaoDomains));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDominioResponseDTO> buscarTipoDominioPorId(@PathVariable Long id){
        TipoDominioDomain tipoDominioDomain = tipoDominioService.buscarTipoDominioPorId(id);
        return ResponseEntity.ok().body(tipoDominioMapper.toTipoDominioResponseDto(tipoDominioDomain));
    }

    @GetMapping("/buscar")
    public ResponseEntity<TipoDominioResponseDTO> buscarTipoDominioPorCodigo(@RequestParam String codigo){
        TipoDominioDomain tipoDominioDomain = tipoDominioService.buscarTipoDominioPorCodigo(codigo);
        return ResponseEntity.ok().body(tipoDominioMapper.toTipoDominioResponseDto(tipoDominioDomain));
    }
}
