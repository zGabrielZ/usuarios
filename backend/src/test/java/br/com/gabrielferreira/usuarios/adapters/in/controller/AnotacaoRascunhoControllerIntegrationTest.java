package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoRascunhoCreateDTO;
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

import static br.com.gabrielferreira.usuarios.tests.AnotacaoFactory.createAnotacaoRascunho;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnotacaoRascunhoControllerIntegrationTest {

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

    private AnotacaoRascunhoCreateDTO anotacaoRascunhoCreateDTO;

    @BeforeEach
    void setUp(){
        idUsuarioInexistente = -1L;
        idUsuarioExistente = 1L;
        idAnotacaoInexistente = -1L;
        idAnotacaoExistente = 1L;
        anotacaoRascunhoCreateDTO = createAnotacaoRascunho("Titulo tal", "Descricao tal");
    }

    @Test
    @DisplayName("Deve criar anotação rascunho")
    @Order(1)
    void deveCriarAnotacaoRascunho() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/rascunhos");

        String jsonBody = objectMapper.writeValueAsString(anotacaoRascunhoCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.titulo").value(anotacaoRascunhoCreateDTO.titulo()));
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.descricao").value(anotacaoRascunhoCreateDTO.descricao()));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(6));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").value("Rascunho"));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").value("RASCUNHO"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").value(11));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").value("Rascunho em aberto"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").value("RASCUNHO_ABERTO"));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve criar anotacao rascunho quando a request vim campos nulos")
    @Order(2)
    void naoDeveCriarAnotacaoRascunhoRequestTudoNulo() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/rascunhos");

        anotacaoRascunhoCreateDTO = createAnotacaoRascunho(null, null);
        String jsonBody = objectMapper.writeValueAsString(anotacaoRascunhoCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.titulo").value("Erro validação de campos"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Ocorreu um erro de validação nos campos"));
        resultActions.andExpect(jsonPath("$.campos").exists());
    }

    @Test
    @DisplayName("Não deve criar anotacao rascunho quando não encontrar usuário")
    @Order(3)
    void naoDeveCriarAnotacaoRascunhoQuandoNaoEncontrarUsuario() throws Exception {
        String url = URL.concat("/").concat(idUsuarioInexistente.toString())
                .concat("/anotacoes/rascunhos");

        String jsonBody = objectMapper.writeValueAsString(anotacaoRascunhoCreateDTO);

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
    @DisplayName("Deve buscar anotação rascunho por id")
    @Order(4)
    void deveBuscarAnotacaoRascunhoPorId() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/rascunhos/")
                .concat(idAnotacaoExistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.titulo").value("Rascunho"));
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.descricao").value("Rascunho...."));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(6));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").value("Rascunho"));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").value("RASCUNHO"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").value(11));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").value("Rascunho em aberto"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").value("RASCUNHO_ABERTO"));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve buscar anotação rascunho por id quando não encontrar")
    @Order(5)
    void naoDeveBuscarAnotacao() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/rascunhos/")
                .concat(idAnotacaoInexistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Anotação informado não encontrado"));
    }

    @Test
    @DisplayName("Deve atualizar anotação rascunho")
    @Order(6)
    void deveAtualizarAnotacaoRascunho() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/rascunhos/")
                .concat(idAnotacaoExistente.toString());

        anotacaoRascunhoCreateDTO = createAnotacaoRascunho("Rascunho editado", "Rascunho editado desc");
        String jsonBody = objectMapper.writeValueAsString(anotacaoRascunhoCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.titulo").value(anotacaoRascunhoCreateDTO.titulo()));
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.descricao").value(anotacaoRascunhoCreateDTO.descricao()));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(6));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").value("Rascunho"));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").value("RASCUNHO"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").value(11));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").value("Rascunho em aberto"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").value("RASCUNHO_ABERTO"));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve atualizar rascunho quando tiver finalizado")
    @Order(7)
    void naoDeveAtualizarRascunhoQuandoTiverFinalizado() throws Exception {
        finalizarAnotacao();

        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/rascunhos/")
                .concat(idAnotacaoExistente.toString());

        anotacaoRascunhoCreateDTO = createAnotacaoRascunho("Rascunho editado", "Rascunho editado desc");
        String jsonBody = objectMapper.writeValueAsString(anotacaoRascunhoCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("Não é possível editar a anotação pois já está finalizado"));
    }

    @Test
    @DisplayName("Deve finalizar rascunho")
    @Order(8)
    void deveFinalizarAnotacaoRascunho() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/rascunhos/")
                .concat(idAnotacaoExistente.toString())
                .concat("/finalizar");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Não deve finalizar rascunho quando já estiver finalizado")
    @Order(9)
    void naoDeveFinalizarAnotacaoRascunho() throws Exception {
        finalizarAnotacao();

        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/rascunhos/")
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
    @DisplayName("Deve reabrir rascunho")
    @Order(10)
    void deveReabrirRascunho() throws Exception {
        finalizarAnotacao();

        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/rascunhos/")
                .concat(idAnotacaoExistente.toString())
                .concat("/reabrir");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Não deve reabrir rascunho quando já estiver aberto")
    @Order(11)
    void naoDeveReabrirAnotacaoRascunho() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/rascunhos/")
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
        DominioEntity dominioEntity = dominioRepository.findById(12L)
                .orElseThrow();
        anotacaoEntity.setSituacaoTipoAnotacao(dominioEntity);
        anotacaoRepository.save(anotacaoEntity);
    }
}
