package br.com.gabrielferreira.usuario.controller;

import br.com.gabrielferreira.usuario.dto.AnotacaoInsertDTO;
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
class AnotacaoControllerIT {

    private static final String URL = "/anotacoes";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    private Long idAnotacaoExistente;

    private Long idAnotacaoInexistente;

    private AnotacaoInsertDTO anotacaoInsertDTO;

    @BeforeEach
    void setUp(){
        idAnotacaoExistente = 1L;
        idAnotacaoInexistente = -1L;
        anotacaoInsertDTO = criarAnotacaoInsert("Anotação teste unitário", 1L);
    }

    @Test
    @DisplayName("Deve cadastrar uma anotação")
    @Order(1)
    void deveCadastrarAnotacao() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(anotacaoInsertDTO);

        String descricaoEsperado = anotacaoInsertDTO.getDescricao();
        Long idUsuarioEsperado = anotacaoInsertDTO.getUsuario().getId();

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
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
}
