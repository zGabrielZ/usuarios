package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoLembreteCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.AnotacaoEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.DominioEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.AnotacaoRepository;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.DominioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

import static br.com.gabrielferreira.usuarios.factory.AnotacaoFactory.createAnotacaoLembrete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnotacaoLembreteControllerIntegrationTest {

    private static final String URL = "/v1/usuarios";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected AnotacaoRepository anotacaoRepository;

    @Autowired
    protected DominioRepository dominioRepository;

    private Long idUsuarioExistente;

    private Long idUsuarioInexistente;

    private Long idAnotacaoExistente;

    private Long idAnotacaoInexistente;

    private AnotacaoLembreteCreateDTO anotacaoLembreteCreateDTO;

    @BeforeEach
    void setUp(){
        idUsuarioInexistente = -1L;
        idUsuarioExistente = 1L;
        idAnotacaoInexistente = -2L;
        idAnotacaoExistente = 2L;
        anotacaoLembreteCreateDTO = createAnotacaoLembrete("Titulo tal", "Descricao tal", ZonedDateTime.now().plusDays(1L));
    }

    @Test
    @DisplayName("Deve criar anotação lembrete")
    @Order(1)
    void deveCriarAnotacaoLembrete() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/lembretes");

        String jsonBody = objectMapper.writeValueAsString(anotacaoLembreteCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.titulo").value(anotacaoLembreteCreateDTO.titulo()));
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.descricao").value(anotacaoLembreteCreateDTO.descricao()));
        resultActions.andExpect(jsonPath("$.dataLembrete").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(8));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").value("Lembrete"));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").value("LEMBRETE"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").value(13));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").value("Lembrete em aberto"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").value("LEMBRETE_ABERTO"));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve criar anotacao lembrete quando não encontrar usuário")
    @Order(2)
    void naoDeveCriarAnotacaoLembreteQuandoNaoEncontrarUsuario() throws Exception {
        String url = URL.concat("/").concat(idUsuarioInexistente.toString())
                .concat("/anotacoes/lembretes");

        String jsonBody = objectMapper.writeValueAsString(anotacaoLembreteCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.titulo").value("Não encontrado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Usuário informado não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar anotação lembrete por id")
    @Order(3)
    void deveBuscarAnotacaoLembretePorId() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/lembretes/")
                .concat(idAnotacaoExistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.titulo").value("Lembrete"));
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.descricao").value("Lembrete...."));
        resultActions.andExpect(jsonPath("$.dataLembrete").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(8));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").value("Lembrete"));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").value("LEMBRETE"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").value(13));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").value("Lembrete em aberto"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").value("LEMBRETE_ABERTO"));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve buscar anotação lembrete por id quando não encontrar")
    @Order(4)
    void naoDeveBuscarAnotacao() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/lembretes/")
                .concat(idAnotacaoInexistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Anotação informado não encontrado"));
    }

    @Test
    @DisplayName("Deve atualizar anotação lembrete")
    @Order(5)
    void deveAtualizarAnotacaoLembrete() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/lembretes/")
                .concat(idAnotacaoExistente.toString());

        anotacaoLembreteCreateDTO = createAnotacaoLembrete("Lembrete editado", "Lembrete editado desc", ZonedDateTime.now().plusDays(1L));
        String jsonBody = objectMapper.writeValueAsString(anotacaoLembreteCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.titulo").value(anotacaoLembreteCreateDTO.titulo()));
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.descricao").value(anotacaoLembreteCreateDTO.descricao()));
        resultActions.andExpect(jsonPath("$.dataLembrete").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(8));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").value("Lembrete"));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").value("LEMBRETE"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").value(13));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").value("Lembrete em aberto"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").value("LEMBRETE_ABERTO"));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve atualizar lembrete quando tiver finalizado")
    @Order(6)
    void naoDeveAtualizarLembreteQuandoTiverFinalizado() throws Exception {
        finalizarAnotacao();

        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/lembretes/")
                .concat(idAnotacaoExistente.toString());

        anotacaoLembreteCreateDTO = createAnotacaoLembrete("Lembrete editado", "Lembrete editado desc", ZonedDateTime.now().plusDays(1L));
        String jsonBody = objectMapper.writeValueAsString(anotacaoLembreteCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("Não é possível editar a anotação pois já está finalizado"));
    }

    @Test
    @DisplayName("Deve finalizar lembrete")
    @Order(7)
    void deveFinalizarAnotacaoLembrete() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/lembretes/")
                .concat(idAnotacaoExistente.toString())
                .concat("/finalizar");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Não deve finalizar lembrete quando já estiver finalizado")
    @Order(8)
    void naoDeveFinalizarAnotacaoLembrete() throws Exception {
        finalizarAnotacao();

        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/lembretes/")
                .concat(idAnotacaoExistente.toString())
                .concat("/finalizar");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("Não é possível finalizar a anotação pois já está finalizado"));
    }

    @Test
    @DisplayName("Deve reabrir lembrete")
    @Order(9)
    void deveReabrirLembrete() throws Exception {
        finalizarAnotacao();

        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/lembretes/")
                .concat(idAnotacaoExistente.toString())
                .concat("/reabrir");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Não deve reabrir lembrete quando já estiver aberto")
    @Order(10)
    void naoDeveReabrirAnotacaoLembrete() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/lembretes/")
                .concat(idAnotacaoExistente.toString())
                .concat("/reabrir");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("Não é possível reabrir a anotação pois já está em aberto"));
    }

    private void finalizarAnotacao(){
        AnotacaoEntity anotacaoEntity = anotacaoRepository.findById(idAnotacaoExistente)
                .orElseThrow();
        DominioEntity dominioEntity = dominioRepository.findById(14L)
                .orElseThrow();
        anotacaoEntity.setSituacaoTipoAnotacao(dominioEntity);
        anotacaoRepository.save(anotacaoEntity);
    }
}
