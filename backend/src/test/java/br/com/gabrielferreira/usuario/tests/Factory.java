package br.com.gabrielferreira.usuario.tests;

import br.com.gabrielferreira.usuario.dto.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Factory {

    private Factory(){}

    public static AnotacaoInsertDTO criarAnotacaoInsert(String descricao, Long idUsuario){
        AnotacaoUsuarioInsertDTO usuario = AnotacaoUsuarioInsertDTO.builder().id(idUsuario).build();
        return AnotacaoInsertDTO.builder()
                .descricao(descricao)
                .usuario(usuario)
                .build();
    }

    public static AnotacaoUpdateDTO criarAnotacaoUpdate(String descricao){
        return AnotacaoUpdateDTO.builder()
                .descricao(descricao)
                .build();
    }

    public static UsuarioInsertDTO criarUsuarioInsert(){
        TipoTelefoneInsertDTO tipoTelefoneInsertDTO = TipoTelefoneInsertDTO.builder()
                .id(1L)
                .build();

        TelefoneInsertDTO telefoneInsertDTO = TelefoneInsertDTO.builder()
                .numero("38713465")
                .ddd("11")
                .descricao("telefone do abel")
                .tipoTelefone(tipoTelefoneInsertDTO)
                .build();

        GeneroInsertDTO generoInsertDTO = GeneroInsertDTO.builder()
                .id(1L)
                .build();

        return UsuarioInsertDTO.builder()
                .nome("Abel Ferreira")
                .email("abel123@email.com")
                .cpf("41804189081")
                .renda(BigDecimal.valueOf(50000.00))
                .dataNascimento(LocalDate.of(1978, 12, 22))
                .quantidadeFilhos(2)
                .telefone(telefoneInsertDTO)
                .genero(generoInsertDTO)
                .build();
    }

    public static UsuarioUpdateDTO criarUsuarioUpdate(){
        TipoTelefoneInsertDTO tipoTelefoneInsertDTO = TipoTelefoneInsertDTO.builder()
                .id(2L)
                .build();

        TelefoneInsertDTO telefoneInsertDTO = TelefoneInsertDTO.builder()
                .numero("989919468")
                .ddd("21")
                .descricao("celular do abel")
                .tipoTelefone(tipoTelefoneInsertDTO)
                .build();

        GeneroInsertDTO generoInsertDTO = GeneroInsertDTO.builder()
                .id(3L)
                .build();

        return UsuarioUpdateDTO.builder()
                .nome("Abel Ferreira Atualizado")
                .renda(BigDecimal.valueOf(100000.00))
                .quantidadeFilhos(3)
                .telefone(telefoneInsertDTO)
                .genero(generoInsertDTO)
                .build();
    }
}
