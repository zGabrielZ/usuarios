package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.dto.TipoTelefoneDTO;
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

    @GetMapping
    public ResponseEntity<List<TipoTelefoneDTO>> buscarTipoTelefones(){
        return ResponseEntity.ok().body(tipoTelefoneService.buscarTiposTelefones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTelefoneDTO> buscarTipoTelefonePorId(@PathVariable Long id){
        return ResponseEntity.ok().body(tipoTelefoneService.buscarTipoTelefonePorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<TipoTelefoneDTO> buscarTipoTelefonePorCodigo(@RequestParam String codigo){
        return ResponseEntity.ok().body(tipoTelefoneService.buscarTipoTelefonePorCodigo(codigo));
    }
}
