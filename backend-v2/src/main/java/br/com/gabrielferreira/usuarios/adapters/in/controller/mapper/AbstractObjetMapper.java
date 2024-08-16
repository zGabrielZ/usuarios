package br.com.gabrielferreira.usuarios.adapters.in.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.ZonedDateTime;

import static br.com.gabrielferreira.usuarios.utils.DataUtils.*;

@Mapper(componentModel = "spring")
public interface AbstractObjetMapper {

    @Named("formatData")
    default ZonedDateTime formatDate(ZonedDateTime data){
        return toFusoPadraoSistema(data);
    }
}
