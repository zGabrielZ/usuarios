package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas.PerfilHateoas;
import br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas.UsuarioHateoas;
import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.PageInfoMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.PerfilMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.UsuarioMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.UsuarioCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.UsuarioUpdateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.PerfilDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioResumidoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.PageInfo;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
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

    private final UsuarioHateoas usuarioHateoas;

    private final FindPerfilInput findPerfilInput;

    private final PerfilMapper perfilMapper;

    private final PerfilHateoas perfilHateoas;

    @Operation(summary = "Cadastrar usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário cadastrado"
            )
    })
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO){
        UsuarioDomain usuarioDomain = usuarioMapper.createUsuarioDomain(usuarioCreateDTO);
        usuarioDomain = createUsuarioInput.create(usuarioDomain);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(usuarioDomain.getId()).toUri();

        UsuarioDTO usuarioDto = usuarioMapper.toUsuarioDto(usuarioDomain);
        usuarioHateoas.addLinkToPost(usuarioDto);
        return ResponseEntity.created(uri).body(usuarioDto);
    }

    @Operation(summary = "Buscar usuário por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário encontrado"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        UsuarioDomain usuarioDomain = findUsuarioInput.findById(id);

        UsuarioDTO usuarioDto = usuarioMapper.toUsuarioDto(usuarioDomain);
        usuarioHateoas.addLinkToGet(usuarioDto);
        return ResponseEntity.ok(usuarioDto);
    }

    @Operation(summary = "Buscar usuário por cpf")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário encontrado"
            )
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioResumidoDTO> findByCpf(@PathVariable String cpf){
        UsuarioDomain usuarioDomain = findUsuarioInput.findByCpf(cpf);
        UsuarioResumidoDTO usuarioResumidoDto = usuarioMapper.toUsuarioResumidoDto(usuarioDomain);
        usuarioHateoas.addLinkToGetCpf(usuarioResumidoDto);
        return ResponseEntity.ok(usuarioResumidoDto);
    }

    @Operation(summary = "Buscar usuário por e-mail")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário encontrado"
            )
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResumidoDTO> findByEmail(@PathVariable String email){
        UsuarioDomain usuarioDomain = findUsuarioInput.findByEmail(email);
        UsuarioResumidoDTO usuarioResumidoDto = usuarioMapper.toUsuarioResumidoDto(usuarioDomain);
        usuarioHateoas.addLinkToGetEmail(usuarioResumidoDto);
        return ResponseEntity.ok(usuarioResumidoDto);
    }

    @Operation(summary = "Atualizar usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário atualizado"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO){
        UsuarioDomain usuarioDomain = usuarioMapper.updateUsuarioDomain(usuarioUpdateDTO, id);
        usuarioDomain = updateUsuarioInput.update(usuarioDomain);
        UsuarioDTO usuarioDto = usuarioMapper.toUsuarioDto(usuarioDomain);
        usuarioHateoas.addLinkToPut(usuarioDto);
        return ResponseEntity.ok().body(usuarioDto);
    }

    @Operation(summary = "Deletar usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário deletado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        deleteUsuarioInput.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar usuários paginados")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuários encontrados"
            )
    })
    @GetMapping
    public ResponseEntity<PagedModel<UsuarioResumidoDTO>> findAll(@ParameterObject @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                                        @RequestParam(required = false) String nome,
                                                                        @RequestParam(required = false) String email,
                                                                        @RequestParam(required = false) BigDecimal renda){
        PageInfo pageInfo = pageInfoMapper.toPageInfo(pageable);
        List<UsuarioResumidoDTO> usuarioResumidoDtos = usuarioMapper.toUsuarioResumidoDtos(findUsuarioInput.findAll(pageInfo, nome, email, renda));
        usuarioHateoas.addLinkToGetUsuarios(usuarioResumidoDtos);

        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(pageInfo.getPageSize(), pageInfo.getPageNumber(), usuarioResumidoDtos.size());
        return ResponseEntity.ok().body(PagedModel.of(usuarioResumidoDtos, pageMetadata, usuarioHateoas.getUsuarios(pageable, nome, email, renda)));
    }

    @Operation(summary = "Atualizar usuário para admin")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário atualizado"
            )
    })
    @PutMapping("/{id}/admin")
    public ResponseEntity<Void> updateRoleAdmin(@PathVariable Long id){
        updateUsuarioInput.updateRoleAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar usuário para cliente")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário atualizado"
            )
    })
    @PutMapping("/{id}/client")
    public ResponseEntity<Void> updateRoleClient(@PathVariable Long id){
        updateUsuarioInput.updateRoleClient(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar perfil do usuário por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Perfil encontrado"
            )
    })
    @GetMapping("/{id}/perfis/{idPerfil}")
    public ResponseEntity<PerfilDTO> findPerfilByIdUsuarioAndIdPerfil(@PathVariable Long id, @PathVariable Long idPerfil){
        PerfilDomain perfilDomain = findPerfilInput.findByIdAndIdUsuario(idPerfil, id);

        PerfilDTO perfilDto = perfilMapper.toPerfilDto(perfilDomain);
        perfilHateoas.addLinkToPerfilUsuario(perfilDto);
        return ResponseEntity.ok(perfilDto);
    }

    @Operation(summary = "Buscar perfis do usuário por id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Perfis encontrados"
            )
    })
    @GetMapping("/{id}/perfis")
    public ResponseEntity<CollectionModel<PerfilDTO>> findPerfisByIdUsuario(@PathVariable Long id){
        List<PerfilDomain> perfilDomains = findPerfilInput.findAllByIdUsuario(id);

        List<PerfilDTO> perfilDtos = perfilMapper.toPerfisDtos(perfilDomains);
        perfilHateoas.addLinkToPerfis(perfilDtos);
        return ResponseEntity.ok(CollectionModel.of(perfilDtos, perfilHateoas.getPerfis()));
    }
}
