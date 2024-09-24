package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.response.GeneroDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GeneroControllerIntegrationTest {

    private static final String URL = "/v1/generos";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    private Long idGeneroExistente;

    private Long idGeneroInexistente;

    @BeforeEach
    void setUp(){
        idGeneroExistente = 1L;
        idGeneroInexistente = -1L;
    }

    @Test
    @DisplayName("Deve buscar gênero por id quando informar o id")
    @Order(1)
    void deveBuscarGeneroPorId() throws Exception {
        String url = URL.concat("/").concat(idGeneroExistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.descricao").value("Masculino"));
        resultActions.andExpect(jsonPath("$.codigo").exists());
        resultActions.andExpect(jsonPath("$.codigo").value("MASCULINO"));
        resultActions.andExpect(jsonPath("$.tipo.id").exists());
        resultActions.andExpect(jsonPath("$.tipo.id").value("1"));
        resultActions.andExpect(jsonPath("$.tipo.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipo.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipo.codigo").value("GENERO"));
    }

    @Test
    @DisplayName("Não deve buscar gênero por id quando não encontrar gênero")
    @Order(2)
    void naoDeveBuscarGeneroPorId() throws Exception {
        String url = URL.concat("/").concat(idGeneroInexistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Gênero informado não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar gêneros")
    @Order(3)
    void deveBuscarGeneros() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());

        String conteudo = resultActions.andReturn().getResponse().getContentAsString();
        List<GeneroDTO> generos = objectMapper.readValue(conteudo, new TypeReference<>() {});
        assertFalse(generos.isEmpty());
        assertEquals("MASCULINO", generos.get(0).codigo());
        assertEquals("Masculino", generos.get(0).descricao());
    }
}
