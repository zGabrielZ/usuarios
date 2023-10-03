package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.GeneroDomain;
import br.com.gabrielferreira.usuario.entity.Genero;
import br.com.gabrielferreira.usuario.exception.MsgException;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.mapper.GeneroMapper;
import br.com.gabrielferreira.usuario.repository.GeneroRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;

    private final GeneroMapper generoMapper;

    public List<GeneroDomain> buscarGeneros(){
        List<Genero> generos = generoRepository.buscarGeneros();
        return generoMapper.toGenerosDomains(generos);
    }

    public GeneroDomain buscarGeneroPorId(Long id){
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Gênero não encontrado"));
        return generoMapper.toGeneroDomain(genero);
    }

    public GeneroDomain buscarGeneroPorCodigo(String codigo){
        if(StringUtils.isBlank(codigo)){
            throw new MsgException("É necessário informar o código");
        }

        Genero genero = generoRepository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new NaoEncontradoException("Gênero não encontrado"));
        return generoMapper.toGeneroDomain(genero);
    }
}
