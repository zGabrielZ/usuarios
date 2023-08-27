package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.dto.GeneroDTO;
import br.com.gabrielferreira.usuario.entities.Genero;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import static br.com.gabrielferreira.usuario.dto.factory.GeneroDTOFactory.*;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;

    public List<GeneroDTO> buscarGeneros(){
        return toGenerosDtos(generoRepository.buscarGeneros());
    }

    public GeneroDTO buscarGeneroPorId(Long id){
        return toGeneroDto(buscarGenero(id));
    }

    public Genero buscarGenero(Long id){
        return generoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Gênero não encontrado"));
    }
}
