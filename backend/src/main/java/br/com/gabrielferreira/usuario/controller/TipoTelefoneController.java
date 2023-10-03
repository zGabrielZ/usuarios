package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.domain.TipoTelefoneDomain;
import br.com.gabrielferreira.usuario.dto.response.TipoTelefoneResponseDTO;
import br.com.gabrielferreira.usuario.mapper.TipoTelefoneMapper;
import br.com.gabrielferreira.usuario.service.TipoTelefoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tipo-telefones")
@RequiredArgsConstructor
public class TipoTelefoneController {

    private final TipoTelefoneService tipoTelefoneService;

    private final TipoTelefoneMapper tipoTelefoneMapper;

    @GetMapping
    public ResponseEntity<List<TipoTelefoneResponseDTO>> buscarTipoTelefones(){
        List<TipoTelefoneDomain> tipoTelefoneDomains = tipoTelefoneService.buscarTiposTelefones();
        return ResponseEntity.ok().body(tipoTelefoneMapper.toTipoTelefonesResponsesDtos(tipoTelefoneDomains));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTelefoneResponseDTO> buscarTipoTelefonePorId(@PathVariable Long id){
        TipoTelefoneDomain tipoTelefoneDomain = tipoTelefoneService.buscarTipoTelefonePorId(id);
        return ResponseEntity.ok().body(tipoTelefoneMapper.toTipoTelefoneResponseDto(tipoTelefoneDomain));
    }

    @GetMapping("/buscar")
    public ResponseEntity<TipoTelefoneResponseDTO> buscarTipoTelefonePorCodigo(@RequestParam String codigo){
        TipoTelefoneDomain tipoTelefoneDomain = tipoTelefoneService.buscarTipoTelefonePorCodigo(codigo);
        return ResponseEntity.ok().body(tipoTelefoneMapper.toTipoTelefoneResponseDto(tipoTelefoneDomain));
    }
}
