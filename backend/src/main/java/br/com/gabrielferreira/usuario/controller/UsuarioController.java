package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.dto.UsuarioDTO;
import br.com.gabrielferreira.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.buscarUsuarioPorId(id));
    }
}
