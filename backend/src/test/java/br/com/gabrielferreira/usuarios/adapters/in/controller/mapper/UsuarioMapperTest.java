package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.*;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioDTO;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.UsuarioResumidoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.DominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.TipoDominioDomain;
import br.com.gabrielferreira.usuarios.application.core.domain.UsuarioDomain;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class UsuarioMapperTest {

    private final UsuarioMapperImpl usuarioMapper = new UsuarioMapperImpl();

    @Nested
    class UsuarioComDataValida {

        private ZonedDateTime date;

        @BeforeEach
        void setUp() {
            date = ZonedDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());
        }

        @Test
        @DisplayName("Deve criar usuário dto")
        @Order(1)
        void deveCriarUsuarioDto(){
            UsuarioDomain usuarioDomain = new UsuarioDomain();
            usuarioDomain.setId(1L);
            usuarioDomain.setNome("nome");
            usuarioDomain.setGenero(new DominioDomain(1L, "descricao", "codigo", new TipoDominioDomain(1L, "descricao", "codigo")));
            usuarioDomain.setCreatedAt(date);

            UsuarioDTO usuarioDTO = usuarioMapper.toUsuarioDto(usuarioDomain);
            assertEquals(usuarioDomain.getId(), usuarioDTO.getId());
            assertEquals(usuarioDomain.getNome(), usuarioDTO.getNome());
            assertEquals(usuarioDomain.getGenero().getId(), usuarioDTO.getGenero().getId());
            assertEquals(usuarioDomain.getGenero().getDescricao(), usuarioDTO.getGenero().getDescricao());
            assertEquals(usuarioDomain.getGenero().getCodigo(), usuarioDTO.getGenero().getCodigo());
            assertEquals(usuarioDomain.getGenero().getTipo().getId(), usuarioDTO.getGenero().getTipo().id());
            assertEquals(usuarioDomain.getGenero().getTipo().getDescricao(), usuarioDTO.getGenero().getTipo().descricao());
            assertEquals(usuarioDomain.getGenero().getTipo().getCodigo(), usuarioDTO.getGenero().getTipo().codigo());
            assertEquals(usuarioDomain.getCreatedAt(), usuarioDTO.getCreatedAt());
        }

        @Test
        @DisplayName("Deve criar lista usuário resumido")
        @Order(2)
        void deveCriarListaUsuarioResumido(){
            List<UsuarioDomain> usuarioDomains = new ArrayList<>();

            UsuarioDomain usuarioDomain = new UsuarioDomain();
            usuarioDomain.setId(1L);
            usuarioDomain.setNome("nome");
            usuarioDomain.setCpf("cpf");
            usuarioDomain.setEmail("email");
            usuarioDomain.setCreatedAt(date);

            usuarioDomains.add(usuarioDomain);

            List<UsuarioResumidoDTO> usuarioResumidoDTOS = usuarioMapper.toUsuarioResumidoDtos(usuarioDomains);
            assertFalse(usuarioResumidoDTOS.isEmpty());
            assertEquals(usuarioDomain.getId(), usuarioResumidoDTOS.get(0).getId());
            assertEquals(usuarioDomain.getNome(), usuarioResumidoDTOS.get(0).getNome());
            assertEquals(usuarioDomain.getCpf(), usuarioResumidoDTOS.get(0).getCpf());
            assertEquals(usuarioDomain.getEmail(), usuarioResumidoDTOS.get(0).getEmail());
            assertEquals(usuarioDomain.getCreatedAt(), usuarioResumidoDTOS.get(0).getCreatedAt());
        }
    }

    @Nested
    class UsuarioSemDataValida {
        @Test
        @DisplayName("Deve criar usuário domain")
        @Order(3)
        void deveCriarUsuarioDomain(){
            UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO("nome", "email@email.com", "cpf", BigDecimal.ONE, LocalDate.now(),
                    1, new TelefoneCreateDTO("numero", "ddd", "descricao", new TipoTelefoneCreateDTO(4L)), new GeneroCreateDTO(1L));

            UsuarioDomain usuarioDomain = usuarioMapper.createUsuarioDomain(usuarioCreateDTO);
            assertEquals(usuarioCreateDTO.nome(), usuarioDomain.getNome());
            assertEquals(usuarioCreateDTO.email(), usuarioDomain.getEmail());
            assertEquals(usuarioCreateDTO.cpf(), usuarioDomain.getCpf());
            assertEquals(usuarioCreateDTO.renda(), usuarioDomain.getRenda());
            assertEquals(usuarioCreateDTO.dataNascimento(), usuarioDomain.getDataNascimento());
            assertEquals(usuarioCreateDTO.quantidadeFilhos(), usuarioDomain.getQuantidadeFilhos());
            assertEquals(usuarioCreateDTO.telefone().numero(), usuarioDomain.getTelefone().getNumero());
            assertEquals(usuarioCreateDTO.telefone().ddd(), usuarioDomain.getTelefone().getDdd());
            assertEquals(usuarioCreateDTO.telefone().descricao(), usuarioDomain.getTelefone().getDescricao());
            assertEquals(usuarioCreateDTO.telefone().tipoTelefone().id(), usuarioDomain.getTelefone().getTipoTelefone().getId());
            assertEquals(usuarioCreateDTO.genero().id(), usuarioDomain.getGenero().getId());
        }

        @Test
        @DisplayName("Deve atualizar usuário domain")
        @Order(4)
        void deveAtualizarUsuarioDomain(){
            UsuarioUpdateDTO usuarioUpdateDTO = new UsuarioUpdateDTO("nome", BigDecimal.ONE, LocalDate.now(), 2, new GeneroCreateDTO(1L));

            UsuarioDomain usuarioDomain = usuarioMapper.updateUsuarioDomain(usuarioUpdateDTO, 1L);
            assertEquals(1L, usuarioDomain.getId());
            assertEquals(usuarioUpdateDTO.nome(), usuarioDomain.getNome());
            assertEquals(usuarioUpdateDTO.renda(), usuarioDomain.getRenda());
            assertEquals(usuarioUpdateDTO.dataNascimento(), usuarioDomain.getDataNascimento());
            assertEquals(usuarioUpdateDTO.quantidadeFilhos(), usuarioDomain.getQuantidadeFilhos());
            assertEquals(usuarioUpdateDTO.genero().id(), usuarioDomain.getGenero().getId());
        }
    }
}
