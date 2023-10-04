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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DominioControllerIntegrationTest {

    private static final String URL = "/dominios";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    private Long idDominioExistente;

    private Long idDominioInexistente;

    @BeforeEach
    void setUp(){
        idDominioExistente = 1L;
        idDominioInexistente = -1L;
    }

    @Test
    @DisplayName("Deve buscar dominios quando não informar parametros")
    @Order(1)
    void deveBuscarTipoDominios() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());

        String conteudo = resultActions.andReturn().getResponse().getContentAsString();
        assertNotEquals("[]", conteudo);
    }

    @Test
    @DisplayName("Deve buscar dominios quando informar parametros")
    @Order(2)
    void deveBuscarTipoDominiosParametros() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("?idTipoDominio=1&codigoTipoDominio=GENERO"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());

        String conteudo = resultActions.andReturn().getResponse().getContentAsString();
        assertNotEquals("[]", conteudo);
    }

    @Test
    @DisplayName("Deve buscar dominios quando informar parametros errados")
    @Order(3)
    void deveBuscarTipoDominiosParametrosErrados() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("?idTipoDominio=2&codigoTipoDominio=GENERO"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());

        String conteudo = resultActions.andReturn().getResponse().getContentAsString();
        assertEquals("[]", conteudo);
    }

    @Test
    @DisplayName("Deve buscar dominio quando existir")
    @Order(4)
    void deveBuscarDominio() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idDominioExistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.codigo").exists());
    }

    @Test
    @DisplayName("Não deve buscar dominio quando não existir")
    @Order(5)
    void naoDeveBuscarDominio() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/{id}"), idDominioInexistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Domínio não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar dominio quando informar o código")
    @Order(6)
    void deveBuscarDominioQuandoInformarCodigo() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo=MASCULINO"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.codigo").exists());
    }

    @Test
    @DisplayName("Não deve buscar dominio quando não informar o código")
    @Order(7)
    void naoDeveBuscarDominioQuandoNaoInformarCodigo() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo="))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("É necessário informar o código"));
    }

    @Test
    @DisplayName("Não deve buscar dominio quando informar o código inválido")
    @Order(8)
    void naoDeveBuscarDominioQuandoInformarCodigoInvalido() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL.concat("/buscar?codigo=invalido"))
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Domínio não encontrado"));
    }
}
