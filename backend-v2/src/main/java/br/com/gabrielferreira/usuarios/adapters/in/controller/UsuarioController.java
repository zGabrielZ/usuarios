package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.PageInfoMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.UsuarioMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.UsuarioCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.UsuarioUpdateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioResumidoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.CreateUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.DeleteUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.FindUsuarioInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UpdateUsuarioInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Tag(name = "Usuário Controller", description = "Endpoints para realizar requisições de usuários")
@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final CreateUsuarioInput createUsuarioInput;

    private final FindUsuarioInput findUsuarioInput;

    private final UpdateUsuarioInput updateUsuarioInput;

    private final DeleteUsuarioInput deleteUsuarioInput;

    private final UsuarioMapper usuarioMapper;

    private final PageInfoMapper pageInfoMapper;

    @Operation(summary = "Cadastrar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Regra de negócio",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO){
        UsuarioDomain usuarioDomain = usuarioMapper.createUsuarioDomain(usuarioCreateDTO);
        usuarioDomain = createUsuarioInput.create(usuarioDomain);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(usuarioDomain.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioMapper.toUsuarioDto(usuarioDomain));
    }

    @Operation(summary = "Buscar usuário por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(id);
        return ResponseEntity.ok(usuarioMapper.toUsuarioDto(usuarioDomain));
    }

    @Operation(summary = "Buscar usuário por cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioResumidoDTO> findByCpf(@PathVariable String cpf){
        UsuarioDomain usuarioDomain = findUsuarioInput.findByCpf(cpf);
        return ResponseEntity.ok(usuarioMapper.toUsuarioResumidoDto(usuarioDomain));
    }

    @Operation(summary = "Buscar usuário por e-mail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResumidoDTO> findByEmail(@PathVariable String email){
        UsuarioDomain usuarioDomain = findUsuarioInput.findByEmail(email);
        return ResponseEntity.ok(usuarioMapper.toUsuarioResumidoDto(usuarioDomain));
    }

    @Operation(summary = "Atualizar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Regra de negócio",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO){
        UsuarioDomain usuarioDomain = usuarioMapper.updateUsuarioDomain(usuarioUpdateDTO, id);
        usuarioDomain = updateUsuarioInput.update(usuarioDomain);
        return ResponseEntity.ok().body(usuarioMapper.toUsuarioDto(usuarioDomain));
    }

    @Operation(summary = "Atualizar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        deleteUsuarioInput.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar usuários paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResumidoDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<Page<UsuarioResumidoDTO>> findAll(@ParameterObject @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                        @RequestParam(required = false) String nome,
                                        @RequestParam(required = false) String email,
                                        @RequestParam(required = false) BigDecimal renda){
        PageInfo pageInfo = pageInfoMapper.toPageInfo(pageable);
        List<UsuarioResumidoDTO> usuarioResumidoDTOS = usuarioMapper.toUsuarioResumidoDtos(findUsuarioInput.findAll(pageInfo, nome, email, renda));
        return ResponseEntity.ok().body(new PageImpl<>(usuarioResumidoDTOS, pageable, usuarioResumidoDTOS.size()));
    }
}
