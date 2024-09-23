package br.com.gabrielferreira.usuarios.factory;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UsuarioFactory {

    private UsuarioFactory(){}

    public static UsuarioCreateDTO criarUsuarioCreateDto(String email, String cpf){
        return new UsuarioCreateDTO("Usuário #111", email, cpf, BigDecimal.ONE, LocalDate.of(1900, 10, 10),
                1, new TelefoneCreateDTO("32644218", "11", "Telefone", new TipoTelefoneCreateDTO(4L)), new GeneroCreateDTO(1L));
    }

    public static UsuarioUpdateDTO atualizarUsuarioUpdateDto(){
        return new UsuarioUpdateDTO("Usuário editado #111", BigDecimal.TEN, LocalDate.of(2000, 10, 10),
                2, new GeneroCreateDTO(1L));
    }
}
