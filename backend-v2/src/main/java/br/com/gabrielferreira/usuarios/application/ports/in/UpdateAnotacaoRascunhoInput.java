package br.com.gabrielferreira.usuarios.application.ports.in;

public interface UpdateAnotacaoRascunhoInput {

    void finalizarAnotacao(Long id, Long idUsuario);
}
