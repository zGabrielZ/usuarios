package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.dto.GeneroDTO;
import br.com.gabrielferreira.usuario.service.GeneroService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> buscarGeneros(){
        return ResponseEntity.ok().body(generoService.buscarGeneros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> buscarGeneroPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(generoService.buscarGeneroPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<GeneroDTO> buscarGeneroPorCodigo(@RequestParam String codigo){
        return ResponseEntity.ok().body(generoService.buscarGeneroPorCodigo(codigo));
    }
}
