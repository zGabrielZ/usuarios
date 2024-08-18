package br.com.gabrielferreira.usuarios.config.exceptionhandler;

import br.com.gabrielferreira.usuarios.application.exception.MsgException;
import br.com.gabrielferreira.usuarios.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuarios.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.usuarios.application.exception.model.ErroPadrao;
import br.com.gabrielferreira.usuarios.application.exception.model.ErroPadraoFormulario;
import br.com.gabrielferreira.usuarios.config.exceptionhandler.mapper.ErroPadraoMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.ZonedDateTime;
import java.util.List;

import static br.com.gabrielferreira.usuarios.utils.DataUtils.*;

@ControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class ApiExceptionHandler {

    private final ErroPadraoMapper erroPadraoMapper;

    private final MessageSource messageSource;

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroPadrao> regraDeNegocioException(RegraDeNegocioException e, HttpServletRequest request){
        log.warn("msgException message : {}, requestUrl : {}", e.getMessage(), request.getRequestURI());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErroPadrao erroPadrao = erroPadraoMapper.toErroPadrao(toFusoPadraoSistema(ZonedDateTime.now()), httpStatus.value(), "Regra de negócio", e.getMessage(), request.getRequestURI(), null);
        return ResponseEntity.status(httpStatus).body(erroPadrao);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> naoEncontradoException(NaoEncontradoException e, HttpServletRequest request){
        log.warn("naoEncontradoException message : {}, requestUrl : {}", e.getMessage(), request.getRequestURI());
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErroPadrao erroPadraoModel = erroPadraoMapper.toErroPadrao(toFusoPadraoSistema(ZonedDateTime.now()), httpStatus.value(), "Não encontrado", e.getMessage(), request.getRequestURI(), null);
        return ResponseEntity.status(httpStatus).body(erroPadraoModel);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> validacaoException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.warn("validacaoException message : {}, requestUrl : {}", e.getMessage(), request.getRequestURI());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        List<ErroPadraoFormulario> campos = e.getBindingResult().getFieldErrors().stream()
                .map(campo -> erroPadraoMapper.toErroPadraoFormulario(campo.getField(), messageSource.getMessage(campo, LocaleContextHolder.getLocale())))
                .toList();
        ErroPadrao erroPadrao = erroPadraoMapper.toErroPadrao(toFusoPadraoSistema(ZonedDateTime.now()), httpStatus.value(), "Erro validação de campos", "Ocorreu um erro de validação nos campos", request.getRequestURI(), campos);

        return ResponseEntity.status(httpStatus).body(erroPadrao);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadrao> erroException(Exception e, HttpServletRequest request){
        log.error("erroException message : {}, requestUrl : {}", e.getMessage(), request.getRequestURI());
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErroPadrao erroPadrao = erroPadraoMapper.toErroPadrao(toFusoPadraoSistema(ZonedDateTime.now()), httpStatus.value(), "Erro inesperado", "Ocorreu um erro inesperado no sistema, tente mais tarde", request.getRequestURI(), null);
        return ResponseEntity.status(httpStatus).body(erroPadrao);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFound(Exception ex) {
        throw new MsgException(ex.getMessage());
    }
}
