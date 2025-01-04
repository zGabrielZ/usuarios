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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnotacaoEntityMapperTest {

    private final AnotacaoEntityMapper anotacaoEntityMapper = Mappers.getMapper(AnotacaoEntityMapper.class);

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

        AnotacaoDomain anotacaoDomain = build(usuarioDomain, dominioDomain, situacaoDomain);
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

    private AnotacaoDomain build(UsuarioDomain usuarioDomain, DominioDomain dominioDomain, DominioDomain situacaoDomain){
        AnotacaoDomain anotacaoDomain = new AnotacaoDomain();
        anotacaoDomain.setId(1L);
        anotacaoDomain.setTitulo("titulo");
        anotacaoDomain.setDescricao("descricao");
        anotacaoDomain.setUsuario(usuarioDomain);
        anotacaoDomain.setTipoAnotacao(dominioDomain);
        anotacaoDomain.setSituacaoTipoAnotacao(situacaoDomain);
        anotacaoDomain.setDataLembrete(ZonedDateTime.now());
        anotacaoDomain.setDataEstudoInicio(ZonedDateTime.now());
        anotacaoDomain.setDataEstudoFim(ZonedDateTime.now());
        anotacaoDomain.setCreatedAt(ZonedDateTime.now());
        anotacaoDomain.setUpdatedAt(ZonedDateTime.now());
        return anotacaoDomain;
    }
}
