package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.DominioEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.TipoDominioEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DominioEntityMapperTest {

    DominioEntityMapper dominioEntityMapper = Mappers.getMapper(DominioEntityMapper.class);

    @Test
    @DisplayName("Deve criar dominio domain")
    @Order(1)
    void deveCriarDominioDomain(){
        DominioEntity dominioEntity = DominioEntity.builder()
                .id(1L)
                .descricao("Masculino")
                .codigo("MASCULINO")
                .tipo(
                        TipoDominioEntity.builder()
                                .id(1L)
                                .descricao("Gênero")
                                .codigo("GENERO")
                                .build()
                )
                .build();

        DominioDomain dominioDomain = dominioEntityMapper.toDominioDomain(dominioEntity);
        assertEquals(dominioEntity.getId(), dominioDomain.getId());
        assertEquals(dominioEntity.getDescricao(), dominioDomain.getDescricao());
        assertEquals(dominioEntity.getCodigo(), dominioDomain.getCodigo());
        assertEquals(dominioEntity.getTipo().getId(), dominioDomain.getTipo().getId());
        assertEquals(dominioEntity.getTipo().getDescricao(), dominioDomain.getTipo().getDescricao());
        assertEquals(dominioEntity.getTipo().getCodigo(), dominioDomain.getTipo().getCodigo());
    }

    @Test
    @DisplayName("Deve criar lista de gêneros dto")
    @Order(2)
    void deveCriarListaGenerosDtos(){
        DominioEntity dominioEntity = DominioEntity.builder()
                .id(1L)
                .descricao("Masculino")
                .codigo("MASCULINO")
                .tipo(
                        TipoDominioEntity.builder()
                                .id(1L)
                                .descricao("Gênero")
                                .codigo("GENERO")
                                .build()
                )
                .build();
        List<DominioEntity> dominioEntities = new ArrayList<>();
        dominioEntities.add(dominioEntity);

        List<DominioDomain> dominioDomains = dominioEntityMapper.toDominiosDomains(dominioEntities);
        assertFalse(dominioDomains.isEmpty());
        assertEquals(dominioEntity.getId(), dominioDomains.get(0).getId());
        assertEquals(dominioEntity.getDescricao(), dominioDomains.get(0).getDescricao());
        assertEquals(dominioEntity.getCodigo(), dominioDomains.get(0).getCodigo());
        assertEquals(dominioEntity.getTipo().getId(), dominioDomains.get(0).getTipo().getId());
        assertEquals(dominioEntity.getTipo().getDescricao(), dominioDomains.get(0).getTipo().getDescricao());
        assertEquals(dominioEntity.getTipo().getCodigo(), dominioDomains.get(0).getTipo().getCodigo());
    }
}
