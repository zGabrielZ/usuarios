package br.com.gabrielferreira.usuarios.adapters.out.adapters;

import br.com.gabrielferreira.usuarios.adapters.out.persistence.repository.UsuarioRepository;
import br.com.gabrielferreira.usuarios.application.ports.out.DeleteUsuarioOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteUsuarioAdapter implements DeleteUsuarioOutput {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
