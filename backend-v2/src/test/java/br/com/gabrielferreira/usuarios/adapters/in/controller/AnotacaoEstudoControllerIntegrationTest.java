package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.AnotacaoEstudoCreateDTO;
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

import static br.com.gabrielferreira.usuarios.factory.AnotacaoFactory.createAnotacaoEstudo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnotacaoEstudoControllerIntegrationTest {

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

    private AnotacaoEstudoCreateDTO anotacaoEstudoCreateDTO;

    @BeforeEach
    void setUp(){
        idUsuarioInexistente = -1L;
        idUsuarioExistente = 1L;
        idAnotacaoInexistente = -3L;
        idAnotacaoExistente = 3L;
        anotacaoEstudoCreateDTO = createAnotacaoEstudo("Titulo tal", "Descricao tal", ZonedDateTime.now().plusHours(1), ZonedDateTime.now().plusHours(2));
    }

    @Test
    @DisplayName("Deve criar anotação estudo")
    @Order(1)
    void deveCriarAnotacaoEstudo() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/estudos");

        String jsonBody = objectMapper.writeValueAsString(anotacaoEstudoCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.titulo").value(anotacaoEstudoCreateDTO.titulo()));
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.descricao").value(anotacaoEstudoCreateDTO.descricao()));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(7));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").value("Estudo"));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").value("ESTUDO"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").value(9));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").value("Estudo em andamento"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").value("ESTUDO_ANDAMENTO"));
        resultActions.andExpect(jsonPath("$.dataEstudoInicio").exists());
        resultActions.andExpect(jsonPath("$.dataEstudoFim").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve criar anotacao estudos quando data estudo inicio for maior que fim")
    @Order(2)
    void naoDeveCriarAnotacaoEstudoQuandoDataEstudoInicioMaiorDataEstudoFim() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/estudos");

        anotacaoEstudoCreateDTO = createAnotacaoEstudo("Estudo", "Estudo...", ZonedDateTime.now().plusHours(2), ZonedDateTime.now().plusHours(1));
        String jsonBody = objectMapper.writeValueAsString(anotacaoEstudoCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.titulo").value("Regra de negócio"));
        resultActions.andExpect(jsonPath("$.mensagem").value("A data início do estudo não pode ser antes ou igual ao data fim do estudo"));
    }

    @Test
    @DisplayName("Não deve criar anotacao estudos quando o periodo for maior que 5 horas")
    @Order(3)
    void naoDeveCriarAnotacaoEstudoQuandoPeriodoEstudoMaiorCincoHoras() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/estudos");

        anotacaoEstudoCreateDTO = createAnotacaoEstudo("Estudo", "Estudo...", ZonedDateTime.now().plusHours(1), ZonedDateTime.now().plusHours(10));
        String jsonBody = objectMapper.writeValueAsString(anotacaoEstudoCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.titulo").value("Regra de negócio"));
        resultActions.andExpect(jsonPath("$.mensagem").value("O estudo não pode ultrapassar de 5 horas"));
    }

    @Test
    @DisplayName("Não deve criar anotacao estudo quando não encontrar usuário")
    @Order(4)
    void naoDeveCriarAnotacaoEstudoQuandoNaoEncontrarUsuario() throws Exception {
        String url = URL.concat("/").concat(idUsuarioInexistente.toString())
                .concat("/anotacoes/estudos");

        String jsonBody = objectMapper.writeValueAsString(anotacaoEstudoCreateDTO);

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
    @DisplayName("Deve buscar anotação estudo por id")
    @Order(5)
    void deveBuscarAnotacaoEstudoPorId() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/estudos/")
                .concat(idAnotacaoExistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(7));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").value("Estudo"));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").value("ESTUDO"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").value(9));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").value("Estudo em andamento"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").value("ESTUDO_ANDAMENTO"));
        resultActions.andExpect(jsonPath("$.dataEstudoInicio").exists());
        resultActions.andExpect(jsonPath("$.dataEstudoFim").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve buscar anotação estudo por id quando não encontrar")
    @Order(6)
    void naoDeveBuscarAnotacao() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/estudos/")
                .concat(idAnotacaoInexistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Anotação informado não encontrado"));
    }

    @Test
    @DisplayName("Deve atualizar anotação estudo")
    @Order(7)
    void deveAtualizarAnotacaoEstudo() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/estudos/")
                .concat(idAnotacaoExistente.toString());

        anotacaoEstudoCreateDTO = createAnotacaoEstudo("Estudo editado", "Estudo descrição editado", ZonedDateTime.now().plusHours(2), ZonedDateTime.now().plusHours(3));
        String jsonBody = objectMapper.writeValueAsString(anotacaoEstudoCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.titulo").value(anotacaoEstudoCreateDTO.titulo()));
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.descricao").value(anotacaoEstudoCreateDTO.descricao()));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(7));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.descricao").value("Estudo"));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.codigo").value("ESTUDO"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.id").value(9));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.descricao").value("Estudo em andamento"));
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").exists());
        resultActions.andExpect(jsonPath("$.situacaoTipoAnotacao.codigo").value("ESTUDO_ANDAMENTO"));
        resultActions.andExpect(jsonPath("$.dataEstudoInicio").exists());
        resultActions.andExpect(jsonPath("$.dataEstudoFim").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve atualizar estudo quando tiver finalizado")
    @Order(8)
    void naoDeveAtualizarEstudoQuandoTiverFinalizado() throws Exception {
        finalizarAnotacao();

        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/estudos/")
                .concat(idAnotacaoExistente.toString());

        anotacaoEstudoCreateDTO = createAnotacaoEstudo("Estudo editado", "Estudo descrição editado", ZonedDateTime.now().plusHours(1), ZonedDateTime.now().plusHours(3));
        String jsonBody = objectMapper.writeValueAsString(anotacaoEstudoCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("Não é possível editar a anotação pois já está finalizado"));
    }

    @Test
    @DisplayName("Deve finalizar estudo")
    @Order(9)
    void deveFinalizarAnotacaoEstudo() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/estudos/")
                .concat(idAnotacaoExistente.toString())
                .concat("/finalizar");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Não deve finalizar estudos quando já estiver finalizado")
    @Order(10)
    void naoDeveFinalizarAnotacaoEstudo() throws Exception {
        finalizarAnotacao();

        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/estudos/")
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
    @DisplayName("Deve reabrir estudo")
    @Order(11)
    void deveReabrirEstudo() throws Exception {
        finalizarAnotacao();

        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/estudos/")
                .concat(idAnotacaoExistente.toString())
                .concat("/reabrir");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Não deve reabrir estudo quando já estiver aberto")
    @Order(12)
    void naoDeveReabrirAnotacaoEstudo() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes/estudos/")
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
        DominioEntity dominioEntity = dominioRepository.findById(10L)
                .orElseThrow();
        anotacaoEntity.setSituacaoTipoAnotacao(dominioEntity);
        anotacaoRepository.save(anotacaoEntity);
    }
}
