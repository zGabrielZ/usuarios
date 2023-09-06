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

    private AnotacaoInsertDTO anotacaoInsertDTO;

    @BeforeEach
    void setUp(){
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
}
