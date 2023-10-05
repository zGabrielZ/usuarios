package br.com.gabrielferreira.usuario.domain;

import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

import static br.com.gabrielferreira.usuario.entity.enumeration.DominioEnumeration.*;
import static br.com.gabrielferreira.usuario.utils.MascaraUtils.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TelefoneDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 4819830058841518744L;

    @EqualsAndHashCode.Include
    private Long id;

    private String numero;

    private String ddd;

    private String descricao;

    private DominioDomain tipoTelefone;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public String getTelefoneFormatado(){
        String dddNumero = this.ddd.concat(this.numero);
        if(this.tipoTelefone != null && isResidencial(tipoTelefone.getCodigo())){
            return toTelefoneResidencialFormatado(dddNumero);
        }

        return toTelefoneResidencialCelular(dddNumero);
    }
}
