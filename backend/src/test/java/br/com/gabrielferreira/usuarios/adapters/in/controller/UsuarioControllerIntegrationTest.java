package br.com.gabrielferreira.usuarios.adapters.in.controller;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.UsuarioCreateDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.request.UsuarioUpdateDTO;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.PerfilEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.entity.UsuarioEntity;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.PerfilRepository;
import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static br.com.gabrielferreira.usuarios.tests.UsuarioFactory.atualizarUsuarioUpdateDto;
import static br.com.gabrielferreira.usuarios.tests.UsuarioFactory.criarUsuarioCreateDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControllerIntegrationTest {

    private static final String URL = "/v1/usuarios";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected PerfilRepository perfilRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    private Long idUsuarioExistente;

    private Long idUsuarioInexistente;

    private Long idPerfilInexistente;

    private Long idPerfilExistente;

    private String emailExistente;

    private String emailInexistente;

    private String cpfExistente;

    private String cpfInexistente;

    private UsuarioCreateDTO usuarioCreateDTO;

    private UsuarioUpdateDTO usuarioUpdateDTO;

    @BeforeEach
    void setUp(){
        usuarioCreateDTO = criarUsuarioCreateDto("teste123321@email.com", "48967064047", "Ac1@");
        idUsuarioExistente = 1L;
        idUsuarioInexistente = -1L;
        emailExistente = "teste@email.com";
        emailInexistente = "naotememail@email.com";
        cpfExistente = "63801219003";
        cpfInexistente = "naotemcpf";
        usuarioUpdateDTO = atualizarUsuarioUpdateDto();
        idPerfilInexistente = -1L;
        idPerfilExistente = 1L;
    }

    @Test
    @DisplayName("Deve criar usuário")
    @Order(1)
    void deveCriarUsuario() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(usuarioCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.nome").exists());
        resultActions.andExpect(jsonPath("$.nome").value(usuarioCreateDTO.nome()));
        resultActions.andExpect(jsonPath("$.email").exists());
        resultActions.andExpect(jsonPath("$.email").value(usuarioCreateDTO.email()));
        resultActions.andExpect(jsonPath("$.cpf").exists());
        resultActions.andExpect(jsonPath("$.cpf").value(usuarioCreateDTO.cpf()));
        resultActions.andExpect(jsonPath("$.cpfFormatado").exists());
        resultActions.andExpect(jsonPath("$.renda").exists());
        resultActions.andExpect(jsonPath("$.renda").value(usuarioCreateDTO.renda()));
        resultActions.andExpect(jsonPath("$.rendaFormatada").exists());
        resultActions.andExpect(jsonPath("$.dataNascimento").exists());
        resultActions.andExpect(jsonPath("$.dataNascimento").value("1900-10-10"));
        resultActions.andExpect(jsonPath("$.quantidadeFilhos").exists());
        resultActions.andExpect(jsonPath("$.quantidadeFilhos").value(usuarioCreateDTO.quantidadeFilhos()));
        resultActions.andExpect(jsonPath("$.telefone.numero").value(usuarioCreateDTO.telefone().numero()));
        resultActions.andExpect(jsonPath("$.telefone.ddd").value(usuarioCreateDTO.telefone().ddd()));
        resultActions.andExpect(jsonPath("$.telefone.descricao").value(usuarioCreateDTO.telefone().descricao()));
        resultActions.andExpect(jsonPath("$.telefone.telefoneFormatado").exists());
        resultActions.andExpect(jsonPath("$.genero.id").value(usuarioCreateDTO.genero().id()));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
        resultActions.andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    @DisplayName("Não deve criar usuário quando informar cpf existente")
    @Order(2)
    void naoDeveCriarUsuarioQuandoInformarCpfExistente() throws Exception {
        usuarioCreateDTO = criarUsuarioCreateDto("teste33333@email.com", "63801219003", "Ac1@");
        String jsonBody = objectMapper.writeValueAsString(usuarioCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.titulo").value("Regra de negócio"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Não vai ser possível cadastrar este usuário pois o CPF '638.012.190-03' já foi cadastrado"));
    }

    @Test
    @DisplayName("Não deve criar usuário quando informar email existente")
    @Order(3)
    void naoDeveCriarUsuarioQuandoInformarEmailExistente() throws Exception {
        usuarioCreateDTO = criarUsuarioCreateDto("teste@email.com", "39128598091", "Ac1@");
        String jsonBody = objectMapper.writeValueAsString(usuarioCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.titulo").value("Regra de negócio"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Não vai ser possível cadastrar este usuário pois o e-mail 'teste@email.com' já foi cadastrado"));
    }

    @Test
    @DisplayName("Deve buscar usuário por id")
    @Order(4)
    void deveBuscarUsuarioPorId() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.nome").exists());
        resultActions.andExpect(jsonPath("$.email").exists());
        resultActions.andExpect(jsonPath("$.cpf").exists());
        resultActions.andExpect(jsonPath("$.cpfFormatado").exists());
        resultActions.andExpect(jsonPath("$.renda").exists());
        resultActions.andExpect(jsonPath("$.rendaFormatada").exists());
        resultActions.andExpect(jsonPath("$.dataNascimento").exists());
        resultActions.andExpect(jsonPath("$.quantidadeFilhos").exists());
        resultActions.andExpect(jsonPath("$.telefone.numero").exists());
        resultActions.andExpect(jsonPath("$.telefone.ddd").exists());
        resultActions.andExpect(jsonPath("$.telefone.descricao").exists());
        resultActions.andExpect(jsonPath("$.telefone.telefoneFormatado").exists());
        resultActions.andExpect(jsonPath("$.genero.id").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
        resultActions.andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    @DisplayName("Não deve buscar usuário por id")
    @Order(5)
    void naoDeveBuscarUsuarioPorId() throws Exception {
        String url = URL.concat("/").concat(idUsuarioInexistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.titulo").value("Não encontrado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Usuário informado não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar usuário por email")
    @Order(6)
    void deveBuscarUsuarioPorEmail() throws Exception {
        String url = URL.concat("/email/").concat(emailExistente);

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.nome").exists());
        resultActions.andExpect(jsonPath("$.email").exists());
        resultActions.andExpect(jsonPath("$.cpf").exists());
        resultActions.andExpect(jsonPath("$.cpfFormatado").exists());
        resultActions.andExpect(jsonPath("$.renda").exists());
        resultActions.andExpect(jsonPath("$.rendaFormatada").exists());
        resultActions.andExpect(jsonPath("$.dataNascimento").exists());
        resultActions.andExpect(jsonPath("$.quantidadeFilhos").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
        resultActions.andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    @DisplayName("Não deve buscar usuário por email")
    @Order(7)
    void naoDeveBuscarUsuarioPorEmail() throws Exception {
        String url = URL.concat("/email/").concat(emailInexistente);

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.titulo").value("Não encontrado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Usuário informado não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar usuário por cpf")
    @Order(8)
    void deveBuscarUsuarioPorCpf() throws Exception {
        String url = URL.concat("/cpf/").concat(cpfExistente);

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.nome").exists());
        resultActions.andExpect(jsonPath("$.email").exists());
        resultActions.andExpect(jsonPath("$.cpf").exists());
        resultActions.andExpect(jsonPath("$.cpfFormatado").exists());
        resultActions.andExpect(jsonPath("$.renda").exists());
        resultActions.andExpect(jsonPath("$.rendaFormatada").exists());
        resultActions.andExpect(jsonPath("$.dataNascimento").exists());
        resultActions.andExpect(jsonPath("$.quantidadeFilhos").exists());
        resultActions.andExpect(jsonPath("$.createdAt").exists());
        resultActions.andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    @DisplayName("Não deve buscar usuário por cpf")
    @Order(9)
    void naoDeveBuscarUsuarioPorCpf() throws Exception {
        String url = URL.concat("/cpf/").concat(cpfInexistente);

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.titulo").value("Não encontrado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Usuário informado não encontrado"));
    }

    @Test
    @DisplayName("Deve atualizar usuário")
    @Order(10)
    void deveAtualizarUsuario() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString());

        String jsonBody = objectMapper.writeValueAsString(usuarioUpdateDTO);

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.nome").exists());
        resultActions.andExpect(jsonPath("$.nome").value(usuarioUpdateDTO.nome()));
        resultActions.andExpect(jsonPath("$.email").exists());
        resultActions.andExpect(jsonPath("$.cpf").exists());
        resultActions.andExpect(jsonPath("$.cpfFormatado").exists());
        resultActions.andExpect(jsonPath("$.renda").exists());
        resultActions.andExpect(jsonPath("$.renda").value(usuarioUpdateDTO.renda()));
        resultActions.andExpect(jsonPath("$.rendaFormatada").exists());
        resultActions.andExpect(jsonPath("$.dataNascimento").exists());
        resultActions.andExpect(jsonPath("$.dataNascimento").value("2000-10-10"));
        resultActions.andExpect(jsonPath("$.quantidadeFilhos").exists());
        resultActions.andExpect(jsonPath("$.quantidadeFilhos").value(usuarioUpdateDTO.quantidadeFilhos()));
        resultActions.andExpect(jsonPath("$.genero.id").value(usuarioUpdateDTO.genero().id()));
        resultActions.andExpect(jsonPath("$.createdAt").exists());
        resultActions.andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    @DisplayName("Deve deletar usuário")
    @Order(10)
    void deveDeletarUsuario() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString());

        ResultActions resultActions = mockMvc
                .perform(delete(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve buscar usuários paginados quando existir")
    @Order(11)
    void deveBuscarUsuariosPaginadas() throws Exception {
        String url = URL.concat("?page=0&size=5&sort=id,desc")
                .concat("&nome=Nome teste&email=teste@email.com&renda=10000");

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$._embedded.usuarios").exists());
        resultActions.andExpect(jsonPath("$._links.self.href").exists());
        resultActions.andExpect(jsonPath("$.page").exists());
    }

    @Test
    @DisplayName("Deve atualizar perfil usuário para admin")
    @Order(12)
    void deveAtualizarPerfilAdmin() throws Exception {
        setUpPerfilClient();

        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/admin");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Não deve atualizar perfil usuário para admin quando já tiver com admin")
    @Order(13)
    void naoDeveAtualizarPerfilAdmin() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/admin");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.titulo").value("Regra de negócio"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Este usuário contém perfil admin"));
    }

    @Test
    @DisplayName("Deve atualizar perfil usuário para client")
    @Order(14)
    void deveAtualizarPerfilClient() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/client");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Não deve atualizar perfil usuário para client quando já tiver com client")
    @Order(15)
    void naoDeveAtualizarPerfilClient() throws Exception {
        setUpPerfilClient();

        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/client");

        ResultActions resultActions = mockMvc
                .perform(put(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.titulo").value("Regra de negócio"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Este usuário contém perfil cliente"));
    }

    @Test
    @DisplayName("Deve buscar perfil e usuário por id")
    @Order(16)
    void deveBuscarPerfilPorUsuarioId() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/perfis/").concat(idPerfilExistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.titulo").exists());
        resultActions.andExpect(jsonPath("$.autoriedade").exists());
        resultActions.andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    @DisplayName("Não deve buscar perfil e usuário por id")
    @Order(17)
    void naoDeveBuscarPerfilPorUsuarioId() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/perfis/").concat(idPerfilInexistente.toString());

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.titulo").value("Não encontrado"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Perfil informado não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar perfis por usuário")
    @Order(18)
    void deveBuscarPerfis() throws Exception {
        String url = URL.concat("/").concat(idUsuarioExistente.toString())
                .concat("/perfis");

        ResultActions resultActions = mockMvc
                .perform(get(url)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$._embedded.perfis").exists());
        resultActions.andExpect(jsonPath("$._links.self").exists());
        resultActions.andExpect(jsonPath("$._embedded.perfis[0].titulo").value("Administrador"));
        resultActions.andExpect(jsonPath("$._embedded.perfis[0].autoriedade").value("ROLE_ADMIN"));
    }

    void setUpPerfilClient(){
        UsuarioEntity usuarioEntity = usuarioRepository.findById(idUsuarioExistente)
                .orElseThrow();
        PerfilEntity perfilEntity = perfilRepository.findById(2L)
                .orElseThrow();
        usuarioEntity.getPerfis().clear();
        usuarioEntity.getPerfis().add(perfilEntity);
        usuarioRepository.save(usuarioEntity);
    }
}
