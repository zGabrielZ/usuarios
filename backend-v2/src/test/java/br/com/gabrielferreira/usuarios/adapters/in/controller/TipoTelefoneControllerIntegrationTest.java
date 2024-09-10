package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.response.TipoTelefoneDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TipoTelefoneControllerIntegrationTest {

    private static final String URL = "/v1/tipos-telefones";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    private Long idTipoTelefoneExistente;

    private Long idTipoTelefoneInexistente;

    @BeforeEach
    void setUp(){
        idTipoTelefoneExistente = 4L;
        idTipoTelefoneInexistente = -1L;
    }

    @Test
    @DisplayName("Deve buscar tipo telefone por id quando informar o id")
    @Order(1)
    void deveBuscarTipoTelefonePorId() throws Exception {
        String url = URL.concat("/").concat(idTipoTelefoneExistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.descricao").exists());
        resultActions.andExpect(jsonPath("$.descricao").value("Residencial"));
        resultActions.andExpect(jsonPath("$.codigo").exists());
        resultActions.andExpect(jsonPath("$.codigo").value("RESIDENCIAL"));
        resultActions.andExpect(jsonPath("$.tipo.id").exists());
        resultActions.andExpect(jsonPath("$.tipo.id").value("2"));
        resultActions.andExpect(jsonPath("$.tipo.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipo.descricao").value("Tipo de telefone"));
        resultActions.andExpect(jsonPath("$.tipo.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipo.codigo").value("TIPO_TELEFONE"));
    }

    @Test
    @DisplayName("Não deve buscar tipo telefone por id quando não encontrar tipo telefone")
    @Order(2)
    void naoDeveBuscarTipoTelefonePorId() throws Exception {
        String url = URL.concat("/").concat(idTipoTelefoneInexistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Tipo de telefone informado não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar tipos telefones")
    @Order(3)
    void deveBuscarTiposTelefones() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get(URL)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());

        String conteudo = resultActions.andReturn().getResponse().getContentAsString();
        List<TipoTelefoneDTO> tiposTelefones = objectMapper.readValue(conteudo, new TypeReference<>() {});
        assertFalse(tiposTelefones.isEmpty());
        assertEquals("RESIDENCIAL", tiposTelefones.get(0).codigo());
        assertEquals("Residencial", tiposTelefones.get(0).descricao());
    }
}
