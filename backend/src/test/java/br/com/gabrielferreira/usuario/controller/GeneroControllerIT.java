package br.com.gabrielferreira.usuario.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GeneroControllerIT {

    private static final String URL = "/generos";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    private Long idGeneroExistente;

    private Long idGeneroInexistente;

    @BeforeEach
    void setUp(){
        idGeneroExistente = 1L;
        idGeneroInexistente = -1L;
    }

    @Test
    @DisplayName("Deve buscar gêneros")
    @Order(1)
    void deveBuscarGeneros() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve buscar gênero quando existir")
    @Order(2)
    void deveBuscarGenero() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idGeneroExistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.codigo").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve buscar gênero quando não existir")
    @Order(3)
    void naoDeveBuscarGenero() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idGeneroInexistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Gênero não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar gênero quando informar o código")
    @Order(4)
    void deveBuscarGeneroQuandoInformarCodigo() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo=MASCULINO"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.codigo").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve buscar gênero quando não informar o código")
    @Order(5)
    void naoDeveBuscarGeneroQuandoNaoInformarCodigo() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo="))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("É necessário informar o código"));
    }

    @Test
    @DisplayName("Não deve buscar gênero quando informar o código inválido")
    @Order(6)
    void naoDeveBuscarGeneroQuandoInformarCodigoInvalido() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo=invalido"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Código não encontrado"));
    }
}
