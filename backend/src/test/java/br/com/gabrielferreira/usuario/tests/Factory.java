package br.com.gabrielferreira.usuario.tests;

import br.com.gabrielferreira.usuario.dto.request.*;
import br.com.gabrielferreira.usuario.utils.DataUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class Factory {

    private Factory(){}

    public static AnotacaoCreateRequestDTO criarAnotacaoInsertEstudo(){
        AnotacaoUsuarioCreateRequestDTO usuario = AnotacaoUsuarioCreateRequestDTO.builder().id(1L).build();
        TipoAnotacaoCreateRequestDTO tipoAnotacao = TipoAnotacaoCreateRequestDTO.builder().id(7L).build();
        ZonedDateTime dataEstudoInicio = ZonedDateTime.now(DataUtils.FUSO_HORARIO_PADRAO_SISTEMA).plusHours(1L);
        ZonedDateTime dataEstudoFim = dataEstudoInicio.plusHours(2L);
        return AnotacaoCreateRequestDTO.builder()
                .titulo("titulo estudo")
                .descricao("descricao estudo")
                .tipoAnotacao(tipoAnotacao)
                .dataEstudoInicio(dataEstudoInicio)
                .dataEstudoFim(dataEstudoFim)
                .usuario(usuario)
                .build();
    }

    public static AnotacaoCreateRequestDTO criarAnotacaoInsertLembrete(){
        AnotacaoUsuarioCreateRequestDTO usuario = AnotacaoUsuarioCreateRequestDTO.builder().id(1L).build();
        TipoAnotacaoCreateRequestDTO tipoAnotacao = TipoAnotacaoCreateRequestDTO.builder().id(8L).build();
        ZonedDateTime dataLembrete = ZonedDateTime.now(DataUtils.FUSO_HORARIO_PADRAO_SISTEMA).plusHours(1L);
        return AnotacaoCreateRequestDTO.builder()
                .titulo("titulo lembrete")
                .descricao("descricao lembrete")
                .tipoAnotacao(tipoAnotacao)
                .dataLembrete(dataLembrete)
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
        ZonedDateTime dataLembrete = ZonedDateTime.now(DataUtils.FUSO_HORARIO_PADRAO_SISTEMA).plusHours(3L);
        return AnotacaoUpdateRequestDTO.builder()
                .titulo("titulo anotacao update")
                .descricao("descricao anotacao update")
                .tipoAnotacao(tipoAnotacao)
                .dataLembrete(dataLembrete)
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

    public static UsuarioCreateRequestDTO criarUsuarioInsertVazio(){
        TipoTelefoneCreateRequestDTO tipoTelefoneCreateRequestDTO = TipoTelefoneCreateRequestDTO.builder()
                .id(null)
                .build();

        TelefoneCreateRequestDTO telefoneCreateRequestDTO = TelefoneCreateRequestDTO.builder()
                .numero(null)
                .ddd(null)
                .descricao(null)
                .tipoTelefone(tipoTelefoneCreateRequestDTO)
                .build();

        GeneroCreateRequestDTO generoCreateRequestDTO = GeneroCreateRequestDTO.builder()
                .id(null)
                .build();

        return UsuarioCreateRequestDTO.builder()
                .nome(null)
                .email(null)
                .cpf(null)
                .renda(null)
                .dataNascimento(null)
                .quantidadeFilhos(null)
                .telefone(telefoneCreateRequestDTO)
                .genero(generoCreateRequestDTO)
                .build();
    }

    public static AnotacaoCreateRequestDTO criarAnotacaoInsertEstudoVazia(){
        AnotacaoUsuarioCreateRequestDTO usuario = AnotacaoUsuarioCreateRequestDTO.builder().id(null).build();
        TipoAnotacaoCreateRequestDTO tipoAnotacao = TipoAnotacaoCreateRequestDTO.builder().id(null).build();
        return AnotacaoCreateRequestDTO.builder()
                .titulo(null)
                .descricao(null)
                .tipoAnotacao(tipoAnotacao)
                .dataEstudoInicio(null)
                .dataEstudoFim(null)
                .usuario(usuario)
                .build();
    }
}
