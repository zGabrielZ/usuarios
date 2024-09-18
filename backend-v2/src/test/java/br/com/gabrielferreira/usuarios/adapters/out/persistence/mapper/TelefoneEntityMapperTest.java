package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.DominioEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.TelefoneEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.TipoDominioEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TelefoneDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TipoDominioDomain;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TelefoneEntityMapperTest {

    TelefoneEntityMapper telefoneEntityMapper = Mappers.getMapper(TelefoneEntityMapper.class);

    @Test
    @DisplayName("Deve criar telefone domain")
    @Order(1)
    void deveCriarTelefoneDomain(){
        TelefoneEntity telefoneEntity = TelefoneEntity.builder()
                .id(1L)
                .numero("999999999")
                .ddd("11")
                .descricao("teste")
                .tipoTelefone(
                        DominioEntity.builder()
                                .id(5L)
                                .descricao("Celular")
                                .codigo("CELULAR")
                                .tipo(
                                        TipoDominioEntity.builder()
                                                .id(2L)
                                                .descricao("Tipo de telefone")
                                                .codigo("TIPO_TELEFONE")
                                                .build()
                                ).build()
                )
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        TelefoneDomain telefoneDomain = telefoneEntityMapper.toTelefoneDomain(telefoneEntity);
        assertEquals(telefoneEntity.getId(), telefoneDomain.getId());
        assertEquals(telefoneEntity.getNumero(), telefoneDomain.getNumero());
        assertEquals(telefoneEntity.getDdd(), telefoneDomain.getDdd());
        assertEquals(telefoneEntity.getDescricao(), telefoneDomain.getDescricao());
        assertEquals(telefoneEntity.getCreatedAt(), telefoneDomain.getCreatedAt());
        assertEquals(telefoneEntity.getUpdatedAt(), telefoneDomain.getUpdatedAt());
        assertEquals(telefoneEntity.getTipoTelefone().getId(), telefoneDomain.getTipoTelefone().getId());
        assertEquals(telefoneEntity.getTipoTelefone().getDescricao(), telefoneDomain.getTipoTelefone().getDescricao());
        assertEquals(telefoneEntity.getTipoTelefone().getCodigo(), telefoneDomain.getTipoTelefone().getCodigo());
        assertEquals(telefoneEntity.getTipoTelefone().getTipo().getId(), telefoneDomain.getTipoTelefone().getTipo().getId());
        assertEquals(telefoneEntity.getTipoTelefone().getTipo().getDescricao(), telefoneDomain.getTipoTelefone().getTipo().getDescricao());
        assertEquals(telefoneEntity.getTipoTelefone().getTipo().getCodigo(), telefoneDomain.getTipoTelefone().getTipo().getCodigo());
    }

    @Test
    @DisplayName("Deve criar telefone entity")
    @Order(2)
    void deveCriarTelefoneEntity(){
        TelefoneDomain telefoneDomain = new TelefoneDomain(1L, "999999999", "11", "teste",
                new DominioDomain(5L, "Celular", "CELULAR", new TipoDominioDomain(2L, "Tipo de telefone", "TIPO_TELEFONE")),
                ZonedDateTime.now(), ZonedDateTime.now());

        TelefoneEntity telefoneEntity = telefoneEntityMapper.updateTelefoneEntity(telefoneDomain);
        assertEquals(telefoneDomain.getId(), telefoneEntity.getId());
        assertEquals(telefoneDomain.getNumero(), telefoneEntity.getNumero());
        assertEquals(telefoneDomain.getDdd(), telefoneEntity.getDdd());
        assertEquals(telefoneDomain.getDescricao(), telefoneEntity.getDescricao());
        assertEquals(telefoneDomain.getCreatedAt(), telefoneEntity.getCreatedAt());
        assertEquals(telefoneDomain.getUpdatedAt(), telefoneEntity.getUpdatedAt());
        assertEquals(telefoneDomain.getTipoTelefone().getId(), telefoneEntity.getTipoTelefone().getId());
        assertEquals(telefoneDomain.getTipoTelefone().getDescricao(), telefoneEntity.getTipoTelefone().getDescricao());
        assertEquals(telefoneDomain.getTipoTelefone().getCodigo(), telefoneEntity.getTipoTelefone().getCodigo());
        assertEquals(telefoneDomain.getTipoTelefone().getTipo().getId(), telefoneEntity.getTipoTelefone().getTipo().getId());
        assertEquals(telefoneDomain.getTipoTelefone().getTipo().getDescricao(), telefoneEntity.getTipoTelefone().getTipo().getDescricao());
        assertEquals(telefoneDomain.getTipoTelefone().getTipo().getCodigo(), telefoneEntity.getTipoTelefone().getTipo().getCodigo());
    }

    @Test
    @DisplayName("Deve criar telefone domain update")
    @Order(3)
    void deveCriarTelefoneDomainUpdate(){
        TelefoneDomain telefoneDomain = new TelefoneDomain(1L, "99999999", "21", "teste2",
                null, null, null);

        TelefoneDomain telefoneDomainEncontrado = new TelefoneDomain(1L, "999999999", "11", "teste",
                new DominioDomain(5L, "Celular", "CELULAR", new TipoDominioDomain(2L, "Tipo de telefone", "TIPO_TELEFONE")),
                ZonedDateTime.now(), ZonedDateTime.now());

        DominioDomain tipoTelefone = new DominioDomain(4L, "Residencial", "RESIDENCIAL", new TipoDominioDomain(2L, "Tipo de telefone", "TIPO_TELEFONE"));

        TelefoneDomain telefoneDomainResult = telefoneEntityMapper.updateTelefone(telefoneDomain, telefoneDomainEncontrado, tipoTelefone);
        assertEquals(telefoneDomain.getNumero(), telefoneDomainResult.getNumero());
        assertEquals(telefoneDomain.getDdd(), telefoneDomainResult.getDdd());
        assertEquals(telefoneDomain.getDescricao(), telefoneDomainResult.getDescricao());
        assertEquals(telefoneDomainEncontrado.getTipoTelefone().getId(), telefoneDomainResult.getTipoTelefone().getId());
        assertEquals(telefoneDomainEncontrado.getTipoTelefone().getDescricao(), telefoneDomainResult.getTipoTelefone().getDescricao());
        assertEquals(telefoneDomainEncontrado.getTipoTelefone().getCodigo(), telefoneDomainResult.getTipoTelefone().getCodigo());
        assertEquals(telefoneDomainEncontrado.getTipoTelefone().getTipo().getId(), telefoneDomainResult.getTipoTelefone().getTipo().getId());
        assertEquals(telefoneDomainEncontrado.getTipoTelefone().getTipo().getDescricao(), telefoneDomainResult.getTipoTelefone().getTipo().getDescricao());
        assertEquals(telefoneDomainEncontrado.getTipoTelefone().getTipo().getCodigo(), telefoneDomainResult.getTipoTelefone().getTipo().getCodigo());
    }
}
