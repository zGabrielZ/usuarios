package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.LoginDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TokenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    @Mapping(source = "token", target = "token")
    @Mapping(target = "tipo", constant = "Bearer")
    TokenDTO tokenDto(String token);

    default UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken(LoginDTO loginDTO) {
        return new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
    }
}
