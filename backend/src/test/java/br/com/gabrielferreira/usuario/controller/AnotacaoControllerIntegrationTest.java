package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.dto.AnotacaoInsertDTO;
import br.com.gabrielferreira.usuario.dto.AnotacaoUpdateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

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

    private Long idAnotacaoExistente;

    private Long idAnotacaoInexistente;

    private AnotacaoInsertDTO anotacaoInsertDTO;

    private AnotacaoUpdateDTO anotacaoUpdateDTO;

    @BeforeEach
    void setUp(){
        idAnotacaoExistente = 1L;
        idAnotacaoInexistente = -1L;
        anotacaoInsertDTO = criarAnotacaoInsert("Teste unitário","Anotação teste unitário", 1L);
        anotacaoUpdateDTO = criarAnotacaoUpdate("Teste unitário alterado","Anotacao teste unitário alterado");
    }

    @Test
    @DisplayName("Deve cadastrar uma anotação")
    @Order(1)
    void deveCadastrarAnotacao() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(anotacaoInsertDTO);

        String tituloEsperado = anotacaoInsertDTO.getTitulo();
        String descricaoEsperado = anotacaoInsertDTO.getDescricao();
        Long idUsuarioEsperado = anotacaoInsertDTO.getUsuario().getId();

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").value(tituloEsperado));
        resultActions.andExpect(jsonPath("$.descricao").value(descricaoEsperado));
        resultActions.andExpect(jsonPath("$.usuario.id").value(idUsuarioEsperado));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Deve buscar anotação quando existir")
    @Order(2)
    void deveBuscarAnotacao() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idAnotacaoExistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.usuario.id").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve buscar anotação quando não existir")
    @Order(3)
    void naoDeveBuscarAnotacao() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idAnotacaoInexistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve alterar anotação quando existir")
    @Order(4)
    void deveAlterarAnotacao() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(anotacaoUpdateDTO);

        Long idEsperado = idAnotacaoExistente;
        String tituloEsperado = anotacaoUpdateDTO.getTitulo();
        String descricaoEsperado = anotacaoUpdateDTO.getDescricao();

        ResultActions resultActions = mockMvc
                .perform(put(URL.concat("/{id}"), idAnotacaoExistente)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").value(idEsperado));
        resultActions.andExpect(jsonPath("$.titulo").value(tituloEsperado));
        resultActions.andExpect(jsonPath("$.descricao").value(descricaoEsperado));
        resultActions.andExpect(jsonPath("$.usuario.id").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve alterar anotação quando não existir")
    @Order(5)
    void naoDeveAlterarAnotacao() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(anotacaoUpdateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(URL.concat("/{id}"), idAnotacaoInexistente)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve deletar anotação quando existir")
    @Order(6)
    void deveDeletarAnotacao() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(delete(URL.concat("/{id}"), idAnotacaoExistente)
                        .accept(MEDIA_TYPE_JSON));
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Não deve deletar anotação quando não existir")
    @Order(7)
    void naoDeveDeletarAnotacao() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(delete(URL.concat("/{id}"), idAnotacaoInexistente)
                        .accept(MEDIA_TYPE_JSON));
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve buscar anotação resumida quando existir")
    @Order(8)
    void deveBuscarAnotacaoResumida() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/resumida/{id}"), idAnotacaoExistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve buscar anotação resumida quando não existir")
    @Order(9)
    void naoDeveBuscarAnotacaoResumida() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/resumida/{id}"), idAnotacaoInexistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve buscar anotações resumida paginada quando existir")
    @Order(10)
    void deveBuscarAnotacaoResumidaPaginadas() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/resumida?idUsuario=1&page=0&size=5&sort=id,desc"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.content").exists());
    }

    @Test
    @DisplayName("Não deve buscar anotações resumida paginada quando informar propriedade incorreta")
    @Order(11)
    void naoDeveBuscarAnotacaoResumidaPaginadas() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/resumida?idUsuario=1&page=0&size=5&sort=iddd,desc"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Não deve buscar anotações resumida paginada quando não informar o id usuário")
    @Order(11)
    void naoDeveBuscarAnotacaoResumidaPaginadasQuandoNaoInformarIdUsuario() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/resumida?idUsuario=&page=0&size=5&sort=id,desc"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isInternalServerError());
        resultActions.andExpect(jsonPath("$.erro").value("Required request parameter 'idUsuario' for method parameter type Long is present but converted to null"));
    }
}
