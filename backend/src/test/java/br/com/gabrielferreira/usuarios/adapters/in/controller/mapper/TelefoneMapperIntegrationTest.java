package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.TelefoneCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.TipoTelefoneCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TelefoneDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TipoDominioDomain;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TelefoneMapperIntegrationTest {

    @Autowired
    protected TelefoneMapper telefoneMapper;

    @Test
    @DisplayName("Deve criar telefone dto")
    @Order(1)
    void deveCriarTelefoneDto(){
        TelefoneDomain telefoneDomain = new TelefoneDomain(1L, "999999999", "11", "teste",
                new DominioDomain(5L, "Celular", "CELULAR", new TipoDominioDomain(2L, "Tipo de telefone", "TIPO_TELEFONE")),
                ZonedDateTime.now(), ZonedDateTime.now());

        TelefoneDTO telefoneDTO = telefoneMapper.toTelefoneDto(telefoneDomain);
        assertEquals(telefoneDomain.getId(), telefoneDTO.id());
        assertEquals(telefoneDomain.getNumero(), telefoneDTO.numero());
        assertEquals(telefoneDomain.getDdd(), telefoneDTO.ddd());
        assertEquals(telefoneDomain.getDescricao(), telefoneDTO.descricao());
        assertEquals(telefoneDomain.getCreatedAt(), telefoneDTO.createdAt());
        assertEquals(telefoneDomain.getUpdatedAt(), telefoneDTO.updatedAt());
        assertEquals(telefoneDomain.getTipoTelefone().getId(), telefoneDTO.tipoTelefone().id());
        assertEquals(telefoneDomain.getTipoTelefone().getDescricao(), telefoneDTO.tipoTelefone().descricao());
        assertEquals(telefoneDomain.getTipoTelefone().getCodigo(), telefoneDTO.tipoTelefone().codigo());
        assertEquals(telefoneDomain.getTipoTelefone().getTipo().getId(), telefoneDTO.tipoTelefone().tipo().id());
        assertEquals(telefoneDomain.getTipoTelefone().getTipo().getDescricao(), telefoneDTO.tipoTelefone().tipo().descricao());
        assertEquals(telefoneDomain.getTipoTelefone().getTipo().getCodigo(), telefoneDTO.tipoTelefone().tipo().codigo());
        assertEquals(telefoneDomain.getTelefoneFormatado(), telefoneDTO.telefoneFormatado());
        assertEquals("(11) 99999-9999", telefoneDTO.telefoneFormatado());
    }

    @Test
    @DisplayName("Deve criar telefone domain")
    @Order(2)
    void deveCriarTelefoneDomain(){
        TelefoneCreateDTO telefoneCreateDTO = new TelefoneCreateDTO("999999999", "11", "teste", new TipoTelefoneCreateDTO(5L));

        TelefoneDomain telefoneDomain = telefoneMapper.createTelefoneDomain(telefoneCreateDTO, 1L);
        assertEquals(telefoneCreateDTO.numero(), telefoneDomain.getNumero());
        assertEquals(telefoneCreateDTO.ddd(), telefoneDomain.getDdd());
        assertEquals(telefoneCreateDTO.descricao(), telefoneDomain.getDescricao());
        assertEquals(telefoneCreateDTO.tipoTelefone().id(), telefoneDomain.getTipoTelefone().getId());
        assertEquals("(11) 99999-9999", telefoneDomain.getTelefoneFormatado());
    }
}
