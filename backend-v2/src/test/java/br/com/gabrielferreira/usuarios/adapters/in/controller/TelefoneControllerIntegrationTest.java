package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.TelefoneCreateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static br.com.gabrielferreira.usuarios.factory.TelefoneFactory.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TelefoneControllerIntegrationTest {

    private static final String URL = "/v1/usuarios";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    private Long idUsuarioExistente;

    private Long idUsuarioInexistente;

    private Long idTelefoneExistente;

    private Long idTelefoneInexistente;

    private TelefoneCreateDTO telefoneCreateDTO;

    @BeforeEach
    void setUp(){
        idUsuarioExistente = 1L;
        idUsuarioInexistente = -1L;
        idTelefoneExistente = 1L;
        idTelefoneInexistente = -1L;
        telefoneCreateDTO = createTelefone("99999999", "21", "Telefone da pessoa tal...", 4L);
    }

    @Test
    @DisplayName("Deve buscar telefone por id quando informar o id")
    @Order(1)
    void deveBuscarTelefonePorId() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/telefones");

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.numero").exists());
        resultActions.andExpect(jsonPath("$.numero").value("999999999"));
        resultActions.andExpect(jsonPath("$.ddd").exists());
        resultActions.andExpect(jsonPath("$.ddd").value("11"));
        resultActions.andExpect(jsonPath("$.telefoneFormatado").exists());
        resultActions.andExpect(jsonPath("$.telefoneFormatado").value("(11) 99999-9999"));
        resultActions.andExpect(jsonPath("$.tipoTelefone.id").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.id").value("5"));
        resultActions.andExpect(jsonPath("$.tipoTelefone.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.descricao").value("Celular"));
        resultActions.andExpect(jsonPath("$.tipoTelefone.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.codigo").value("CELULAR"));
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.id").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.id").value("2"));
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.descricao").value("Tipo de telefone"));
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.codigo").value("TIPO_TELEFONE"));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve buscar telefone por id usuário quando não encontrar telefone")
    @Order(2)
    void naoDeveBuscarTelefonePorId() throws Exception {
        String url = URL.concat("/").concat(idUsuarioInexistente.toString())
                .concat("/telefones");

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Telefone informado não encontrado"));
    }

    @Test
    @DisplayName("Deve alterar telefone quando existir")
    @Order(3)
    void deveAlterarTelefone() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/telefones")
                .concat("/").concat(idTelefoneExistente.toString());

        String jsonBody = objectMapper.writeValueAsString(telefoneCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.id").value(idTelefoneExistente));
        resultActions.andExpect(jsonPath("$.numero").exists());
        resultActions.andExpect(jsonPath("$.numero").value(telefoneCreateDTO.numero()));
        resultActions.andExpect(jsonPath("$.ddd").exists());
        resultActions.andExpect(jsonPath("$.ddd").value(telefoneCreateDTO.ddd()));
        resultActions.andExpect(jsonPath("$.telefoneFormatado").exists());
        resultActions.andExpect(jsonPath("$.telefoneFormatado").value("(21) 9999-9999"));
        resultActions.andExpect(jsonPath("$.tipoTelefone.id").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.id").value(telefoneCreateDTO.tipoTelefone().id()));
        resultActions.andExpect(jsonPath("$.tipoTelefone.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.descricao").value("Residencial"));
        resultActions.andExpect(jsonPath("$.tipoTelefone.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.codigo").value("RESIDENCIAL"));
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.id").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.id").value("2"));
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.descricao").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.descricao").value("Tipo de telefone"));
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.codigo").exists());
        resultActions.andExpect(jsonPath("$.tipoTelefone.tipo.codigo").value("TIPO_TELEFONE"));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Não deve alterar telefone quando não encontrar telefone")
    @Order(4)
    void naoDeveAlterarTelefoneQuandoNaoEncontrar() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/telefones")
                .concat("/").concat(idTelefoneInexistente.toString());

        String jsonBody = objectMapper.writeValueAsString(telefoneCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Telefone informado não encontrado"));
    }

    @Test
    @DisplayName("Não deve alterar telefone quando informar tipo telefone incorreto")
    @Order(6)
    void naoDeveAlterarTelefoneQuandoNaoEncontrarTipoTelefone() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/telefones")
                .concat("/").concat(idTelefoneExistente.toString());

        telefoneCreateDTO = createTelefone("99999999", "21", "Telefone da pessoa tal...", -1L);
        String jsonBody = objectMapper.writeValueAsString(telefoneCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Tipo de telefone informado não encontrado"));
    }

    @Test
    @DisplayName("Não deve alterar telefone quando informar tipo telefone e número incorreto")
    @Order(8)
    void naoDeveAlterarTelefoneQuandoInformarTipoTelefoneNumeroIncorreto() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/telefones")
                .concat("/").concat(idTelefoneExistente.toString());

        telefoneCreateDTO = createTelefone("99999999", "21", "Telefone da pessoa tal...", 5L);
        String jsonBody = objectMapper.writeValueAsString(telefoneCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("O número do telefone '(21) 9999-9999' tem ser do tipo residencial"));
    }

    @Test
    @DisplayName("Não deve alterar celular quando informar tipo telefone e número incorreto")
    @Order(9)
    void naoDeveAlterarCelularQuandoInformarTipoTelefoneNumeroIncorreto() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/telefones")
                .concat("/").concat(idTelefoneExistente.toString());

        telefoneCreateDTO = createTelefone("999999999", "21", "Telefone da pessoa tal...", 4L);
        String jsonBody = objectMapper.writeValueAsString(telefoneCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.mensagem").value("O número de telefone '(21) 99999-9999' tem ser do tipo celular"));
    }
}
