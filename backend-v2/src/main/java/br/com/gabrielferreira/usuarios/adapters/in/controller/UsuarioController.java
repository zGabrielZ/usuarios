package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.UsuarioMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.UsuarioCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.CreateUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final CreateUsuarioInput createUsuarioInput;

    private final FindUsuarioInput findUsuarioInput;

    private final UsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO){
        UsuarioDomain usuarioDomain = usuarioMapper.createUsuarioDomain(usuarioCreateDTO);
        usuarioDomain = createUsuarioInput.create(usuarioDomain);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(usuarioDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioMapper.toUsuarioDto(usuarioDomain));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> find(@PathVariable Long id){
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(id);
        return ResponseEntity.ok(usuarioMapper.toUsuarioDto(usuarioDomain));
    }
}
