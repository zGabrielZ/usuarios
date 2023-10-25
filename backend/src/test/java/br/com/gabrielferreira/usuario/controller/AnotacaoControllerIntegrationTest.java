package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.dto.request.AnotacaoCreateRequestDTO;
import br.com.gabrielferreira.usuario.dto.request.AnotacaoUpdateRequestDTO;
import br.com.gabrielferreira.usuario.utils.DataUtils;
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

import static br.com.gabrielferreira.usuario.tests.Factory.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnotacaoControllerIntegrationTest {

    private static final String URL = "/anotacoes";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    private Long idAnotacaoExistenteEstudo;
    private Long idAnotacaoExistenteLembrete;
    private Long idAnotacaoExistenteRascunho;

    private Long idAnotacaoInexistente;

    private AnotacaoCreateRequestDTO anotacaoCreateRequestEstudoDTO;

    private AnotacaoCreateRequestDTO anotacaoCreateRequestLembreteDTO;

    private AnotacaoCreateRequestDTO anotacaoCreateRequestRascunhoDTO;

    private AnotacaoUpdateRequestDTO anotacaoUpdateRequestDTO;

    @BeforeEach
    void setUp(){
        idAnotacaoExistenteEstudo = 1L;
        idAnotacaoExistenteLembrete = 2L;
        idAnotacaoExistenteRascunho = 3L;
        idAnotacaoInexistente = -1L;
        anotacaoCreateRequestEstudoDTO = criarAnotacaoInsertEstudo();
        anotacaoCreateRequestLembreteDTO = criarAnotacaoInsertLembrete();
        anotacaoCreateRequestRascunhoDTO = criarAnotacaoInsertRascunho();
        anotacaoUpdateRequestDTO = criarAnotacaoUpdate();
    }

    @Test
    @DisplayName("Deve cadastrar uma anotação estudo")
    @Order(1)
    void deveCadastrarAnotacaoEstudo() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestEstudoDTO);

        String tituloEsperado = anotacaoCreateRequestEstudoDTO.getTitulo();
        String descricaoEsperado = anotacaoCreateRequestEstudoDTO.getDescricao();
        Long idTipoAnotacao = anotacaoCreateRequestEstudoDTO.getTipoAnotacao().getId();

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").value(tituloEsperado));
        resultActions.andExpect(jsonPath("$.descricao").value(descricaoEsperado));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(idTipoAnotacao));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Deve cadastrar uma anotação lembrete")
    @Order(2)
    void deveCadastrarAnotacaoLembrete() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestLembreteDTO);

        String tituloEsperado = anotacaoCreateRequestLembreteDTO.getTitulo();
        String descricaoEsperado = anotacaoCreateRequestLembreteDTO.getDescricao();
        Long idTipoAnotacao = anotacaoCreateRequestLembreteDTO.getTipoAnotacao().getId();

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").value(tituloEsperado));
        resultActions.andExpect(jsonPath("$.descricao").value(descricaoEsperado));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(idTipoAnotacao));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Deve cadastrar uma anotação rascunho")
    @Order(3)
    void deveCadastrarAnotacaoRascunho() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestRascunhoDTO);

        String tituloEsperado = anotacaoCreateRequestRascunhoDTO.getTitulo();
        String descricaoEsperado = anotacaoCreateRequestRascunhoDTO.getDescricao();
        Long idTipoAnotacao = anotacaoCreateRequestRascunhoDTO.getTipoAnotacao().getId();

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").value(tituloEsperado));
        resultActions.andExpect(jsonPath("$.descricao").value(descricaoEsperado));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(idTipoAnotacao));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Deve buscar anotação estudo quando existir")
    @Order(4)
    void deveBuscarAnotacaoEstudo() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idAnotacaoExistenteEstudo)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(7L));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Deve buscar anotação lembrete quando existir")
    @Order(5)
    void deveBuscarAnotacaoLembrete() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idAnotacaoExistenteLembrete)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(8L));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Deve buscar anotação rascunho quando existir")
    @Order(6)
    void deveBuscarAnotacaoRascunho() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idAnotacaoExistenteRascunho)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(6L));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve buscar anotação quando não existir")
    @Order(7)
    void naoDeveBuscarAnotacao() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idAnotacaoInexistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve alterar anotação quando existir")
    @Order(8)
    void deveAlterarAnotacao() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(anotacaoUpdateRequestDTO);

        Long idEsperado = idAnotacaoExistenteEstudo;
        String tituloEsperado = anotacaoUpdateRequestDTO.getTitulo();
        String descricaoEsperado = anotacaoUpdateRequestDTO.getDescricao();
        Long idTipoAnotacao = anotacaoUpdateRequestDTO.getTipoAnotacao().getId();

        ResultActions resultActions = mockMvc
                .perform(put(URL.concat("/{id}"), idAnotacaoExistenteEstudo)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").value(idEsperado));
        resultActions.andExpect(jsonPath("$.titulo").value(tituloEsperado));
        resultActions.andExpect(jsonPath("$.descricao").value(descricaoEsperado));
        resultActions.andExpect(jsonPath("$.tipoAnotacao.id").value(idTipoAnotacao));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve alterar anotação quando não existir")
    @Order(9)
    void naoDeveAlterarAnotacao() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(anotacaoUpdateRequestDTO);

        ResultActions resultActions = mockMvc
                .perform(put(URL.concat("/{id}"), idAnotacaoInexistente)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve deletar anotação quando existir")
    @Order(10)
    void deveDeletarAnotacao() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(delete(URL.concat("/{id}"), idAnotacaoExistenteEstudo)
                        .accept(MEDIA_TYPE_JSON));
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Não deve deletar anotação quando não existir")
    @Order(11)
    void naoDeveDeletarAnotacao() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(delete(URL.concat("/{id}"), idAnotacaoInexistente)
                        .accept(MEDIA_TYPE_JSON));
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve buscar anotações paginada quando existir")
    @Order(12)
    void deveBuscarAnotacaoPaginadas() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("?idUsuario=1&page=0&size=5&sort=id,desc"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.content").exists());
    }

    @Test
    @DisplayName("Não deve buscar anotações paginada quando informar propriedade incorreta")
    @Order(13)
    void naoDeveBuscarAnotacaoPaginadas() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("?idUsuario=1&page=0&size=5&sort=iddd,desc"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Não deve buscar anotações paginada quando não informar o id usuário")
    @Order(14)
    void naoDeveBuscarAnotacaoPaginadasQuandoNaoInformarIdUsuario() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("?idUsuario=&page=0&size=5&sort=id,desc"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isInternalServerError());
        resultActions.andExpect(jsonPath("$.erro").value("Required request parameter 'idUsuario' for method parameter type Long is present but converted to null"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma anotação quando não informar campos")
    @Order(15)
    void naoDeveCadastrarAnotacao() throws Exception{
        anotacaoCreateRequestEstudoDTO = criarAnotacaoInsertEstudoVazia();
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestEstudoDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro validação de campos"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Ocorreu um erro de validação nos campos"));
        resultActions.andExpect(jsonPath("$.erroFormularios").exists());
    }

    @Test
    @DisplayName("Não deve cadastrar uma anotação quando não informar data inicio estudo")
    @Order(16)
    void naoDeveCadastrarAnotacaoQuandoNaoInformarDataInicioEstudo() throws Exception{
        anotacaoCreateRequestEstudoDTO.setDataEstudoInicio(null);
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestEstudoDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("É necessário informar a data início estudo da anotação"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma anotação quando não informar data fim estudo")
    @Order(17)
    void naoDeveCadastrarAnotacaoQuandoNaoInformarDataFimEstudo() throws Exception{
        anotacaoCreateRequestEstudoDTO.setDataEstudoFim(null);
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestEstudoDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("É necessário informar a data fim estudo da anotação"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma anotação quando informar data inicio estudo antes do futuro")
    @Order(18)
    void naoDeveCadastrarAnotacaoQuandoInformarDataInicioEstudoAntesFuturo() throws Exception{
        anotacaoCreateRequestEstudoDTO.setDataEstudoInicio(ZonedDateTime.now(DataUtils.FUSO_HORARIO_PADRAO_SISTEMA).minusDays(1L));
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestEstudoDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("A data do estudo início da anotação deve ser no futuro"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma anotação quando informar data fim estudo antes do futuro")
    @Order(19)
    void naoDeveCadastrarAnotacaoQuandoInformarDataFimEstudoAntesFuturo() throws Exception{
        anotacaoCreateRequestEstudoDTO.setDataEstudoFim(ZonedDateTime.now(DataUtils.FUSO_HORARIO_PADRAO_SISTEMA).minusDays(1L));
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestEstudoDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("A data do estudo fim da anotação deve ser no futuro"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma anotação quando informar data fim estudo maior que data inicio estudo")
    @Order(20)
    void naoDeveCadastrarAnotacaoQuandoInformarDataFimEstudoMaiorQueDataIncioEstudo() throws Exception{
        anotacaoCreateRequestEstudoDTO.setDataEstudoInicio(ZonedDateTime.now(DataUtils.FUSO_HORARIO_PADRAO_SISTEMA).plusHours(3L));
        anotacaoCreateRequestEstudoDTO.setDataEstudoFim(ZonedDateTime.now(DataUtils.FUSO_HORARIO_PADRAO_SISTEMA).plusHours(2L));
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestEstudoDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("A data início do estudo tem que ser antes da data fim"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma anotação quando o periodo do estudo ultrapassar de 5 horas")
    @Order(21)
    void naoDeveCadastrarAnotacaoQuandoPeriodoEstudoUltrapassarCincoHoras() throws Exception{
        anotacaoCreateRequestEstudoDTO.setDataEstudoInicio(ZonedDateTime.now(DataUtils.FUSO_HORARIO_PADRAO_SISTEMA).plusHours(1L));
        anotacaoCreateRequestEstudoDTO.setDataEstudoFim(ZonedDateTime.now(DataUtils.FUSO_HORARIO_PADRAO_SISTEMA).plusHours(10L));
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestEstudoDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("O estudo não pode ultrapassar de 5 horas"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma anotação quando não informar data lembrete")
    @Order(22)
    void naoDeveCadastrarAnotacaoQuandoNaoInformarDataLembrete() throws Exception{
        anotacaoCreateRequestLembreteDTO.setDataLembrete(null);
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestLembreteDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("É necessário informar a data lembrete da anotação"));
    }

    @Test
    @DisplayName("Não deve cadastrar uma anotação quando informar data lembrete antes do futuro")
    @Order(23)
    void naoDeveCadastrarAnotacaoQuandoInformarDataLembreteAntesFuturo() throws Exception{
        anotacaoCreateRequestLembreteDTO.setDataLembrete(ZonedDateTime.now(DataUtils.FUSO_HORARIO_PADRAO_SISTEMA).minusDays(1L));
        String jsonBody = objectMapper.writeValueAsString(anotacaoCreateRequestLembreteDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.erro").value("Erro personalizado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("A data do lembrete da anotação deve ser no futuro"));
    }
}
