package br.com.gabrielferreira.usuarios.adapters.in.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnotacaoControllerIntegrationTest {

    private static final String URL = "/v1/usuarios";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    private Long idUsuarioExistente;

    private Long idUsuarioInexistente;

    @BeforeEach
    void setUp(){
        idUsuarioInexistente = -1L;
        idUsuarioExistente = 1L;
    }

    @Test
    @DisplayName("Deve buscar anotações paginada quando existir")
    @Order(1)
    void deveBuscarAnotacaoPaginadas() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes")
                .concat("?idUsuario=1&page=0&size=5&sort=id,desc");

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.content").exists());

        String conteudo = resultActions.andReturn().getResponse().getContentAsString();
        assertNotNull(conteudo);
    }

    @Test
    @DisplayName("Não deve buscar anotações paginada quando não encontrar")
    @Order(2)
    void naoDeveBuscarAnotacaoPaginadaQuandoNaoEncontrarUsuario() throws Exception {
        String url = URL.concat("/").concat(idUsuarioInexistente.toString())
                .concat("/anotacoes")
                .concat("?idUsuario=1&page=0&size=5&sort=id,desc");

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.titulo").value("Não encontrado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Usuário informado não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar anotações paginada quando existir por titulo")
    @Order(3)
    void deveBuscarAnotacaoPaginadasBuscaPorTitulo() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes")
                .concat("?idUsuario=1&titulo=Estudo&page=0&size=1&sort=id,desc");

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.content").exists());

        String conteudo = resultActions.andReturn().getResponse().getContentAsString();
        assertNotNull(conteudo);
    }

    @Test
    @DisplayName("Deve buscar anotações paginada quando existir por descrição")
    @Order(4)
    void deveBuscarAnotacaoPaginadasBuscaPorDescricao() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/anotacoes")
                .concat("?idUsuario=1&descricao=Estudo....&page=0&size=1&sort=id,desc");

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.content").exists());

        String conteudo = resultActions.andReturn().getResponse().getContentAsString();
        assertNotNull(conteudo);
    }
}
