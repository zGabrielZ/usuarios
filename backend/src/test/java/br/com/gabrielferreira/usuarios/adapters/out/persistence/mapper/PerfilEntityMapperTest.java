package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.PerfilEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.PerfilDomain;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PerfilEntityMapperTest {

    private final PerfilEntityMapper perfilEntityMapper = Mappers.getMapper(PerfilEntityMapper.class);

    @Test
    @DisplayName("Deve criar perfil domain")
    @Order(1)
    void deveCriarPerfilDomain(){
        PerfilEntity perfilEntity = PerfilEntity.builder()
                .id(1L)
                .titulo("Cliente")
                .autoriedade("ROLE_CLIENT")
                .build();

        PerfilDomain perfilDomain = perfilEntityMapper.toPerfilDomain(perfilEntity);
        assertNotNull(perfilDomain);
        assertEquals(perfilEntity.getId(), perfilDomain.getId());
        assertEquals(perfilEntity.getTitulo(), perfilDomain.getTitulo());
        assertEquals(perfilEntity.getAutoriedade(), perfilDomain.getAutoriedade());
    }

    @Test
    @DisplayName("Deve criar perfis domains")
    @Order(2)
    void deveCriarPerfisDomains(){
        PerfilEntity perfilEntity = PerfilEntity.builder()
                .id(1L)
                .titulo("Cliente")
                .autoriedade("ROLE_CLIENT")
                .build();
        List<PerfilEntity> perfilEntities = List.of(perfilEntity);

        List<PerfilDomain> perfilDomains = perfilEntityMapper.toPerfisDomains(perfilEntities);
        assertFalse(perfilDomains.isEmpty());
    }
}
