package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.dto.TipoTelefoneDTO;
import br.com.gabrielferreira.usuario.entities.TipoTelefone;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.repository.TipoTelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.gabrielferreira.usuario.dto.factory.TipoTelefoneDTOFactory.*;

@Service
@RequiredArgsConstructor
public class TipoTelefoneService {

    private final TipoTelefoneRepository tipoTelefoneRepository;

    public List<TipoTelefoneDTO> buscarTiposTelefones(){
        return toTipoTelefonesDtos(tipoTelefoneRepository.buscarTiposTelefones());
    }

    public TipoTelefoneDTO buscarTipoTelefonePorId(Long id){
        return toTipoTelefoneDto(buscarTipoTelefone(id));
    }

    public TipoTelefone buscarTipoTelefone(Long id){
        return tipoTelefoneRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Tipo de telefone não encontrado"));
    }
}
