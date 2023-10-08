package br.com.gabrielferreira.usuario.dto.response;

import java.time.ZonedDateTime;

public record AnotacaoResponseDTO(Long id, String titulo, String descricao, DominioResponseDTO tipoAnotacao, ZonedDateTime dataLembrete,
                                  ZonedDateTime dataEstudoInicio, ZonedDateTime dataEstudoFim, DominioResponseDTO situacaoTipoAnotacao, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
}
