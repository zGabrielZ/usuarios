package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.domain.UsuarioDomain;
import br.com.gabrielferreira.usuario.dto.request.UsuarioUpdateRequestDTO;
import br.com.gabrielferreira.usuario.dto.response.UsuarioResponseDTO;
import br.com.gabrielferreira.usuario.dto.request.UsuarioCreateRequestDTO;
import br.com.gabrielferreira.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

import static br.com.gabrielferreira.usuario.utils.PageUtils.*;
import static br.com.gabrielferreira.usuario.factory.domain.UsuarioDomainFactory.*;
import static br.com.gabrielferreira.usuario.factory.dto.UsuarioDTOFactory.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@Valid @RequestBody UsuarioCreateRequestDTO usuarioCreateRequestDTO){
        UsuarioDomain usuarioDomain = usuarioService.cadastrarUsuario(toCreateUsuario(usuarioCreateRequestDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(usuarioDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(toUsuarioResponseDto(usuarioDomain));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id){
        UsuarioDomain usuarioDomain = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok().body(toUsuarioResponseDto(usuarioDomain));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateRequestDTO usuarioUpdateRequestDTO){
        UsuarioDomain usuarioDomain = usuarioService.atualizarUsuario(toUpdateUsuario(id, usuarioUpdateRequestDTO));
        return ResponseEntity.ok().body(toUsuarioResponseDto(usuarioDomain));
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
        return ResponseEntity.ok().body(toUsuariosResponsesDtos(usuarioDomains));
    }
}
