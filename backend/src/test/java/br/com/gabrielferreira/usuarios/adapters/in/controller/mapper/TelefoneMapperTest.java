package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.TelefoneCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.TipoTelefoneCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TelefoneDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TipoDominioDomain;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class TelefoneMapperTest {

    private TelefoneMapperImpl telefoneMapper = new TelefoneMapperImpl();

    @Test
    @DisplayName("Deve criar telefone dto")
    @Order(1)
    void deveCriarTelefoneDto(){
        ZonedDateTime date = ZonedDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());

        TelefoneDomain telefoneDomain = new TelefoneDomain(1L, "999999999", "11", "teste",
                new DominioDomain(5L, "Celular", "CELULAR", new TipoDominioDomain(2L, "Tipo de telefone", "TIPO_TELEFONE")),
                date, date);

        TelefoneDTO telefoneDTO = telefoneMapper.toTelefoneDto(telefoneDomain);
        assertEquals(telefoneDomain.getId(), telefoneDTO.getId());
        assertEquals(telefoneDomain.getNumero(), telefoneDTO.getNumero());
        assertEquals(telefoneDomain.getDdd(), telefoneDTO.getDdd());
        assertEquals(telefoneDomain.getDescricao(), telefoneDTO.getDescricao());
        assertEquals(telefoneDomain.getCreatedAt(), telefoneDTO.getCreatedAt());
        assertEquals(telefoneDomain.getUpdatedAt(), telefoneDTO.getUpdatedAt());
        assertEquals(telefoneDomain.getTipoTelefone().getId(), telefoneDTO.getTipoTelefone().getId());
        assertEquals(telefoneDomain.getTipoTelefone().getDescricao(), telefoneDTO.getTipoTelefone().getDescricao());
        assertEquals(telefoneDomain.getTipoTelefone().getCodigo(), telefoneDTO.getTipoTelefone().getCodigo());
        assertEquals(telefoneDomain.getTipoTelefone().getTipo().getId(), telefoneDTO.getTipoTelefone().getTipo().id());
        assertEquals(telefoneDomain.getTipoTelefone().getTipo().getDescricao(), telefoneDTO.getTipoTelefone().getTipo().descricao());
        assertEquals(telefoneDomain.getTipoTelefone().getTipo().getCodigo(), telefoneDTO.getTipoTelefone().getTipo().codigo());
        assertEquals(telefoneDomain.getTelefoneFormatado(), telefoneDTO.getTelefoneFormatado());
        assertEquals("(11) 99999-9999", telefoneDTO.getTelefoneFormatado());
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
