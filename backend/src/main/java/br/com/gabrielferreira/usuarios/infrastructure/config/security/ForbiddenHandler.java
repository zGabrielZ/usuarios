package br.com.gabrielferreira.usuarios.infrastructure.config.security;

import br.com.gabrielferreira.usuarios.infrastructure.config.model.ErroPadrao;
import br.com.gabrielferreira.usuarios.infrastructure.mapper.ErroPadraoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.ZonedDateTime;

import static br.com.gabrielferreira.usuarios.common.utils.DataUtils.UTC;

@Component
@RequiredArgsConstructor
public class ForbiddenHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    private final ErroPadraoMapper erroPadraoMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(httpStatus.value());

        ErroPadrao erroPadrao = erroPadraoMapper.toErroPadrao(ZonedDateTime.now(UTC), httpStatus.value(), "Proibido", "Você não tem a permissão de realizar esta ação", request.getRequestURI(), null);

        String json = objectMapper.writeValueAsString(erroPadrao);
        response.getWriter().write(json);
    }
}
