package br.com.gabrielferreira.usuarios.adapters.out.persistence.mapper;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.AnotacaoEntity;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnotacaoEntityMapperTest {

    AnotacaoEntityMapper anotacaoEntityMapper = Mappers.getMapper(AnotacaoEntityMapper.class);

    @Test
    @DisplayName("Deve criar anotação entity")
    @Order(1)
    void deveCriarAnotacaoEntity(){
        UsuarioDomain usuarioDomain = new UsuarioDomain();
        usuarioDomain.setId(1L);
        usuarioDomain.setNome("usuario");

        DominioDomain dominioDomain = new DominioDomain();
        dominioDomain.setId(1L);
        dominioDomain.setDescricao("dominio");

        DominioDomain situacaoDomain = new DominioDomain();
        situacaoDomain.setId(1L);
        situacaoDomain.setDescricao("situacao");

        AnotacaoDomain anotacaoDomain = new AnotacaoDomain(1L, "titulo", "descricao", usuarioDomain, dominioDomain, ZonedDateTime.now(),
                ZonedDateTime.now(), ZonedDateTime.now(), situacaoDomain, ZonedDateTime.now(), ZonedDateTime.now());
        AnotacaoEntity anotacaoEntity = anotacaoEntityMapper.createAnotacaoEntity(anotacaoDomain);
        assertEquals(anotacaoDomain.getId(), anotacaoEntity.getId());
        assertEquals(anotacaoDomain.getTitulo(), anotacaoEntity.getTitulo());
        assertEquals(anotacaoDomain.getDescricao(), anotacaoEntity.getDescricao());
        assertEquals(anotacaoDomain.getTipoAnotacao().getId(), anotacaoEntity.getTipoAnotacao().getId());
        assertEquals(anotacaoDomain.getTipoAnotacao().getDescricao(), anotacaoEntity.getTipoAnotacao().getDescricao());
        assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getId(), anotacaoEntity.getSituacaoTipoAnotacao().getId());
        assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getDescricao(), anotacaoEntity.getSituacaoTipoAnotacao().getDescricao());
        assertEquals(anotacaoDomain.getCreatedAt(), anotacaoEntity.getCreatedAt());
        assertEquals(anotacaoDomain.getUpdatedAt(), anotacaoEntity.getUpdatedAt());
    }

    @Test
    @DisplayName("Deve criar anotação domain")
    @Order(2)
    void deveCriarAnotacaoDomain(){
        AnotacaoEntity anotacaoEntity = AnotacaoEntity.builder()
                .id(1L)
                .titulo("titulo")
                .descricao("descricao")
                .usuario(null)
                .tipoAnotacao(null)
                .dataLembrete(ZonedDateTime.now())
                .dataEstudoInicio(ZonedDateTime.now())
                .situacaoTipoAnotacao(null)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        AnotacaoDomain anotacaoDomain = anotacaoEntityMapper.toAnotacaoDomain(anotacaoEntity);
        assertEquals(anotacaoEntity.getId(), anotacaoDomain.getId());
        assertEquals(anotacaoEntity.getTitulo(), anotacaoDomain.getTitulo());
        assertEquals(anotacaoEntity.getDescricao(), anotacaoDomain.getDescricao());
        assertEquals(anotacaoEntity.getDataLembrete(), anotacaoDomain.getDataLembrete());
        assertEquals(anotacaoEntity.getDataEstudoInicio(), anotacaoDomain.getDataEstudoInicio());
        assertEquals(anotacaoEntity.getCreatedAt(), anotacaoDomain.getCreatedAt());
        assertEquals(anotacaoEntity.getUpdatedAt(), anotacaoDomain.getUpdatedAt());
    }

    @Test
    @DisplayName("Deve criar anotação domain resumido")
    @Order(3)
    void deveCriarPageAnotacaoDomainResumido(){
        AnotacaoEntity anotacaoEntity = AnotacaoEntity.builder()
                .id(1L)
                .titulo("titulo")
                .descricao("descricao")
                .usuario(null)
                .tipoAnotacao(null)
                .dataLembrete(ZonedDateTime.now())
                .dataEstudoInicio(ZonedDateTime.now())
                .situacaoTipoAnotacao(null)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        List<AnotacaoEntity> anotacaoEntities = new ArrayList<>();
        anotacaoEntities.add(anotacaoEntity);
        Page<AnotacaoEntity> anotacaoEntityPage = new PageImpl<>(anotacaoEntities, PageRequest.of(1, 5), anotacaoEntities.size());

        List<AnotacaoDomain> anotacaoDomains = anotacaoEntityMapper.toAnotacoesDomains(anotacaoEntityPage);
        assertFalse(anotacaoDomains.isEmpty());
    }

    @Test
    @DisplayName("Deve criar anotação domain rascunho")
    @Order(4)
    void deveCriarAnotacaoDomainRascunho(){
        AnotacaoDomain anotacaoDomain = new AnotacaoDomain();
        DominioDomain tipo = new DominioDomain();
        DominioDomain situacao = new DominioDomain();
        UsuarioDomain usuario = new UsuarioDomain();

        AnotacaoDomain anotacaoDomainCreate = anotacaoEntityMapper.createAnotacaoDomainRascunho(anotacaoDomain, tipo, situacao, usuario);
        assertNotNull(anotacaoDomainCreate);
        assertNotNull(anotacaoDomainCreate.getTipoAnotacao());
        assertNotNull(anotacaoDomainCreate.getSituacaoTipoAnotacao());
        assertNotNull(anotacaoDomainCreate.getUsuario());
    }

    @Test
    @DisplayName("Deve criar anotação domain estudo")
    @Order(5)
    void deveCriarAnotacaoDomainEstudo(){
        AnotacaoDomain anotacaoDomain = new AnotacaoDomain();
        anotacaoDomain.setDataEstudoInicio(ZonedDateTime.now());
        anotacaoDomain.setDataEstudoFim(ZonedDateTime.now());

        DominioDomain tipo = new DominioDomain();
        DominioDomain situacao = new DominioDomain();
        UsuarioDomain usuario = new UsuarioDomain();

        AnotacaoDomain anotacaoDomainCreate = anotacaoEntityMapper.createAnotacaoDomainEstudo(anotacaoDomain, tipo, situacao, usuario);
        assertNotNull(anotacaoDomainCreate);
        assertNotNull(anotacaoDomainCreate.getTipoAnotacao());
        assertNotNull(anotacaoDomainCreate.getSituacaoTipoAnotacao());
        assertNotNull(anotacaoDomainCreate.getUsuario());
        assertEquals(anotacaoDomain.getDataEstudoInicio(), anotacaoDomainCreate.getDataEstudoInicio());
        assertEquals(anotacaoDomain.getDataEstudoFim(), anotacaoDomainCreate.getDataEstudoFim());
    }

    @Test
    @DisplayName("Deve criar anotação domain lembrete")
    @Order(6)
    void deveCriarAnotacaoDomainLembrete(){
        AnotacaoDomain anotacaoDomain = new AnotacaoDomain();
        anotacaoDomain.setDataLembrete(ZonedDateTime.now());

        DominioDomain tipo = new DominioDomain();
        DominioDomain situacao = new DominioDomain();
        UsuarioDomain usuario = new UsuarioDomain();

        AnotacaoDomain anotacaoDomainCreate = anotacaoEntityMapper.createAnotacaoDomainLembrete(anotacaoDomain, tipo, situacao, usuario);
        assertNotNull(anotacaoDomainCreate);
        assertNotNull(anotacaoDomainCreate.getTipoAnotacao());
        assertNotNull(anotacaoDomainCreate.getSituacaoTipoAnotacao());
        assertNotNull(anotacaoDomainCreate.getUsuario());
        assertEquals(anotacaoDomain.getDataLembrete(), anotacaoDomainCreate.getDataLembrete());
    }

    @Test
    @DisplayName("Deve finalizar ou reabrir anotacao domain")
    @Order(7)
    void deveFinalizarOuReabrirAnotacaoDomain(){
        AnotacaoDomain anotacaoDomain = new AnotacaoDomain();
        DominioDomain situacao = new DominioDomain();

        AnotacaoDomain anotacaoDomainCreate = anotacaoEntityMapper.updateFinalizarReabrirAnotacaoDomain(anotacaoDomain, situacao);
        assertNotNull(anotacaoDomainCreate);
        assertNotNull(anotacaoDomainCreate.getSituacaoTipoAnotacao());
        assertEquals(anotacaoDomain.getSituacaoTipoAnotacao(), anotacaoDomainCreate.getSituacaoTipoAnotacao());
    }

    @Test
    @DisplayName("Deve atualizar anotação domain")
    @Order(8)
    void deveAtualizarAnotacaoDomain(){
        AnotacaoDomain anotacaoDomainFound = new AnotacaoDomain();

        AnotacaoDomain anotacaoDomain = new AnotacaoDomain();
        anotacaoDomain.setTitulo("titulo");
        anotacaoDomain.setDescricao("descricao");
        anotacaoDomain.setDataEstudoInicio(ZonedDateTime.now());
        anotacaoDomain.setDataEstudoFim(ZonedDateTime.now());
        anotacaoDomain.setDataLembrete(ZonedDateTime.now());

        AnotacaoDomain anotacaoDomainCreate = anotacaoEntityMapper.updateAnotacao(anotacaoDomainFound, anotacaoDomain);
        assertNotNull(anotacaoDomainCreate);
        assertEquals(anotacaoDomain.getTitulo(), anotacaoDomainCreate.getTitulo());
        assertEquals(anotacaoDomain.getDescricao(), anotacaoDomainCreate.getDescricao());
        assertEquals(anotacaoDomain.getDataEstudoInicio(), anotacaoDomainCreate.getDataEstudoInicio());
        assertEquals(anotacaoDomain.getDataEstudoFim(), anotacaoDomainCreate.getDataEstudoFim());
        assertEquals(anotacaoDomain.getDataLembrete(), anotacaoDomainCreate.getDataLembrete());
    }
}
