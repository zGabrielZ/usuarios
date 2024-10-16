package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.response.PerfilDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PerfilMapperTest {

    PerfilMapper perfilMapper = Mappers.getMapper(PerfilMapper.class);

    @Test
    @DisplayName("Deve criar perfil dto")
    @Order(1)
    void deveCriarPerfilDto(){
        PerfilDomain perfilDomain = new PerfilDomain(1L, "Cliente", "ROLE_CLIENT");

        PerfilDTO perfilDTO = perfilMapper.toPerfilDto(perfilDomain);
        assertEquals(perfilDomain.getId(), perfilDTO.id());
        assertEquals(perfilDomain.getTitulo(), perfilDTO.titulo());
        assertEquals(perfilDomain.getAutoriedade(), perfilDTO.autoriedade());
    }

    @Test
    @DisplayName("Deve criar lista perfil dto")
    @Order(2)
    void deveCriarListaPerfilDto(){
        PerfilDomain perfilDomain = new PerfilDomain(1L, "Cliente", "ROLE_CLIENT");
        List<PerfilDomain> perfilDomains = List.of(perfilDomain);

        List<PerfilDTO> perfilDTOS = perfilMapper.toPerfisDtos(perfilDomains);
        assertFalse(perfilDTOS.isEmpty());
    }
}
