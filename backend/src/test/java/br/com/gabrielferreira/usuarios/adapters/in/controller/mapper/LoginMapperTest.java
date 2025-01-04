package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.LoginDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TokenDTO;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginMapperTest {

    private final LoginMapper loginMapper = Mappers.getMapper(LoginMapper.class);

    @Test
    @DisplayName("Deve criar token dto")
    @Order(1)
    void deveCriarTokenDto(){
        String token = "123";

        TokenDTO tokenDto = loginMapper.tokenDto(token);
        assertEquals("Bearer", tokenDto.tipo());
        assertEquals(token, tokenDto.token());
    }

    @Test
    @DisplayName("Deve criar usernamePasswordAuthenticationToken")
    @Order(1)
    void deveCriarUsernamePasswordAuthenticationToken(){
        LoginDTO loginDTO = new LoginDTO("email@email.com", "123");

        UsernamePasswordAuthenticationToken user = loginMapper.toUsernamePasswordAuthenticationToken(loginDTO);
        assertEquals(loginDTO.email(), user.getPrincipal());
        assertEquals(loginDTO.senha(), user.getCredentials());
    }
}
