package br.com.gabrielferreira.usuario.mapper.domain;

import br.com.gabrielferreira.usuario.domain.GeneroDomain;
import br.com.gabrielferreira.usuario.entity.Genero;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroDomainMapper {

    GeneroDomain toGeneroDomain(Genero genero);

    List<GeneroDomain> toGenerosDomains(List<Genero> generos);
}
