package br.com.gabrielferreira.usuarios.adapters.in.controller.hateoas;

import br.com.gabrielferreira.usuarios.adapters.in.controller.AnotacaoController;
import br.com.gabrielferreira.usuarios.adapters.in.controller.response.AnotacaoResumidoDTO;
import br.com.gabrielferreira.usuarios.application.core.domain.enums.TipoAnotacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class AnotacaoHateoas {

    private final AnotacaoRascunhoHateoas anotacaoRascunhoHateoas;

    private final AnotacaoLembreteHateoas anotacaoLembreteHateoas;

    private final AnotacaoEstudoHateoas anotacaoEstudoHateoas;

    public void addLinkToGetAnotacoes(List<AnotacaoResumidoDTO> anotacaoResumidoDtos, Long idUsuario) {
        anotacaoResumidoDtos.forEach(anotacaoResumidoDto -> {
            if (TipoAnotacaoEnum.RASCUNHO.name().equals(anotacaoResumidoDto.getTipoAnotacao().codigo())) {
                anotacaoResumidoDto.add(anotacaoRascunhoHateoas.getAnotacaoRascunho(anotacaoResumidoDto.getId(), idUsuario));
                anotacaoResumidoDto.add(anotacaoRascunhoHateoas.putAnotacaoRascunho(anotacaoResumidoDto.getId(), idUsuario));
                anotacaoResumidoDto.add(anotacaoRascunhoHateoas.putAnotacaoRascunhoReabrir(anotacaoResumidoDto.getId(), idUsuario));
                anotacaoResumidoDto.add(anotacaoRascunhoHateoas.putAnotacaoRascunhoFinalizar(anotacaoResumidoDto.getId(), idUsuario));
            }

            if (TipoAnotacaoEnum.LEMBRETE.name().equals(anotacaoResumidoDto.getTipoAnotacao().codigo())) {
                anotacaoResumidoDto.add(anotacaoLembreteHateoas.getAnotacaoLembrete(anotacaoResumidoDto.getId(), idUsuario));
                anotacaoResumidoDto.add(anotacaoLembreteHateoas.putAnotacaoLembrete(anotacaoResumidoDto.getId(), idUsuario));
                anotacaoResumidoDto.add(anotacaoLembreteHateoas.putAnotacaoLembreteReabrir(anotacaoResumidoDto.getId(), idUsuario));
                anotacaoResumidoDto.add(anotacaoLembreteHateoas.putAnotacaoLembreteFinalizar(anotacaoResumidoDto.getId(), idUsuario));
            }

            if (TipoAnotacaoEnum.ESTUDO.name().equals(anotacaoResumidoDto.getTipoAnotacao().codigo())) {
                anotacaoResumidoDto.add(anotacaoEstudoHateoas.getAnotacaoEstudo(anotacaoResumidoDto.getId(), idUsuario));
                anotacaoResumidoDto.add(anotacaoEstudoHateoas.putAnotacaoEstudo(anotacaoResumidoDto.getId(), idUsuario));
                anotacaoResumidoDto.add(anotacaoEstudoHateoas.putAnotacaoEstudoReabrir(anotacaoResumidoDto.getId(), idUsuario));
                anotacaoResumidoDto.add(anotacaoEstudoHateoas.putAnotacaoEstudoFinalizar(anotacaoResumidoDto.getId(), idUsuario));
            }
        });
    }

    public Link getAnotacoes(Long idUsuario, Pageable pageable, String titulo, String descricao) {
        return linkTo(methodOn(AnotacaoController.class).findAll(idUsuario, pageable, titulo, descricao))
                .withSelfRel().withType("GET");
    }
}
