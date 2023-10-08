package br.com.gabrielferreira.usuario.tests;

import br.com.gabrielferreira.usuario.dto.request.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Factory {

    private Factory(){}

    public static AnotacaoCreateRequestDTO criarAnotacaoInsertEstudo(){
        AnotacaoUsuarioCreateRequestDTO usuario = AnotacaoUsuarioCreateRequestDTO.builder().id(1L).build();
        TipoAnotacaoCreateRequestDTO tipoAnotacao = TipoAnotacaoCreateRequestDTO.builder().id(7L).build();
        LocalDateTime dataEstudoInicio = LocalDateTime.of(2023, 10, 8, 20, 0, 0);
        LocalDateTime dataEstudoFim = LocalDateTime.of(2023, 10, 8, 21, 0, 0);
        return AnotacaoCreateRequestDTO.builder()
                .titulo("titulo estudo")
                .descricao("descricao estudo")
                .tipoAnotacao(tipoAnotacao)
                .dataEstudoInicio(ZonedDateTime.of(dataEstudoInicio,ZoneId.of("America/Sao_Paulo")))
                .dataEstudoFim(ZonedDateTime.of(dataEstudoFim,ZoneId.of("America/Sao_Paulo")))
                .usuario(usuario)
                .build();
    }

    public static AnotacaoCreateRequestDTO criarAnotacaoInsertLembrete(){
        AnotacaoUsuarioCreateRequestDTO usuario = AnotacaoUsuarioCreateRequestDTO.builder().id(1L).build();
        TipoAnotacaoCreateRequestDTO tipoAnotacao = TipoAnotacaoCreateRequestDTO.builder().id(8L).build();
        LocalDateTime dataLembrete = LocalDateTime.of(2023, 10, 8, 21, 0, 0);
        return AnotacaoCreateRequestDTO.builder()
                .titulo("titulo lembrete")
                .descricao("descricao lembrete")
                .tipoAnotacao(tipoAnotacao)
                .dataLembrete(ZonedDateTime.of(dataLembrete,ZoneId.of("America/Sao_Paulo")))
                .usuario(usuario)
                .build();
    }

    public static AnotacaoCreateRequestDTO criarAnotacaoInsertRascunho(){
        AnotacaoUsuarioCreateRequestDTO usuario = AnotacaoUsuarioCreateRequestDTO.builder().id(1L).build();
        TipoAnotacaoCreateRequestDTO tipoAnotacao = TipoAnotacaoCreateRequestDTO.builder().id(6L).build();
        return AnotacaoCreateRequestDTO.builder()
                .titulo("titulo rascunho")
                .descricao("descricao rascunho")
                .tipoAnotacao(tipoAnotacao)
                .usuario(usuario)
                .build();
    }

    public static AnotacaoUpdateRequestDTO criarAnotacaoUpdate(){
        TipoAnotacaoCreateRequestDTO tipoAnotacao = TipoAnotacaoCreateRequestDTO.builder().id(8L).build();
        LocalDateTime dataLembrete = LocalDateTime.of(2023, 10, 8, 21, 0, 0);
        return AnotacaoUpdateRequestDTO.builder()
                .titulo("titulo anotacao update")
                .descricao("descricao anotacao update")
                .tipoAnotacao(tipoAnotacao)
                .dataLembrete(ZonedDateTime.of(dataLembrete,ZoneId.of("America/Sao_Paulo")))
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
