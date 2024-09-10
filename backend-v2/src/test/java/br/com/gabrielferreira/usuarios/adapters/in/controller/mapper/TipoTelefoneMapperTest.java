package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TipoTelefoneDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TipoDominioDomain;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TipoTelefoneMapperTest {

    TipoTelefoneMapper tipoTelefoneMapper = TipoTelefoneMapper.INSTANCE;

    @Test
    @DisplayName("Deve criar tipo telefone dto")
    @Order(1)
    void deveCriarTipoTelefoneDto(){
        DominioDomain tipoTelefone = new DominioDomain(4L, "Residencial", "RESIDENCIAL",
                new TipoDominioDomain(2L, "Tipo de telefone", "TIPO_TELEFONE"));

        TipoTelefoneDTO tipoTelefoneDTO = tipoTelefoneMapper.toTipoTelefoneDto(tipoTelefone);
        assertEquals(tipoTelefone.getId(), tipoTelefoneDTO.id());
        assertEquals(tipoTelefone.getDescricao(), tipoTelefoneDTO.descricao());
        assertEquals(tipoTelefone.getCodigo(), tipoTelefoneDTO.codigo());
        assertEquals(tipoTelefone.getTipo().getId(), tipoTelefoneDTO.tipo().id());
        assertEquals(tipoTelefone.getTipo().getDescricao(), tipoTelefoneDTO.tipo().descricao());
        assertEquals(tipoTelefone.getTipo().getCodigo(), tipoTelefoneDTO.tipo().codigo());
    }

    @Test
    @DisplayName("Deve criar lista de tipos telefones dto")
    @Order(2)
    void deveCriarListaTiposTelefonesDtos(){
        DominioDomain tipoTelefone = new DominioDomain(4L, "Residencial", "RESIDENCIAL",
                new TipoDominioDomain(2L, "Tipo de telefone", "TIPO_TELEFONE"));
        List<DominioDomain> tipos = new ArrayList<>();
        tipos.add(tipoTelefone);

        List<TipoTelefoneDTO> tipoTelefoneDTOS = tipoTelefoneMapper.toTiposTelefonesDtos(tipos);
        assertFalse(tipoTelefoneDTOS.isEmpty());
        assertEquals(tipoTelefone.getId(), tipoTelefoneDTOS.get(0).id());
        assertEquals(tipoTelefone.getDescricao(), tipoTelefoneDTOS.get(0).descricao());
        assertEquals(tipoTelefone.getCodigo(), tipoTelefoneDTOS.get(0).codigo());
        assertEquals(tipoTelefone.getTipo().getId(), tipoTelefoneDTOS.get(0).tipo().id());
        assertEquals(tipoTelefone.getTipo().getDescricao(), tipoTelefoneDTOS.get(0).tipo().descricao());
        assertEquals(tipoTelefone.getTipo().getCodigo(), tipoTelefoneDTOS.get(0).tipo().codigo());
    }
}
