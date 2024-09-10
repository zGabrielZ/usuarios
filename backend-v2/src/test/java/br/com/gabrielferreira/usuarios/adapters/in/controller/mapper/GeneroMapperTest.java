package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.response.GeneroDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TipoDominioDomain;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GeneroMapperTest {

    GeneroMapper generoMapper = GeneroMapper.INSTANCE;

    @Test
    @DisplayName("Deve criar gênero dto")
    @Order(1)
    void deveCriarGeneroDto(){
        DominioDomain genero = new DominioDomain(1L, "Masculino", "MASCULINO",
                new TipoDominioDomain(1L, "Gênero", "GENERO"));

        GeneroDTO generoDTO = generoMapper.toGeneroDto(genero);
        assertEquals(genero.getId(), generoDTO.id());
        assertEquals(genero.getDescricao(), generoDTO.descricao());
        assertEquals(genero.getCodigo(), generoDTO.codigo());
        assertEquals(genero.getTipo().getId(), generoDTO.tipo().id());
        assertEquals(genero.getTipo().getDescricao(), generoDTO.tipo().descricao());
        assertEquals(genero.getTipo().getCodigo(), generoDTO.tipo().codigo());
    }

    @Test
    @DisplayName("Deve criar lista de gêneros dto")
    @Order(2)
    void deveCriarListaGenerosDtos(){
        DominioDomain genero = new DominioDomain(1L, "Masculino", "MASCULINO",
                new TipoDominioDomain(1L, "Gênero", "GENERO"));
        List<DominioDomain> generos = new ArrayList<>();
        generos.add(genero);

        List<GeneroDTO> generoDTOS = generoMapper.toGenerosDtos(generos);
        assertFalse(generoDTOS.isEmpty());
        assertEquals(genero.getId(), generoDTOS.get(0).id());
        assertEquals(genero.getDescricao(), generoDTOS.get(0).descricao());
        assertEquals(genero.getCodigo(), generoDTOS.get(0).codigo());
        assertEquals(genero.getTipo().getId(), generoDTOS.get(0).tipo().id());
        assertEquals(genero.getTipo().getDescricao(), generoDTOS.get(0).tipo().descricao());
        assertEquals(genero.getTipo().getCodigo(), generoDTOS.get(0).tipo().codigo());
    }
}
