package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.domain.DominioDomain;
import br.com.gabrielferreira.usuario.dto.response.DominioResponseDTO;
import br.com.gabrielferreira.usuario.service.DominioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.gabrielferreira.usuario.factory.dto.DominioDTOFactory.*;

@RestController
@RequestMapping("/dominios")
@RequiredArgsConstructor
public class DominioController {

    private final DominioService dominioService;

    @GetMapping
    public ResponseEntity<List<DominioResponseDTO>> buscarDominios(@RequestParam(name = "idTipoDominio", required = false) Long idTipoDominio
            , @RequestParam(name = "codigoTipoDominio", required = false) String codigoTipoDominio){
        List<DominioDomain> dominioDomains = dominioService.buscarDominios(idTipoDominio, codigoTipoDominio);
        return ResponseEntity.ok().body(toDominiosResponsesDtos(dominioDomains));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DominioResponseDTO> buscarDominioPorId(@PathVariable Long id){
        DominioDomain dominioDomain = dominioService.buscarDominioPorId(id);
        return ResponseEntity.ok().body(toDominioResponseDto(dominioDomain));
    }

    @GetMapping("/buscar")
    public ResponseEntity<DominioResponseDTO> buscarDominioPorCodigo(@RequestParam String codigo){
        DominioDomain dominioDomain = dominioService.buscarDominioPorCodigo(codigo);
        return ResponseEntity.ok().body(toDominioResponseDto(dominioDomain));
    }
}
