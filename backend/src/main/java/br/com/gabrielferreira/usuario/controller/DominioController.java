package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.dto.response.DominioResponseDTO;
import br.com.gabrielferreira.usuario.mapper.DominioMapper;
import br.com.gabrielferreira.usuario.service.DominioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dominios")
@RequiredArgsConstructor
public class DominioController {

    private final DominioService dominioService;

    private final DominioMapper dominioMapper;

    @GetMapping
    public ResponseEntity<List<DominioResponseDTO>> buscarDominios(@RequestParam(name = "idTipoDominio", required = false) Long idTipoDominio
            , @RequestParam(name = "codigoTipoDominio", required = false) String codigoTipoDominio){
        List<DominioDomain> dominioDomains = dominioService.buscarDominios(idTipoDominio, codigoTipoDominio);
        return ResponseEntity.ok().body(dominioMapper.toDominiosResponsesDtos(dominioDomains));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DominioResponseDTO> buscarDominioPorId(@PathVariable Long id){
        DominioDomain dominioDomain = dominioService.buscarDominioPorId(id);
        return ResponseEntity.ok().body(dominioMapper.toDominioResponseDto(dominioDomain));
    }

    @GetMapping("/buscar")
    public ResponseEntity<DominioResponseDTO> buscarDominioPorCodigo(@RequestParam String codigo){
        DominioDomain dominioDomain = dominioService.buscarDominioPorCodigo(codigo);
        return ResponseEntity.ok().body(dominioMapper.toDominioResponseDto(dominioDomain));
    }
}
