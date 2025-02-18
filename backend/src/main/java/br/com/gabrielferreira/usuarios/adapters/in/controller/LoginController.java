package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.LoginMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.LoginDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TokenDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import br.com.gabrielferreira.usuarios.application.ports.in.GenerateTokenInput;
import br.com.gabrielferreira.usuarios.application.ports.in.UserCurrentInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login Controller", description = "Endpoints para realizar login")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final GenerateTokenInput generateTokenInput;

    private final LoginMapper loginMapper;

    private final UserCurrentInput userCurrentInput;

    @Operation(summary = "Logar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenDTO.class)) })
    })
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken dadosLogin = loginMapper.toUsernamePasswordAuthenticationToken(loginDTO);
        Authentication authentication =  authenticationManager.authenticate(dadosLogin);

        String token = generateTokenInput.generate((UsuarioDomain) authentication.getPrincipal());
        return ResponseEntity.ok(loginMapper.tokenDto(token));
    }

    @Operation(summary = "Refresh token do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token atualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
                    content = @Content)
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenDTO> refreshToken(){
        UsuarioDomain usuarioDomain = userCurrentInput.getUserCurrent();

        String token = generateTokenInput.refreshToken(usuarioDomain);
        return ResponseEntity.ok(loginMapper.tokenDto(token));
    }
}
