package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoEstudoCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoLembreteCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoRascunhoCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoEstudoDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoLembreteDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoRascunhoDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoResumidoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.AnotacaoDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class AnotacaoMapperIntegrationTest {

    @Autowired
    protected AnotacaoMapper anotacaoMapper;

    @Test
    @DisplayName("Deve criar anotação domain rascunho")
    @Order(1)
    void deveCriarAnotacaoDomainRascunho(){
        AnotacaoRascunhoCreateDTO anotacaoRascunhoCreateDTO = new AnotacaoRascunhoCreateDTO("titulo", "descricao");

        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoRascunhoCreateDTO);
        assertEquals(anotacaoRascunhoCreateDTO.titulo(), anotacaoDomain.getTitulo());
        assertEquals(anotacaoRascunhoCreateDTO.descricao(), anotacaoDomain.getDescricao());
    }

    @Test
    @DisplayName("Deve criar anotação rascunho dto")
    @Order(2)
    void deveCriarAnotacaoRascunhoDto(){
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

        AnotacaoRascunhoDTO anotacaoRascunhoDTO = anotacaoMapper.toAnotacaoRascunhoDto(anotacaoDomain);
        assertEquals(anotacaoDomain.getId(), anotacaoRascunhoDTO.id());
        assertEquals(anotacaoDomain.getTitulo(), anotacaoRascunhoDTO.titulo());
        assertEquals(anotacaoDomain.getDescricao(), anotacaoRascunhoDTO.descricao());
        assertEquals(anotacaoDomain.getTipoAnotacao().getId(), anotacaoRascunhoDTO.tipoAnotacao().id());
        assertEquals(anotacaoDomain.getTipoAnotacao().getDescricao(), anotacaoRascunhoDTO.tipoAnotacao().descricao());
        assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getId(), anotacaoRascunhoDTO.situacaoTipoAnotacao().id());
        assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getDescricao(), anotacaoRascunhoDTO.situacaoTipoAnotacao().descricao());
        assertEquals(anotacaoDomain.getCreatedAt(), anotacaoRascunhoDTO.createdAt());
        assertEquals(anotacaoDomain.getUpdatedAt(), anotacaoRascunhoDTO.updatedAt());
    }

    @Test
    @DisplayName("Deve criar anotação domain lembrete")
    @Order(3)
    void deveCriarAnotacaoDomainLembrete(){
        AnotacaoLembreteCreateDTO anotacaoLembreteCreateDTO = new AnotacaoLembreteCreateDTO("titulo", "descricao", ZonedDateTime.now());

        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoLembreteCreateDTO);
        assertEquals(anotacaoLembreteCreateDTO.titulo(), anotacaoDomain.getTitulo());
        assertEquals(anotacaoLembreteCreateDTO.descricao(), anotacaoDomain.getDescricao());
        assertEquals(anotacaoLembreteCreateDTO.dataLembrete(), anotacaoDomain.getDataLembrete());
    }

    @Test
    @DisplayName("Deve criar anotação lembrete dto")
    @Order(4)
    void deveCriarAnotacaoLembreteDto(){
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

        AnotacaoLembreteDTO anotacaoLembreteDto = anotacaoMapper.toAnotacaoLembreteDto(anotacaoDomain);
        assertEquals(anotacaoDomain.getId(), anotacaoLembreteDto.id());
        assertEquals(anotacaoDomain.getTitulo(), anotacaoLembreteDto.titulo());
        assertEquals(anotacaoDomain.getDescricao(), anotacaoLembreteDto.descricao());
        assertEquals(anotacaoDomain.getTipoAnotacao().getId(), anotacaoLembreteDto.tipoAnotacao().id());
        assertEquals(anotacaoDomain.getTipoAnotacao().getDescricao(), anotacaoLembreteDto.tipoAnotacao().descricao());
        assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getId(), anotacaoLembreteDto.situacaoTipoAnotacao().id());
        assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getDescricao(), anotacaoLembreteDto.situacaoTipoAnotacao().descricao());
        assertEquals(anotacaoDomain.getDataLembrete(), anotacaoLembreteDto.dataLembrete());
        assertEquals(anotacaoDomain.getCreatedAt(), anotacaoLembreteDto.createdAt());
        assertEquals(anotacaoDomain.getUpdatedAt(), anotacaoLembreteDto.updatedAt());
    }

    @Test
    @DisplayName("Deve criar anotação domain estudo")
    @Order(5)
    void deveCriarAnotacaoDomainEstudo(){
        AnotacaoEstudoCreateDTO anotacaoEstudoCreateDTO = new AnotacaoEstudoCreateDTO("titulo", "descricao", ZonedDateTime.now(),
                ZonedDateTime.now());

        AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoEstudoCreateDTO);
        assertEquals(anotacaoEstudoCreateDTO.titulo(), anotacaoDomain.getTitulo());
        assertEquals(anotacaoEstudoCreateDTO.descricao(), anotacaoDomain.getDescricao());
        assertEquals(anotacaoEstudoCreateDTO.dataEstudoInicio(), anotacaoDomain.getDataEstudoInicio());
        assertEquals(anotacaoEstudoCreateDTO.dataEstudoFim(), anotacaoDomain.getDataEstudoFim());
    }

    @Test
    @DisplayName("Deve criar anotação estudo dto")
    @Order(6)
    void deveCriarAnotacaoEstudoDto(){
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

        AnotacaoEstudoDTO anotacaoEstudoDTO = anotacaoMapper.toAnotacaoEstudoDto(anotacaoDomain);
        assertEquals(anotacaoDomain.getId(), anotacaoEstudoDTO.id());
        assertEquals(anotacaoDomain.getTitulo(), anotacaoEstudoDTO.titulo());
        assertEquals(anotacaoDomain.getDescricao(), anotacaoEstudoDTO.descricao());
        assertEquals(anotacaoDomain.getTipoAnotacao().getId(), anotacaoEstudoDTO.tipoAnotacao().id());
        assertEquals(anotacaoDomain.getTipoAnotacao().getDescricao(), anotacaoEstudoDTO.tipoAnotacao().descricao());
        assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getId(), anotacaoEstudoDTO.situacaoTipoAnotacao().id());
        assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getDescricao(), anotacaoEstudoDTO.situacaoTipoAnotacao().descricao());
        assertEquals(anotacaoDomain.getDataEstudoInicio(), anotacaoEstudoDTO.dataEstudoInicio());
        assertEquals(anotacaoDomain.getDataEstudoFim(), anotacaoEstudoDTO.dataEstudoFim());
        assertEquals(anotacaoDomain.getCreatedAt(), anotacaoEstudoDTO.createdAt());
        assertEquals(anotacaoDomain.getUpdatedAt(), anotacaoEstudoDTO.updatedAt());
    }

    @Test
    @DisplayName("Deve criar lista anotação domain resumido")
    @Order(7)
    void deveCriarListaAnotacaoDomainResumido(){
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
        List<AnotacaoDomain> anotacaoDomains = new ArrayList<>();
        anotacaoDomains.add(anotacaoDomain);

        List<AnotacaoResumidoDTO> anotacaoResumidoDTOS = anotacaoMapper.toAnotacoesResumidosDtos(anotacaoDomains);
        assertFalse(anotacaoResumidoDTOS.isEmpty());
    }
}
