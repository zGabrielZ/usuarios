package br.com.gabrielferreira.usuario.tests;

import br.com.gabrielferreira.usuario.dto.request.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Factory {

    private Factory(){}

    public static AnotacaoCreateRequestDTO criarAnotacaoInsert(String titulo, String descricao, Long idUsuario){
        AnotacaoUsuarioCreateRequestDTO usuario = AnotacaoUsuarioCreateRequestDTO.builder().id(idUsuario).build();
        return AnotacaoCreateRequestDTO.builder()
                .titulo(titulo)
                .descricao(descricao)
                .usuario(usuario)
                .build();
    }

    public static AnotacaoUpdateRequestDTO criarAnotacaoUpdate(String titulo, String descricao){
        return AnotacaoUpdateRequestDTO.builder()
                .titulo(titulo)
                .descricao(descricao)
                .build();
    }

    public static UsuarioCreateRequestDTO criarUsuarioInsert(){
        TipoTelefoneCreateRequestDTO tipoTelefoneCreateRequestDTO = TipoTelefoneCreateRequestDTO.builder()
                .id(4L)
                .build();

        TelefoneCreateRequestDTO telefoneCreateRequestDTO = TelefoneCreateRequestDTO.builder()
                .numero("38713465")
                .ddd("11")
                .descricao("telefone do abel")
                .tipoTelefone(tipoTelefoneCreateRequestDTO)
                .build();

        GeneroCreateRequestDTO generoCreateRequestDTO = GeneroCreateRequestDTO.builder()
                .id(1L)
                .build();

        return UsuarioCreateRequestDTO.builder()
                .nome("Abel Ferreira")
                .email("abel123@email.com")
                .cpf("41804189081")
                .renda(BigDecimal.valueOf(50000.00))
                .dataNascimento(LocalDate.of(1978, 12, 22))
                .quantidadeFilhos(2)
                .telefone(telefoneCreateRequestDTO)
                .genero(generoCreateRequestDTO)
                .build();
    }

    public static UsuarioUpdateRequestDTO criarUsuarioUpdate(){
        TipoTelefoneCreateRequestDTO tipoTelefoneCreateRequestDTO = TipoTelefoneCreateRequestDTO.builder()
                .id(5L)
                .build();

        TelefoneCreateRequestDTO telefoneCreateRequestDTO = TelefoneCreateRequestDTO.builder()
                .numero("989919468")
                .ddd("21")
                .descricao("celular do abel")
                .tipoTelefone(tipoTelefoneCreateRequestDTO)
                .build();

        GeneroCreateRequestDTO generoCreateRequestDTO = GeneroCreateRequestDTO.builder()
                .id(3L)
                .build();

        return UsuarioUpdateRequestDTO.builder()
                .nome("Abel Ferreira Atualizado")
                .renda(BigDecimal.valueOf(100000.00))
                .quantidadeFilhos(3)
                .telefone(telefoneCreateRequestDTO)
                .genero(generoCreateRequestDTO)
                .build();
    }
}
