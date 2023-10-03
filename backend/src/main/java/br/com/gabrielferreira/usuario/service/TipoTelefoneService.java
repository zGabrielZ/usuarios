package br.com.gabrielferreira.usuario.service;

import br.com.gabrielferreira.usuario.domain.TipoTelefoneDomain;
import br.com.gabrielferreira.usuario.entity.TipoTelefone;
import br.com.gabrielferreira.usuario.exception.MsgException;
import br.com.gabrielferreira.usuario.exception.NaoEncontradoException;
import br.com.gabrielferreira.usuario.mapper.TipoTelefoneMapper;
import br.com.gabrielferreira.usuario.repository.TipoTelefoneRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoTelefoneService {

    private final TipoTelefoneRepository tipoTelefoneRepository;

    private final TipoTelefoneMapper tipoTelefoneMapper;

    public List<TipoTelefoneDomain> buscarTiposTelefones(){
        List<TipoTelefone> tipoTelefones = tipoTelefoneRepository.buscarTiposTelefones();
        return tipoTelefoneMapper.toTiposTelefonesDomains(tipoTelefones);
    }

    public TipoTelefoneDomain buscarTipoTelefonePorId(Long id){
        TipoTelefone tipoTelefone = tipoTelefoneRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Tipo de telefone não encontrado"));
        return tipoTelefoneMapper.toTipoTelefoneDomain(tipoTelefone);
    }

    public TipoTelefoneDomain buscarTipoTelefonePorCodigo(String codigo){
        if(StringUtils.isBlank(codigo)){
            throw new MsgException("É necessário informar o código");
        }

        TipoTelefone tipoTelefone = tipoTelefoneRepository.buscarPorCodigo(codigo)
                .orElseThrow(() -> new NaoEncontradoException("Tipo de telefone não encontrado"));
        return tipoTelefoneMapper.toTipoTelefoneDomain(tipoTelefone);
    }
}
