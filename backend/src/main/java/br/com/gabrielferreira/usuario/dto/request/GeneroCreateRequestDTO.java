package br.com.gabrielferreira.usuario.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneroCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -918570165334510853L;

    private Long id;
}
