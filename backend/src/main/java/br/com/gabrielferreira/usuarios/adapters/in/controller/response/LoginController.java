package br.com.gabrielferreira.usuarios.adapters.in.controller.response;

import br.com.gabrielferreira.usuarios.adapters.in.controller.mapper.LoginMapper;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.LoginDTO;
import br.com.gabrielferreira.usuarios.application.ports.in.GenerateTokenInput;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login Controller", description = "Endpoints para realizar login")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final GenerateTokenInput generateTokenInput;

    private final LoginMapper loginMapper;

    @Operation(summary = "Logar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Regra de negócio",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken dadosLogin = loginMapper.toUsernamePasswordAuthenticationToken(loginDTO);
        Authentication authentication =  authenticationManager.authenticate(dadosLogin);

        String token = generateTokenInput.generate(authentication);
        return ResponseEntity.ok(loginMapper.tokenDto(token));
    }
}
