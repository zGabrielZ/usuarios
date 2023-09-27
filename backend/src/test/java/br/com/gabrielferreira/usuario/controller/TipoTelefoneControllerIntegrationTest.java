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
class TipoTelefoneControllerIntegrationTest {

    private static final String URL = "/tipo-telefones";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    private Long idTipoTelefoneExistente;

    private Long idTipoTelefoneInexistente;

    @BeforeEach
    void setUp(){
        idTipoTelefoneExistente = 1L;
        idTipoTelefoneInexistente = -1L;
    }

    @Test
    @DisplayName("Deve buscar tipo de telefones")
    @Order(1)
    void deveBuscarTipoDeTelefones() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve buscar tipo de telefone quando existir")
    @Order(2)
    void deveBuscarTipoDeTelefone() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idTipoTelefoneExistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.codigo").exists());
    }

    @Test
    @DisplayName("Não deve buscar tipo de telefone quando não existir")
    @Order(3)
    void naoDeveBuscarTipoDeTelefone() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idTipoTelefoneInexistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Tipo de telefone não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar tipo de telefone quando informar o código")
    @Order(4)
    void deveBuscarTipoDeTelefoneQuandoInformarCodigo() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo=RESIDENCIAL"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.codigo").exists());
    }

    @Test
    @DisplayName("Não deve buscar tipo de telefone quando não informar o código")
    @Order(5)
    void naoDeveBuscarTipoDeTelefoneQuandoNaoInformarCodigo() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo="))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("É necessário informar o código"));
    }

    @Test
    @DisplayName("Não deve buscar tipo de telefone quando informar o código inválido")
    @Order(6)
    void naoDeveBuscarGeneroQuandoInformarCodigoInvalido() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo=invalido"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Tipo de telefone não encontrado"));
    }
}
