package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.domain.GeneroDomain;
import br.com.gabrielferreira.usuario.dto.response.GeneroResponseDTO;
import br.com.gabrielferreira.usuario.mapper.GeneroMapper;
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

    private final GeneroMapper generoMapper;

    @GetMapping
    public ResponseEntity<List<GeneroResponseDTO>> buscarGeneros(){
        List<GeneroDomain> generoDomains = generoService.buscarGeneros();
        return ResponseEntity.ok().body(generoMapper.toGenerosResponsesDtos(generoDomains));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroResponseDTO> buscarGeneroPorId(@PathVariable Long id){
        GeneroDomain generoDomain = generoService.buscarGeneroPorId(id);
        return ResponseEntity.ok().body(generoMapper.toGeneroResponseDto(generoDomain));
    }

    @GetMapping("/buscar")
    public ResponseEntity<GeneroResponseDTO> buscarGeneroPorCodigo(@RequestParam String codigo){
        GeneroDomain generoDomain = generoService.buscarGeneroPorCodigo(codigo);
        return ResponseEntity.ok().body(generoMapper.toGeneroResponseDto(generoDomain));
    }
}
