package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.dto.request.UsuarioUpdateRequestDTO;
import br.com.gabrielferreira.usuario.dto.response.UsuarioResponseDTO;
import br.com.gabrielferreira.usuario.dto.request.UsuarioCreateRequestDTO;
import br.com.gabrielferreira.usuario.mapper.UsuarioMapper;
import br.com.gabrielferreira.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

import static br.com.gabrielferreira.usuario.utils.PageUtils.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody UsuarioCreateRequestDTO usuarioCreateRequestDTO){
        UsuarioDomain usuarioDomain = usuarioService.cadastrarUsuario(usuarioMapper.toUsuarioDomain(usuarioCreateRequestDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(usuarioDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioMapper.toUsuarioResponseDto(usuarioDomain));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id){
        UsuarioDomain usuarioDomain = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok().body(usuarioMapper.toUsuarioResponseDto(usuarioDomain));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioUpdateRequestDTO usuarioUpdateRequestDTO){
        UsuarioDomain usuarioDomain = usuarioService.atualizarUsuario(usuarioMapper.toUsuarioDomain(id, usuarioUpdateRequestDTO));
        return ResponseEntity.ok().body(usuarioMapper.toUsuarioResponseDto(usuarioDomain));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuarioPorId(@PathVariable Long id){
        usuarioService.deletarUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> buscarUsuarios(@PageableDefault(size = 5) Pageable pageable){
        validarPropriedades(pageable.getSort(), UsuarioResponseDTO.class);
        Page<UsuarioDomain> usuarioDomains = usuarioService.buscarUsuarios(pageable);
        return ResponseEntity.ok().body(usuarioMapper.toUsuariosResponsesDtos(usuarioDomains));
    }
}
