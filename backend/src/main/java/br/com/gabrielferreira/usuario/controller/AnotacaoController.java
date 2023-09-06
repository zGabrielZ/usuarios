package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.dto.*;
import br.com.gabrielferreira.usuario.service.AnotacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/anotacoes")
@RequiredArgsConstructor
public class AnotacaoController {

    private final AnotacaoService anotacaoService;

    @PostMapping
    public ResponseEntity<AnotacaoDTO> cadastrarAnotacao(@RequestBody AnotacaoInsertDTO anotacaoInsertDTO){
        AnotacaoDTO anotacaoDTO = anotacaoService.cadastrarAnotacao(anotacaoInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(anotacaoDTO.id()).toUri();
        return ResponseEntity.created(uri).body(anotacaoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoDTO> buscarAnotacao(@PathVariable Long id){
        return ResponseEntity.ok().body(anotacaoService.buscarAnotacaoPorId(id));
    }

    @GetMapping("/resumida/{id}")
    public ResponseEntity<AnotacaoResumidaDTO> buscarAnotacaoResumida(@PathVariable Long id){
        return ResponseEntity.ok().body(anotacaoService.buscarAnotacaoResumidaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnotacaoDTO> atualizarAnotacao(@PathVariable Long id, @RequestBody AnotacaoUpdateDTO anotacaoUpdateDTO){
        return ResponseEntity.ok().body(anotacaoService.atualizarAnotacao(id, anotacaoUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnotacao(@PathVariable Long id){
        anotacaoService.deletarAnotacao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/resumida")
    public ResponseEntity<Page<AnotacaoResumidaDTO>> buscarAnotacoes(@RequestParam Long idUsuario, Pageable pageable){
        return ResponseEntity.ok().body(anotacaoService.buscarAnotacoes(idUsuario, pageable));
    }
}
