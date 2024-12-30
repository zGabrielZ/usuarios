package br.com.gabrielferreira.usuarios.adapters.in.controller;

import org.hamcrest.collection.IsCollectionWithSize;
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
class PerfilControllerIntegrationTest {

    private static final String URL = "/v1/perfis";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    private Long idPerfilExistente;

    private Long idPerfilInexistente;

    @BeforeEach
    void setUp(){
        idPerfilExistente = 1L;
        idPerfilInexistente = -1L;
    }

    @Test
    @DisplayName("Deve buscar perfil por id")
    @Order(1)
    void deveBuscarPerfilPorId() throws Exception {
        String url = URL.concat("/").concat(idPerfilExistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.autoriedade").exists());
    }

    @Test
    @DisplayName("Não deve buscar perfil por id")
    @Order(2)
    void naoDeveBuscarPerfilPorId() throws Exception {
        String url = URL.concat("/").concat(idPerfilInexistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.titulo").value("Não encontrado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Perfil informado não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar perfis")
    @Order(3)
    void deveBuscarPerfis() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$._embedded.perfis", IsCollectionWithSize.hasSize(2)));
        resultActions.andExpect(jsonPath("$._embedded.perfis").exists());
        resultActions.andExpect(jsonPath("$._links.self").exists());
        resultActions.andExpect(jsonPath("$._embedded.perfis[0].titulo").value("Administrador"));
        resultActions.andExpect(jsonPath("$._embedded.perfis[0].autoriedade").value("ROLE_ADMIN"));
    }
}
