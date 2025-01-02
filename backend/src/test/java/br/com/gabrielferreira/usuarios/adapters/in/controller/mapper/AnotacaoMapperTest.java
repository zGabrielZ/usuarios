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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class AnotacaoMapperTest {

    private final AnotacaoMapper anotacaoMapper = Mappers.getMapper(AnotacaoMapper.class);

    @Nested
    class AnotacaoComDataValido {

        private ZonedDateTime date;

        @BeforeEach
        void setUp() {
            date = ZonedDateTime.of(2025, 1, 1, 0,0,0, 0, ZoneId.systemDefault());
        }

        @Test
        @DisplayName("Deve criar anotação rascunho dto")
        @Order(1)
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

            AnotacaoDomain anotacaoDomain = build(usuarioDomain, dominioDomain, situacaoDomain);

            AnotacaoRascunhoDTO anotacaoRascunhoDTO = anotacaoMapper.toAnotacaoRascunhoDto(anotacaoDomain);
            assertEquals(anotacaoDomain.getId(), anotacaoRascunhoDTO.getId());
            assertEquals(anotacaoDomain.getTitulo(), anotacaoRascunhoDTO.getTitulo());
            assertEquals(anotacaoDomain.getDescricao(), anotacaoRascunhoDTO.getDescricao());
            assertEquals(anotacaoDomain.getTipoAnotacao().getId(), anotacaoRascunhoDTO.getTipoAnotacao().id());
            assertEquals(anotacaoDomain.getTipoAnotacao().getDescricao(), anotacaoRascunhoDTO.getTipoAnotacao().descricao());
            assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getId(), anotacaoRascunhoDTO.getSituacaoTipoAnotacao().id());
            assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getDescricao(), anotacaoRascunhoDTO.getSituacaoTipoAnotacao().descricao());
            assertEquals(anotacaoDomain.getCreatedAt(), anotacaoRascunhoDTO.getCreatedAt());
            assertEquals(anotacaoDomain.getUpdatedAt(), anotacaoRascunhoDTO.getUpdatedAt());
        }

        @Test
        @DisplayName("Deve criar anotação lembrete dto")
        @Order(2)
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

            AnotacaoDomain anotacaoDomain = build(usuarioDomain, dominioDomain, situacaoDomain);

            AnotacaoLembreteDTO anotacaoLembreteDto = anotacaoMapper.toAnotacaoLembreteDto(anotacaoDomain);
            assertEquals(anotacaoDomain.getId(), anotacaoLembreteDto.getId());
            assertEquals(anotacaoDomain.getTitulo(), anotacaoLembreteDto.getTitulo());
            assertEquals(anotacaoDomain.getDescricao(), anotacaoLembreteDto.getDescricao());
            assertEquals(anotacaoDomain.getTipoAnotacao().getId(), anotacaoLembreteDto.getTipoAnotacao().id());
            assertEquals(anotacaoDomain.getTipoAnotacao().getDescricao(), anotacaoLembreteDto.getTipoAnotacao().descricao());
            assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getId(), anotacaoLembreteDto.getSituacaoTipoAnotacao().id());
            assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getDescricao(), anotacaoLembreteDto.getSituacaoTipoAnotacao().descricao());
            assertEquals(anotacaoDomain.getDataLembrete(), anotacaoLembreteDto.getDataLembrete());
            assertEquals(anotacaoDomain.getCreatedAt(), anotacaoLembreteDto.getCreatedAt());
            assertEquals(anotacaoDomain.getUpdatedAt(), anotacaoLembreteDto.getUpdatedAt());
        }

        @Test
        @DisplayName("Deve criar anotação estudo dto")
        @Order(3)
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

            AnotacaoDomain anotacaoDomain = build(usuarioDomain, dominioDomain, situacaoDomain);

            AnotacaoEstudoDTO anotacaoEstudoDTO = anotacaoMapper.toAnotacaoEstudoDto(anotacaoDomain);
            assertEquals(anotacaoDomain.getId(), anotacaoEstudoDTO.getId());
            assertEquals(anotacaoDomain.getTitulo(), anotacaoEstudoDTO.getTitulo());
            assertEquals(anotacaoDomain.getDescricao(), anotacaoEstudoDTO.getDescricao());
            assertEquals(anotacaoDomain.getTipoAnotacao().getId(), anotacaoEstudoDTO.getTipoAnotacao().id());
            assertEquals(anotacaoDomain.getTipoAnotacao().getDescricao(), anotacaoEstudoDTO.getTipoAnotacao().descricao());
            assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getId(), anotacaoEstudoDTO.getSituacaoTipoAnotacao().id());
            assertEquals(anotacaoDomain.getSituacaoTipoAnotacao().getDescricao(), anotacaoEstudoDTO.getSituacaoTipoAnotacao().descricao());
            assertEquals(anotacaoDomain.getDataEstudoInicio(), anotacaoEstudoDTO.getDataEstudoInicio());
            assertEquals(anotacaoDomain.getDataEstudoFim(), anotacaoEstudoDTO.getDataEstudoFim());
            assertEquals(anotacaoDomain.getCreatedAt(), anotacaoEstudoDTO.getCreatedAt());
            assertEquals(anotacaoDomain.getUpdatedAt(), anotacaoEstudoDTO.getUpdatedAt());
        }

        @Test
        @DisplayName("Deve criar lista anotação domain resumido")
        @Order(4)
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

            AnotacaoDomain anotacaoDomain = build(usuarioDomain, dominioDomain, situacaoDomain);
            List<AnotacaoDomain> anotacaoDomains = new ArrayList<>();
            anotacaoDomains.add(anotacaoDomain);

            List<AnotacaoResumidoDTO> anotacaoResumidoDTOS = anotacaoMapper.toAnotacoesResumidosDtos(anotacaoDomains);
            assertFalse(anotacaoResumidoDTOS.isEmpty());
        }

        @Test
        @DisplayName("Deve criar anotação domain lembrete")
        @Order(5)
        void deveCriarAnotacaoDomainLembrete() {
            AnotacaoLembreteCreateDTO anotacaoLembreteCreateDTO = new AnotacaoLembreteCreateDTO("titulo", "descricao", date);

            AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoLembreteCreateDTO);
            assertEquals(anotacaoLembreteCreateDTO.titulo(), anotacaoDomain.getTitulo());
            assertEquals(anotacaoLembreteCreateDTO.descricao(), anotacaoDomain.getDescricao());
            assertEquals(anotacaoLembreteCreateDTO.dataLembrete(), anotacaoDomain.getDataLembrete());
        }


        @Test
        @DisplayName("Deve criar anotação domain estudo")
        @Order(6)
        void deveCriarAnotacaoDomainEstudo() {
            AnotacaoEstudoCreateDTO anotacaoEstudoCreateDTO = new AnotacaoEstudoCreateDTO("titulo", "descricao", date,
                    date);

            AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoEstudoCreateDTO);
            assertEquals(anotacaoEstudoCreateDTO.titulo(), anotacaoDomain.getTitulo());
            assertEquals(anotacaoEstudoCreateDTO.descricao(), anotacaoDomain.getDescricao());
            assertEquals(anotacaoEstudoCreateDTO.dataEstudoInicio(), anotacaoDomain.getDataEstudoInicio());
            assertEquals(anotacaoEstudoCreateDTO.dataEstudoFim(), anotacaoDomain.getDataEstudoFim());
        }

        private AnotacaoDomain build(UsuarioDomain usuarioDomain, DominioDomain dominioDomain, DominioDomain situacaoDomain){
            AnotacaoDomain anotacaoDomain = new AnotacaoDomain();
            anotacaoDomain.setId(1L);
            anotacaoDomain.setTitulo("titulo");
            anotacaoDomain.setDescricao("descricao");
            anotacaoDomain.setUsuario(usuarioDomain);
            anotacaoDomain.setTipoAnotacao(dominioDomain);
            anotacaoDomain.setSituacaoTipoAnotacao(situacaoDomain);
            anotacaoDomain.setDataLembrete(date);
            anotacaoDomain.setDataEstudoInicio(date);
            anotacaoDomain.setDataEstudoFim(date);
            anotacaoDomain.setCreatedAt(date);
            anotacaoDomain.setUpdatedAt(date);
            return anotacaoDomain;
        }
    }

    @Nested
    class AnotacaoSemDataValida {

        @Test
        @DisplayName("Deve criar anotação domain rascunho")
        @Order(7)
        void deveCriarAnotacaoDomainRascunho() {
            AnotacaoRascunhoCreateDTO anotacaoRascunhoCreateDTO = new AnotacaoRascunhoCreateDTO("titulo", "descricao");

            AnotacaoDomain anotacaoDomain = anotacaoMapper.createAnotacaoDomain(anotacaoRascunhoCreateDTO);
            assertEquals(anotacaoRascunhoCreateDTO.titulo(), anotacaoDomain.getTitulo());
            assertEquals(anotacaoRascunhoCreateDTO.descricao(), anotacaoDomain.getDescricao());
        }
    }
}
