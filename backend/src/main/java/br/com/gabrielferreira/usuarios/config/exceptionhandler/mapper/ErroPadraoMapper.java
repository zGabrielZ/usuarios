package br.com.gabrielferreira.usuarios.config.exceptionhandler.mapper;

import br.com.gabrielferreira.usuarios.application.exception.model.ErroPadrao;
import br.com.gabrielferreira.usuarios.application.exception.model.ErroPadraoFormulario;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class ErroPadraoMapper {

    public ErroPadrao toErroPadrao(ZonedDateTime dataAtual, Integer status, String titulo, String mensagem, String caminhoUrl, List<ErroPadraoFormulario> campos){
        return ErroPadrao.builder()
                .dataAtual(dataAtual)
                .status(status)
                .titulo(titulo)
                .status(status)
                .mensagem(mensagem)
                .caminhoUrl(caminhoUrl)
                .campos(campos)
                .build();
    }

    public ErroPadraoFormulario toErroPadraoFormulario(String campo, String mensagem){
        return ErroPadraoFormulario.builder()
                .campo(campo)
                .mensagem(mensagem)
                .build();
    }
}
